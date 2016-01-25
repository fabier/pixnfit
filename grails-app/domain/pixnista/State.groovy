package pixnista

class State extends BaseEntity {

    /**
     * Posts with this state
     */
    static hasMany = [posts: Post]

    static constraints = {
        name blank: false, unique: true
    }

    // Delete is forbidden
    def beforeDelete() {
        return false
    }
}
