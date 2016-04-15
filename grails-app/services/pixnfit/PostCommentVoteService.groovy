package pixnfit

import grails.transaction.Transactional

@Transactional
class PostCommentVoteService {

    /**
     * Gets User's post comment votes
     */
    List<PostCommentVote> getPostCommentVotes(User user) {
        PostCommentVote.findAllByCreator(user)
    }
}
