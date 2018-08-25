package good.thing.server

import org.springframework.dao.DataIntegrityViolationException

class UserMessageController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [userMessageInstanceList: UserMessage.list(params), userMessageInstanceTotal: UserMessage.count()]
    }

    def create() {
        [userMessageInstance: new UserMessage(params)]
    }

    def save() {
        def userMessageInstance = new UserMessage(params)
        if (!userMessageInstance.save(flush: true)) {
            render(view: "create", model: [userMessageInstance: userMessageInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'userMessage.label', default: 'UserMessage'), userMessageInstance.id])
        redirect(action: "show", id: userMessageInstance.id)
    }

    def show(Long id) {
        def userMessageInstance = UserMessage.get(id)
        if (!userMessageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userMessage.label', default: 'UserMessage'), id])
            redirect(action: "list")
            return
        }

        [userMessageInstance: userMessageInstance]
    }

    def edit(Long id) {
        def userMessageInstance = UserMessage.get(id)
        if (!userMessageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userMessage.label', default: 'UserMessage'), id])
            redirect(action: "list")
            return
        }

        [userMessageInstance: userMessageInstance]
    }

    def update(Long id, Long version) {
        def userMessageInstance = UserMessage.get(id)
        if (!userMessageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userMessage.label', default: 'UserMessage'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (userMessageInstance.version > version) {
                userMessageInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'userMessage.label', default: 'UserMessage')] as Object[],
                          "Another user has updated this UserMessage while you were editing")
                render(view: "edit", model: [userMessageInstance: userMessageInstance])
                return
            }
        }

        userMessageInstance.properties = params

        if (!userMessageInstance.save(flush: true)) {
            render(view: "edit", model: [userMessageInstance: userMessageInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'userMessage.label', default: 'UserMessage'), userMessageInstance.id])
        redirect(action: "show", id: userMessageInstance.id)
    }

    def delete(Long id) {
        def userMessageInstance = UserMessage.get(id)
        if (!userMessageInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userMessage.label', default: 'UserMessage'), id])
            redirect(action: "list")
            return
        }

        try {
            userMessageInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'userMessage.label', default: 'UserMessage'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'userMessage.label', default: 'UserMessage'), id])
            redirect(action: "show", id: id)
        }
    }
}
