class UrlMappings {

    static mappings = {

        /**
         * REST API MAPPINGS
         */
        "/api/bodyType"(resources: 'bodyTypeRest')
        "/api/country"(resources: 'countryRest')
        "/api/fashionStyle"(resources: 'fashionStyleRest')
        "/api/image"(resources: 'imageRest')
        "/api/imageData"(resources: 'imageDataRest')
        "/api/imageType"(resources: 'imageTypeRest')
        "/api/language"(resources: 'languageRest')
        "/api/message"(resources: 'messageRest')
        "/api/post"(resources: 'postRest')
        "/api/postComment"(resources: 'postCommentRest')
        "/api/postCommentVote"(resources: 'postCommentVoteRest')
        "/api/postVote"(resources: 'postVoteRest')
        "/api/state"(resources: 'stateRest')
        "/api/user"(resources: 'userRest')
        "/api/visibility"(resources: 'visibilityRest')
        "/api/voteReason"(resources: 'voteReasonRest')

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
