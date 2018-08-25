import org.springframework.dao.DataIntegrityViolationException
import org.apache.shiro.crypto.hash.*

class ShiroUserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [shiroUserInstanceList: ShiroUser.list(params), shiroUserInstanceTotal: ShiroUser.count()]
    }

    def create() {
        [shiroUserInstance: new ShiroUser(params)]
    }

    def save() {
        params.passwordHash = new Sha256Hash(params.passwordHash).toHex()
        def shiroUserInstance = new ShiroUser(params)
        if (!shiroUserInstance.save(flush: true)) {
            render(view: "create", model: [shiroUserInstance: shiroUserInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'shiroUser.label', default: 'ShiroUser'), shiroUserInstance.id])
        redirect(action: "show", id: shiroUserInstance.id)
    }

    def show(Long id) {
        def shiroUserInstance = ShiroUser.get(id)
        if (!shiroUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'shiroUser.label', default: 'ShiroUser'), id])
            redirect(action: "list")
            return
        }

        [shiroUserInstance: shiroUserInstance]
    }

    def edit(Long id) {
        def shiroUserInstance = ShiroUser.get(id)
        if (!shiroUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'shiroUser.label', default: 'ShiroUser'), id])
            redirect(action: "list")
            return
        }

        [shiroUserInstance: shiroUserInstance]
    }

    def update(Long id, Long version) {
        def shiroUserInstance = ShiroUser.get(id)
        if (!shiroUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'shiroUser.label', default: 'ShiroUser'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (shiroUserInstance.version > version) {
                shiroUserInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'shiroUser.label', default: 'ShiroUser')] as Object[],
                          "Another user has updated this ShiroUser while you were editing")
                render(view: "edit", model: [shiroUserInstance: shiroUserInstance])
                return
            }
        }
        params.passwordHash = new Sha256Hash(params.passwordHash).toHex()
        shiroUserInstance.properties = params

        if (!shiroUserInstance.save(flush: true)) {
            render(view: "edit", model: [shiroUserInstance: shiroUserInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'shiroUser.label', default: 'ShiroUser'), shiroUserInstance.id])
        redirect(action: "show", id: shiroUserInstance.id)
    }

    def delete(Long id) {
        def shiroUserInstance = ShiroUser.get(id)
        if (!shiroUserInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'shiroUser.label', default: 'ShiroUser'), id])
            redirect(action: "list")
            return
        }

        try {
            shiroUserInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'shiroUser.label', default: 'ShiroUser'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'shiroUser.label', default: 'ShiroUser'), id])
            redirect(action: "show", id: id)
        }
    }
}
