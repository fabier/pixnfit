package pixnfit

import grails.transaction.Transactional

@Transactional
class PostService {

    PostTypeService postTypeService

    List<Post> getMostRecentPosts(int max = 10) {
        Post.list([max: max, sort: "dateCreated", order: "desc"])
    }

    List<Post> getMostRecentHelpPosts(int max = 10) {
        Post.findAll(sort: "dateCreated", order: "desc", max: max) {
            eq "postType", postTypeService.help()
        }
    }

    List<Post> getMostRecentDressingPosts(int max = 10) {
        Post.findAll(sort: "dateCreated", order: "desc", max: max) {
            eq "postType", postTypeService.dressing()
        }
    }

    List<Post> getRandomHelpPosts(int max = 10) {
        Post.withCriteria {
            eq "postType", postTypeService.help()
            maxResults max
            sqlRestriction " 1=1 ORDER BY random()"
        }
    }

    List<Post> getRandomDressingPosts(int max = 10) {
        Post.withCriteria {
            eq "postType", postTypeService.dressing()
            maxResults max
            sqlRestriction " 1=1 ORDER BY random()"
        }
    }

    Post getPreviousPost(Post post) {
        return Post.withCriteria(uniqueResult: true) {
            eq "postType", post.postType
            gt "dateCreated", post.dateCreated
            order("dateCreated", "asc")
            maxResults(1)
        }
    }

    Post getNextPost(Post post) {
        return Post.withCriteria(uniqueResult: true) {
            eq "postType", post.postType
            lt "dateCreated", post.dateCreated
            order("dateCreated", "desc")
            maxResults(1)
        }
    }
}
