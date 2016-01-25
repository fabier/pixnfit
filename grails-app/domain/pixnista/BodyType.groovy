package pixnista

class BodyType extends BaseEntity {

    static constraints = {
        name blank: false, unique: true
    }

    /**
     * Gets users with this body type
     */
    List<User> getUsers() {
        User.findAllByBodyType(this)
    }

    // Delete is forbidden
    def beforeDelete() {
        return false
    }
}
