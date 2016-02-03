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
        "/api/v1/bodyType"(resources: "bodyTypeRest", includes: ["index", "show"])
        "/api/v1/country"(resources: "countryRest", includes: ["index", "show"])
        "/api/v1/fashionStyle"(resources: "fashionStyleRest", includes: ["index", "show"])
        "/api/v1/gender"(resources: "genderRest", includes: ["index", "show"])
        "/api/v1/imageType"(resources: "imageTypeRest", includes: ["index", "show"])
        "/api/v1/language"(resources: "languageRest", includes: ["index", "show"])
        "/api/v1/postType"(resources: "postTypeRest", includes: ["index", "show"])
        "/api/v1/state"(resources: "stateRest", includes: ["index", "show"])
        "/api/v1/visibility"(resources: "visibilityRest", includes: ["index", "show"])
        "/api/v1/voteReason"(resources: "voteReasonRest", includes: ["index", "show"])

        // ------------------------
        // -- Dynamic Data (READABLE, INSERTABLE, UPDATABLE, DELETABLE)
        // ------------------------
        // On peut visualiser des images, en créer, et récupérer les données associées
        "/api/v1/image"(resources: "imageRest", includes: ["show", "save", "update", "delete"]) {
            "/data"(controller: "imageRest", action: "data", method: "GET")
        }

        // On peut créer des messages
        "/api/v1/message"(resources: "messageRest", includes: ["show", "save", "update", "delete"])

        // On peut voir les posts,
        // les commentaires sur ce post, poster un nouveau commentaire
        // les votes sur ce post
        "/api/v1/post"(resources: "postRest", includes: ["show", "save", "update", "delete"]) {
            "/comments"(controller: "postRest", action: "comments", method: "GET")
            "/comments"(controller: "postRest", action: "addComment", method: "POST")
            "/votes"(controller: "postRest", action: "votes", method: "GET")
            "/votes"(controller: "postRest", action: "addVote", method: "POST")
        }

        // Liste de posts qui ont besoin d'aide et de votes
        "/api/v1/post/help"(controller: "postRest", action: "help", method: "GET")

        // Sélection de posts qui ont recu de l'aide
        "/api/v1/post/featured"(controller: "postRest", action: "featured", method: "GET")

        // On peut récupérer des informations sur les commentaires
        // et voter pour un commentaire
        "/api/v1/postComment"(resources: "postCommentRest", includes: ["index", "show", "save", "update", "delete"]) {
            "/votes"(controller: "postCommentRest") {
                action = [GET: "votes", POST: "addVote"]
            }
        }

        // On peut récupérer des informations sur un profil utilisateur
        "/api/v1/user"(resources: "userRest", includes: ["show", "save", "update", "delete"]) {
            "/incomingMessages"(controller: "userRest") {
                action = [GET: "incomingMe copyessages"]
            }
            "/outgoingMessages"(controller: "userRest") {
                action = [GET: "outgoingMessages"]
            }
            "/posts"(controller: "userRest") {
                action = [GET: "posts", POST: "addPost"]
            }
            "/followers"(controller: "userRest") {
                action = [GET: "followers", POST: "addFollower"]
            }
            "/following"(controller: "userRest") {
                action = [GET: "following"]
            }
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
