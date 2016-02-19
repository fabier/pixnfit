package pixnfit

import org.springframework.security.access.annotation.Secured

import java.awt.image.BufferedImage

@Secured("permitAll")
class ImageController {

    ImageService imageService

    private static final boolean ENABLE_IMAGE_CACHE = true
    private static final Map<String, Object> IMAGE_CACHE = Collections.synchronizedMap(new LRUCache<>(100))

    private static final MAX_WIDTH = 1024;
    private static final MAX_HEIGHT = 1024;

    def show(long id) {
        int width = params.width ? Math.max(0, Math.min(MAX_WIDTH, Integer.parseInt(params.width))) : 0
        int height = params.height ? Math.max(0, Math.min(MAX_HEIGHT, Integer.parseInt(params.height))) : 0

        String key = "${id}|${width}|${height}"

        if (ENABLE_IMAGE_CACHE) {
            if (IMAGE_CACHE.containsKey(key)) {
                render file: IMAGE_CACHE.get(key), contentType: "image/jpeg"
                return
            }
        }

        Image image = Image.get(id)
        if (image != null) {
            ImageData imageData = image.imageData
            if (imageData != null) {
                byte[] data = imageData.data
                if (data != null) {
                    if (width > 0 && height > 0) {
                        // Rescale image
                        BufferedImage rescaled = imageService.getScaledImageCropCenteredAndKeepOrientationUsingEXIFMetadata(data, width, height)
                        byte[] binaryData = imageService.getDataAsJPEG(rescaled)
                        if (ENABLE_IMAGE_CACHE) {
                            IMAGE_CACHE.put(key, binaryData)
                        }
                        render file: binaryData, contentType: "image/jpeg"
                    } else {
                        // On ne met pas les images fullscreen en cache, ca prend trop de mémoire
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
