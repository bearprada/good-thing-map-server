package good.thing.server



import org.junit.*
import grails.test.mixin.*

@TestFor(GoodThingController)
@Mock(GoodThing)
class GoodThingControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/goodThing/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.goodThingInstanceList.size() == 0
        assert model.goodThingInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.goodThingInstance != null
    }

    void testSave() {
        controller.save()

        assert model.goodThingInstance != null
        assert view == '/goodThing/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/goodThing/show/1'
        assert controller.flash.message != null
        assert GoodThing.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/goodThing/list'

        populateValidParams(params)
        def goodThing = new GoodThing(params)

        assert goodThing.save() != null

        params.id = goodThing.id

        def model = controller.show()

        assert model.goodThingInstance == goodThing
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/goodThing/list'

        populateValidParams(params)
        def goodThing = new GoodThing(params)

        assert goodThing.save() != null

        params.id = goodThing.id

        def model = controller.edit()

        assert model.goodThingInstance == goodThing
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/goodThing/list'

        response.reset()

        populateValidParams(params)
        def goodThing = new GoodThing(params)

        assert goodThing.save() != null

        // test invalid parameters in update
        params.id = goodThing.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/goodThing/edit"
        assert model.goodThingInstance != null

        goodThing.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/goodThing/show/$goodThing.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        goodThing.clearErrors()

        populateValidParams(params)
        params.id = goodThing.id
        params.version = -1
        controller.update()

        assert view == "/goodThing/edit"
        assert model.goodThingInstance != null
        assert model.goodThingInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/goodThing/list'

        response.reset()

        populateValidParams(params)
        def goodThing = new GoodThing(params)

        assert goodThing.save() != null
        assert GoodThing.count() == 1

        params.id = goodThing.id

        controller.delete()

        assert GoodThing.count() == 0
        assert GoodThing.get(goodThing.id) == null
        assert response.redirectedUrl == '/goodThing/list'
    }
}
