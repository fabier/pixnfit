package api

import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController
import pixnfit.ImageData

@Secured("hasRole('ROLE_USER')")
class ImageDataRestController extends RestfulController {
    static responseFormats = ['json', 'xml']

    ImageDataRestController() {
        super(ImageData)
    }
}
