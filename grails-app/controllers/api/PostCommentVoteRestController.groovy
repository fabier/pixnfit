package api

import org.springframework.security.access.annotation.Secured
import pixnfit.DynamicDataRestfulController
import pixnfit.PostCommentVote

@Secured("hasRole('ROLE_USER')")
class PostCommentVoteRestController extends DynamicDataRestfulController {

    // On ne peut qu'ajouter des votes sur des commentaires, pas de mise à jour ou de suppression
    static allowedMethods = [save: "POST"]

    PostCommentVoteRestController() {
        super(PostCommentVote)
    }
}
