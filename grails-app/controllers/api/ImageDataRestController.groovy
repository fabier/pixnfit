package api

import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController
import pixnista.ImageData

@Secured("hasRole('ROLE_USER')")
class ImageDataRestController extends RestfulController {
    static responseFormats = ['json', 'xml']

    ImageDataRestController() {
        super(ImageData)
    }
}
