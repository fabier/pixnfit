package admin

import org.springframework.security.access.annotation.Secured

@Secured("permitAll")
class LandingPageController {

    def mailService

    def submitEmail() {
        String email = params.email
        LandingPageEmail landingPageEmail = new LandingPageEmail(email: email)
        if (landingPageEmail.validate()) {
            landingPageEmail.save()

            // Envoi de mail à l'utilisateur pour lui assurer qu'on a bien enregistré son adresse
            mailService.sendMail {
                to params.email
                from "noreply@pixnfit.com"
                subject "PixnFit - You are on the waiting list !"
                html "Hi !<br/>" +
                        "<br/>" +
                        "You’ve been added to the <b>PixnFit waiting list</b>.<br/>" +
                        "<br/>" +
                        "<b>Thank you for your subscription.</b><br/>" +
                        "Pixnfit is on an early stage, we are working on the app to provide you amazing functionalities.<br/>" +
                        "You will be notified by email soon when the app is ready, to get an early access.<br/>" +
                        "<br/>" +
                        "Pixnfit Team.<br/>" +
                        "<br/>" +
                        "<i>Please do not respond to this automatic email.</i>"
            }

            // Envoi de mail à l'administration pour avoir des nouvelles fraiches de la saisie de nouveaux emails !
            mailService.sendMail {
                to "contact@pixnfit.com"
                from "noreply@pixnfit.com"
                subject "PixnFit - A new user submitted its email !"
                html "A new user has submitted its email on PixnFit !" +
                        "<br/>" +
                        "Email : <b>$params.email</b><br/>" +
                        "Total emails registered : <b>${LandingPageEmail.count()}</b><br/>" +
                        "<br/>" +
                        "Pixnfit Team.<br/>" +
                        "<br/>" +
                        "<i>Please do not respond to this automatic email.</i>"
            }

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
