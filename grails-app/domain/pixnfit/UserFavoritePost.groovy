package pixnfit

import org.apache.commons.lang.builder.HashCodeBuilder

class UserFavoritePost extends BaseDomain {

    static belongsTo = [user: User, post: Post]

    static constraints = {
        user unique: ['post']
    }

    static mapping = {
        id composite: ['user', 'post']
        version false
    }

    boolean equals(other) {
        if (!(other instanceof UserFavoritePost)) {
            return false
        }

        other.user?.id == user?.id &&
                other.post?.id == post?.id
    }

    @Override
    int hashCode() {
        def builder = new HashCodeBuilder()
        if (user) builder.append(user.id)
        if (post) builder.append(post.id)
        return builder.toHashCode()
    }
}
