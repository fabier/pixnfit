package pixnista

import grails.plugin.springsecurity.SpringSecurityService
import org.springframework.security.access.annotation.Secured

@Secured("hasRole('ROLE_USER')")
class PostController {

    SpringSecurityService springSecurityService

    def create() {
        render view: "create"
    }

    def show(long id) {
        Post post = Post.get(id)
        List<PostComment> comments = post.postComments
        comments.sort { a, b ->
            -a.dateCreated.compareTo(b.dateCreated)
        }
        int positiveVoteCount = post.getPositiveVoteCount()
        int negativeVoteCount = post.getNegativeVoteCount()
        int totalVoteCount = post.getVoteCount()

        User user = springSecurityService.currentUser
        boolean userHasVoted = PostVote.findByPostAndCreator(post, user) != null

        render view: "show", model: [
                post             : post,
                positiveVoteCount: positiveVoteCount,
                negativeVoteCount: negativeVoteCount,
                totalVoteCount   : totalVoteCount,
                creator          : post.creator,
                comments         : comments,
                userHasVoted     : userHasVoted,
                currentUser      : springSecurityService.currentUser
        ]
    }
}
