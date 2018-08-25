package good.thing.server

import org.springframework.dao.DataIntegrityViolationException

class GoodThingTypeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [goodThingTypeInstanceList: GoodThingType.list(params), goodThingTypeInstanceTotal: GoodThingType.count()]
    }

    def create() {
        [goodThingTypeInstance: new GoodThingType(params)]
    }

    def save() {
        def goodThingTypeInstance = new GoodThingType(params)
        if (!goodThingTypeInstance.save(flush: true)) {
            render(view: "create", model: [goodThingTypeInstance: goodThingTypeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'goodThingType.label', default: 'GoodThingType'), goodThingTypeInstance.id])
        redirect(action: "show", id: goodThingTypeInstance.id)
    }

    def show(Long id) {
        def goodThingTypeInstance = GoodThingType.get(id)
        if (!goodThingTypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'goodThingType.label', default: 'GoodThingType'), id])
            redirect(action: "list")
            return
        }

        [goodThingTypeInstance: goodThingTypeInstance]
    }

    def edit(Long id) {
        def goodThingTypeInstance = GoodThingType.get(id)
        if (!goodThingTypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'goodThingType.label', default: 'GoodThingType'), id])
            redirect(action: "list")
            return
        }

        [goodThingTypeInstance: goodThingTypeInstance]
    }

    def update(Long id, Long version) {
        def goodThingTypeInstance = GoodThingType.get(id)
        if (!goodThingTypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'goodThingType.label', default: 'GoodThingType'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (goodThingTypeInstance.version > version) {
                goodThingTypeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'goodThingType.label', default: 'GoodThingType')] as Object[],
                          "Another user has updated this GoodThingType while you were editing")
                render(view: "edit", model: [goodThingTypeInstance: goodThingTypeInstance])
                return
            }
        }

        goodThingTypeInstance.properties = params

        if (!goodThingTypeInstance.save(flush: true)) {
            render(view: "edit", model: [goodThingTypeInstance: goodThingTypeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'goodThingType.label', default: 'GoodThingType'), goodThingTypeInstance.id])
        redirect(action: "show", id: goodThingTypeInstance.id)
    }

    def delete(Long id) {
        def goodThingTypeInstance = GoodThingType.get(id)
        if (!goodThingTypeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'goodThingType.label', default: 'GoodThingType'), id])
            redirect(action: "list")
            return
        }

        try {
            goodThingTypeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'goodThingType.label', default: 'GoodThingType'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'goodThingType.label', default: 'GoodThingType'), id])
            redirect(action: "show", id: id)
        }
    }
}
