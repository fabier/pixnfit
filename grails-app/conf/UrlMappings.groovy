class UrlMappings {

    static mappings = {

        /**
         * REST API MAPPINGS
         */
        "/api/v1/bodyType"(resources: 'bodyTypeRest')
        "/api/v1/country"(resources: 'countryRest')
        "/api/v1/fashionStyle"(resources: 'fashionStyleRest')
        "/api/v1/fileExtension"(resources: 'fileExtensionRest')
        "/api/v1/gender"(resources: 'genderRest')
        "/api/v1/image"(resources: 'imageRest')
        "/api/v1/imageData"(resources: 'imageDataRest')
        "/api/v1/imageType"(resources: 'imageTypeRest')
        "/api/v1/language"(resources: 'languageRest')
        "/api/v1/message"(resources: 'messageRest')
        "/api/v1/mimetype"(resources: 'mimetypeRest')
        "/api/v1/post"(resources: 'postRest')
        "/api/v1/postComment"(resources: 'postCommentRest')
        "/api/v1/postCommentVote"(resources: 'postCommentVoteRest')
        "/api/v1/postType"(resources: 'postTypeRest')
        "/api/v1/postVote"(resources: 'postVoteRest')
        "/api/v1/role"(resources: 'roleRest')
        "/api/v1/state"(resources: 'stateRest')
        "/api/v1/user"(resources: 'userRest')
        "/api/v1/visibility"(resources: 'visibilityRest')
        "/api/v1/voteReason"(resources: 'voteReasonRest')

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
