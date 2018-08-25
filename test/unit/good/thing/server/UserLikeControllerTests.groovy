package good.thing.server



import org.junit.*
import grails.test.mixin.*

@TestFor(UserLikeController)
@Mock(UserLike)
class UserLikeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/userLike/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.userLikeInstanceList.size() == 0
        assert model.userLikeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.userLikeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.userLikeInstance != null
        assert view == '/userLike/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/userLike/show/1'
        assert controller.flash.message != null
        assert UserLike.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/userLike/list'

        populateValidParams(params)
        def userLike = new UserLike(params)

        assert userLike.save() != null

        params.id = userLike.id

        def model = controller.show()

        assert model.userLikeInstance == userLike
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/userLike/list'

        populateValidParams(params)
        def userLike = new UserLike(params)

        assert userLike.save() != null

        params.id = userLike.id

        def model = controller.edit()

        assert model.userLikeInstance == userLike
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/userLike/list'

        response.reset()

        populateValidParams(params)
        def userLike = new UserLike(params)

        assert userLike.save() != null

        // test invalid parameters in update
        params.id = userLike.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/userLike/edit"
        assert model.userLikeInstance != null

        userLike.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/userLike/show/$userLike.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        userLike.clearErrors()

        populateValidParams(params)
        params.id = userLike.id
        params.version = -1
        controller.update()

        assert view == "/userLike/edit"
        assert model.userLikeInstance != null
        assert model.userLikeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/userLike/list'

        response.reset()

        populateValidParams(params)
        def userLike = new UserLike(params)

        assert userLike.save() != null
        assert UserLike.count() == 1

        params.id = userLike.id

        controller.delete()

        assert UserLike.count() == 0
        assert UserLike.get(userLike.id) == null
        assert response.redirectedUrl == '/userLike/list'
    }
}
