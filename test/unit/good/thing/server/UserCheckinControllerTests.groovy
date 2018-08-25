package good.thing.server



import org.junit.*
import grails.test.mixin.*

@TestFor(UserCheckinController)
@Mock(UserCheckin)
class UserCheckinControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/userCheckin/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.userCheckinInstanceList.size() == 0
        assert model.userCheckinInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.userCheckinInstance != null
    }

    void testSave() {
        controller.save()

        assert model.userCheckinInstance != null
        assert view == '/userCheckin/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/userCheckin/show/1'
        assert controller.flash.message != null
        assert UserCheckin.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/userCheckin/list'

        populateValidParams(params)
        def userCheckin = new UserCheckin(params)

        assert userCheckin.save() != null

        params.id = userCheckin.id

        def model = controller.show()

        assert model.userCheckinInstance == userCheckin
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/userCheckin/list'

        populateValidParams(params)
        def userCheckin = new UserCheckin(params)

        assert userCheckin.save() != null

        params.id = userCheckin.id

        def model = controller.edit()

        assert model.userCheckinInstance == userCheckin
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/userCheckin/list'

        response.reset()

        populateValidParams(params)
        def userCheckin = new UserCheckin(params)

        assert userCheckin.save() != null

        // test invalid parameters in update
        params.id = userCheckin.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/userCheckin/edit"
        assert model.userCheckinInstance != null

        userCheckin.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/userCheckin/show/$userCheckin.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        userCheckin.clearErrors()

        populateValidParams(params)
        params.id = userCheckin.id
        params.version = -1
        controller.update()

        assert view == "/userCheckin/edit"
        assert model.userCheckinInstance != null
        assert model.userCheckinInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/userCheckin/list'

        response.reset()

        populateValidParams(params)
        def userCheckin = new UserCheckin(params)

        assert userCheckin.save() != null
        assert UserCheckin.count() == 1

        params.id = userCheckin.id

        controller.delete()

        assert UserCheckin.count() == 0
        assert UserCheckin.get(userCheckin.id) == null
        assert response.redirectedUrl == '/userCheckin/list'
    }
}
