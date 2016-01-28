package pixnista

import org.springframework.security.access.annotation.Secured

@Secured(['permitAll'])
class PublicController {

    static defaultAction = "index"
    PostService postService

    def index() {
        List<Post> helpPosts = postService.getRandomHelpPosts(12)
        List<Post> dressingPosts = postService.getRandomDressingPosts(24)
        render view: "index", model: [
                helpPosts    : helpPosts,
                dressingPosts: dressingPosts
        ]
    }
}
