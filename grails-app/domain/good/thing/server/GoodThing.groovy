package good.thing.server

import goodthings.Consts

class GoodThing {
    static hasMany = [likes: UserLike,
                      images: String,
                      checkins: UserCheckin, 
                      userMessages: UserMessage]

    String title
    String imageUrl
    String listImageUrl
    String detailImageUrl
    String address
    String businessTime
    String story
    String memo
    String content

    GoodThingType goodThingType

    BigDecimal longtitude 
    BigDecimal latitude

    Date dateCreated
    Date lastUpdated 

    Boolean canPost = true
    Boolean isDeleted = false

    List likes
    List images
    List checkins
    List userMessages

    static constraints = {
        longtitude scale:16
        latitude scale:16
    }

    static mapping = {
        story type: 'text'
        memo type: 'text'
        businessTime nullable: true
        autoTimestamp true
    }

    public Boolean getIsFuzzyLocation() {
        return (latitude==-1||longtitude==-1)
    }

    public final static int DEFAULT_MESSAGE_NUM = 5

    public def getMessages() {
        return getMessages(0, DEFAULT_MESSAGE_NUM)
    }

    public def getMessages(int offset, int max) {
        def result = []
        if( userMessages.size()==0 || max<=0 || offset<0 ) {
            return []
        } else if( userMessages.size() < (offset+max) ) {
            for(int i=offset;i<userMessages.size()-1;i++)
                result.add(userMessages[i].toDict())
            return result
        } else {
            for(int i=offset;i<max+offset;i++)
                result.add(userMessages[i].toDict())
            return result
        }
    }

    public boolean containsMessage(String userId){
        int len = userMessages.size()
        for(int i=0;i<len;i++) {
            if( userMessages[i].userId.equals(userId) ) {
                return true;
            }
        }
        return false
    }

    public boolean containsCheckin(String userId){
        int len = checkins.size()
        for(int i=0;i<len;i++) {
            if(checkins[i].userId.equals(userId)) {
                return true;
            }
        }
        return false
    }

    public boolean contains(String userId){
        if(likes!=null) {
            int len = likes.size()
            for(int i=0;i<len;i++) {
                if(likes[i].userId.equals(userId)) {
                    return true;
                }
            }
        }
        return false
    }

    public String toString() {
        return "(${id}) ${title} : ${goodThingType}"
    }

    private def gps2m(float lat, float lng) {
        float pk = (float) (180/3.14169);
        float a1 = lat / pk;
        float a2 = lng / pk;
        float b1 = latitude / pk;
        float b2 = longtitude / pk;

        float t1 = Math.cos(a1)*Math.cos(a2)*Math.cos(b1)*Math.cos(b2);
        float t2 = Math.cos(a1)*Math.sin(a2)*Math.cos(b1)*Math.sin(b2);
        float t3 = Math.sin(a1)*Math.sin(b1);
        double tt = Math.acos(t1 + t2 + t3);
        return 6366000*tt;
    }

    public def toDict(){
        def messages = []
        UserMessage.findAllByGoodThing(this, 
            [max: DEFAULT_MESSAGE_NUM , sort: "dateCreated", order: "desc", offset: 0]).each{
            messages.add(it.toDict())
        }
        return ['gid' : id,
                'title' : title,
                'imageUrl' : imageUrl,
                'list_image_url' : listImageUrl,
                'detail_image_url' : detailImageUrl,
                'business_time': businessTime,
                'address' : address,
                'content' : content,
                'memo' : memo,
                'story' : story,
                'images' : images,
                'longtitude' : longtitude,
                'latitude' : latitude,
                'can_post' : canPost,
                'is_big_issue' : (goodThingType.type == Consts.TYPE_THE_BIG_ISSUE),
                'messages' : messages,
                'time' : lastUpdated.getTime()]
    }
}
