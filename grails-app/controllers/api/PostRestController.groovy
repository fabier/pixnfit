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
                postType: postTypeService.help(),
                state: stateService.active(),
                visibility: visibilityService.public()
        )
        post.setCreator(user)
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

    // Get user related information
    def me() {
        Post post = Post.get(params.postRestId)
        User user = springSecurityService.currentUser
        def postVote = PostVote.findByPostAndCreator(post, user)
        List<PostComment> postComments = PostComment.findAllByPostAndCreator(post, user)
        respond([vote: postVote, comments: postComments.toArray()])
    }

    def comments() {
        Post post = Post.get(params.postRestId)
        respond post.postComments.toArray()
    }

    def addComment() {
        def json = request.JSON
        Post post = Post.get(params.postRestId)
        User user = springSecurityService.currentUser

        PostComment postComment = new PostComment(
                post: post
        )
        postComment.setCreator(user)
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
        User user = springSecurityService.currentUser

        // Création du vote
        PostVote postVote = new PostVote(
                post: post
        )
        postVote.setCreator(user)
        bindData(postVote, json, [include: ['vote', 'description']])
        foreignKeyBindDataIfNotNull(postVote, json, [voteReason: VoteReason])

        if (postVote.validate()) {
            // Il est *nécessaire* de flusher pour pouvoir faire PostVote.findBy
            // et retrouver l'entité lors de cette requete... (notamment avec l'url "/me")
            postVote.save(flush: true)
            respond postVote, [status: HttpStatus.CREATED]
        } else {
            respond postVote, [status: HttpStatus.UNPROCESSABLE_ENTITY]
        }
    }

    def addImage() {
        def json = request.JSON
        Post post = Post.get(params.postRestId)

        Image image = Image.get(json.imageId)
        if (image != null) {
            post.addToImages(image)
            if (post.validate()) {
                post.save()
                respond post
            } else {
                respond post
            }
        } else {
            respond((Object) [error: "imageId : ${json.imageId} is not a valid value"], [status: HttpStatus.BAD_REQUEST])
        }
    }

    def help() {
        // TODO : A améliorer en mettant en place la pagination, et en remontant en priorité ceux qui n'ont pas de votes...
        int max = Math.min((params.max ?: 10) as int, 100)
        int offset = (params.offset ?: 0) as int
        respond Post.findAllByPostType(postTypeService.help(),
                [max: max, offset: offset]).toArray()
    }

    def featured() {
        // TODO : A améliorer en mettant en place la pagination, en mettant un peu d'aléatoire dans l'ordre des posts, etc...
        int max = Math.min((params.max ?: 10) as int, 100)
        int offset = (params.offset ?: 0) as int
        respond Post.findAllByPostType(postTypeService.dressing(),
                [max: max, offset: offset]).toArray()
    }
}
