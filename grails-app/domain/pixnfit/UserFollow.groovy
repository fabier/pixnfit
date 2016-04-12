package pixnfit

import org.apache.commons.lang.builder.HashCodeBuilder

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

    boolean equals(other) {
        if (!(other instanceof UserFollow)) {
            return false
        }

        other.followedUser?.id == followedUser?.id &&
                other.followingUser?.id == followingUser?.id
    }

    @Override
    int hashCode() {
        def builder = new HashCodeBuilder()
        if (followedUser) builder.append(followedUser.id)
        if (followingUser) builder.append(followingUser.id)
        return builder.toHashCode()
    }
}
