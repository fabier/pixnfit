package pixnista

import grails.transaction.Transactional

@Transactional
class FileExtensionService {

    FileExtension jpeg() {
        FileExtension.findByExtension("jpeg")
    }

    FileExtension jpg() {
        FileExtension.findByExtension("jpg")
    }

    FileExtension gif() {
        FileExtension.findByExtension("gif")
    }

    FileExtension png() {
        FileExtension.findByExtension("png")
    }
}
