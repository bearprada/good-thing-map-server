package good.thing.server

class UserCheckin {

	String facebookPostId
    String userId

    Date dateCreated
    Date lastUpdated 

    static mapping = {
        autoTimestamp true
        facebookPostId type: 'text'
    }

    public String toString() {
        return facebookPostId
    }
}
