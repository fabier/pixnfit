<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <meta name='layout' content='home'/>
    <title>Pixnfit - The social shopper</title>
</head>

<body>
<div class="container">
    <div class="row">
        <h1>
            Ils ont besoin de votre aide
        </h1>

        <g:each in="${helpPosts}" var="helpPost">
            <div class="col-md-4 col-xs-6 padding-5">
                <g:link controller="post" action="show" id="${helpPost.id}">
                    <g:set var="imageId" value="${helpPost.images?.isEmpty() ? null : helpPost.images?.first()?.id}"/>
                    <g:set var="imageURL"
                           value="${createLink(controller: "image", action: "show", id: imageId, params: [width: 380, height: 380])}"/>
                    <img class="media-object image-thumb" src="${imageURL}" alt="...">
                </g:link>
            </div>
        </g:each>
    </div>

    %{--<div class="row">--}%
    %{--<h1>Ils ont recu votre aide, merci à vous !</h1>--}%
    %{----}%
    %{--<g:each in="${dressingPosts}" var="dressingPost">--}%
    %{--<div class="col-md-4 col-xs-6 padding-5">--}%
    %{--<div class="image-thumb-wrapper">--}%
    %{--<g:link controller="post" action="show" id="${dressingPost.id}">--}%
    %{--<g:set var="imageId" value="${dressingPost.images?.first()?.id}"/>--}%
    %{--<g:set var="imageURL" value="${createLink(controller: "image", action: "show", id: imageId)}"/>--}%
    %{--<img class="media-object image-thumb" src="${imageURL}" alt="...">--}%
    %{--<i class="glyphicon glyphicon-ok-sign ${helpPost.ok ? 'ok' : 'ko'}"></i>--}%
    %{--<i class="glyphicon glyphicon-ok-sign ok"></i>--}%
    %{--</g:link>--}%
    %{--</div>--}%
    %{--</div>--}%
    %{--</g:each>--}%
    %{--</div>--}%
</div>
</body>
</html>