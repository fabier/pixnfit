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
                    <asset:image src="logo/logo-black-white.png" class="logo-top"/>
                </span>
            </g:link>
        </div>

        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active">
                    <g:link controller="public" action="index">
                        <i class="glyphicon glyphicon-asterisk"></i>
                        &nbsp;Explore
                    </g:link>
                </li>
                <li>
                    <a href="#">
                        <i class="glyphicon glyphicon-chevron-up"></i>
                        &nbsp;Followers
                    </a>
                </li>
                <li>
                    <a href="#">
                        <i class="glyphicon glyphicon-inbox"></i>
                        &nbsp;Inbox
                    </a>
                </li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <sec:ifNotLoggedIn>
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
                </sec:ifNotLoggedIn>
                <sec:ifLoggedIn>
                    <li>
                        <a href="#">
                            <i class="glyphicon glyphicon-user"></i>
                            &nbsp;<g:loggedInUserInfo field="username"/>
                        </a>
                    </li>
                    <li>
                        <g:link controller="logout" action="">
                            <i class="glyphicon glyphicon-log-out"></i>
                            &nbsp;Déconnexion
                        </g:link>
                    </li>
                </sec:ifLoggedIn>
            </ul>
        </div><!--/.nav-collapse -->
    </div>
</nav>