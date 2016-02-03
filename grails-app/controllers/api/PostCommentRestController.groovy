package api

import org.springframework.security.access.annotation.Secured
import pixnfit.DynamicDataRestfulController
import pixnfit.PostComment

@Secured("hasRole('ROLE_USER')")
class PostCommentRestController extends DynamicDataRestfulController {

    // On ne peut qu'ajouter des commentaires sur des posts, pas de mise à jour ou de suppression
    static allowedMethods = [save: "POST"]

    PostCommentRestController() {
        super(PostComment)
    }
}
