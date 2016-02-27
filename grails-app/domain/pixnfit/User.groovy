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
            fashionStyles    : FashionStyle,
            incomingMessages : Message,
            outgoingMessages : Message,
            followingUsers   : UserFollow,
            followedUsers    : UserFollow,
            blacklistingUsers: UserBlacklist,
            blacklistedUsers : UserBlacklist,
            favoritePosts    : UserFavoritePost
    ]

    static mappedBy = [
            incomingMessages : 'recipient', // Messages WHERE this user is the recipient
            outgoingMessages : 'creator', // Messages WHERE this user is the creator
            followingUsers   : 'followedUser', // UserFollows WHERE this user is the followed User
            followedUsers    : 'followingUser', // UserFollow WHERE this user is the following User
            blacklistingUsers: 'blacklistedUser', // UserBlacklist WHERE this user is the blacklistedUser User
            blacklistedUsers : 'blacklistingUser', // UserBlacklist WHERE this user is the blacklistingUser User
            favoritePosts    : 'user' // UserFavoritePost WHERE this user has favorited one or more posts
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
     * Adds this user to the 'follow list' of user in params
     */
    def addToFollowers(User user) {
        this.addToFollowingUsers(
                new UserFollow(followedUser: this, followingUser: user)
        )
    }

    /**
     * Adds user in params to this user's 'follow list'
     */
    def addToFollowedUsers(User user) {
        this.addToFollowedUsers(
                new UserFollow(followedUser: user, followingUser: this)
        )
    }

    def removeFromFollowers(User user) {
        def follower = UserFollow.findByFollowedUserAndFollowingUser(this, user)
        if (follower != null) {
            this.removeFromFollowingUsers(follower)
            follower.delete()
        }
    }

    def addToBlacklistedUsers(User user) {
        this.addToBlacklistedUsers(
                new UserBlacklist(blacklistingUser: this, blacklistedUser: user)
        )
    }

    def removeFromBlacklistedUsers(User user) {
        def blacklistedUser = UserBlacklist.findByBlacklistingUserAndBlacklistedUser(this, user)
        if (blacklistedUser != null) {
            this.removeFromBlacklistedUsers(blacklistedUser)
            blacklistedUser.delete()
        }
    }

    /**
     * Gets users following this user
     */
    Set<User> getFollowersAsUserSet() {
        this.followingUsers*.followingUser
    }

    /**
     * Gets users this user follows
     */
    Set<User> getFollowedUsersAsUserSet() {
        this.followedUsers*.followedUser
    }

    /**
     * Users that blacklisted this user
     * Synonym for blacklistingUsers
     */
    Set<User> getBlacklistersAsUserSet() {
        this.blacklistingUsers*.blacklistingUser
    }

    /**
     * Users blacklisted by this user
     * @return
     */
    Set<User> getBlacklistedUsersAsUserSet() {
        this.blacklistedUsers*.blacklistedUser
    }

    /**
     * Users blacklisting this user
     * @return
     */
    Set<User> getBlacklistingUsersAsUserSet() {
        this.blacklistingUsers*.blacklistingUser
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
