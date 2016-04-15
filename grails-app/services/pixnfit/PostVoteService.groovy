package pixnfit

import grails.transaction.Transactional

@Transactional
class PostVoteService {

    /**
     * Gets User's post votes
     */
    List<PostVote> getPostVotes(User user) {
        PostVote.findAllByCreator(user)
    }
}
