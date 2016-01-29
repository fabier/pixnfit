package pixnista

import grails.transaction.Transactional

@Transactional
class ImageService {

    DownloadService downloadService
    ImageTypeService imageTypeService

    Image createDefaultLoremIpsumImage(User creator) {
        createLoremIpsumImage(creator, 256, 256, "selfie", "fashion")
    }

    Image createLoremIpsumImage(User creator, int width, int height, String... keywords) {
        String keywordTag = keywords.join(",")
        String filename = "loremflickr_${keywords.join("_")}.jpg"
        String name = "${keywords.join(" ")} (${width}x${height})"
        String url = "http://www.loremflickr.com/$width/$height/${keywordTag}"
        byte[] data = downloadService.download(url)

        ImageData imageData = new ImageData(
                creator: creator,
                name: name,
                data: data
        )

        Image image = new Image(
                creator: creator,
                name: name,
                filename: filename,
                height: height,
                width: width,
                imageData: imageData,
                imageType: imageTypeService.jpeg()
        ).save(failOnError: true)

        return image
    }
}
