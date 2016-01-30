package pixnfit

class Gender extends BaseEntity {

    static constraints = {
        name blank: false, unique: true
    }

    /**
     * Gets users with this gender
     */
    List<User> getUsers() {
        User.findAllByGender(this)
    }

    // Delete is forbidden
    def beforeDelete() {
        return false
    }
}
