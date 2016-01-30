package pixnfit

class FashionStyle extends BaseEntity {

    static constraints = {
        name blank: false, unique: true
    }

    /**
     * Gets users with (at least) this fashion style
     */
    List<User> getUsers() {
        User.findAll("from User where ? in elements(fashionStyles)", [this])
    }

    // Delete is forbidden
    def beforeDelete() {
        return false
    }
}
