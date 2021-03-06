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
        User user = (User) springSecurityService.currentUser
        Post post = new Post(
                postType: postTypeService.help(),
                state: stateService.active(),
                visibility: visibilityService.public()
        )
        post.setCreator(user)
        bindData(post, json, [include: ['name', 'description']])
        foreignKeyBindDataIfNotNull(post, json, [postType: PostType, visibility: Visibility])

        if (post.validate()) {
            post.save(flush: true)
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
        User creator = post.creator
        User user = (User) springSecurityService.currentUser
        boolean isCreator = user.equals(creator)
        boolean isFavorite = UserFavoritePost.findByUserAndPost(user, post) != null
        boolean isFollowingUser = UserFollow.findByFollowingUserAndFollowedUser(user, creator) != null
        PostVote userPostVote = PostVote.findByPostAndCreator(post, user)
        List<PostComment> userPostComments = PostComment.findAllByPostAndCreator(post, user)
        respond([
                comments       : userPostComments.toArray(),
                isCreator      : isCreator,
                isFavorite     : isFavorite,
                isFollowingUser: isFollowingUser,
                vote           : userPostVote
        ])
    }

    def comments() {
        Post post = Post.get(params.postRestId)
        List<PostComment> comments = PostComment.createCriteria().list {
            eq "post", post
            order "dateCreated", "desc"
        }
        respond comments.toArray()
    }

    def addComment() {
        def json = request.JSON
        Post post = Post.get(params.postRestId)
        User user = (User) springSecurityService.currentUser

        PostComment postComment = new PostComment(
                post: post
        )
        postComment.setCreator(user)
        bindData(postComment, json, [include: ['description']])

        if (postComment.validate()) {
            postComment.save(flush: true)
            respond postComment, [status: HttpStatus.CREATED]
        } else {
            respond postComment, [status: HttpStatus.UNPROCESSABLE_ENTITY]
        }
    }

    def votes() {
        Post post = Post.get(params.postRestId)
        respond post.postVotes?.toArray()
    }

    def addVote() {
        def json = request.JSON
        Post post = Post.get(params.postRestId)
        User user = (User) springSecurityService.currentUser

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
                post.save(flush: true)
                respond post, [status: HttpStatus.CREATED]
            } else {
                respond post, [status: HttpStatus.UNPROCESSABLE_ENTITY]
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
                [max: max, offset: offset, sort: "dateCreated", order: "desc"]).toArray()
    }

    def featured() {
        // TODO : A améliorer en mettant en place la pagination, en mettant un peu d'aléatoire dans l'ordre des posts, etc...
        int max = Math.min((params.max ?: 10) as int, 100)
        int offset = (params.offset ?: 0) as int
        respond Post.findAllByPostType(postTypeService.dressing(),
                [max: max, offset: offset, sort: "dateCreated", order: "desc"]).toArray()
    }

    def addToFavorites() {
        Post post = Post.get(params.postRestId)
        User user = (User) springSecurityService.currentUser
        UserFavoritePost userFavoritePost = new UserFavoritePost(
                post: post,
                user: user
        )

        if (userFavoritePost.validate()) {
            // Il est *nécessaire* de flusher pour pouvoir faire userFavoritePost.findBy
            // et retrouver l'entité lors de cette requete... (notamment avec l'url "/me")
            userFavoritePost.save(flush: true)
            respond userFavoritePost, [status: HttpStatus.CREATED]
        } else {
            respond userFavoritePost, [status: HttpStatus.UNPROCESSABLE_ENTITY]
        }
    }

    def removeFromFavorites() {
        Post post = Post.get(params.postRestId)
        User user = (User) springSecurityService.currentUser

        UserFavoritePost userFavoritePost = UserFavoritePost.findByPostAndUser(post, user)
        if (userFavoritePost != null) {
            userFavoritePost.delete(flush: true)
            respond userFavoritePost, [status: HttpStatus.OK]
        } else {
            respond userFavoritePost, [status: HttpStatus.OK]
        }
    }
}
