package pixnista

/**
 * Post title is field "name", from BaseEntity
 * Post content is field "description", from BaseEntity
 */
class Post extends BaseEntity {

    /**
     * Post visibility
     */
    Visibility visibility

    /**
     * Post type, either help or dressing
     */
    PostType postType

    /**
     * Images visible in Post
     */
    List<Image> images

    /**
     * Comments on this post
     */
    List<PostComment> postComments

    /**
     * Votes on this post
     */
    List<PostVote> postVotes

    static hasMany = [images: Image, postComments: PostComment, postVotes: PostVote]

    static constraints = {
    }

    /**
     * Gets the number of positive votes this post got so far
     */
    int getPositiveVoteCount() {
        PostVote.countByPostAndVote(this, true)
    }

    /**
     * Get the number of negative votes this post got so far
     */
    int getNegativeVoteCount() {
        PostVote.countByPostAndVote(this, false)
    }

    /**
     * Gets number of votes issued so far
     */
    int getVoteCount() {
        PostVote.countByPost(this)
    }

    float getPostScoreAsPercentage() {

    }

    /**
     * Gets users that favorited this post
     */
    List<User> getFavoritedByUsers() {
        User.findAll("from User where ? in elements(favoritedPosts)", [this])
    }
}
