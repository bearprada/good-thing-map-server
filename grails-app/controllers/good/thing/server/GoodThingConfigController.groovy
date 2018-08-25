package good.thing.server



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class GoodThingConfigController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond GoodThingConfig.list(params), model:[goodThingConfigInstanceCount: GoodThingConfig.count()]
    }

    def show(GoodThingConfig goodThingConfigInstance) {
        respond goodThingConfigInstance
    }

    def create() {
        respond new GoodThingConfig(params)
    }

    @Transactional
    def save(GoodThingConfig goodThingConfigInstance) {
        if (goodThingConfigInstance == null) {
            notFound()
            return
        }

        if (goodThingConfigInstance.hasErrors()) {
            respond goodThingConfigInstance.errors, view:'create'
            return
        }

        goodThingConfigInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.created.message', args: [message(code: 'goodThingConfigInstance.label', default: 'GoodThingConfig'), goodThingConfigInstance.id])
                redirect goodThingConfigInstance
            }
            '*' { respond goodThingConfigInstance, [status: CREATED] }
        }
    }

    def edit(GoodThingConfig goodThingConfigInstance) {
        respond goodThingConfigInstance
    }

    @Transactional
    def update(GoodThingConfig goodThingConfigInstance) {
        if (goodThingConfigInstance == null) {
            notFound()
            return
        }

        if (goodThingConfigInstance.hasErrors()) {
            respond goodThingConfigInstance.errors, view:'edit'
            return
        }

        goodThingConfigInstance.save flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'GoodThingConfig.label', default: 'GoodThingConfig'), goodThingConfigInstance.id])
                redirect goodThingConfigInstance
            }
            '*'{ respond goodThingConfigInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(GoodThingConfig goodThingConfigInstance) {

        if (goodThingConfigInstance == null) {
            notFound()
            return
        }

        goodThingConfigInstance.delete flush:true

        request.withFormat {
            form {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'GoodThingConfig.label', default: 'GoodThingConfig'), goodThingConfigInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'goodThingConfigInstance.label', default: 'GoodThingConfig'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
