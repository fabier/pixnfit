package pixnista

/**
 * Voter identity is stored in field "creator", from BaseEntity
 */
class PostVote extends BaseEntity {

    /**
     * Vote value
     * True means positive vote
     * False means negative vote
     */
    Boolean vote

    /**
     * Vote reason, explaining voters motivations.
     *
     * May be null, if vote is negative, to explain vote motivations,
     * May also be null if user didn't want to tell why he voted this way,
     * otherwise should have a value.
     */
    VoteReason voteReason

    /**
     * The post on which this vote is
     */
    static belongsTo = [post: Post]

    static constraints = {
        name nullable: true
        vote nullable: false
        voteReason nullable: true

        // Only one vote per user per post
        post unique: 'creator'
    }

    // Delete is forbidden
    def beforeDelete() {
        return false
    }
}
