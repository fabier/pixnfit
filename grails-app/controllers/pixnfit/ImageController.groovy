package pixnfit

import org.springframework.security.access.annotation.Secured

import java.awt.image.BufferedImage

@Secured("permitAll")
class ImageController {

    ImageService imageService

    private static final boolean ENABLE_IMAGE_CACHE = true
    private static final Map<String, Object> IMAGE_CACHE = Collections.synchronizedMap(new LRUCache<>(100))

    def show(long id) {
        Image image = Image.get(id)
        Integer width = params.width ? Integer.parseInt(params.width) : null
        Integer height = params.height ? Integer.parseInt(params.height) : null

        String key = "${id}|${width}|${height}"

        if (ENABLE_IMAGE_CACHE) {
            synchronized (IMAGE_CACHE) {
                if (IMAGE_CACHE.containsKey(key)) {
                    render file: IMAGE_CACHE.get(key), contentType: "image/jpeg"
                    return
                }
            }
        }

        if (image != null) {
            ImageData imageData = image.imageData
            if (imageData != null) {
                byte[] data = imageData.data
                if (data != null) {
                    if (width != null && height != null) {
                        // Rescale image
                        BufferedImage rescaled = imageService.getScaledImageCropCenteredAndKeepOrientationUsingEXIFMetadata(data, width, height)
                        byte[] binaryData = imageService.getDataAsJPEG(rescaled)
                        if (ENABLE_IMAGE_CACHE) {
                            IMAGE_CACHE.put(key, binaryData)
                        }
                        render file: binaryData, contentType: "image/jpeg"
                    } else {
                        if (ENABLE_IMAGE_CACHE) {
                            IMAGE_CACHE.put(key, data)
                        }
                        render file: data, contentType: imageData?.imageType?.defaultMimeType?.mimetype
                    }
                } else {
                    // Dans tous les autres cas, erreur 404
                    response.sendError(404)
                }
            } else {
                // Dans tous les autres cas, erreur 404
                response.sendError(404)
            }
        } else {
            // Dans tous les autres cas, erreur 404
            response.sendError(404)
        }
    }
}
