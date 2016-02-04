package api

import grails.plugin.springsecurity.SpringSecurityService
import org.springframework.http.HttpStatus
import org.springframework.security.access.annotation.Secured
import pixnfit.*

@Secured("hasRole('ROLE_USER')")
class PostRestController extends DynamicDataRestfulController {

    PostTypeService postTypeService

    SpringSecurityService springSecurityService

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
        def json = request.JSON
        Post post = Post.get(params.postRestId)
        PostComment postComment = new PostComment(
                post: post,
                description: json.description,
                creator: springSecurityService.currentUser
        )
        if (postComment.validate()) {
            postComment.save()
            respond postComment, [status: HttpStatus.CREATED]
        } else {
            respond postComment, [status: HttpStatus.UNPROCESSABLE_ENTITY]
        }
        respond postComment
    }

    def votes() {
        Post post = Post.get(params.postRestId)
        respond post.postVotes?.toArray()
    }

    def addVote() {
        def json = request.JSON
        Post post = Post.get(params.postRestId)

        // Si on a passé un identifiant de voteReason, on doit avoir une valeur conhérente
        VoteReason voteReason
        if (json.voteReasonId != null) {
            voteReason = VoteReason.get(json.voteReasonId)
            if (voteReason == null) {
                respond([error: "voteReasonId : ${json.voteReasonId} is not a valid value"])
            }
        }

        // Création du vote
        PostVote postVote = new PostVote(
                post: post,
                vote: json.vote,
                voteReason: voteReason,
                creator: springSecurityService.currentUser
        )
        if (postVote.validate()) {
            postVote.save()
            respond postVote, [status: HttpStatus.CREATED]
        } else {
            respond postVote, [status: HttpStatus.UNPROCESSABLE_ENTITY]
        }
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
