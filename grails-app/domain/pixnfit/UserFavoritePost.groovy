package pixnfit

class UserFavoritePost extends BaseDomain {

    static belongsTo = [user: User, post: Post]

    static constraints = {
        user unique: 'post'
    }

    static mapping = {
        id composite: ['user', 'post']
        version false
    }
}
