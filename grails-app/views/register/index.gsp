<html>

<head>
    <meta name='layout' content='main'/>
    <title>Register</title>
</head>

<body>

<p/>

<div class="container">
    <div class="row">
        <div class="col-md-6 center-block">
            <g:if test='${emailSent}'>
                <div class="panel panel-success">
                    <div class="panel-heading">
                        Félicitations !
                    </div>

                    <div class="panel-body">
                        <p>
                            <g:message code='default.register.emailSent'/>
                        </p>
                    </div>
                </div>
            </g:if>
            <g:else>
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <g:message code="default.register"/>
                    </div>

                    <div class="panel-body">
                        <form action='register' method='POST' id="registerForm" name="registerForm"
                              class="form-horizontal">

                            <div class="form-group ${hasErrors(bean: command, field: "username") {
                                "has-error has-feedback"
                            }}">
                                <label for="username" class="col-md-4 control-label">
                                    <g:message code="default.username" default="Username"/>
                                </label>

                                <div class="col-md-8">
                                    <input type="text" name="username" id="username" class="form-control"
                                           value="${command.username}"/>
                                    <g:hasErrors bean="${command}" field="username">
                                        <span class="glyphicon glyphicon-remove form-control-feedback"></span>
                                        <span class="text-danger">
                                            <g:renderError bean="${command}" field="username"/>
                                        </span>
                                    </g:hasErrors>
                                </div>
                            </div>

                            <div class="form-group ${hasErrors(bean: command, field: "email") {
                                "has-error has-feedback"
                            }}">
                                <label for="email" class="col-md-4 control-label">
                                    <g:message code="default.email" default="Email"/>
                                </label>

                                <div class="col-md-8">
                                    <input type="text" name="email" id="email" class="form-control"
                                           value="${command.email}"/>
                                    <g:hasErrors bean="${command}" field="email">
                                        <span class="glyphicon glyphicon-remove form-control-feedback"></span>
                                        <span class="text-danger">
                                            <g:renderError bean="${command}" field="email"/>
                                        </span>
                                    </g:hasErrors>
                                </div>
                            </div>

                            <div class="form-group ${hasErrors(bean: command, field: "password") {
                                "has-error has-feedback"
                            }}">
                                <label for="password" class="col-md-4 control-label">
                                    <g:message code="default.password" default="Password"/>
                                </label>

                                <div class="col-md-8">
                                    <input type="password" name="password" id="password" class="form-control"
                                           value="${command.password}"/>
                                    <g:hasErrors bean="${command}" field="password">
                                        <span class="glyphicon glyphicon-remove form-control-feedback"></span>
                                        <span class="text-danger">
                                            <g:renderError bean="${command}" field="password"/>
                                        </span>
                                    </g:hasErrors>
                                </div>
                            </div>

                            <div class="form-group ${hasErrors(bean: command, field: "password2") {
                                "has-error has-feedback"
                            }}">
                                <label for="password2" class="col-md-4 control-label">
                                    <g:message code="default.password.confirm" default="Password (again)"/>
                                </label>

                                <div class="col-md-8">
                                    <input type="password" name="password2" id="password2" class="form-control"
                                           value="${command.password2}"/>
                                    <g:hasErrors bean="${command}" field="password2">
                                        <span class="glyphicon glyphicon-remove form-control-feedback"></span>
                                        <span class="text-danger">
                                            <g:renderError bean="${command}" field="password2"/>
                                        </span>
                                    </g:hasErrors>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-8 col-md-offset-4">
                                    <button type="submit" class="btn btn-primary">
                                        <g:message code="default.register" default="Créer un compte"/>
                                    </button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </g:else>
        </div>
    </div>
</div>
</div>

<script>
    $(document).ready(function () {
        $('#username').focus();
    });
</script>

</body>
</html>
