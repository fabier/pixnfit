package pixnfit

class State extends BaseEntity {

    static constraints = {
        name blank: false, unique: true
    }

    /**
     * Posts with this state
     */
    List<Post> getPosts() {
        Post.findAllByState(this)
    }

    // Delete is forbidden
    def beforeDelete() {
        return false
    }
}
