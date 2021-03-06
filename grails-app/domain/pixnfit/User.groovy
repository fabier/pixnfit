package pixnfit

import java.sql.Date as SQLDate

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
    SQLDate birthdate

    /**
     * height (in cm)
     */
    Integer height

    /**
     * Weight (in kg)
     */
    Integer weight

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

    /**
     * Points earned by this user
     */
    Integer points

    /**
     * User's visibility
     */
    Visibility visibility

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
            // Fashion styles of this user
            fashionStyles    : UserFashionStyle,
            // Incoming private messages
            incomingMessages : Message,
            // Messages sent
            outgoingMessages : Message,
            // Users following this user
//            followingUsers   : UserFollow,
            // Users that this user follows
//            followedUsers    : UserFollow,
            // Users that blacklisted this user
            blacklistingUsers: UserBlacklist,
            // Users that this user blacklisted
            blacklistedUsers : UserBlacklist,
            // List of posts favorited by this user
            favoritePosts    : UserFavoritePost
    ]

    static fetchMode = [
            fashionStyles: 'eager'
    ]

    static mappedBy = [
            // Messages WHERE this user is the recipient
            fashionStyles    : 'user',
            // Messages WHERE this user is the recipient
            incomingMessages : 'recipient',
            // Messages WHERE this user is the creator
            outgoingMessages : 'creator',
            // UserFollows WHERE this user is the followed User
//            followingUsers   : 'followedUser',
            // UserFollow WHERE this user is the following User
//            followedUsers    : 'followingUser',
            // UserBlacklist WHERE this user is the blacklistedUser User
            blacklistingUsers: 'blacklistedUser',
            // UserBlacklist WHERE this user is the blacklistingUser User
            blacklistedUsers : 'blacklistingUser',
            // UserFavoritePost WHERE this user has favorited one or more posts
            favoritePosts    : 'user'
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
        points nullable: true
        visibility nullable: true
    }

    static mapping = {
        table '`user`'
        password column: '`password`'
        description type: 'text'
    }

    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this).collect { it.role }
    }

    def addToBlacklistedUsers(User user) {
        this.addToBlacklistedUsers(
                new UserBlacklist(blacklistingUser: this, blacklistedUser: user)
        )
    }

    def removeFromBlacklistedUsers(User user, boolean flush = false) {
        def blacklistedUser = UserBlacklist.findByBlacklistingUserAndBlacklistedUser(this, user)
        if (blacklistedUser != null) {
            this.removeFromBlacklistedUsers(blacklistedUser)
            blacklistedUser.delete(flush: flush)
        }
    }

    def removeFromFashionStyle(FashionStyle fashionStyle, boolean flush = false) {
        def userFashionStyle = UserFashionStyle.findByUserAndFashionStyle(this, fashionStyle)
        if (userFashionStyle != null) {
            this.removeFromFashionStyle(userFashionStyle)
            userFashionStyle.delete(flush: flush)
        }
    }

    def addToFashionStyle(FashionStyle fashionStyle) {
        this.addToFashionStyles(
                new UserFashionStyle(user: this, fashionStyle: fashionStyle)
        )
    }

    Set<FashionStyle> getFashionStylesAsFashionStyle() {
        this.fashionStyles*.fashionStyle
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
