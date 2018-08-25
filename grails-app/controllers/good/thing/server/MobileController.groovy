package good.thing.server

import goodthings.Consts

class MobileController {

    def index() { }

    def post() {
        def goodThing = GoodThing.get(params.rid)
        def message = params.message
        if( goodThing != null && params.uid != null && message != null ) {
            goodThing.addToUserMessages(new UserMessage(userId:params.uid, message:message)).save()
            render(contentType:"application/json"){ result=goodThing.userMessages.size() }
        }else {
            render(contentType:"application/json"){ 
                message="something wrong :(" 
            }
        }
    }

    private int getLimit() {
        def config = GoodThingConfig.list()[0]
        return (config == null) ? Consts.DEFAULT_SIZE_OF_PAGE : config.responseNumber
    }

    def listPosts() {
        def goodThing = GoodThing.get(params.rid)
        if (goodThing != null) {
            int offset = -1
            int max = -1
            try {
                offset = params.offset.toInteger()
                max = params.max.toInteger()
            } catch (Exception ex) {}
            def messages = (offset >= 0 && max > 0) ?
                            UserMessage.findAllByGoodThing(goodThing, [max: max, sort: "dateCreated", order: "desc", offset: offset]) :
                            UserMessage.findAllByGoodThing(goodThing, [max: getLimit() , sort: "dateCreated", order: "desc", offset: 0])
            def result = []
            messages.each { result.add(it.toDict()) }
            render(contentType:"application/json"){ result=result }
        }else {
            render(contentType:"application/json"){ 
                message="illegal arugmenets" 
            }
        }   
    }

    def addCheckin() {
        def goodThing = GoodThing.get(params.rid)
        def checkInId = params.cid
        if (goodThing != null && params.uid != null && checkInId != null ) {
            goodThing.addToCheckins(new UserCheckin(userId:params.uid, facebookPostId:checkInId)).save()
            render(contentType:"application/json"){ result=goodThing.checkins.size() }
        } else {
            render(contentType:"application/json") { 
                result=goodThing.checkins?.size()
                message="already like it" 
            }    
        }
    }

    def getCheckinNum() {
        def goodThing = GoodThing.get(params.rid)
        if (goodThing != null) {
            render(contentType:"application/json") { result=goodThing.checkins.size() }
        } else {
            render(contentType:"application/json") { message="invalid arugmenets" }    
        }
    }

    def addLike() {
        def goodThing = GoodThing.get(params.rid)
        if (goodThing?.contains(params.uid) == false && params.uid != null ) {
            goodThing.addToLikes(new UserLike(userId:params.uid)).save()
            render(contentType:"application/json"){ result=goodThing.likes.size() }
        } else {
            render(contentType:"application/json"){ 
                result=goodThing.likes.size() 
                message="already like it" 
            }    
        }
    }

    def getLikeNum() {
        def goodThing = GoodThing.get(params.rid)
        if (goodThing != null) {
            render(contentType:"application/json"){ result = goodThing.likes.size() }
        } else {
            render(contentType:"application/json"){ message = "invalid arugmenets" }
        }
    }

    def findTopStory() {
        def config = GoodThingConfig.list()[0]
        def thing = (config != null) ? config.coverStory : queryTopStory()
        render(contentType:"application/json"){ result = thing.toDict() }
    }

    private GoodThing queryTopStory() {
        return GoodThing.findByIsDeleted(false) // FIXME it's not correct implementation
    }

    def findGoodThings() {
        def t = params.type
        def lat, lon
        try{
            lat = params.lat.toDouble()
            lon = params.lon.toDouble()
        } catch(Exception ex) {}
        boolean hasError = true
        def r = []
    	if (t != null) {
    		def goodThingType = GoodThingType.findByType(t)
            if(goodThingType!=null){
                if (lat != null && lon != null) {
                    r = findNearestThings(goodThingType, lat, lon, getLimit())
                } else {
                    GoodThing.findAll("from GoodThing as g where g.isDeleted=false and g.goodThingType=:type", [type: goodThingType], [max: getLimit()]).each{ r.add(it.toDict()) }
                }
                render(contentType:"application/json"){ results=r } 
                hasError = false
            }
    	}else{
            /** if no type id. so we will find out the all of thing */
            if (lat != null && lon != null) {
                r = findNearestThings(lat, lon, getLimit())
            } else {
                GoodThing.findAllByIsDeleted(false, [max : getLimit(), sort : "dateCreated", order:"asc"]).each{ r.add(it.toDict()) }
            }
            render(contentType:"application/json"){ results=r } 
            hasError = false
    	}

        if(hasError){
            render(contentType:"application/json"){
                message="invalid arugmenets"
            }
        }
    }

    private def findNearestThings(def goodThingType, double lat, double lon, int max) {
        def allItems = GoodThing.findAll("from GoodThing as g where g.isDeleted=false and g.goodThingType=:type", [type: goodThingType])
        return sortByDistance(allItems, lat, lon, max)
    }

    private def sortByDistance(def items, double lat, double lon, int max) {
        def dists = []
        items.each {
            dists.add([d : it.gps2m((float)lat, (float)lon), obj : it])
        }
        dists.sort{ a,b -> a.d==b.d ? 0: a.d < b.d ? -1: 1 }
        def result = []
        if (max > 0) {
            for (int i = 0 ; i < max && i < dists.size() ; i++) {
                result.add(dists[i].obj.toDict()) 
            }
        } else {
            dists.each { result.add(it.obj.toDict()) }
        }
        return result
    }

    private def findNearestThings(double lat, double lon, int max) {
        return sortByDistance(GoodThing.findAllByIsDeleted(false), lat, lon, max)
    }
}
