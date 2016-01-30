package api

import grails.rest.RestfulController
import org.springframework.security.access.annotation.Secured
import pixnfit.Image

@Secured("hasRole('ROLE_USER')")
class ImageRestController extends RestfulController {
    static responseFormats = ['json', 'xml']

    ImageRestController(){
        super(Image)
    }
}
