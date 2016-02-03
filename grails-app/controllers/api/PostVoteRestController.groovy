package api

import org.springframework.security.access.annotation.Secured
import pixnfit.DynamicDataRestfulController
import pixnfit.PostVote

@Secured("hasRole('ROLE_USER')")
class PostVoteRestController extends DynamicDataRestfulController {

    // On ne peut qu'ajouter des votes sur des posts, pas de mise à jour ou de suppression
    static allowedMethods = [save: "POST"]

    PostVoteRestController() {
        super(PostVote)
    }
}
