package pixnfit

import grails.plugin.springsecurity.SpringSecurityService
import org.springframework.security.access.annotation.Secured
import org.springframework.web.multipart.commons.CommonsMultipartFile

@Secured("hasRole('ROLE_USER')")
class PostController {

    SpringSecurityService springSecurityService
    PostTypeService postTypeService
    StateService stateService
    VisibilityService visibilityService
    PostService postService

    static allowedMethods = [addComment: "POST"]

    def create() {
        render view: "create"
    }

    def show(long id) {
        Post post = Post.get(id)
        List<PostComment> comments = post.postComments
        comments?.sort(EntityUtils.dateCreatedComparatorDesc)
        int positiveVoteCount = post.getPositiveVoteCount()
        int negativeVoteCount = post.getNegativeVoteCount()
        int totalVoteCount = post.getVoteCount()

        User user = (User) springSecurityService.currentUser

        Post previousPost = postService.getPreviousPost(post)
        Post nextPost = postService.getNextPost(post)

        PostVote userPostVote = PostVote.findByCreatorAndPost(user, post)
        boolean userHasVoted = userPostVote != null

        render view: "show", model: [
                post             : post,
                previousPost     : previousPost,
                nextPost         : nextPost,
                positiveVoteCount: positiveVoteCount,
                negativeVoteCount: negativeVoteCount,
                creator          : post.creator,
                comments         : comments,
                userPostVote     : userPostVote,
                userHasVoted     : userHasVoted,
                totalVoteCount   : totalVoteCount,
                currentUser      : springSecurityService.currentUser
        ]
    }

    def save() {
        User user = (User) springSecurityService.currentUser

        // Initialisation des données du post
        Post post = new Post(
                postType: postTypeService.help(),
                state: stateService.active(),
                visibility: visibilityService.public()
        )
        post.setCreator(user)
        bindData(post, params, [include: ['name', 'description']])

        // Initialisation des données de l'image
        byte[] data = null
        String originalFilename = null
        if (params.file != null) {
            def file = request.getFile("file")
            if (file instanceof CommonsMultipartFile) {
                data = file.getBytes()
                originalFilename = file.originalFilename
            }
        }

        ImageData imageData = new ImageData(
                name: originalFilename,
                filename: originalFilename,
                data: data
        )
        imageData.setCreator(user)
        imageData.updateAutoCalculatedFields()

        Image image = new Image(
                imageData: imageData,
                name: originalFilename
        )
        // On ne doit pas le mettre dans le constructeur, sinon user.image se met à jour avec cette valeur !!!
        image.setCreator(user)

        // Si les données du post sont correctes et que les données de l'image aussi,
        // alors on peut créer le post, et associer le post et l'image
        if (post.validate() && image.validate()) {
            image.save()
            post.addToImages(image)
            post.save()
            redirect controller: "post", action: "show", id: post.id
        } else {
            flash.error = "Impossible to create post. See logs for details."
            redirect action: 'create', params: params
        }
    }

    def addComment(long id) {
        Post post = Post.get(id)
        User user = (User) springSecurityService.currentUser

        PostComment postComment = new PostComment(
                post: post
        )
        postComment.setCreator(user)
        bindData(postComment, params, [include: ['description']])

        if (postComment.validate()) {
            postComment.save()
//            post.addToPostComments(postComment)
//            post.save()
            redirect controller: "post", action: "show", id: post.id, fragment: "comments"
        } else {
            flash.error = "Impossible to create comment. See logs for details."
            redirect controller: "post", action: "show", id: post.id, params: params
        }
    }

    def voteUp(long id) {
        vote(id, true)
    }

    def voteDown(long id) {
        vote(id, false, null)
    }

    private def vote(long id, boolean vote, VoteReason voteReason = null) {
        Post post = Post.get(id)
        User user = (User) springSecurityService.currentUser

        PostVote postVote = new PostVote(
                post: post,
                vote: vote,
                voteReason: voteReason
        )
        postVote.setCreator(user)

        if (postVote.validate()) {
            postVote.save()
//            post.save()
            flash.message = "Your vote has been submitted."
            redirect controller: "post", action: "show", id: post.id
        } else {
            flash.error = "Impossible to vote. See logs for details."
            redirect controller: "post", action: "show", id: post.id
        }
    }
}
