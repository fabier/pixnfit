class UrlMappings {

    static mappings = {

        /**
         * REST API MAPPINGS
         */
//        "/api/bodyTypes"(resources: 'bodyType')
//        "/api/countrys"(resources: 'country')
//        "/api/fashionStyles"(resources: 'fashionStyle')
//        "/api/images"(resources: 'image')
//        "/api/imageTypes"(resources: 'imageType')
//        "/api/languages"(resources: 'language')
//        "/api/messages"(resources: 'message')
//        "/api/posts"(resources: 'post')
//        "/api/postComments"(resources: 'postComment')
//        "/api/postCommentVotes"(resources: 'postCommentVote')
//        "/api/postVotes"(resources: 'postVote')
//        "/api/states"(resources: 'state')
//        "/api/users"(resources: 'user')
//        "/api/visibilitys"(resources: 'visibility')
//        "/api/voteReasons"(resources: 'voteReason')

        /**
         * OTHER CONTROLLERS
         */
        "/$controller/$action?/$id?" {
            constraints {
                // apply constraints here
                id(matches: /\d*/)
            }
        }

        "/"(controller: "public")
        "500"(view: '/error')
    }
}
