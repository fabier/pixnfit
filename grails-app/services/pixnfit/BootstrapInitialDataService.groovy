package pixnfit

import com.coderberry.faker.FakerService
import grails.transaction.Transactional
import org.apache.commons.lang3.RandomStringUtils

@Transactional
class BootstrapInitialDataService {

    FileExtensionService fileExtensionService
    MimeTypeService mimeTypeService
    FakerService fakerService
    UserService userService
    ImageService imageService
    ImageTypeService imageTypeService
    PostTypeService postTypeService
    StateService stateService
    VisibilityService visibilityService

    /**
     * Initializes static data in database
     */
    def initStaticData() {
        User admin = userService.admin()

        // Language
        log.info "Initializing Languages..."
        if (Language.count() == 0) {
            new Language(name: "French", nativeName: "Français", isoCode6391: "fr", creator: admin).save(failOnError: true)
            new Language(name: "English", nativeName: "English", isoCode6391: "en", creator: admin).save(failOnError: true)
        }
        log.info "Languages OK"

        // Country
        log.info "Initializing Countries..."
        if (Country.count() == 0) {
            new Country(name: "France", nativeName: "France", isoCode31661: "FR", creator: admin).save(failOnError: true)
            new Country(name: "England", nativeName: "England", isoCode31661: "EN", creator: admin).save(failOnError: true)
            new Country(name: "USA", nativeName: "USA", isoCode31661: "US", creator: admin).save(failOnError: true)
        }
        log.info "Countries OK"

        // Visibility
        log.info "Initializing Visibilities..."
        if (Visibility.count() == 0) {
            new Visibility(name: "public", creator: admin).save(failOnError: true)
            new Visibility(name: "followers", creator: admin).save(failOnError: true)
            new Visibility(name: "private", creator: admin).save(failOnError: true)
        }
        log.info "Visibilities OK"

        // BodyType
        log.info "Initializing BodyTypes..."
        if (BodyType.count() == 0) {
            new BodyType(name: "skinny", creator: admin).save(failOnError: true)
            new BodyType(name: "average", creator: admin).save(failOnError: true)
            new BodyType(name: "big", creator: admin).save(failOnError: true)
        }
        log.info "BodyTypes OK"

        // FashionStyle
        log.info "Initializing FashionStyles..."
        if (FashionStyle.count() == 0) {
            new FashionStyle(name: "bohemian", creator: admin).save(failOnError: true)
            new FashionStyle(name: "arty", creator: admin).save(failOnError: true)
            new FashionStyle(name: "chic", creator: admin).save(failOnError: true)
            new FashionStyle(name: "classic", creator: admin).save(failOnError: true)
            new FashionStyle(name: "exotic", creator: admin).save(failOnError: true)
            new FashionStyle(name: "flamboyant", creator: admin).save(failOnError: true)
            new FashionStyle(name: "glamourous", creator: admin).save(failOnError: true)
            new FashionStyle(name: "romantic", creator: admin).save(failOnError: true)
            new FashionStyle(name: "sexy", creator: admin).save(failOnError: true)
            new FashionStyle(name: "sophisticated", creator: admin).save(failOnError: true)
            new FashionStyle(name: "western", creator: admin).save(failOnError: true)
            new FashionStyle(name: "traditional", creator: admin).save(failOnError: true)
            new FashionStyle(name: "preppy", creator: admin).save(failOnError: true)
            new FashionStyle(name: "punk", creator: admin).save(failOnError: true)
            new FashionStyle(name: "tomboy", creator: admin).save(failOnError: true)
            new FashionStyle(name: "rocker", creator: admin).save(failOnError: true)
            new FashionStyle(name: "goth", creator: admin).save(failOnError: true)
        }
        log.info "FashionStyles OK"

        // State
        log.info "Initializing States..."
        if (State.count() == 0) {
            new State(name: "active", creator: admin).save(failOnError: true)
            new State(name: "inactive", creator: admin).save(failOnError: true)
            new State(name: "deleted", creator: admin).save(failOnError: true)
        }
        log.info "States OK"

        // VoteReason
        log.info "Initializing VoteReasons..."
        if (VoteReason.count() == 0) {
            new VoteReason(name: "style", creator: admin).save(failOnError: true)
            new VoteReason(name: "color", creator: admin).save(failOnError: true)
            new VoteReason(name: "size", creator: admin).save(failOnError: true)
            new VoteReason(name: "shape", creator: admin).save(failOnError: true)
        }
        log.info "VoteReasons OK"

        // PostType
        log.info "Initializing PostTypes..."
        if (PostType.count() == 0) {
            new PostType(name: "help", creator: admin).save(failOnError: true)
            new PostType(name: "dressing", creator: admin).save(failOnError: true)
        }
        log.info "PostTypes OK"

        // Gender
        log.info "Initializing Genders..."
        if (Gender.count() == 0) {
            new Gender(name: "male", creator: admin).save(failOnError: true)
            new Gender(name: "female", creator: admin).save(failOnError: true)
        }
        log.info "Genders OK"

        // ImageType
        log.info "Initializing ImageTypes..."
        ImageType jpegImageType, pngImageType, gifImageType
        if (ImageType.count() == 0) {
            jpegImageType = new ImageType(name: "JPEG", creator: admin).save(flush: true)
            pngImageType = new ImageType(name: "PNG", creator: admin).save(flush: true)
            gifImageType = new ImageType(name: "GIF", creator: admin).save(flush: true)
        } else {
            jpegImageType = imageTypeService.jpeg()
            pngImageType = imageTypeService.png()
            gifImageType = imageTypeService.gif()
        }
        log.info "ImageTypes OK"

        // Mimetype
        log.info "Initializing MimeTypes..."
        if (Mimetype.count() == 0) {
            def jpgMimeType = new Mimetype(name: "JPEG", mimetype: "image/jpeg", imageType: jpegImageType, creator: admin).save(failOnError: true)
            jpegImageType.setDefaultMimeType(jpgMimeType)
            jpegImageType.save()

            def pngMimeType = new Mimetype(name: "PNG", mimetype: "image/png", imageType: pngImageType, creator: admin).save(failOnError: true)
            pngImageType.setDefaultMimeType(pngMimeType)
            pngImageType.save()

            def gifMimeType = new Mimetype(name: "GIF", mimetype: "image/gif", imageType: gifImageType, creator: admin).save(failOnError: true)
            gifImageType.setDefaultMimeType(gifMimeType)
            gifImageType.save()
        }
        log.info "MimeTypes OK"

        // FileExtension
        log.info "Initializing FileExtensions..."
        if (FileExtension.count() == 0) {
            def jpgFileExtension = new FileExtension(name: "JPG", extension: "jpg", imageType: jpegImageType, creator: admin).save(failOnError: true)
            jpegImageType.setDefaultFileExtension(jpgFileExtension)
            jpegImageType.save()

            new FileExtension(name: "JPEG", extension: "jpeg", imageType: jpegImageType, creator: admin).save(failOnError: true)

            def gifFileExtension = new FileExtension(name: "PNG", extension: "png", imageType: pngImageType, creator: admin).save(failOnError: true)
            pngImageType.setDefaultFileExtension(gifFileExtension)
            pngImageType.save()

            def pngFileExtension = new FileExtension(name: "GIF", extension: "gif", imageType: gifImageType, creator: admin).save(failOnError: true)
            gifImageType.setDefaultFileExtension(pngFileExtension)
            gifImageType.save()
        }
        log.info "FileExtensions OK"
    }

