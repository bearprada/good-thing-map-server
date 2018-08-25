package good.thing.server
import java.util.UUID
import goodthings.Consts

class AdminController {

    def imageUploadService
    def grailsApplication

    def index() { }

    def clean() {
        GoodThing.list().each { it.delete(flush:true) }
    }

    def imageUpload() {
        def goodThing = GoodThing.get(params.id)
        if(goodThing!=null) {
            // TOOD be care the delete operation
            goodThing.addToImages(imageUploadService.uploadFile(request.getFile('file_image'),"good_thing_${goodThing.id}_${UUID.randomUUID() as String}.jpg")).save()
        }
    }

   private String getAssetsUrl(String fileName){
        return "${grailsApplication.config.grails.serverURL}/assets/${fileName}"
    }

    def upload() {
        def webRootDir = servletContext.getRealPath("/")
        def userDir = new File(webRootDir, "/${session.user}")
        userDir.mkdirs()
        def uploadedFile = request.getFile('file')
        def translatedFile = new File( userDir, uploadedFile.originalFilename)
        translatedFile.deleteOnExit()
        uploadedFile.transferTo( translatedFile )
        int count = 0
        boolean isFirstLine = true
        translatedFile.eachCsvLine { t ->
            if (isFirstLine) {
                isFirstLine = false
            } else if (t.size() >= 10) {
                def geo = t[8]?.split(",")
                float lon = -1
                float lat = -1
                if (geo?.size() == 2) { 
                    try{
                        lat = geo[0].toFloat()
                        lon = geo[1].toFloat()
                    }catch(Exception ex){ println ex}
                }
                def photoDir = t[9]
                def instance = new GoodThing(
                    title: nullChecker(t[1]),
                    content: nullChecker(t[2]),
                    imageUrl : getAssetsUrl(t[9]+"A-cover.jpg"),
                    listImageUrl : getAssetsUrl(t[9]+"B.jpg"),
                    detailImageUrl : getAssetsUrl(t[9]+"C-cover.jpg"),
                    address : nullChecker(t[5]),
                    businessTime : nullChecker(t[4]),
                    story : nullChecker(t[6]),
                    memo : nullChecker(t[7]),
                    canPost : (t[0] != Consts.TYPE_THE_BIG_ISSUE),
                    goodThingType : GoodThingType.findByType(t[0]),
                    longtitude : lon,
                    latitude : lat)
                if (!instance.save(flush: true)) {
                    println instance.errors
                } else {
                    // TODO upload the images
                    count++
                }
            }
       }
       print "[[[[[[[ proc ${count} row ]]]]]]]]]"
    }

    private def nullChecker(def t) {
        return (t==null) ? "" : t
    }
}
