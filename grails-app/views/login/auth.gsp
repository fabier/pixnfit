<!DOCTYPE html>
<html lang="en">
<head>
    <meta name="layout" content="main"/>
    <title>Login</title>
</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-md-6 center-block">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <g:message code="default.login"/>
                </div>

                <div class="panel-body">
                    <form action='${postUrl}' method='POST' id="loginForm" name="loginForm"
                          class="form-horizontal">
                        <div class="form-group">
                            <label for="username" class="col-md-4 control-label">
                                <g:message code="default.email" default="Email"/>
                            </label>

                            <div class="col-md-8">
                                <input type="text" name="j_username" id="username" class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="password" class="col-md-4 control-label">
                                <g:message code="default.password" default="Mot de passe"/>
                            </label>

                            <div class="col-md-8">
                                <input type="password" name="j_password" id="password"
                                       class="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-8 col-md-offset-4">
                                <input type='checkbox' class='chk' name='${rememberMeParameter}'
                                       id='remember_me' checked='checked'/>
                                <label for='remember_me'>
                                    <g:message code="default.rememberMe" default="Rester connecté"/>
                                </label>
                            </div>

                        </div>

                        <div class="form-group">
                            <div class="col-md-8 col-md-offset-4">
                                <button type="submit" class="btn btn-primary">
                                    <g:message code="default.login" default="Se connecter"/>
                                </button>
                                <span class="text-small margin-left-20">
                                    <g:link controller='register' action='forgotPassword'>
                                        <g:message code="default.forgotPassword"
                                                   default="Mot de passe oublié ?"/>
                                    </g:link>
                                </span>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-md-offset-4">
                                <span class="text-small">Vous n'avez pas encore de compte ?</span>
                                <br/>
                                <span class="text-small">
                                    <g:link controller="register" action="index">
                                        Cliquez ici pour créer un compte.
                                    </g:link>
                                </span>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        $('#username').focus();
    });
    <s2ui:initCheckboxes/>
</script>
</body>
</html>
