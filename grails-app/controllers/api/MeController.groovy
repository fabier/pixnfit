package api

import grails.plugin.springsecurity.SpringSecurityService
import org.json.JSONArray
import org.springframework.security.access.annotation.Secured
import pixnfit.FashionStyle
import pixnfit.User

@Secured("hasRole('ROLE_USER')")
class MeController {

    static responseFormats = ['json']
    SpringSecurityService springSecurityService

    // Get user related information
    def me() {
        User user = (User) springSecurityService.currentUser
        respond user
    }

    def fashionStyles() {
        User user = (User) springSecurityService.currentUser
        respond user.fashionStyles.toArray()
    }

    def addFashionStyle() {
        def json = request.JSON
        User user = (User) springSecurityService.currentUser
        JSONArray fashionStyleIds = json.fashionStyleIds
        if (fashionStyleIds != null) {
            fashionStyleIds.each {
                user.addToFashionStyle(FashionStyle.get(it.id))
            }
        }
        respond user.fashionStyles.toArray()
    }

    def removeFashionStyle() {
        def json = request.JSON
        User user = (User) springSecurityService.currentUser
        JSONArray fashionStyleIds = json.fashionStyleIds
        if (fashionStyleIds != null) {
            fashionStyleIds.each {
                user.removeFromFashionStyle(FashionStyle.get(it.id))
            }
        }
        respond user.fashionStyles.toArray()
    }
}
