package starter

class CommonTagLib {
    static defaultEncodeAs = [taglib: 'html']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]

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
}
