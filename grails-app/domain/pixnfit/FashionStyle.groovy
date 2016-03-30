package pixnfit

class FashionStyle extends BaseEntity {

    static hasMany = [
            // Users with (at least) this fashion style
            users: UserFashionStyle
    ]

    static constraints = {
        name blank: false, unique: true
    }

    // Delete is forbidden
    def beforeDelete() {
        return false
    }
}
