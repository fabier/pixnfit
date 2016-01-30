package api

import grails.rest.RestfulController
import org.springframework.security.access.annotation.Secured
import pixnista.ImageType

@Secured("hasRole('ROLE_USER')")
class ImageTypeRestController extends RestfulController {
    static responseFormats = ['json', 'xml']

    ImageTypeRestController(){
        super(ImageType)
    }
}
