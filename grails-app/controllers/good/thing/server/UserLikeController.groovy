package good.thing.server

import org.springframework.dao.DataIntegrityViolationException

class UserLikeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [userLikeInstanceList: UserLike.list(params), userLikeInstanceTotal: UserLike.count()]
    }

    def create() {
        [userLikeInstance: new UserLike(params)]
    }

    def save() {
        def userLikeInstance = new UserLike(params)
        if (!userLikeInstance.save(flush: true)) {
            render(view: "create", model: [userLikeInstance: userLikeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'userLike.label', default: 'UserLike'), userLikeInstance.id])
        redirect(action: "show", id: userLikeInstance.id)
    }

    def show(Long id) {
        def userLikeInstance = UserLike.get(id)
        if (!userLikeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userLike.label', default: 'UserLike'), id])
            redirect(action: "list")
            return
        }

        [userLikeInstance: userLikeInstance]
    }

    def edit(Long id) {
        def userLikeInstance = UserLike.get(id)
        if (!userLikeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userLike.label', default: 'UserLike'), id])
            redirect(action: "list")
            return
        }

        [userLikeInstance: userLikeInstance]
    }

    def update(Long id, Long version) {
        def userLikeInstance = UserLike.get(id)
        if (!userLikeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userLike.label', default: 'UserLike'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (userLikeInstance.version > version) {
                userLikeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'userLike.label', default: 'UserLike')] as Object[],
                          "Another user has updated this UserLike while you were editing")
                render(view: "edit", model: [userLikeInstance: userLikeInstance])
                return
            }
        }

        userLikeInstance.properties = params

        if (!userLikeInstance.save(flush: true)) {
            render(view: "edit", model: [userLikeInstance: userLikeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'userLike.label', default: 'UserLike'), userLikeInstance.id])
        redirect(action: "show", id: userLikeInstance.id)
    }

    def delete(Long id) {
        def userLikeInstance = UserLike.get(id)
        if (!userLikeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'userLike.label', default: 'UserLike'), id])
            redirect(action: "list")
            return
        }

        try {
            userLikeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'userLike.label', default: 'UserLike'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'userLike.label', default: 'UserLike'), id])
            redirect(action: "show", id: id)
        }
    }
}
