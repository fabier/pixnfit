package pixnfit

class UserFashionStyle extends BaseDomain {

    static belongsTo = [user: User, fashionStyle: FashionStyle]

    static constraints = {
        user unique: 'fashionStyle'
    }

    static mapping = {
        id composite: ['user', 'fashionStyle']
        version false
    }
}
