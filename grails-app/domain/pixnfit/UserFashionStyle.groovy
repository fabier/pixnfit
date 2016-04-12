package pixnfit

import org.apache.commons.lang.builder.HashCodeBuilder

class UserFashionStyle extends BaseDomain {

    static belongsTo = [user: User, fashionStyle: FashionStyle]

    static constraints = {
        user unique: ['fashionStyle']
    }

    static mapping = {
        id composite: ['user', 'fashionStyle']
        version false
    }

    boolean equals(other) {
        if (!(other instanceof UserFashionStyle)) {
            return false
        }

        other.user?.id == user?.id &&
                other.fashionStyle?.id == fashionStyle?.id
    }

    @Override
    int hashCode() {
        def builder = new HashCodeBuilder()
        if (user) builder.append(user.id)
        if (fashionStyle) builder.append(fashionStyle.id)
        return builder.toHashCode()
    }
}
