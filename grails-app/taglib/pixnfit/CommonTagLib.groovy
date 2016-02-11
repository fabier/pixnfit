package pixnfit

class CommonTagLib {
    static defaultEncodeAs = [taglib: 'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

    def springSecurityService
    def messageSource

    def renderError = { attrs, body ->
        assert attrs.bean
        assert attrs.field

        def bean = attrs.bean
        def field = attrs.field

        if (bean.hasErrors()) {
            def error = bean.errors.getFieldError(field)
            out << messageSource.getMessage(error, (Locale) null)
        }
    }

    /**
     * Réécriture de loggedInUserInfo de springSecurity, car les informations retournées lorsqu'on
     * demande "username" retourne l'email.
     */
    def loggedInUserInfo = { attrs, body ->
        if (springSecurityService.isLoggedIn()) {
            out << springSecurityService.currentUser."${attrs.field ?: "username"}"
        } else {
            out << ""
        }
    }
}
