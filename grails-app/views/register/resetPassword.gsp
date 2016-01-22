<html>

<head>
    <title><g:message code="default.forgotPassword.header"/></title>
    <meta name='layout' content='main'/>
</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-md-6 center-block">
            <div class="panel panel-info">
                <div class="panel-heading">
                    <g:message code="default.resetPassword.header"/>
                </div>

                <div class="panel-body">
                    <form action='resetPassword' method='POST' id="resetPasswordForm" name="resetPasswordForm"
                          class="form-horizontal">

                        <div class="form-group">
                            <label for="password" class="col-md-4 control-label">
                                <g:message code="default.password" default="Password"/>
                            </label>

                            <div class="col-md-8">
                                <input type="password" name="password" id="password" class="form-control"
                                       value="${command?.password}"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="password2" class="col-md-4 control-label">
                                <g:message code="default.password.confirm" default="Password (again)"/>
                            </label>

                            <div class="col-md-8">
                                <input type="password" name="password2" id="password2" class="form-control"
                                       value="${command?.password2}"/>
                            </div>
                        </div>

                        <g:hiddenField name='t' value='${token}'/>

                        <div class="form-group">
                            <div class="col-md-8 col-md-offset-4">
                                <button type="submit" class="btn btn-primary">
                                    <g:message code="default.resetPassword.submit" default="Change my password"/>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

%{--<s2ui:form width='475' height='250' elementId='resetPasswordFormContainer'--}%
%{--titleCode='spring.security.ui.resetPassword.header' center='true'>--}%
%{----}%
%{--<g:form action='resetPassword' name='resetPasswordForm' autocomplete='off'>--}%
%{--<g:hiddenField name='t' value='${token}'/>--}%
%{--<div class="sign-in">--}%
%{----}%
%{--<br/>--}%
%{--<h4><g:message code='spring.security.ui.resetPassword.description'/></h4>--}%
%{----}%
%{--<table>--}%
%{--<s2ui:passwordFieldRow name='password' labelCode='resetPasswordCommand.password.label' bean="${command}"--}%
%{--labelCodeDefault='Password' value="${command?.password}"/>--}%
%{----}%
%{--<s2ui:passwordFieldRow name='password2' labelCode='resetPasswordCommand.password2.label'--}%
%{--bean="${command}"--}%
%{--labelCodeDefault='Password (again)' value="${command?.password2}"/>--}%
%{--</table>--}%
%{----}%
%{--<s2ui:submitButton elementId='reset' form='resetPasswordForm'--}%
%{--messageCode='spring.security.ui.resetPassword.submit'/>--}%
%{----}%
%{--</div>--}%
%{--</g:form>--}%
%{----}%
%{--</s2ui:form>--}%

<script>
    $(document).ready(function () {
        $('#password').focus();
    });
</script>

</body>
</html>
