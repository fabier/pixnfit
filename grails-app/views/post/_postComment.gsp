<g:if test="${comment}">
    <div class="row">
        <g:if test="${comment.creator?.image}">
            <img class="comment-creator-avatar"
                 src="${createLink(controller: "image", action: "show", id: comment.creator.imageId, params: [width: 128, height: 128])}"/>
        </g:if>
        <g:else>
            <asset:image src="user/user-image-64.png" class="comment-creator-avatar"/>
        </g:else>
        <span class="comment-creator-name">
            ${comment.creator.username},
        </span>
        <span class="comment-date">
            <g:formatDate date="${comment.dateCreated}" format="dd MMMM HH:mm"/>
        </span>
        <br/>

        <p class="comment-content">
            ${comment.description}
        </p>
    </div>
</g:if>