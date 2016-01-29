<div class="row">
    <g:if test="${comment.creator?.image}">
        <img class="comment-creator-avatar"
             src="${createLink(controller: "image", action: "show", id: comment.creator.imageId)}"/>
    </g:if>
    <g:else>
        <img class="comment-creator-avatar" src="/images/user-avatar.png"/>
    </g:else>
    <span class="comment-creator-name">
        ${comment.creator.username}
    </span>,
    <span class="comment-date">
        <g:formatDate date="${comment.dateCreated}" format="dd MMMM HH:mm"/>
    </span>
    <br/>

    <p class="comment-content">
        ${comment.description}
    </p>
</div>
