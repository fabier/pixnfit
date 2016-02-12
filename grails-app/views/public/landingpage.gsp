<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <meta name='layout' content='landingpage'/>
    <title>PixnFit - The social shopper</title>
</head>

<body>

<!-- Header -->
<a name="about"></a>

<div class="intro-header">
    <div class="container">

        <div class="row">
            <div class="col-lg-12">
                <div class="intro-message">
                    <h1>The social shopper</h1>

                    <hr class="intro-divider">

                    <h3>Get your invitation for the app launch !</h3>
                    <hr class="intro-divider">

                    <div class="row">
                        <div class="col-lg-6 col-md-offset-3">
                            <g:form controller="landingPage" action="submitEmail" class="form-inline">
                                <div class="form-group">
                                    <input name="email" class="form-control" type="text" placeholder="Email"
                                           value="${params.email ?: ""}"/>

                                    <button type="submit" class="btn btn-primary">
                                        Submit
                                    </button>
                                </div>
                            </g:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Page Content -->

<a name="services"></a>

<div class="content-section-a">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 col-lg-offset-2 col-sm-6">
                <h2 class="section-heading">Undecided ?</h2>

                <hr class="section-heading-spacer">

                <div class="clearfix"></div>

                <p class="lead">
                    Does your wear fit you ?
                    <br/>
                    Need some advice ?
                </p>
            </div>

            <div class="col-lg-4 col-sm-6">
                <g:img file="home/intro-1.jpg" class="img-responsive"/>
            </div>
        </div>
    </div>
</div>

<div class="content-section-b">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 col-sm-push-6 col-sm-6">
                <h2 class="section-heading">
                    Take a Pix
                </h2>

                <hr class="section-heading-spacer">

                <div class="clearfix"></div>

                <p class="lead">
                    Share your outfit to receive ads,
                    <br/>
                    votes instantly from your friends,
                    <br/>
                    followers and more !
                </p>
            </div>

            <div class="col-lg-4 col-lg-offset-4 col-sm-pull-6 col-sm-6">
                <g:img file="home/intro-2.jpg" class="img-responsive"/>
            </div>
        </div>
    </div>
</div>

<div class="content-section-a">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 col-lg-offset-2 col-sm-6">

                <h2 class="section-heading">Check out the votes</h2>

                <hr class="section-heading-spacer">

                <div class="clearfix"></div>

                <p class="lead">
                    Voting is the best way to receive
                    <br/>
                    feedback for your style
                </p>
            </div>

            <div class="col-lg-4 col-sm-6">
                <g:img file="home/intro-3.png" class="img-responsive"/>
            </div>
        </div>
    </div>
</div>

<div class="content-section-b">
    <div class="container">
        <div class="row">
            <div class="col-lg-4 col-sm-push-6 col-sm-6">
                <h2 class="section-heading">
                    Make your choice
                </h2>

                <hr class="section-heading-spacer">

                <div class="clearfix"></div>

                <p class="lead">
                    For a perfect style and fit !
                </p>
            </div>

            <div class="col-lg-4 col-lg-offset-4 col-sm-pull-6 col-sm-6">
                <g:img file="home/intro-4.jpg" class="img-responsive"/>
            </div>
        </div>
    </div>
</div>

<a name="contact"></a>

%{--<div class="banner">--}%
%{----}%
%{--<div class="container">--}%
%{----}%
%{--<div class="row">--}%
%{--<div class="col-lg-6">--}%
%{--<h2>Connect to Start Bootstrap:</h2>--}%
%{--</div>--}%
%{----}%
%{--<div class="col-lg-6">--}%
%{--<ul class="list-inline banner-social-buttons">--}%
%{--<li>--}%
%{--<a href="https://twitter.com/SBootstrap" class="btn btn-default btn-lg"><i--}%
%{--class="fa fa-twitter fa-fw"></i> <span class="network-name">Twitter</span></a>--}%
%{--</li>--}%
%{--<li>--}%
%{--<a href="https://github.com/IronSummitMedia/startbootstrap"--}%
%{--class="btn btn-default btn-lg"><i--}%
%{--class="fa fa-github fa-fw"></i> <span class="network-name">Github</span></a>--}%
%{--</li>--}%
%{--<li>--}%
%{--<a href="#" class="btn btn-default btn-lg"><i class="fa fa-linkedin fa-fw"></i> <span--}%
%{--class="network-name">Linkedin</span></a>--}%
%{--</li>--}%
%{--</ul>--}%
%{--</div>--}%
%{--</div>--}%
%{----}%
%{--</div>--}%
%{--<!-- /.container -->--}%

%{--</div>--}%
<!-- /.banner -->
</body>

</html>