    /**
     * Deletes all generated random data
     */
    def resetRandomData() {
        log.info "Resetting all random datas ..."

        log.info "Deleting all users except admin user ..."
        // Do not delete admin user...
        User admin = userService.admin()
        User.all.each {
            if (!it.equals(admin)) {
                it.delete()
            }
        }
        log.info "... Users deleted."

        log.info "Deleting all Posts..."
        Post.executeUpdate("delete from Post")
        log.info "... Posts deleted."

        log.info "Deleting all PostComments..."
        PostComment.executeUpdate("delete from PostComment")
        log.info "... PostComments deleted."

        log.info "Deleting all PostVotes..."
        PostVote.executeUpdate("delete from PostVote")
        log.info "... PostVotes deleted."

        log.info "Deleting all PostCommentVotes..."
        PostCommentVote.executeUpdate("delete from PostCommentVote")
        log.info "... PostCommentVotes deleted."

        log.info "Deleting all Images..."
        Image.executeUpdate("delete from Image")
        log.info "... Images deleted."

        log.info "Deleting all ImageDatas..."
        ImageData.executeUpdate("delete from ImageData")
        log.info "... ImageDatas deleted."
    }

    def initRandomData(boolean reset = false, int userCount = 5) {
        if (reset) {
            resetRandomData()
        }

        Random random = new Random()
        User admin = userService.admin()

        log.info("Creating ${userCount} Users. Please wait...");
        if (User.count() < userCount) {
            char[] charset = (('A'..'Z') + ('0'..'9')).join().toCharArray()
            for (int i = 0; i < userCount; i++) {
                String name = fakerService.name()
                new User(
                        username: name,
                        gender: Gender.random(),
                        birthdate: fakerService.veryPastDate(),
                        bodyType: BodyType.random(),
                        country: Country.random(),
                        language: Language.random(),
                        description: fakerService.paragraph(3),
                        height: Math.round(170 + Math.max(Math.min(3.0, new Random().nextGaussian()), -3.0) * 10),
                        weight: Math.round(70 + Math.max(Math.min(2.0, new Random().nextGaussian()), -2.0) * 10),
                        password: RandomStringUtils.random(20, charset),
                        email: fakerService.freeEmail("$i"), // Pour éviter les doublons
                        enabled: false,
                        creator: admin).save(failOnError: true)
            }
        }
        log.info("... ${userCount} Users created.");

        // All users minus the admin user (will not create a post or a comment)
        List<User> allUsers = User.all.minus(admin)

        log.info("Posts being created. Please wait...");
        State activeState = stateService.active()
        Visibility publicVisibility = visibilityService.public()
        for (PostType postType in PostType.all) {
            for (User user : allUsers) {
                int postCountForUser = Math.round(Math.max(0, random.nextGaussian() * 10 + 10))
                for (int i in 0..postCountForUser) {
                    Image loremIpsumImage = imageService.createDefaultLoremIpsumImage(user)
                    new Post(
                            creator: user,
                            name: fakerService.sentence(5),
                            description: fakerService.paragraph(1),
                            postType: postType,
                            state: activeState,
                            visibility: publicVisibility
                    ).addToImages(loremIpsumImage).save()
                }
            }
        }
        log.info("... ${Post.count()} Posts created.");

        log.info("PostComments and PostVotes being created. Please wait...");
        for (Post post in Post.all) {
            int commentCountForPost = Math.round(Math.max(0, random.nextGaussian() * 10 + 30))
            for (int i in 0..commentCountForPost) {
                User userCommenting = allUsers.get(random.nextInt(allUsers.size()))
                new PostComment(
                        creator: userCommenting,
                        description: fakerService.paragraph(2),
                        post: post
                ).save(failOnError: true)
            }

            int voteCountForPost = Math.round(Math.max(0, random.nextGaussian() * 10 + 30))
            Collections.shuffle(allUsers)
            List<User> voters = allUsers.subList(0, Math.min(voteCountForPost, allUsers.size()))
            for (User voter : voters) {
                boolean vote = random.nextGaussian() > -1
                VoteReason voteReason = vote ? null : VoteReason.random()
                new PostVote(
                        post: post,
                        creator: voter,
                        vote: vote,
                        voteReason: voteReason
                ).save(failOnError: true)
            }
        }
        log.info("... ${PostComment.count()} PostComments and ${PostVote.count()} PostVotes created.");

        log.info("PostCommentVotes being created. Please wait...");
        for (PostComment postComment : PostComment.all) {
            int voteCountForPostComment = Math.round(Math.max(0, random.nextGaussian() * 10 - 10))
            Collections.shuffle(allUsers)
            List<User> voters = allUsers.subList(0, Math.min(voteCountForPostComment, allUsers.size()))
            for (User voter : voters) {
                boolean vote = random.nextGaussian() > -10
                new PostCommentVote(
                        postComment: postComment,
                        creator: voter,
                        vote: vote
                ).save(failOnError: true)
            }
        }
        log.info("... ${PostCommentVote.count()} PostCommentVotes created.");
    }
}
