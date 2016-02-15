<!DOCTYPE html>
<html>
<head>
    <title><g:if env="development">Grails Runtime Exception</g:if><g:else>Error</g:else></title>
    <meta name="layout" content="main_nolinks">
    <g:if env="development"><asset:stylesheet src="errors.css"/></g:if>
</head>

<body>
<div class="container margin-top-20">
    <div class="row">
        <g:renderException exception="${exception}"/>
    </div>
</div>
</body>
</html>
