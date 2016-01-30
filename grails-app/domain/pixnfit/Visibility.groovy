package pixnfit

class Visibility extends BaseEntity {

    static constraints = {
        name blank: false, unique: true
    }

    /**
     * Gets posts with this vibility
     */
    List<Post> getPosts() {
        Post.findAllByVisibility(this)
    }

    // Delete is forbidden
    def beforeDelete() {
        return false
    }
}
