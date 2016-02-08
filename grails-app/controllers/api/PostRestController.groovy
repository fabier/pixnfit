package api

import grails.plugin.springsecurity.SpringSecurityService
import grails.transaction.Transactional
import org.springframework.http.HttpStatus
import org.springframework.security.access.annotation.Secured
import pixnfit.*

@Secured("hasRole('ROLE_USER')")
class PostRestController extends DynamicDataRestfulController {

    PostTypeService postTypeService
    StateService stateService
    VisibilityService visibilityService

    SpringSecurityService springSecurityService

    // On ne peut qu'ajouter des posts, pas de mise à jour ou de suppression
    static allowedMethods = [help: "GET", featured: "GET", save: "POST", addComment: "POST", addVote: "POST"]

    PostRestController() {
        super(Post)
    }

    @Override
    @Transactional
    Object save() {
        def json = request.JSON
        User user = springSecurityService.currentUser
        Post post = new Post(
                creator: user,
                postType: postTypeService.help(),
                state: stateService.active(),
                visibility: visibilityService.public()
        )
        bindData(post, json, [include: ['name', 'description']])
        foreignKeyBindDataIfNotNull(post, json, [postType: PostType, visibility: Visibility])

        if (post.validate()) {
            post.save()
            respond post, [status: HttpStatus.CREATED]
        } else {
            respond post, [status: HttpStatus.UNPROCESSABLE_ENTITY]
        }
    }

    @Override
    @Transactional
    def update() {
        def json = request.JSON
        Post post = Post.get(params.id)
        bindData(post, json, [include: ['name', 'description']])
        foreignKeyBindDataIfNotNull(post, json, [postType: PostType, state: State, visibility: Visibility])
        post.save()
        respond post
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
                creator: springSecurityService.currentUser
        )
        bindData(postComment, json, [include: ['description']])

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

        // Création du vote
        PostVote postVote = new PostVote(
                post: post,
                creator: springSecurityService.currentUser
        )
        bindData(post, json, [include: ['vote', 'description']])
        foreignKeyBindDataIfNotNull(post, json, [voteReason: VoteReason])

        if (postVote.validate()) {
            postVote.save()
            respond postVote, [status: HttpStatus.CREATED]
        } else {
            respond postVote, [status: HttpStatus.UNPROCESSABLE_ENTITY]
        }
    }

    def help() {
        // TODO : A améliorer en mettant en place la pagination, et en remontant en priorité ceux qui n'ont pas de votes...
        int max = Math.min(params.max ?: 10, 100)
        int offset = params.offset ?: 0
        respond Post.findAllByPostType(postTypeService.help(),
                [max: max, offset: offset]).toArray()
    }

    def featured() {
        // TODO : A améliorer en mettant en place la pagination, en mettant un peu d'aléatoire dans l'ordre des posts, etc...
        int max = Math.min(params.max ?: 10, 100)
        int offset = params.offset ?: 0
        respond Post.findAllByPostType(postTypeService.dressing(),
                [max: max, offset: offset]).toArray()
    }
}
