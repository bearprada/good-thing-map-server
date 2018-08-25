package good.thing.server



import org.junit.*
import grails.test.mixin.*

@TestFor(UserMessageController)
@Mock(UserMessage)
class UserMessageControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/userMessage/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.userMessageInstanceList.size() == 0
        assert model.userMessageInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.userMessageInstance != null
    }

    void testSave() {
        controller.save()

        assert model.userMessageInstance != null
        assert view == '/userMessage/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/userMessage/show/1'
        assert controller.flash.message != null
        assert UserMessage.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/userMessage/list'

        populateValidParams(params)
        def userMessage = new UserMessage(params)

        assert userMessage.save() != null

        params.id = userMessage.id

        def model = controller.show()

        assert model.userMessageInstance == userMessage
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/userMessage/list'

        populateValidParams(params)
        def userMessage = new UserMessage(params)

        assert userMessage.save() != null

        params.id = userMessage.id

        def model = controller.edit()

        assert model.userMessageInstance == userMessage
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/userMessage/list'

        response.reset()

        populateValidParams(params)
        def userMessage = new UserMessage(params)

        assert userMessage.save() != null

        // test invalid parameters in update
        params.id = userMessage.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/userMessage/edit"
        assert model.userMessageInstance != null

        userMessage.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/userMessage/show/$userMessage.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        userMessage.clearErrors()

        populateValidParams(params)
        params.id = userMessage.id
        params.version = -1
        controller.update()

        assert view == "/userMessage/edit"
        assert model.userMessageInstance != null
        assert model.userMessageInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/userMessage/list'

        response.reset()

        populateValidParams(params)
        def userMessage = new UserMessage(params)

        assert userMessage.save() != null
        assert UserMessage.count() == 1

        params.id = userMessage.id

        controller.delete()

        assert UserMessage.count() == 0
        assert UserMessage.get(userMessage.id) == null
        assert response.redirectedUrl == '/userMessage/list'
    }
}
