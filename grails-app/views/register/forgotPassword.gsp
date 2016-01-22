<html>

<head>
    <title><g:message code="default.forgotPassword" default="Forgot password ?"/></title>
    <meta name='layout' content='main'/>
</head>

<body>

<p/>

<div class="container">
    <div class="row">
        <div class="col-md-6 center-block">

            <g:if test='${emailSent}'>
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <g:message code="default.forgotPassword.header"/>
                    </div>

                    <div class="panel-body">
                        <p>
                            <g:message code='default.forgotPassword.emailSent'/>
                        </p>
                    </div>
                </div>
            </g:if>
            <g:else>
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <g:message code="default.forgotPassword.header"/>
                    </div>

                    <div class="panel-body">
                        <form action='forgotPassword' method='POST' id="forgotPasswordForm" name="forgotPasswordForm"
                              class="form-horizontal">
                            <div class="form-group">
                                <label for="username" class="col-md-4 control-label">
                                    <g:message code="default.email" default="Email"/>
                                </label>

                                <div class="col-md-8">
                                    <input type="text" name="username" id="username" class="form-control"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-md-8 col-md-offset-4">
                                    <button type="submit" class="btn btn-primary">
                                        <g:message code="default.forgotPassword.submit" default="Recover my password"/>
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

<script>
    $(document).ready(function () {
        $('#username').focus();
    });
</script>

</body>
</html>
