package pixnfit

import grails.transaction.Transactional

@Transactional
class PostCommentService {

    /**
     * Gets User's post comments
     */
    List<PostComment> getPostComments(User user) {
        PostComment.findAllByCreator(user)
    }
}
