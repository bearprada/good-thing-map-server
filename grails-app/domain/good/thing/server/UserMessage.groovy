package good.thing.server

class UserMessage {

  	static belongsTo = [goodThing:GoodThing]

    String userId
    String message

    Date dateCreated
    Date lastUpdated 

    static mapping = {
        autoTimestamp true
        message type: 'text'
    }

    public def toDict(){
        return ['messageId':id,
                'userId':userId,
                'message':message,
                'time':dateCreated?.getTime()]
    }

    public String toString() {
        return "${message} ${dateCreated}"
    }    
}
