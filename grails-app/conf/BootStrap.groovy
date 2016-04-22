import grails.converters.JSON
import grails.util.Environment
import org.codehaus.groovy.grails.web.mapping.LinkGenerator
import pixnfit.*

class BootStrap {

    def grailsApplication
    def bootstrapInitialDataService
    LinkGenerator grailsLinkGenerator
    PostService postService
    UserService userService

    def init = { servletContext ->
        // Adds random() method to all domain classes
        addMetaMethods()

        // Register JSON Marshallers
        registerMarshallers()

        // Création des rôles utilisateurs
        if (grailsApplication.config.role) {
            def adminRole = Role.findByAuthority(grailsApplication.config.role.admin) ?: new Role(authority: grailsApplication.config.role.admin).save(failOnError: true, flush: true)
            log.info("ADMIN Role : ${adminRole.authority}")

            def userRole = Role.findByAuthority(grailsApplication.config.role.user) ?: new Role(authority: grailsApplication.config.role.user).save(failOnError: true, flush: true)
            log.info("USER Role : ${userRole.authority}")

            if (grailsApplication.config.admin
                    && grailsApplication.config.admin.username
                    && grailsApplication.config.admin.password
                    && grailsApplication.config.admin.email) {
                def adminUser = User.findByEmail(grailsApplication.config.admin.email) ?:
                        new User(
                                username: grailsApplication.config.admin.username,
                                password: grailsApplication.config.admin.password,
                                email: grailsApplication.config.admin.email,
                                enabled: true
                        ).save(failOnError: true, flush: true)

                log.info("Admin User : ${adminUser.username}")
                if (!UserRole.findByUserAndRole(adminUser, adminRole)) {
                    UserRole.create adminUser, adminRole, true
                }
                if (!UserRole.findByUserAndRole(adminUser, userRole)) {
                    UserRole.create adminUser, userRole, true
                }

                // On dit que le créateur des rôles ROLE_ADMIN et ROLE_USER est "ADMIN"
                adminRole.setCreator(adminUser)
                adminRole.save()

                userRole.setCreator(adminUser)
                userRole.save()
            }
        }

        if (Environment.current == Environment.DEVELOPMENT || Environment.current == Environment.TEST) {
            bootstrapInitialDataService.initStaticData()
            // bootstrapInitialDataService.initRandomData(true)
        }
    }

    /**
     * Adds random() method to all domain classes
     */
    def addMetaMethods() {
        grailsApplication.domainClasses.each { dc ->
            dc.metaClass.'static'.random << {
                delegate.withCriteria(uniqueResult: true) {
                    maxResults 1
                    sqlRestriction " 1=1 order by random()"
                }
            }
        }
    }

