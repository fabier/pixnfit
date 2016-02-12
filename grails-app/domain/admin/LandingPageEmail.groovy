package admin

import pixnfit.BaseDomain

/**
 * Emails registered using landing page form
 */
class LandingPageEmail extends BaseDomain {

    String email

    static constraints = {
        email email: true, unique: true
    }

    static mapping = {
        table schema: "admin"
    }
}
