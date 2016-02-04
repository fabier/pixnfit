package pixnfit

/**
 * http://stackoverflow.com/questions/17653567/how-to-implement-self-referencing-relationships-in-grails
 */
class UserBlacklist extends BaseDomain {

    static belongsTo = [blacklistingUser: User, blacklistedUser: User]

    static constraints = {
        blacklistingUser unique: 'blacklistedUser', validator: { val, obj ->
            // On ne peut pas se blacklister soi même !
            val != obj.blacklistedUser
        }
    }
}
