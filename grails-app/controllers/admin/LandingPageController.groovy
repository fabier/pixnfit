package admin

import org.springframework.security.access.annotation.Secured

@Secured("permitAll")
class LandingPageController {

    def submitEmail() {
        String email = params.email
        LandingPageEmail landingPageEmail = new LandingPageEmail(email: email)
        if (landingPageEmail.validate()) {
            landingPageEmail.save()
            flash.message = "Your email has been successfully submitted.<br/>We'll catch up to you soon !"
            redirect controller: "public", action: "index"
        } else {
            def emailError = landingPageEmail.errors.getFieldError("email")
            if (email == null) {
                flash.error = "Please provide an email address."
                redirect controller: "public", action: "index", params: params
            } else if (LandingPageEmail.findByEmail(email) != null) {
                flash.message = "Your email has been successfully submitted (again).<br/>We'll catch up to you soon !"
                redirect controller: "public", action: "index"
            } else {
                flash.error = "Please provide a valid email address."
                redirect controller: "public", action: "index", params: params
            }
        }
    }
}
