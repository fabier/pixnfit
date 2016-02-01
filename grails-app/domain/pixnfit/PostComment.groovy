package pixnfit

class PostComment extends BaseEntity {

    /**
     * The post on which this postComment is
     */
    Post post

    /**
     * Votes on this comment
     */
    static hasMany = [postCommentVotes: PostCommentVote]

    static belongsTo = [post: Post]

    static constraints = {
        name nullable: true
    }
}