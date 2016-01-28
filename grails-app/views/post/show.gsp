<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name='layout' content='main'/>
    <title>${post.name}</title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-md-4">
            <g:set var="imageId" value="${post.images?.first()?.id}"/>
            <div class="row">
                <div class="col-md-12">
                    <img src="${createLink(controller: "image", action: "show", id: imageId)}" style="width:100%;"/>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <small class="text-muted">
                        ${creator?.username} <g:formatDate date="${post.dateCreated}" format="dd MMMM"/>
                    </small>
                </div>
            </div>

            <div class="row">
                <div class="col-md-12">
                    <g:if test="${creator.image}">
                        <img class="post-creator-avatar"
                             src="${createLink(controller: "image", action: "show", id: creator.imageId)}"/>
                    </g:if>
                    <g:else>
                        <img class="post-creator-avatar" src="/images/user-avatar.png"/>
                    </g:else>
                    <h4>
                        ${creator.username}
                        <g:if test="${creator.description}">
                            <br/>
                            <small>
                                ${creator.description}
                            </small>
                        </g:if>
                        <g:else>
                        </g:else>
                    </h4>
                </div>
            </div>
        </div>

        <div class="col-md-6">
            <div class="row">
                <h2>
                    ${post.name}
                    <g:if test="${post.description}">
                        <br/>
                        <small>
                            ${post.description}
                        </small>
                    </g:if>
                </h2>
            </div>

            <g:if test="${comments.isEmpty()}">
                <p class="post-nocomment">Pas de commentaires pour cette demande d'aide.</p>
            </g:if>
            <g:else>
                <g:each in="${comments}" var="comment">
                    <g:render template="postComment" model="[comment: comment]"/>
                </g:each>
            </g:else>

            <g:if test="${currentUser}">

                <div class="row">
                    <form id="postComment-form" class="form-horizontal">
                        <h4>Ajouter un commentaire</h4>

                        <div class="form-group">
                            <div class="col-sm-10">
                                <textarea id="comment" name="comment" rows="5"
                                          class="form-control"></textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-10">
                                <button type="submit" class="btn btn-primary">
                                    <span>Publier un commentaire</span>
                                    <span class="fa fa-spinner"></span>
                                    <span>Publication en cours...</span>
                                </button>
                            </div>
                        </div>
                        <input type="hidden" id="postId" name="postId" value="${post.id}"/>
                    </form>
                </div>
            </g:if>
            <g:else>
                <div class="row">
                    <!-- Login FORM -->
                    <form id="login-form" class="form-horizontal">
                        <h2>Pour commenter, veuillez vous logguer.</h2>

                        <div class="form-group">
                            <label for="email" class="col-sm-2 control-label">Email</label>

                            <div class="col-sm-4">
                                <input type="email" class="form-control" id="email" name="email"
                                       placeholder="Email" required>
                                <span class="help-block has-error">
                                    <span>Email address is required.</span>
                                    <span>Not a valid email address.</span>
                                </span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="password" class="col-sm-2 control-label">Password</label>

                            <div class="col-sm-4">
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="">
                                <span class="help-block has-error">
                                    <span>Password is required.</span>
                                    <span>Password must be at least 6 characters.</span>
                                </span>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-2">
                                <button type="submit" class="btn btn-success btn-lg btn-block">
                                    <span>Sign in</span>
                                    <span class="fa fa-spinner"></span>
                                    <span>Signing in...</span>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </g:else>
        </div>

        <div class="col-sm-2">
            <g:if test="${userHasVoted}">
                <div class="c100 p${positiveVoteCount / totalVoteCount * 100}> center">
                    <span><g:formatNumber number="${positiveVoteCount / totalVoteCount * 100}" maxFractionDigits="0"
                                          maxIntegerDigits="3"/>%</span>

                    <div class="slice">
                        <div class="bar"></div>

                        <div class="fill"></div>
                    </div>
                </div>
            </g:if>
            <g:else>
                <div class="c100 p0> center">
                    <span>?</span>

                    <div class="slice">
                        <div class="bar"></div>

                        <div class="fill"></div>
                    </div>
                </div>
                <g:if test="${currentUser}">
                    <div class="row">
                        <div class="col-sm-6">
                            <a href="" class="vote-button btn btn-success margin-5">+1</a>
                        </div>

                        <div class="col-sm-6">
                            <a href="" class="vote-button btn btn-warning margin-5">-1</a>
                        </div>
                    </div>
                </g:if>
            </g:else>
        </div>
    </div>
</div>

</body>
</html>