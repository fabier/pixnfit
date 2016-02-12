package admin

import pixnfit.BaseDomain

class PrivateBetaRegistrationEmail extends BaseDomain {

    String email

    static constraints = {
        email email: true, unique: true
    }

    static mapping = {
        table schema: 'admin'
    }
}
