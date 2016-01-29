<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name='layout' content='main'/>
    <title>Create a post</title>
</head>

<body>

<div class="container">
    <div class="row">
        <!-- Login FORM -->
        <div class="col-md-6 col-md-offset-3">
            <h2 class="form-signin-heading">Nouveau post</h2>

            <form id="post-form" class="form-horizontal">
                <div class="form-group">
                    <label for="file" class="col-sm-2 control-label">Image</label>

                    <div class="col-sm-10">
                        <input type="file" class="" id="file" name="file">
                    </div>
                </div>

                <div class="form-group">
                    <label for="title" class="col-sm-2 control-label">Title</label>

                    <div class="col-sm-10">
                        <input type="text" class="form-control"
                               id="title" name="title"
                               placeholder="Type your post title here">
                    </div>
                </div>

                <div class="form-group">
                    <label for="content" class="col-sm-2 control-label">Text</label>

                    <div class="col-sm-10">
                        <textarea id="content" name="content" class="form-control" rows="6"></textarea>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-10 col-sm-offset-2">
                        <button type="submit" class="btn btn-primary btn-lg btn-block">
                            <span>Create Post</span>
                            <span class="fa fa-spinner"></span>
                            <span>Creating Post...</span>
                        </button>
                    </div>
                </div>
            </form>
            <img/>
        </div>
    </div>
</div>

</body>
</html>