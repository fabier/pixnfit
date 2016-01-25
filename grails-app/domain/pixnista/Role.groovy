package pixnista

class Role {

    String authority

    Date lastUpdated
    Date dateCreated
    User creator

    static mapping = {
        cache true
    }

    static constraints = {
        authority blank: false, unique: true
        creator nullable: true
    }

    // Delete is forbidden
    def beforeDelete() {
        return false
    }
}
