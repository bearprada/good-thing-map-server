package good.thing.server



import grails.test.mixin.*
import spock.lang.*

@TestFor(GoodThingConfigController)
@Mock(GoodThingConfig)
class GoodThingConfigControllerSpec extends Specification {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void "Test the index action returns the correct model"() {

        when:"The index action is executed"
            controller.index()

        then:"The model is correct"
            !model.goodThingConfigInstanceList
            model.goodThingConfigInstanceCount == 0
    }

    void "Test the create action returns the correct model"() {
        when:"The create action is executed"
            controller.create()

        then:"The model is correctly created"
            model.goodThingConfigInstance!= null
    }

    void "Test the save action correctly persists an instance"() {

        when:"The save action is executed with an invalid instance"
            def goodThingConfig = new GoodThingConfig()
            goodThingConfig.validate()
            controller.save(goodThingConfig)

        then:"The create view is rendered again with the correct model"
            model.goodThingConfigInstance!= null
            view == 'create'

        when:"The save action is executed with a valid instance"
            response.reset()
            populateValidParams(params)
            goodThingConfig = new GoodThingConfig(params)

            controller.save(goodThingConfig)

        then:"A redirect is issued to the show action"
            response.redirectedUrl == '/goodThingConfig/show/1'
            controller.flash.message != null
            GoodThingConfig.count() == 1
    }

    void "Test that the show action returns the correct model"() {
        when:"The show action is executed with a null domain"
            controller.show(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the show action"
            populateValidParams(params)
            def goodThingConfig = new GoodThingConfig(params)
            controller.show(goodThingConfig)

        then:"A model is populated containing the domain instance"
            model.goodThingConfigInstance == goodThingConfig
    }

    void "Test that the edit action returns the correct model"() {
        when:"The edit action is executed with a null domain"
            controller.edit(null)

        then:"A 404 error is returned"
            response.status == 404

        when:"A domain instance is passed to the edit action"
            populateValidParams(params)
            def goodThingConfig = new GoodThingConfig(params)
            controller.edit(goodThingConfig)

        then:"A model is populated containing the domain instance"
            model.goodThingConfigInstance == goodThingConfig
    }

    void "Test the update action performs an update on a valid domain instance"() {
        when:"Update is called for a domain instance that doesn't exist"
            controller.update(null)

        then:"A 404 error is returned"
            response.redirectedUrl == '/goodThingConfig/index'
            flash.message != null


        when:"An invalid domain instance is passed to the update action"
            response.reset()
            def goodThingConfig = new GoodThingConfig()
            goodThingConfig.validate()
            controller.update(goodThingConfig)

        then:"The edit view is rendered again with the invalid instance"
            view == 'edit'
            model.goodThingConfigInstance == goodThingConfig

        when:"A valid domain instance is passed to the update action"
            response.reset()
            populateValidParams(params)
            goodThingConfig = new GoodThingConfig(params).save(flush: true)
            controller.update(goodThingConfig)

        then:"A redirect is issues to the show action"
            response.redirectedUrl == "/goodThingConfig/show/$goodThingConfig.id"
            flash.message != null
    }

    void "Test that the delete action deletes an instance if it exists"() {
        when:"The delete action is called for a null instance"
            controller.delete(null)

        then:"A 404 is returned"
            response.redirectedUrl == '/goodThingConfig/index'
            flash.message != null

        when:"A domain instance is created"
            response.reset()
            populateValidParams(params)
            def goodThingConfig = new GoodThingConfig(params).save(flush: true)

        then:"It exists"
            GoodThingConfig.count() == 1

        when:"The domain instance is passed to the delete action"
            controller.delete(goodThingConfig)

        then:"The instance is deleted"
            GoodThingConfig.count() == 0
            response.redirectedUrl == '/goodThingConfig/index'
            flash.message != null
    }
}
