package pixnfit

import org.hibernate.sql.JoinType
import org.springframework.security.access.annotation.Secured
import util.CalendarService

import java.awt.image.BufferedImage

@Secured("permitAll")
class ImageController {

    ImageService imageService
    CalendarService calendarService

    private static final MAX_WIDTH = 1024;
    private static final MAX_HEIGHT = 1024;

    def show(long id) {
        Image image = Image.get(id)
        if (image == null) {
            // Pas d'image avec cet identifiant
            response.sendError(404)
        } else {
            int width = params.width ? Math.max(0, Math.min(MAX_WIDTH, Integer.parseInt(params.width))) : 0
            int height = params.height ? Math.max(0, Math.min(MAX_HEIGHT, Integer.parseInt(params.height))) : 0

            // On regarde si on n'a pas déjà calculé la miniature pour cette image
            ImageData imageData = ImageData.createCriteria().get {
                createAlias("imageDataCaches", "idc", JoinType.INNER_JOIN)
                eq "idc.image", image
                eq "width", width
                eq "height", height
            }

            if (imageData == null || imageService.hasExpired(imageData.dateCreated, Calendar.DAY_OF_YEAR, 7)) {
                // Si les données n'existent pas encore, ou qu'elles ont expiré
                // On cherche à calculer l'image à partir des données maitres, et on retaille
                ImageData masterData = image.imageData
                if (masterData != null) {
                    byte[] masterDataBytes = masterData.data
                    if (masterDataBytes != null) {
                        byte[] binaryDataBytes = masterDataBytes
                        if (width > 0 && height > 0) {
                            // Rescale image
                            BufferedImage rescaled = imageService.getScaledImageCropCenteredAndKeepOrientationUsingEXIFMetadata(masterDataBytes, width, height)
                            binaryDataBytes = imageService.getDataAsJPEG(rescaled)

                            // Put data in cache
                            ImageData imageDataSmall = new ImageData(
                                    name: imageData.name,
                                    filename: imageData.filename,
                                    data: binaryDataBytes
                            )
                            imageDataSmall.setCreator(imageData.creator)
                            imageDataSmall.updateAutoCalculatedFields()
                            imageDataSmall.save()

                            new ImageDataCache(
                                    image: image,
                                    imageData: imageDataSmall
                            ).save()
                        }
                        // On ne met pas les images fullscreen en cache, ca prend trop de mémoire
                        render file: binaryDataBytes, contentType: imageData?.imageType?.defaultMimeType?.mimetype
                    } else {
                        // Dans tous les autres cas, erreur 404
                        response.sendError(404)
                    }
                } else {
                    // Pas de données maitres associées à cette image
                    response.sendError(404)
                }
            } else {
                render file: imageData?.data, contentType: imageData?.imageType?.defaultMimeType?.mimetype
            }
        }
    }
}