    def registerMarshallers() {
        [BodyType, FashionStyle, FileExtension, Gender, ImageType, Mimetype, PostType, State, Visibility, VoteReason].each {
            JSON.registerObjectMarshaller(it) {
                return [
                        id         : it.id,
                        name       : it.name,
                        description: it.description,
                        dateCreated: it.dateCreated
                ]
            }
        }
        JSON.registerObjectMarshaller(Country) {
            Country country = it
            return [
                    id          : country.id,
                    name        : country.name,
                    description : country.description,
                    nativeName  : country.nativeName,
                    isoCode31661: country.isoCode31661,
                    dateCreated : country.dateCreated
            ]
        }
        JSON.registerObjectMarshaller(Language) {
            Language language = it
            return [
                    id         : language.id,
                    name       : language.name,
                    description: language.description,
                    nativeName : language.nativeName,
                    dateCreated: language.dateCreated
            ]
        }
        JSON.registerObjectMarshaller(Image) {
            Image image = it
            User creator = image.creator
            Image creatorImage = creator?.image
            return [
                    id         : image.id,
                    name       : image.name,
                    description: image.description,
                    imageUrl   : grailsLinkGenerator.link(controller: "image", action: "show", id: image.id, params: [width: 128, height: 128], absolute: true),
                    creator    : [
                            id      : creator.id,
                            username: creator.username,
                            image   : creatorImage ? [
                                    id      : creatorImage.id,
                                    imageUrl: grailsLinkGenerator.link(controller: "image", action: "show", id: creatorImage.id, params: [width: 128, height: 128], absolute: true)
                            ] : null
                    ],
                    dateCreated: image.dateCreated
            ]
        }
        JSON.registerObjectMarshaller(ImageData) {
            ImageData imageData = it
            ImageType imageType = imageData.imageType
            return [
                    id         : imageData.id,
                    filename   : imageData.filename,
                    width      : imageData.width,
                    height     : imageData.height,
                    imageType  : imageType ? [
                            id  : imageType.id,
                            name: imageType.name,
                    ] : null,
                    md5        : imageData.md5,
                    dateCreated: imageData.dateCreated
            ]
        }
        JSON.registerObjectMarshaller(Message) {
            Message message = it
            User creator = message.creator
            Image creatorImage = creator?.image
            User recipient = message.recipient
            Image recipientImage = recipient?.image
            return [
                    id         : message.id,
                    name       : message.name,
                    description: message.description,
                    creator    : [
                            id      : creator.id,
                            username: creator.username,
                            image   : creatorImage ? [
                                    id      : creatorImage.id,
                                    imageUrl: grailsLinkGenerator.link(controller: "image", action: "show", id: creatorImage.id, params: [width: 128, height: 128], absolute: true)
                            ] : null
                    ],
                    recipient  : [
                            id      : recipient.id,
                            username: recipient.username,
                            image   : recipientImage ? [
                                    id      : recipientImage.id,
                                    imageUrl: grailsLinkGenerator.link(controller: "image", action: "show", id: recipientImage.id, params: [width: 128, height: 128], absolute: true)
                            ] : null
                    ],
                    dateCreated: message.dateCreated
            ]
        }
        JSON.registerObjectMarshaller(Post) {
            Post post = it
            User creator = post.creator
            Image creatorImage = creator?.image
            List<Image> images = post.images
            def imagesAsMapArray = images.inject([]) { array, entry ->
                array.add([
                        id      : entry.id,
                        imageUrl: grailsLinkGenerator.link(controller: "image", action: "show", id: entry.id, params: [width: 128, height: 128], absolute: true)
                ])
                return array
            }
            PostType postType = post.postType
            Visibility visibility = post.visibility
            State state = post.state
            return [
                    id         : post.id,
                    name       : post.name,
                    description: post.description,
                    creator    : [
                            id      : creator.id,
                            username: creator.username,
                            image   : creatorImage ? [
                                    id      : creatorImage.id,
                                    imageUrl: grailsLinkGenerator.link(controller: "image", action: "show", id: creatorImage.id, params: [width: 128, height: 128], absolute: true)
                            ] : null
                    ],
                    images     : imagesAsMapArray,
                    postType   : postType ? [
                            id  : postType.id,
                            name: postType.name,
                    ] : null,
                    visibility : visibility ? [
                            id  : visibility.id,
                            name: visibility.name,
                    ] : null,
                    state      : state ? [
                            id  : state.id,
                            name: state.name,
                    ] : null,
                    dateCreated: post.dateCreated
            ]
        }
        JSON.registerObjectMarshaller(PostComment) {
            PostComment postComment = it
            Post post = postComment.post
            User creator = postComment.creator
            Image creatorImage = creator?.image
            return [
                    id         : postComment.id,
                    name       : postComment.name,
                    description: postComment.description,
                    post       : post ? [
                            id  : post.id,
                            name: post.name
                    ] : null,
                    creator    : [
                            id      : creator.id,
                            username: creator.username,
                            image   : creatorImage ? [
                                    id      : creatorImage.id,
                                    imageUrl: grailsLinkGenerator.link(controller: "image", action: "show", id: creatorImage.id, params: [width: 128, height: 128], absolute: true)
                            ] : null
                    ],
                    dateCreated: postComment.dateCreated
            ]
        }
        JSON.registerObjectMarshaller(PostCommentVote) {
            PostCommentVote postCommentVote = it
            PostComment postComment = postCommentVote.postComment
            User creator = postCommentVote.creator
            Image creatorImage = creator?.image
            return [
                    id         : postCommentVote.id,
                    vote       : postCommentVote.vote,
                    postComment: postComment ? [
                            id         : postComment.id,
                            name       : postComment.name,
                            description: postComment.description
                    ] : null,
                    creator    : [
                            id      : creator.id,
                            username: creator.username,
                            image   : creatorImage ? [
                                    id      : creatorImage.id,
                                    imageUrl: grailsLinkGenerator.link(controller: "image", action: "show", id: creatorImage.id, params: [width: 128, height: 128], absolute: true)
                            ] : null
                    ],
                    dateCreated: postCommentVote.dateCreated
            ]
        }
        JSON.registerObjectMarshaller(PostVote) {
            PostVote postVote = it
            Post post = postVote.post
            VoteReason voteReason = postVote.voteReason
            User creator = postVote.creator
            Image creatorImage = creator?.image
            return [
                    id         : postVote.id,
                    vote       : postVote.vote,
                    voteReason : voteReason ? [
                            id  : voteReason.id,
                            name: voteReason.name
                    ] : null,
                    post       : [
                            id  : post.id,
                            name: post.name
                    ],
                    creator    : [
                            id      : creator.id,
                            username: creator.username,
                            image   : creatorImage ? [
                                    id      : creatorImage.id,
                                    imageUrl: grailsLinkGenerator.link(controller: "image", action: "show", id: creatorImage.id, params: [width: 128, height: 128], absolute: true)
                            ] : null
                    ],
                    dateCreated: postVote.dateCreated
            ]
        }
        JSON.registerObjectMarshaller(User) {
            User user = it
            BodyType bodyType = user.bodyType
            Image image = user.image
            Gender gender = user.gender
            Country country = user.country
            Language language = user.language
            Visibility visibility = user.visibility
            Integer points = user.points

            Set<UserFollow> followers = userService.getFollowers(user)
            Set<UserFollow> followeds = userService.getUsersFollowedBy(user)
            List<Post> posts = postService.getPosts(user)
            Set<FashionStyle> fashionStyles = user.getFashionStylesAsFashionStyle()

            return [
                    id            : user.id,
                    username      : user.username,
                    description   : user.description,
                    bodyType      : bodyType ? [
                            id  : bodyType.id,
                            name: bodyType.name,
                    ] : null,
                    gender        : gender ? [
                            id  : gender.id,
                            name: gender.name,
                    ] : null,
                    birthdate     : user.birthdate,
                    height        : user.height,
                    weight        : user.weight,
                    image         : image ? [
                            id      : image.id,
                            imageUrl: grailsLinkGenerator.link(controller: "image", action: "show", id: image.id, params: [width: 128, height: 128], absolute: true)
                    ] : null,
                    country       : country ? [
                            id  : country.id,
                            name: country.name
                    ] : null,
                    language      : language ? [
                            id  : language.id,
                            name: language.name
                    ] : null,
                    fashionStyles : fashionStyles ? fashionStyles.collect {
                        [id: it.id, name: it.name]
                    } : null,
                    visibility    : visibility ? [
                            id  : visibility.id,
                            name: visibility.name,
                    ] : null,
                    points        : points ?: 0,
                    postCount     : posts ? posts.size() : 0,
                    followersCount: followers ? followers.size() : 0,
                    followedCount : followeds ? followeds.size() : 0,
                    dateCreated   : it.dateCreated
            ]
        }
        JSON.registerObjectMarshaller(UserFavoritePost) {
            UserFavoritePost userFavoritePost = it
            Post post = userFavoritePost.post
            User user = userFavoritePost.user
            return [
                    post: post ? [
                            id  : post.id,
                            name: post.name
                    ] : null,
                    user: user ? [
                            id      : user.id,
                            username: user.username
                    ] : null
            ]
        }
    }

    def destroy = {
    }
}
