package pixnfit

class PostType extends BaseEntity {

    static constraints = {
        name blank: false, unique: true
    }

    /**
     * Gets posts with this type
     */
    List<Post> getPosts() {
        Post.findAllByPostType(this)
    }

    // Delete is forbidden
    def beforeDelete() {
        return false
    }
}
