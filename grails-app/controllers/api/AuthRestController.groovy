package api

import org.springframework.security.access.annotation.Secured

@Secured("hasRole('ROLE_USER')")
class AuthRestController {
    static responseFormats = ['json']

    static allowedMethods = [checkCredentials: 'POST']

    def springSecurityService

    def checkCredentials() {
        respond springSecurityService.currentUser
    }
}
