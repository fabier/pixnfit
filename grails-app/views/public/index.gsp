<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <meta name='layout' content='main'/>
    <title>Pixnfit - The social shopper</title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="jumbotron">
            <h1>Pixnfit</h1>

            <h2>The tiny fitting room inside your phone !</h2>
            <g:link controller="register" action="index" class="btn btn-primary">
                Sign up now!
            </g:link>
        </div>
    </div>

    <div class="row">
        <h1>
            Ils ont besoin de votre aide
            <g:link controller="post" action="create" class="btn btn-success pull-right">
                Poster une photo
            </g:link>
        </h1>

        <g:each in="${helpPosts}" var="helpPost">
            <div class="col-md-1 col-xs-2 padding-1">
                <g:link controller="post" action="show" id="${helpPost.id}">
                    <g:set var="imageId" value="${helpPost.images?.first()?.id}"/>
                    <g:set var="imageURL" value="${createLink(controller: "image", action: "show", id: imageId)}"/>
                    <img class="media-object image-thumb" src="${imageURL}" alt="...">
                </g:link>
            </div>
        </g:each>
    </div>

    <div class="row">
        <h1>Ils ont recu votre aide, merci à vous !</h1>

        <g:each in="${dressingPosts}" var="dressingPost">
            <div class="col-md-1 col-xs-2 padding-1">
                <div class="image-thumb-wrapper">
                    <g:link controller="post" action="show" id="${dressingPost.id}">
                        <g:set var="imageId" value="${dressingPost.images?.first()?.id}"/>
                        <g:set var="imageURL" value="${createLink(controller: "image", action: "show", id: imageId)}"/>
                        <img class="media-object image-thumb" src="${imageURL}" alt="...">
                    %{--<i class="glyphicon glyphicon-ok-sign ${helpPost.ok ? 'ok' : 'ko'}"></i>--}%
                        <i class="glyphicon glyphicon-ok-sign ok"></i>
                    </g:link>
                </div>
            </div>
        </g:each>
    </div>
</div>
</body>
</html>