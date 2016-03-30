package pixnfit

/**
 * http://stackoverflow.com/questions/17653567/how-to-implement-self-referencing-relationships-in-grails
 */
class UserFollow extends BaseDomain {

    static belongsTo = [followedUser: User, followingUser: User]

    static constraints = {
        followedUser unique: ['followingUser'], validator: { val, obj ->
            // On ne peut pas se suivre soi même !
            val != obj.followingUser
        }
    }

    static mapping = {
        id composite: ['followedUser', 'followingUser']
        version false
    }
}
