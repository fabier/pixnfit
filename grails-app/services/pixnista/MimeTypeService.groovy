package pixnista

import grails.transaction.Transactional

@Transactional
class MimeTypeService {

    Mimetype jpeg() {
        Mimetype.findByMimetype("image/jpeg")
    }

    Mimetype gif() {
        Mimetype.findByMimetype("image/gif")
    }

    Mimetype png() {
        Mimetype.findByMimetype("image/png")
    }
}
