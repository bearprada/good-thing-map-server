

import org.junit.*
import grails.test.mixin.*

@TestFor(ShiroRoleController)
@Mock(ShiroRole)
class ShiroRoleControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/shiroRole/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.shiroRoleInstanceList.size() == 0
        assert model.shiroRoleInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.shiroRoleInstance != null
    }

    void testSave() {
        controller.save()

        assert model.shiroRoleInstance != null
        assert view == '/shiroRole/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/shiroRole/show/1'
        assert controller.flash.message != null
        assert ShiroRole.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/shiroRole/list'

        populateValidParams(params)
        def shiroRole = new ShiroRole(params)

        assert shiroRole.save() != null

        params.id = shiroRole.id

        def model = controller.show()

        assert model.shiroRoleInstance == shiroRole
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/shiroRole/list'

        populateValidParams(params)
        def shiroRole = new ShiroRole(params)

        assert shiroRole.save() != null

        params.id = shiroRole.id

        def model = controller.edit()

        assert model.shiroRoleInstance == shiroRole
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/shiroRole/list'

        response.reset()

        populateValidParams(params)
        def shiroRole = new ShiroRole(params)

        assert shiroRole.save() != null

        // test invalid parameters in update
        params.id = shiroRole.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/shiroRole/edit"
        assert model.shiroRoleInstance != null

        shiroRole.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/shiroRole/show/$shiroRole.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        shiroRole.clearErrors()

        populateValidParams(params)
        params.id = shiroRole.id
        params.version = -1
        controller.update()

        assert view == "/shiroRole/edit"
        assert model.shiroRoleInstance != null
        assert model.shiroRoleInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/shiroRole/list'

        response.reset()

        populateValidParams(params)
        def shiroRole = new ShiroRole(params)

        assert shiroRole.save() != null
        assert ShiroRole.count() == 1

        params.id = shiroRole.id

        controller.delete()

        assert ShiroRole.count() == 0
        assert ShiroRole.get(shiroRole.id) == null
        assert response.redirectedUrl == '/shiroRole/list'
    }
}
