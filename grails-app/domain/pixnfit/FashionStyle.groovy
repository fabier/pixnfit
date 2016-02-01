package pixnfit

class FashionStyle extends BaseEntity {

    /**
     * Users with (at least) this fashion style
     */
    Set<User> users

    static belongsTo = [User]
    static hasMany = [users: User]

    static constraints = {
        name blank: false, unique: true
    }

    // Delete is forbidden
    def beforeDelete() {
        return false
    }
}
