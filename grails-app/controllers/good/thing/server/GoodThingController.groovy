package good.thing.server

import org.springframework.dao.DataIntegrityViolationException

class GoodThingController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def imageUploadService

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [goodThingInstanceList: GoodThing.list(params), goodThingInstanceTotal: GoodThing.count()]
    }

    def category(Integer type) {
        def t = GoodThingType.findByType(type)
        if (t != null) {
            def results = GoodThing.findAllByGoodThingType(t, [max: 10, sort: "title", order: "desc"])
            render(view: "list", model: [goodThingInstanceList: results, goodThingInstanceTotal: GoodThing.count()])
        } else {
            redirect(action: "list", params: params)
        }
    }

    def query(String q) {
        def results = GoodThing.findAllByTitleLikeOrStoryLikeOrAddressLike("%"+q+"%", "%"+q+"%", "%"+q+"%", [max: 10, sort: "title", order: "desc"])
        if (results == null) {
            flash.message = message(code: 'default.not.found.query.message', args: [q])
            render(view: "list", model: [goodThingInstanceList: GoodThing.list(params), goodThingInstanceTotal: GoodThing.count()])
        } else
            render(view: "list", model: [goodThingInstanceList: results, goodThingInstanceTotal: results.size()])
    }

    def create() {
        [goodThingInstance: new GoodThing(params)]
    }

    def save() {
        def goodThingInstance = new GoodThing(params)

        saveImageFile(goodThingInstance)

        if (!goodThingInstance.save(flush: true)) {
            render(view: "create", model: [goodThingInstance: goodThingInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'goodThing.label', default: 'GoodThing'), goodThingInstance.id])
        redirect(action: "show", id: goodThingInstance.id)
    }

    def show(Long id) {
        def goodThingInstance = GoodThing.get(id)
        if (!goodThingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'goodThing.label', default: 'GoodThing'), id])
            redirect(action: "list")
            return
        }
        [goodThingInstance: goodThingInstance]
    }

    def edit(Long id) {
        def goodThingInstance = GoodThing.get(id)
        if (!goodThingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'goodThing.label', default: 'GoodThing'), id])
            redirect(action: "list")
            return
        }

        [goodThingInstance: goodThingInstance]
    }

    def update(Long id, Long version) {
        def goodThingInstance = GoodThing.get(id)
        if (!goodThingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'goodThing.label', default: 'GoodThing'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (goodThingInstance.version > version) {
                goodThingInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'goodThing.label', default: 'GoodThing')] as Object[],
                          "Another user has updated this GoodThing while you were editing")
                render(view: "edit", model: [goodThingInstance: goodThingInstance])
                return
            }
        }

        saveImageFile(goodThingInstance)

        goodThingInstance.properties = params

        if (!goodThingInstance.save(flush: true)) {
            render(view: "edit", model: [goodThingInstance: goodThingInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'goodThing.label', default: 'GoodThing'), goodThingInstance.id])
        redirect(action: "show", id: goodThingInstance.id)
    }

    private saveImageFile(def goodThingInstance) {
        // try to update images
        def listImageFile = request.getFile('list_image_url')
        if (!listImageFile.isEmpty()) {
            goodThingInstance.listImageUrl = imageUploadService.uploadFile(listImageFile,
                "good_thing_list_${goodThingInstance.id}_${UUID.randomUUID() as String}.jpg")
        }
        def imageFile = request.getFile('image_url')
        if (!imageFile.isEmpty()) {
            goodThingInstance.imageUrl = imageUploadService.uploadFile(imageFile,
                "good_thing_${goodThingInstance.id}_${UUID.randomUUID() as String}.jpg")
        }
        def detailImageFile = request.getFile('detail_image_url')
        if (!detailImageFile.isEmpty()) {
            goodThingInstance.detailImageUrl = imageUploadService.uploadFile(detailImageFile,
                "good_thing_cover_${goodThingInstance.id}_${UUID.randomUUID() as String}.jpg")
        }
    }

    def delete(Long id) {
        def goodThingInstance = GoodThing.get(id)
        if (!goodThingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'goodThing.label', default: 'GoodThing'), id])
            redirect(action: "list")
            return
        }

        try {
            goodThingInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'goodThing.label', default: 'GoodThing'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'goodThing.label', default: 'GoodThing'), id])
            redirect(action: "show", id: id)
        }
    }

    def update_image(Long id) {
        def goodThingInstance = GoodThing.get(id)
        if (!goodThingInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'goodThing.label', default: 'GoodThing'), id])
            redirect(action: "list")
            return
        }

        def f = request.getFile('file_image')
        if (f.empty) {
            flash.message = 'file cannot be empty'
        } else {
            print goodThingInstance.images;
            def uploadedImageFile = imageUploadService.uploadFile(f, "good_thing_${goodThingInstance.id}_${UUID.randomUUID() as String}.jpg")
            print uploadedImageFile
            goodThingInstance.addToImages(uploadedImageFile).save()
            print goodThingInstance.images;
            flash.message = message(code: 'default.updated.message', args: [message(code: 'goodThing.label', default: 'GoodThing'), goodThingInstance.id])
        }
        redirect(action: "show", id: goodThingInstance.id)
    }
}
