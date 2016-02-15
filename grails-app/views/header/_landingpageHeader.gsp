<nav class="navbar navbar-default navbar-fixed-top topnav" role="navigation">
    <div class="container topnav">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <g:link uri="/" class="navbar-brand topnav">
                <span>
                    <asset:image src="logo/logo.png" class="logo-top"/>
                </span>
            </g:link>
        </div>

        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <g:link controller="login" action="">
                        <i class="glyphicon glyphicon-log-in"></i>
                        &nbsp;<g:message code="default.connection"/>
                    </g:link>
                </li>
                <li>
                    <g:link controller="register" action="">
                        <i class="glyphicon glyphicon-plus-sign"></i>
                        &nbsp;<g:message code="default.register"/>
                    </g:link>
                </li>
            </ul>
        </div>
    </div>
</nav>