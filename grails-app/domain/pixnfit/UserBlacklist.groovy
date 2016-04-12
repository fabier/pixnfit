package pixnfit

import org.apache.commons.lang.builder.HashCodeBuilder

/**
 * http://stackoverflow.com/questions/17653567/how-to-implement-self-referencing-relationships-in-grails
 */
class UserBlacklist extends BaseDomain {

    static belongsTo = [blacklistingUser: User, blacklistedUser: User]

    static constraints = {
        blacklistingUser validator: { val, obj ->
            // On ne peut pas se blacklister soi même !
            val != obj.blacklistedUser
        }
    }

    static mapping = {
        id composite: ['blacklistingUser', 'blacklistedUser']
        version false
    }

    boolean equals(other) {
        if (!(other instanceof UserBlacklist)) {
            return false
        }

        other.blacklistingUser?.id == blacklistingUser?.id &&
                other.blacklistedUser?.id == blacklistedUser?.id
    }

    @Override
    int hashCode() {
        def builder = new HashCodeBuilder()
        if (blacklistingUser) builder.append(blacklistingUser.id)
        if (blacklistedUser) builder.append(blacklistedUser.id)
        return builder.toHashCode()
    }
}
