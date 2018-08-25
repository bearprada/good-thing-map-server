package good.thing.server



import org.junit.*
import grails.test.mixin.*

@TestFor(GoodThingTypeController)
@Mock(GoodThingType)
class GoodThingTypeControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/goodThingType/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.goodThingTypeInstanceList.size() == 0
        assert model.goodThingTypeInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.goodThingTypeInstance != null
    }

    void testSave() {
        controller.save()

        assert model.goodThingTypeInstance != null
        assert view == '/goodThingType/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/goodThingType/show/1'
        assert controller.flash.message != null
        assert GoodThingType.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/goodThingType/list'

        populateValidParams(params)
        def goodThingType = new GoodThingType(params)

        assert goodThingType.save() != null

        params.id = goodThingType.id

        def model = controller.show()

        assert model.goodThingTypeInstance == goodThingType
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/goodThingType/list'

        populateValidParams(params)
        def goodThingType = new GoodThingType(params)

        assert goodThingType.save() != null

        params.id = goodThingType.id

        def model = controller.edit()

        assert model.goodThingTypeInstance == goodThingType
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/goodThingType/list'

        response.reset()

        populateValidParams(params)
        def goodThingType = new GoodThingType(params)

        assert goodThingType.save() != null

        // test invalid parameters in update
        params.id = goodThingType.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/goodThingType/edit"
        assert model.goodThingTypeInstance != null

        goodThingType.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/goodThingType/show/$goodThingType.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        goodThingType.clearErrors()

        populateValidParams(params)
        params.id = goodThingType.id
        params.version = -1
        controller.update()

        assert view == "/goodThingType/edit"
        assert model.goodThingTypeInstance != null
        assert model.goodThingTypeInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/goodThingType/list'

        response.reset()

        populateValidParams(params)
        def goodThingType = new GoodThingType(params)

        assert goodThingType.save() != null
        assert GoodThingType.count() == 1

        params.id = goodThingType.id

        controller.delete()

        assert GoodThingType.count() == 0
        assert GoodThingType.get(goodThingType.id) == null
        assert response.redirectedUrl == '/goodThingType/list'
    }
}
