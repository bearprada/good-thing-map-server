package good.thing.server

import org.springframework.web.multipart.MultipartFile
import org.codehaus.groovy.grails.web.context.*

class ImageUploadService {

    def grailsApplication

    private String getAssetsUrl(String fileName){
        return "${grailsApplication.config.grails.serverURL}/assets/${fileName}"
    }

    def uploadFile(MultipartFile file, String name) {
        String storagePath = ""
        def servletContext = ServletContextHolder.getServletContext()
        storagePath = servletContext.getRealPath('assets')
        
        // Create storage path directory if it does not exist
        def storagePathDirectory = new File(storagePath)
        if (!storagePathDirectory.exists()) {
          print "CREATING DIRECTORY ${storagePath}: "
          if (storagePathDirectory.mkdirs()) {
            println "SUCCESS"
          } else {
            println "FAILED"
          }
        }

        // Store file
        if (!file.isEmpty()) {
          file.transferTo(new File("${storagePath}/${name}"))
          println "Saved file: ${storagePath}/${name}"
          return "${getAssetsUrl(name)}"
        } else {
          println "File ${file.inspect()} was empty!"
          return null
        }
    }

}
