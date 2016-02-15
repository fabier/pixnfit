<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name='layout' content='main'/>
    <title>${post.name}</title>
</head>

<body>

<g:set var="imageId" value="${post.images?.first()?.id}"/>

<div class="container">

    <div class="row">
        <div class="col-md-3">
            <g:link controller="public" action="index" class="btn btn-link">
                <i class="glyphicon glyphicon-chevron-left"></i>
                Back to gallery
            </g:link>
        </div>

        <div class="col-md-6">
            <h3>${post.name}</h3>
        </div>

        <div class="col-md-3">
        </div>
    </div>

    <div class="row vcenter">
        <div class="col-md-3">
            <g:if test="${previousPost}">
                <g:link controller="post" action="show" id="${previousPost.id}">
                    <asset:image src="post/arrow-blue-left.png" class="right"/>
                </g:link>
            </g:if>
        </div>

        <div class="col-md-6">
            <img src="${createLink(controller: "image", action: "show", id: imageId, params: [width: 512, height: 512])}"
                 style="width:100%;"/>
        </div>

        <div class="col-md-3">
            <g:if test="${nextPost}">
                <g:link controller="post" action="show" id="${nextPost.id}">
                    <asset:image src="post/arrow-blue-right.png" class="left"/>
                </g:link>
            </g:if>
        </div>
    </div>

    <div class="row">

        <div class="col-md-1 col-xs-2 text-center"></div>

        <div class="col-md-1 col-xs-2 text-center">
            <i class="glyphicon glyphicon-heart glyphicon-large"></i>
        </div>

        <div class="col-md-1 col-xs-2 text-center">
            <i class="glyphicon glyphicon-share glyphicon-large"></i>
        </div>

        <div class="col-md-6 col-xs-0">

        </div>

        <div class="col-md-1 col-xs-2 text-center">
            <g:if test="${userHasVoted}">
                <i class="glyphicon glyphicon-thumbs-up glyphicon-large ${userPostVote.vote == true ? "glyphicon-blue" : "glyphicon-greyout"}"></i>
            </g:if>
            <g:else>
                <g:link controller="post" action="voteUp" id="${post.id}">
                    <i class="glyphicon glyphicon-thumbs-up glyphicon-large glyphicon-black"></i>
                </g:link>
            </g:else>
        </div>

        <div class="col-md-1 col-xs-2 text-center">
            <g:if test="${userHasVoted}">
                <i class="glyphicon glyphicon-thumbs-down glyphicon-large ${userPostVote.vote == false ? "glyphicon-blue" : "glyphicon-greyout"}"></i>
            </g:if>
            <g:else>
                <g:link controller="post" action="voteDown" id="${post.id}">
                    <i class="glyphicon glyphicon-thumbs-down glyphicon-large glyphicon-black"></i>
                </g:link>
            </g:else>
        </div>

        <div class="col-md-1 col-xs-2 text-center"></div>
    </div>

    <a name="comments"></a>

    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="row vcenter">
                <div class="col-md-8 vcenter">
                    <g:if test="${post.creator?.image}">
                        <img class="post-creator-avatar"
                             src="${createLink(controller: "image", action: "show", id: post.creator.imageId, params: [width: 128, height: 128])}"/>
                    </g:if>
                    <g:else>
                        <img class="post-creator-avatar" src="/images/user-avatar.png"/>
                    </g:else>
                    <p class="post-creator-name">
                        ${post.creator.username}
                        <g:if test="${post.creator.description}">
                            <br/>
                            <span class="post-content">
                                ${post.creator.description}
                            </span>
                        </g:if>
                    </p>
                </div>

                <div class="col-md-4 center">
                    <div class="row">
                        <g:if test="${userHasVoted}">
                            <div class="c100 p${positiveVoteCount / totalVoteCount * 100}> center">
                                <span><g:formatNumber number="${positiveVoteCount / totalVoteCount * 100}"
                                                      maxFractionDigits="0"
                                                      maxIntegerDigits="3"/>%</span>

                                <div class="slice">
                                    <div class="bar"></div>

                                    <div class="fill"></div>
                                </div>
                            </div>

                            <p class="text-center">${voteCount} voters</p>
                        </g:if>
                        <g:else>
                            <div class="c100 p0> center">
                                <span>?</span>

                                <div class="slice">
                                    <div class="bar"></div>

                                    <div class="fill"></div>
                                </div>
                            </div>
                        </g:else>
                    </div>
                </div>
            </div>

            <div class="row">
                <g:form controller="post" action="addComment" id="${post.id}" class="form-horizontal">
                    <h4>Ajouter un commentaire</h4>

                    <div class="form-group">
                        <div class="col-sm-12">
                            <textarea id="description" name="description" rows="5"
                                      class="form-control">${params.description ?: ""}</textarea>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-10">
                            <button type="submit" class="btn btn-primary">
                                <span>Publier un commentaire</span>
                            </button>
                        </div>
                    </div>
                    <input type="hidden" id="postId" name="postId" value="${post.id}"/>
                </g:form>
            </div>

            <g:if test="${!comments.isEmpty()}">
                <div class="row">
                    <g:each in="${comments}" var="comment">
                        <g:render template="postComment" model="[comment: comment]"/>
                    </g:each>
                </div>
            </g:if>
        </div>

        <div class="col-sm-2">

        </div>
    </div>
</div>

</body>
</html>