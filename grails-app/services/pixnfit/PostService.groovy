package pixnfit

import grails.transaction.Transactional

@Transactional
class PostService {

    PostTypeService postTypeService

    List<Post> mostRecentPosts(int count = 10) {
        Post.list([max: count, sort: "dateCreated", order: "desc"])
    }

    List<Post> mostRecentHelpPosts(int count = 10) {
        Post.findAll(sort: "dateCreated", order: "desc", max: count) {
            eq "postType", postTypeService.help()
        }
    }

    List<Post> mostRecentDressingPosts(int count = 10) {
        Post.findAll(sort: "dateCreated", order: "desc", max: count) {
            eq "postType", postTypeService.dressing()
        }
    }

    List<Post> getRandomHelpPosts(int count = 10) {
        Post.withCriteria {
            eq "postType", postTypeService.help()
            maxResults count
            sqlRestriction " 1=1 ORDER BY random()"
        }
    }

    List<Post> getRandomDressingPosts(int count = 10) {
        Post.withCriteria {
            eq "postType", postTypeService.dressing()
            maxResults count
            sqlRestriction " 1=1 ORDER BY random()"
        }
    }
}
