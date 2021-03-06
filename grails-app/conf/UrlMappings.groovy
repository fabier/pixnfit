class UrlMappings {

    static mappings = {

        /**
         * REST API MAPPINGS
         */

        // ------------------------
        // -- Authentication
        // ------------------------
        "/api/v1/auth"(controller: "authRest", action: "checkCredentials", method: "POST")

        // ------------------------
        // -- Static Data (READ ONLY)
        // ------------------------
        "/api/v1/bodyTypes"(resources: "bodyTypeRest", includes: ["index", "show"])
        "/api/v1/countries"(resources: "countryRest", includes: ["index", "show"])
        "/api/v1/fashionStyles"(resources: "fashionStyleRest", includes: ["index", "show"])
        "/api/v1/genders"(resources: "genderRest", includes: ["index", "show"])
        "/api/v1/imageTypes"(resources: "imageTypeRest", includes: ["index", "show"])
        "/api/v1/languages"(resources: "languageRest", includes: ["index", "show"])
        "/api/v1/postTypes"(resources: "postTypeRest", includes: ["index", "show"])
        "/api/v1/states"(resources: "stateRest", includes: ["index", "show"])
        "/api/v1/visibilities"(resources: "visibilityRest", includes: ["index", "show"])
        "/api/v1/voteReasons"(resources: "voteReasonRest", includes: ["index", "show"])

        // ------------------------
        // -- Dynamic Data (READABLE, INSERTABLE, UPDATABLE, DELETABLE)
        // ------------------------
        // On peut visualiser des images, en créer, et récupérer les données associées
        "/api/v1/images"(resources: "imageRest", includes: ["show", "save", "update", "delete"]) {
            "/data"(controller: "imageRest", action: "data", method: "GET")
        }

        // On peut créer des messages
        "/api/v1/messages"(resources: "messageRest", includes: ["show", "save"])

        // On peut voir les posts,
        // les commentaires sur ce post, poster un nouveau commentaire
        // les votes sur ce post
        "/api/v1/posts"(resources: "postRest", includes: ["show", "save", "update", "delete"]) {
            // Gets user related informations
            "/me"(controller: "postRest", action: "me", method: "GET")
            // Adds to favorite
            "/favorite"(controller: "postRest", action: "addToFavorites", method: "POST")
            // Removes from favorite
            "/favorite"(controller: "postRest", action: "removeFromFavorites", method: "DELETE")
            // Get post images
            "/images"(controller: "postRest", action: "addImage", method: "POST")
            // Get comments
            "/comments"(controller: "postRest", action: "comments", method: "GET")
            // Submits a comment
            "/comments"(controller: "postRest", action: "addComment", method: "POST")
            // Gets votes
            "/votes"(controller: "postRest", action: "votes", method: "GET")
            // Submits a vote
            "/votes"(controller: "postRest", action: "addVote", method: "POST")
        }

        // Liste de posts qui ont besoin d'aide et de votes
        "/api/v1/posts/help"(controller: "postRest", action: "help", method: "GET")

        // Sélection de posts qui ont recu de l'aide
        "/api/v1/posts/featured"(controller: "postRest", action: "featured", method: "GET")

        // On peut récupérer des informations sur les commentaires
        // et voter pour un commentaire
        "/api/v1/postComments"(resources: "postCommentRest", includes: ["show"]) {
            "/votes"(controller: "postCommentRest", action: "votes", method: "GET")
            "/votes"(controller: "postCommentRest", action: "addVote", method: "POST")
        }

        // On peut récupérer des informations sur le profil de l'utilisateur connecté
        "/api/v1/me"(controller: "meRest", action: "me", method: "GET")
        "/api/v1/me/fashionStyles"(controller: "meRest", action: "addFashionStyle", method: "POST")
        "/api/v1/me/fashionStyles"(controller: "meRest", action: "removeFashionStyle", method: "DELETE")

        // On peut récupérer des informations sur un profil utilisateur
        "/api/v1/users"(resources: "userRest", includes: ["show", "save", "update"]) {
            "/initImage"(controller: "userRest", action: "initUserImage", method: "POST")
            "/initProfile"(controller: "userRest", action: "initUserProfile", method: "POST")
            "/incomingMessages"(controller: "userRest", action: "incomingMessages", method: "GET")
            "/outgoingMessages"(controller: "userRest", action: "outgoingMessages", method: "GET")
            "/me"(controller: "userRest", action: "me", method: "GET")
            "/posts"(controller: "userRest", action: "posts", method: "GET")
            "/posts/dressing"(controller: "userRest", action: "dressingPosts", method: "GET")
            "/posts/help"(controller: "userRest", action: "helpPosts", method: "GET")
            "/postComments"(controller: "userRest", action: "postComments", method: "GET")
            "/postVotes"(controller: "userRest", action: "postVotes", method: "GET")
            "/followers"(controller: "userRest", action: "followers", method: "GET")
            "/follow"(controller: "userRest", action: "follow", method: "POST")
            "/follow"(controller: "userRest", action: "unfollow", method: "DELETE")
            "/followedUsers"(controller: "userRest", action: "followedUsers", method: "GET")
            "/blacklistedUsers"(controller: "userRest", action: "blacklistedUsers", method: "GET")
            "/blacklistedBy"(controller: "userRest", action: "blacklistedBy", method: "GET")
            "/blacklist"(controller: "userRest", action: "blacklist", method: "POST")
            "/blacklist"(controller: "userRest", action: "unblacklist", method: "DELETE")
            "/image"(controller: "userRest", action: "updateUserImage", method: "POST")
        }

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
        "500"(view: "/error")
    }
}
