package pixnfit

import grails.plugin.springsecurity.SpringSecurityService
import org.springframework.security.access.annotation.Secured

@Secured(['permitAll'])
class PublicController {

    static defaultAction = "index"

    SpringSecurityService springSecurityService
    PostService postService

    def index() {
        User user = (User) springSecurityService.currentUser
        if (user == null) {
            // Utilisateur non connecté, on affiche la landingPage
            render view: "landingpage"
        } else {
            // Utilisateur connecté, on affiche la home
//            List<Post> helpPosts = postService.getRandomHelpPosts(36)
            List<Post> helpPosts = postService.getMostRecentHelpPosts(36)
//            List<Post> dressingPosts = postService.getRandomDressingPosts(24)

            render view: "home", model: [
                    helpPosts    : helpPosts
            ]
        }
    }
}
