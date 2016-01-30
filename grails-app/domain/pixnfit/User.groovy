package pixnfit

class User {

    transient springSecurityService

    /** #############################
     *  ## Basic user informations ##
     *  ############################# */
    String username
    String password
    String email

    /**
     * User's self-description
     */
    String description

    /** ##################
     *  ## User profile ##
     *  ################## */
    /**
     * User's body type
     */
    BodyType bodyType

    /**
     * Gender (male or female)
     */
    Gender gender

    /**
     * Birthdate
     */
    Date birthdate

    /**
     * height (in cm)
     */
    Integer height

    /**
     * Weight (in kg)
     */
    Float weight

    /**
     * User's avatar
     */
    Image image

    /**
     * User's country
     */
    Country country

    /**
     * User's language
     */
    Language language

    /** ########################
     *  ## Account management ##
     *  ######################## */
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    /** ########################
     *  ## Other informations ##
     *  ######################## */
    Date lastUpdated
    Date dateCreated
    User creator

    static transients = ['springSecurityService']

    static hasMany = [
            fashionStyles   : FashionStyle,
            followedUsers   : User,
            blacklistedUsers: User,
            favoritedPosts  : Post
    ]

    static constraints = {
        username blank: false
        password blank: false
        email email: true, unique: true
        creator nullable: true
        description nullable: true
        bodyType nullable: true
        gender nullable: true
        birthdate nullable: true
        height nullable: true
        weight nullable: true
        image nullable: true
        country nullable: true
        language nullable: true
    }

    static mapping = {
        table '`user`'
        password column: '`password`'
        description type: 'text'
    }

    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this).collect { it.role }
    }

    /**
     * Gets users posts
     */
    List<Post> getPosts() {
        Post.findAllByCreator(this)
    }

    /**
     * Gets User's post votes
     */
    List<PostVote> getPostVotes() {
        PostVote.findAllByCreator(this)
    }

    /**
     * Gets User's post comments
     */
    List<PostComment> getPostComments() {
        PostComment.findAllByCreator(this)
    }

    /**
     * Gets User's post comment votes
     */
    List<PostCommentVote> getPostCommentVotes() {
        PostCommentVote.findAllByCreator(this)
    }

    /**
     * Gets messages sent
     */
    List<Message> getOutgoingMessages() {
        Message.findAllByCreator(this)
    }

    /**
     * Gets messages received
     */
    List<Message> getIncomingMessages() {
        Message.findAllByRecipient(this)
    }

    /**
     * Synonym for getFollowers
     */
    List<User> getFollowingUsers() {
        getFollowers()
    }

    /**
     * Gets followers
     */
    List<User> getFollowers() {
        User.findAll("from User where ? in elements(followedUsers)", [this])
    }

    /**
     * Synonym for getBlacklisters
     */
    List<User> getBlacklistingUsers() {
        getBlacklisters()
    }

    /**
     * Gets users who blacklisted this user
     */
    List<User> getBlacklisters() {
        User.findAll("from User where ? in elements(blacklistedUsers)", [this])
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
    }

    // Delete is forbidden
    def beforeDelete() {
        return false
    }
}
