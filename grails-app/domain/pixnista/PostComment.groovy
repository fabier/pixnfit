package pixnista

class PostComment extends BaseEntity {

    /**
     * Votes on this comment
     */
    static hasMany = [postCommentVotes: PostCommentVote]

    /**
     * The post on which this comment is
     */
    static belongsTo = [post: Post]

    static constraints = {
    }
}
