import grails.rest.render.json.JsonCollectionRenderer
import grails.rest.render.json.JsonRenderer
import grails.rest.render.xml.XmlCollectionRenderer
import grails.rest.render.xml.XmlRenderer
import pixnfit.*

// This file helps to define which fields are to be returned by the RestFul API webservice

def excludeClass = ['class']
def staticDataExcludes = ['class', 'creator', 'dateCreated', 'lastUpdated']
def staticDataIncludes = ['id', 'name', 'description']

def fashionStyleExcludes = ['class', 'creator', 'dateCreated', 'lastUpdated', 'users']
def fileExtensionExcludes = ['class', 'creator', 'dateCreated', 'lastUpdated', 'imageType']
def mimetypeExcludes = ['class', 'creator', 'dateCreated', 'lastUpdated', 'imageType']

def imageExcludes = excludeClass
def imageDataExcludes = excludeClass
def messageExcludes = excludeClass
def postCommentExcludes = excludeClass
def postCommentVoteExcludes = excludeClass
def postVoteExcludes = excludeClass
def postExcludes = excludeClass
def roleExcludes = excludeClass
def userExcludes = excludeClass

beans = {
    // --------------------------------
    // -- Image
    // --------------------------------
    imageXmlRenderer(XmlRenderer, Image) {
        excludes = imageExcludes
    }
    imageJsonRenderer(JsonRenderer, Image) {
        excludes = imageExcludes
    }
    imageXmlCollectionRenderer(XmlCollectionRenderer, Image) {
        excludes = imageExcludes
    }
    imageJsonCollectionRenderer(JsonCollectionRenderer, Image) {
        excludes = imageExcludes
    }

    // --------------------------------
    // -- ImageData
    // --------------------------------
    imageDataXmlRenderer(XmlRenderer, ImageData) {
        excludes = imageDataExcludes
    }
    imageDataJsonRenderer(JsonRenderer, ImageData) {
        excludes = imageDataExcludes
    }
    imageDataXmlCollectionRenderer(XmlCollectionRenderer, ImageData) {
        excludes = imageDataExcludes
    }
    imageDataJsonCollectionRenderer(JsonCollectionRenderer, ImageData) {
        excludes = imageDataExcludes
    }

    // --------------------------------
    // -- Message
    // --------------------------------
    messageXmlRenderer(XmlRenderer, Message) {
        excludes = messageExcludes
    }
    messageJsonRenderer(JsonRenderer, Message) {
        excludes = messageExcludes
    }
    messageXmlCollectionRenderer(XmlCollectionRenderer, Message) {
        excludes = messageExcludes
    }
    messageJsonCollectionRenderer(JsonCollectionRenderer, Message) {
        excludes = messageExcludes
    }

    // --------------------------------
    // -- Post
    // --------------------------------
    postXmlRenderer(XmlRenderer, Post) {
        excludes = postExcludes
    }
    postJsonRenderer(JsonRenderer, Post) {
        excludes = postExcludes
    }
    postXmlCollectionRenderer(XmlCollectionRenderer, Post) {
        excludes = postExcludes
    }
    postJsonCollectionRenderer(JsonCollectionRenderer, Post) {
        excludes = postExcludes
    }

    // --------------------------------
    // -- PostComment
    // --------------------------------
    postCommentXmlRenderer(XmlRenderer, PostComment) {
        excludes = postCommentExcludes
    }
    postCommentJsonRenderer(JsonRenderer, PostComment) {
        excludes = postCommentExcludes
    }
    postCommentXmlCollectionRenderer(XmlCollectionRenderer, PostComment) {
        excludes = postCommentExcludes
    }
    postCommentJsonCollectionRenderer(JsonCollectionRenderer, PostComment) {
        excludes = postCommentExcludes
    }

    // --------------------------------
    // -- PostCommentVote
    // --------------------------------
    postCommentVoteXmlRenderer(XmlRenderer, PostCommentVote) {
        excludes = postCommentVoteExcludes
    }
    postCommentVoteJsonRenderer(JsonRenderer, PostCommentVote) {
        excludes = postCommentVoteExcludes
    }
    postCommentVoteXmlCollectionRenderer(XmlCollectionRenderer, PostCommentVote) {
        excludes = postCommentVoteExcludes
    }
    postCommentVoteJsonCollectionRenderer(JsonCollectionRenderer, PostCommentVote) {
        excludes = postCommentVoteExcludes
    }

    // --------------------------------
    // -- PostVote
    // --------------------------------
    postVoteXmlRenderer(XmlRenderer, PostVote) {
        excludes = postVoteExcludes
    }
    postVoteJsonRenderer(JsonRenderer, PostVote) {
        excludes = postVoteExcludes
    }
    postVoteXmlCollectionRenderer(XmlCollectionRenderer, PostVote) {
        excludes = postVoteExcludes
    }
    postVoteJsonCollectionRenderer(JsonCollectionRenderer, PostVote) {
        excludes = postVoteExcludes
    }

    // --------------------------------
    // -- Role
    // --------------------------------
    roleXmlRenderer(XmlRenderer, Role) {
        excludes = roleExcludes
    }
    roleJsonRenderer(JsonRenderer, Role) {
        excludes = roleExcludes
    }
    roleXmlCollectionRenderer(XmlCollectionRenderer, Role) {
        excludes = roleExcludes
    }
    roleJsonCollectionRenderer(JsonCollectionRenderer, Role) {
        excludes = roleExcludes
    }

    // --------------------------------
    // -- User
    // --------------------------------
    userXmlRenderer(XmlRenderer, User) {
        excludes = userExcludes
    }
    userJsonRenderer(JsonRenderer, User) {
        excludes = userExcludes
    }
    userXmlCollectionRenderer(XmlCollectionRenderer, User) {
        excludes = userExcludes
    }
    userJsonCollectionRenderer(JsonCollectionRenderer, User) {
        excludes = userExcludes
    }

    // --------------------------------
    // -- BodyType
    // --------------------------------
    bodyTypeXmlRenderer(XmlRenderer, BodyType) {
        excludes = staticDataExcludes
    }
    bodyTypeJsonRenderer(JsonRenderer, BodyType) {
        excludes = staticDataExcludes
    }
    bodyTypeXmlCollectionRenderer(XmlCollectionRenderer, BodyType) {
        excludes = staticDataExcludes
    }
    bodyTypeJsonCollectionRenderer(JsonCollectionRenderer, BodyType) {
        excludes = staticDataExcludes
    }

    // --------------------------------
    // -- Country
    // --------------------------------
    countryXmlRenderer(XmlRenderer, Country) {
        excludes = staticDataExcludes
    }
    countryJsonRenderer(JsonRenderer, Country) {
        excludes = staticDataExcludes
    }
    countryXmlCollectionRenderer(XmlCollectionRenderer, Country) {
        excludes = staticDataExcludes
    }
    countryJsonCollectionRenderer(JsonCollectionRenderer, Country) {
        excludes = staticDataExcludes
    }

    // --------------------------------
    // -- FashionStyle
    // --------------------------------
    fashionStyleXmlRenderer(XmlRenderer, FashionStyle) {
        excludes = fashionStyleExcludes
    }
    fashionStyleJsonRenderer(JsonRenderer, FashionStyle) {
        excludes = fashionStyleExcludes
    }
    fashionStyleXmlCollectionRenderer(XmlCollectionRenderer, FashionStyle) {
        excludes = fashionStyleExcludes
    }
    fashionStyleJsonCollectionRenderer(JsonCollectionRenderer, FashionStyle) {
        excludes = fashionStyleExcludes
    }

    // --------------------------------
    // -- FileExtension
    // --------------------------------
    fileExtensionXmlRenderer(XmlRenderer, FileExtension) {
        excludes = fileExtensionExcludes
    }
    fileExtensionJsonRenderer(JsonRenderer, FileExtension) {
        excludes = fileExtensionExcludes
    }
    fileExtensionXmlCollectionRenderer(XmlCollectionRenderer, FileExtension) {
        excludes = fileExtensionExcludes
    }
    fileExtensionJsonCollectionRenderer(JsonCollectionRenderer, FileExtension) {
        excludes = fileExtensionExcludes
    }

    // --------------------------------
    // -- Gender
    // --------------------------------
    genderXmlRenderer(XmlRenderer, Gender) {
        excludes = staticDataExcludes
    }
    genderJsonRenderer(JsonRenderer, Gender) {
        excludes = staticDataExcludes
    }
    genderXmlCollectionRenderer(XmlCollectionRenderer, Gender) {
        excludes = staticDataExcludes
    }
    genderJsonCollectionRenderer(JsonCollectionRenderer, Gender) {
        excludes = staticDataExcludes
    }

    // --------------------------------
    // -- ImageType
    // --------------------------------
    imageTypeXmlRenderer(XmlRenderer, ImageType) {
        excludes = staticDataExcludes
    }
    imageTypeJsonRenderer(JsonRenderer, ImageType) {
        excludes = staticDataExcludes
    }
    imageTypeXmlCollectionRenderer(XmlCollectionRenderer, ImageType) {
        excludes = staticDataExcludes
    }
    imageTypeJsonCollectionRenderer(JsonCollectionRenderer, ImageType) {
        excludes = staticDataExcludes
    }

    // --------------------------------
    // -- Language
    // --------------------------------
    languageXmlRenderer(XmlRenderer, Language) {
        excludes = staticDataExcludes
    }
    languageJsonRenderer(JsonRenderer, Language) {
        excludes = staticDataExcludes
    }
    languageXmlCollectionRenderer(XmlCollectionRenderer, Language) {
        excludes = staticDataExcludes
    }
    languageJsonCollectionRenderer(JsonCollectionRenderer, Language) {
        excludes = staticDataExcludes
    }

    // --------------------------------
    // -- Mimetype
    // --------------------------------
    mimetypeXmlRenderer(XmlRenderer, Mimetype) {
        excludes = mimetypeExcludes
    }
    mimetypeJsonRenderer(JsonRenderer, Mimetype) {
        excludes = mimetypeExcludes
    }
    mimetypeXmlCollectionRenderer(XmlCollectionRenderer, Mimetype) {
        excludes = mimetypeExcludes
    }
    mimetypeJsonCollectionRenderer(JsonCollectionRenderer, Mimetype) {
        excludes = mimetypeExcludes
    }

    // --------------------------------
    // -- PostType
    // --------------------------------
    postTypeXmlRenderer(XmlRenderer, PostType) {
        excludes = staticDataExcludes
    }
    postTypeJsonRenderer(JsonRenderer, PostType) {
        excludes = staticDataExcludes
    }
    postTypeXmlCollectionRenderer(XmlCollectionRenderer, PostType) {
        excludes = staticDataExcludes
    }
    postTypeJsonCollectionRenderer(JsonCollectionRenderer, PostType) {
        excludes = staticDataExcludes
    }

    // --------------------------------
    // -- State
    // --------------------------------
    stateXmlRenderer(XmlRenderer, State) {
        excludes = staticDataExcludes
    }
    stateJsonRenderer(JsonRenderer, State) {
        excludes = staticDataExcludes
    }
    stateXmlCollectionRenderer(XmlCollectionRenderer, State) {
        excludes = staticDataExcludes
    }
    stateJsonCollectionRenderer(JsonCollectionRenderer, State) {
        excludes = staticDataExcludes
    }

    // --------------------------------
    // -- Visibility
    // --------------------------------
    visibilityXmlRenderer(XmlRenderer, Visibility) {
        excludes = staticDataExcludes
    }
    visibilityJsonRenderer(JsonRenderer, Visibility) {
        excludes = staticDataExcludes
    }
    visibilityXmlCollectionRenderer(XmlCollectionRenderer, Visibility) {
        excludes = staticDataExcludes
    }
    visibilityJsonCollectionRenderer(JsonCollectionRenderer, Visibility) {
        excludes = staticDataExcludes
    }

    // --------------------------------
    // -- VoteReason
    // --------------------------------
    voteReasonXmlRenderer(XmlRenderer, VoteReason) {
        excludes = staticDataExcludes
    }
    voteReasonJsonRenderer(JsonRenderer, VoteReason) {
        excludes = staticDataExcludes
    }
    voteReasonXmlCollectionRenderer(XmlCollectionRenderer, VoteReason) {
        excludes = staticDataExcludes
    }
    voteReasonJsonCollectionRenderer(JsonCollectionRenderer, VoteReason) {
        excludes = staticDataExcludes
    }
}
