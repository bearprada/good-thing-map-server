package good.thing.server

class UserLike {

    static belongsTo = [goodThing:GoodThing]

    String userId

    Date dateCreated
    Date lastUpdated 

    static mapping = {
        autoTimestamp true
    }

    public String toString() {
        return userId
    }
}
