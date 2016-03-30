package pixnfit

/**
 * Voter identity is stored in field "creator", from BaseEntity
 */
class PostCommentVote extends BaseEntity {

    /**
     * The postComment on which this vote is
     */
    PostComment postComment

    /**
     * Vote value
     * True means positive vote
     * False means negative vote
     */
    Boolean vote

    static belongsTo = [postComment: PostComment]

    static constraints = {
        name nullable: true
        vote nullable: false

        // Only one vote per user per postComment
        postComment unique: ['creator']
    }
}
