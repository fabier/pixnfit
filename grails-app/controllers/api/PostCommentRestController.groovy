package api

import grails.plugin.springsecurity.SpringSecurityService
import org.springframework.http.HttpStatus
import org.springframework.security.access.annotation.Secured
import pixnfit.DynamicDataRestfulController
import pixnfit.PostComment
import pixnfit.PostCommentVote
import pixnfit.User

@Secured("hasRole('ROLE_USER')")
class PostCommentRestController extends DynamicDataRestfulController {

    SpringSecurityService springSecurityService

    PostCommentRestController() {
        super(PostComment)
    }

    def votes() {
        PostComment postComment = PostComment.get(params.postCommentRestId)
        respond postComment.postCommentVotes?.toArray()
    }

    def addVote() {
        def json = request.JSON
        PostComment postComment = PostComment.get(params.postCommentRestId)
        User user = springSecurityService.currentUser

        // Création du vote
        PostCommentVote postCommentVote = new PostCommentVote(
                postComment: postComment
        )
        postCommentVote.setCreator(user)
        bindData(postCommentVote, json, [include: ['vote']])

        if (postCommentVote.validate()) {
            postCommentVote.save()
            respond postCommentVote, [status: HttpStatus.CREATED]
        } else {
            respond postCommentVote, [status: HttpStatus.UNPROCESSABLE_ENTITY]
        }
    }
}
