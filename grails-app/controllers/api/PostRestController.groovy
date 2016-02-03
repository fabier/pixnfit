package api

import org.springframework.security.access.annotation.Secured
import pixnfit.DynamicDataRestfulController
import pixnfit.Post
import pixnfit.PostTypeService

@Secured("hasRole('ROLE_USER')")
class PostRestController extends DynamicDataRestfulController {

    PostTypeService postTypeService

    // On ne peut qu'ajouter des posts, pas de mise à jour ou de suppression
    static allowedMethods = [help: "GET", featured: "GET", save: "POST", addComment: "POST", addVote: "POST"]

    PostRestController() {
        super(Post)
    }

    def comments() {
        Post post = Post.get(params.postRestId)
        respond post.postComments.toArray()
    }

    def addComment() {
        log.info "params : $params"
        log.info "request : $request"
        respond null
    }

    def votes() {
        Post post = Post.get(params.postRestId)
        respond post.postVotes?.toArray()
    }

    def addVote() {
        log.info "params : $params"
        log.info "request : $request"
        respond null
    }

    def help() {
        // TODO : A améliorer en mettant en place la pagination, et en remontant en priorité ceux qui n'ont pas de votes...
        respond Post.findAllByPostType(postTypeService.help()).toArray()
    }

    def featured() {
        // TODO : A améliorer en mettant en place la pagination, en mettant un peu d'aléatoire dans l'ordre des posts, etc...
        respond Post.findAllByPostType(postTypeService.dressing()).toArray()
    }
}
