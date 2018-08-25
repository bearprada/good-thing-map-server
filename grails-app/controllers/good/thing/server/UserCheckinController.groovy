package good.thing.server

import org.springframework.dao.DataIntegrityViolationException

class UserCheckinController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [userCheckinInstanceList: UserCheckin.list(params), userCheckinInstanceTotal: UserCheckin.count()]
    }

    def create() {
        [userCheckinInstance: new UserCheckin(params)]
    }

    def save() {
        def userCheckinInstance = new UserCheckin(params)
        if (!userCheckinInstance.save(flush: true)) {
            render(view: "create", model: [userCheckinInstance: userCheckinInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'userCheckin.label', default: 'UserCheckin'), userCheckinInstance.id])
        redirect(action: "show", id: userCheckinInstance.id)
    }

    def show(Long id) {
        def userCheckinInstance = UserCheckin.get(id)
        if (!userCheckinInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userCheckin.label', default: 'UserCheckin'), id])
            redirect(action: "list")
            return
        }

        [userCheckinInstance: userCheckinInstance]
    }

    def edit(Long id) {
        def userCheckinInstance = UserCheckin.get(id)
        if (!userCheckinInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userCheckin.label', default: 'UserCheckin'), id])
            redirect(action: "list")
            return
        }

        [userCheckinInstance: userCheckinInstance]
    }

    def update(Long id, Long version) {
        def userCheckinInstance = UserCheckin.get(id)
        if (!userCheckinInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userCheckin.label', default: 'UserCheckin'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (userCheckinInstance.version > version) {
                userCheckinInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'userCheckin.label', default: 'UserCheckin')] as Object[],
                          "Another user has updated this UserCheckin while you were editing")
                render(view: "edit", model: [userCheckinInstance: userCheckinInstance])
                return
            }
        }

        userCheckinInstance.properties = params

        if (!userCheckinInstance.save(flush: true)) {
            render(view: "edit", model: [userCheckinInstance: userCheckinInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'userCheckin.label', default: 'UserCheckin'), userCheckinInstance.id])
        redirect(action: "show", id: userCheckinInstance.id)
    }

    def delete(Long id) {
        def userCheckinInstance = UserCheckin.get(id)
        if (!userCheckinInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userCheckin.label', default: 'UserCheckin'), id])
            redirect(action: "list")
            return
        }

        try {
            userCheckinInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'userCheckin.label', default: 'UserCheckin'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'userCheckin.label', default: 'UserCheckin'), id])
            redirect(action: "show", id: id)
        }
    }
}
