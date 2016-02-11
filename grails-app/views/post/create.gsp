<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name='layout' content='main'/>
    <title>Create a post</title>
</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-primary margin-top-20">
                <div class="panel-heading">
                    New post
                </div>

                <div class="panel-body">
                    <g:uploadForm controller="post" action="save" class="form-horizontal">
                        <div class="form-group">
                            <label for="file" class="col-sm-2 control-label">Image</label>

                            <div class="col-sm-10">
                                <input type="file" class="" id="file" name="file" accept="image/jpeg">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="name" class="col-sm-2 control-label">Title</label>

                            <div class="col-sm-10">
                                <input type="text" class="form-control"
                                       id="name" name="name"
                                       placeholder="Type your post title here"
                                       value="${params?.name ?: ""}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="description" class="col-sm-2 control-label">Text</label>

                            <div class="col-sm-10">
                                <textarea id="description" name="description" class="form-control"
                                          rows="6">${params?.description ?: ""}</textarea>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-10 col-sm-offset-2">
                                <button type="submit" class="btn btn-primary">
                                    <span>Create Post</span>
                                    %{--<span class="fa fa-spinner"></span>--}%
                                    %{--<span>Creating Post...</span>--}%
                                </button>
                            </div>
                        </div>
                    </g:uploadForm>
                </div>
            </div>
            %{--<h2 class="form-signin-heading">Nouveau post</h2>--}%
        </div>
    </div>
</div>

</body>
</html>