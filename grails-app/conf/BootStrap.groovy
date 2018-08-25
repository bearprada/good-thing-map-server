import good.thing.server.*
import org.apache.shiro.crypto.hash.*
import goodthings.Consts

class BootStrap {

    def init = { servletContext ->

        if(ShiroRole.count()==0) {
            new ShiroRole(name:"admin").save(flush:true)
            new ShiroRole(name:"user").save(flush:true)
        }

        if(ShiroUser.count()==0) {
            new ShiroUser(username:"admin", passwordHash:new Sha256Hash("admin").toHex()).addToRoles(ShiroRole.get(1)).save(flush:true)
        }

        if(GoodThingType.count()==0) {
            new GoodThingType(name : "MAIN MEAL", type:1).save(flush:true)
            new GoodThingType(name : "SNACK", type:2).save(flush:true)
            new GoodThingType(name : "frozen food/frult", type:3).save(flush:true)
            new GoodThingType(name : "OTHER", type:4).save(flush:true)
            new GoodThingType(name : "The Big Issue", type : Consts.TYPE_THE_BIG_ISSUE).save(flush:true)
        }
    }
    def destroy = {
    }
}
