# Posts API

<a name="save"></a>
## POST /api/v1/posts
Creates a new post
### Params
```json
{
  "name": "Aut tempore repellat vero et sed alias.",
  "description": "Quibusdam veritatis voluptas veritatis laboriosam aliquid.",
  "postTypeId": 1,
  "visibilityId": 1
}
```
### Authentication
Yes : **HTTP BASIC**
### Output
```json
{
  "id": 1,
  "name": "Aut tempore repellat vero et sed alias.",
  "description": "Quibusdam veritatis voluptas veritatis laboriosam aliquid.",
  "creator": {
    "id": 2,
    "username": "Theodore Cummings",
    "image": {
      "id": 29,
      "imageUrl": "http://www.pixnfit.com/image/show/29?width=128&height=128"
    }
  },
  "images": null,
  "postType": {
    "id": 1,
    "name": "help"
  },
  "visibility": {
    "id": 1,
    "name": "public"
  },
  "state": {
    "id": 1,
    "name": "active"
  },
  "dateCreated": "2016-02-08T16:01:48Z"
}
```

<a name="show"></a>
## GET /api/v1/posts/:id
Gets a post by its Id
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
```json
{
  "id": 1,
  "name": "Aut tempore repellat vero et sed alias.",
  "description": "Quibusdam veritatis voluptas veritatis laboriosam aliquid.",
  "creator": {
    "id": 2,
    "username": "Theodore Cummings",
    "image": {
      "id": 29,
      "imageUrl": "http://www.pixnfit.com/image/show/29?width=128&height=128"
    }
  },
  "images": [
    {
      "id": 1,
      "imageUrl": "http://www.pixnfit.com/image/show/1?width=256&height=256"
    }
  ],
  "postType": {
    "id": 1,
    "name": "help"
  },
  "visibility": {
    "id": 1,
    "name": "public"
  },
  "state": {
    "id": 1,
    "name": "active"
  },
  "dateCreated": "2016-02-08T16:01:48Z"
}
```

<a name="update"></a>
## PUT /api/v1/posts
Updates an existing post
### Params
```json
{
  "name": "Aut tempore repellat vero et sed alias.",
  "description": "Quibusdam veritatis voluptas veritatis laboriosam aliquid.",
  "postTypeId": 1,
  "visibilityId": 1
}
```
### Authentication
Yes : **HTTP BASIC**
### Output
```json
{
  "id": 1,
  "name": "Aut tempore repellat vero et sed alias.",
  "description": "Quibusdam veritatis voluptas veritatis laboriosam aliquid.",
  "creator": {
    "id": 2,
    "username": "Theodore Cummings",
    "image": {
      "id": 29,
      "imageUrl": "http://www.pixnfit.com/image/show/29?width=128&height=128"
    }
  },
  "images": null,
  "postType": {
    "id": 1,
    "name": "help"
  },
  "visibility": {
    "id": 1,
    "name": "public"
  },
  "state": {
    "id": 1,
    "name": "active"
  },
  "dateCreated": "2016-02-08T16:01:48Z"
}
```

<a name="delete"></a>
## DELETE /api/v1/posts/:id
Deletes an existing post
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
HTTP 204 No Content


<a name="addImage"></a>
## POST /api/v1/posts/:id/images
Adds an existing image to a post
### Params
```json
{
  "imageId": 1
}
```
### Authentication
Yes : **HTTP BASIC**
### Output
```json
{
  "id": 1,
  "name": "Aut tempore repellat vero et sed alias.",
  "description": "Quibusdam veritatis voluptas veritatis laboriosam aliquid.",
  "creator": {
    "id": 2,
    "username": "Theodore Cummings",
    "image": {
      "id": 29,
      "imageUrl": "http://www.pixnfit.com/image/show/29?width=128&height=128"
    }
  },
  "images": [
      {
        "id": 1,
        "imageUrl": "http://www.pixnfit.com/image/show/1?width=256&height=256"
      }
  ],
  "postType": {
    "id": 1,
    "name": "help"
  },
  "visibility": {
    "id": 1,
    "name": "public"
  },
  "state": {
    "id": 1,
    "name": "active"
  },
  "dateCreated": "2016-02-08T16:01:48Z"
}
```

<a name="comments"></a>
## GET /api/v1/posts/:id/comments
Gets all comments on a post
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
```json
[
  {
    "id": 1,
    "name": null,
    "description": "Non dolorum itaque in est fuga qui aliquam. Culpa eum molestiae eveniet aut blanditiis.",
    "postId": 1,
    "creator": {
      "id": 4,
      "username": "Marvin Hoppe",
      "image": {
        "id": 58,
        "imageUrl": "http://www.pixnfit.com/image/show/58?width=128&height=128"
      }
    },
    "dateCreated": "2016-02-08T16:01:59Z"
  },
  ...
]
```

<a name="addComment"></a>
## POST /api/v1/posts/:id/comments
Adds a comment on a post
### Params
```json
{
  "description": "Voici mon commentaire pour ce post !"
}
```
### Authentication
Yes : **HTTP BASIC**
### Output
```json
{
  "id": 1858,
  "name": null,
  "description": "Voici mon commentaire pour ce post !",
  "postId": 1,
  "creator": {
    "id": 2,
    "username": "Theodore Cummings",
    "image": {
      "id": 29,
      "imageUrl": "http://www.pixnfit.com/image/show/29?width=128&height=128"
    }
  },
  "dateCreated": "2016-02-08T16:01:48Z"
}
```

<a name="votes"></a>
## GET /api/v1/posts/:id/votes
Gets all votes on a post
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
```json
[
  {
    "id": 1,
    "vote": true,
    "voteReason": null,
    "post": {
      "id": 1,
      "name": "Aut tempore repellat vero et sed alias."
    },
    "creator": {
      "id": 6,
      "username": "Willard Gottlieb",
      "image": {
        "id": 62,
        "imageUrl": "http://www.pixnfit.com/image/show/62?width=128&height=128"
      }
    },
    "dateCreated": "2016-02-08T16:01:59Z"
  },
  ...
]
```

<a name="votes"></a>
## POST /api/v1/posts/:id/votes
Creates a new vote on a post
### Params
```json
{
    "vote" : true
}
```
**OR**
```json
{
    "vote" : false,
    "voteReasonId" : 1
}
```
### Authentication
Yes : **HTTP BASIC**
### Output
```json
{
  "id": 304,
  "vote": true,
  "voteReason": null,
  "post": {
    "id": 17,
    "name": "Consequatur esse molestias est at."
  },
  "creator": {
    "id": 6,
    "username": "Willard Gottlieb",
    "image": {
      "id": 62,
      "imageUrl": "http://www.pixnfit.com/image/show/62?width=128&height=128"
    }
  },
  "dateCreated": "2016-02-08T16:01:59Z"
}
```
**OR**
```json
{
  "id": 304,
  "vote": false,
  "voteReason": {
    "id": 1,
    "name": "style"
  },
  "post": {
    "id": 17,
    "name": "Consequatur esse molestias est at."
  },
  "creator": {
    "id": 6,
    "username": "Willard Gottlieb",
    "image": {
      "id": 62,
      "imageUrl": "http://www.pixnfit.com/image/show/62?width=128&height=128"
    }
  },
  "dateCreated": "2016-02-08T16:01:59Z"
}
```


<a name="help"></a>
## GET /api/v1/posts/help
Gets help posts
### Params
Yes :
 * **max** (int) : Max results in output, defaults to 10
 * **offset** (int) : Offset for results, defaults to 0

*Ex : /api/v1/posts/help?max=5&offset=2*
### Authentication
Yes : **HTTP BASIC**
### Output
```json
[
  {
    "id": 1,
    "name": "Aut tempore repellat vero et sed alias.",
    "description": "Quibusdam veritatis voluptas veritatis laboriosam aliquid.",
    "creator": {
      "id": 2,
      "username": "Theodore Cummings",
      "image": {
        "id": 29,
        "imageUrl": "http://www.pixnfit.com/image/show/29?width=128&height=128"
      }
    },
    "images": [
      {
        "id": 1,
        "imageUrl": "http://www.pixnfit.com/image/show/1?width=256&height=256"
      }
    ],
    "postType": {
      "id": 1,
      "name": "help"
    },
    "visibility": {
      "id": 1,
      "name": "public"
    },
    "state": {
      "id": 1,
      "name": "active"
    },
    "dateCreated": "2016-02-08T16:01:48Z"
  },
  ...
]
```


<a name="featured"></a>
## GET /api/v1/posts/featured
Gets featured posts
### Params
Yes :
 * **max** (int) : Max results in output, defaults to 10
 * **offset** (int) : Offset for results, defaults to 0

*Ex : /api/v1/posts/dressing?max=5&offset=2*
### Authentication
Yes : **HTTP BASIC**
### Output
```json
[
  {
    "id": 1,
    "name": "Aut tempore repellat vero et sed alias.",
    "description": "Quibusdam veritatis voluptas veritatis laboriosam aliquid.",
    "creator": {
      "id": 2,
      "username": "Theodore Cummings",
      "image": {
        "id": 29,
        "imageUrl": "http://www.pixnfit.com/image/show/29?width=128&height=128"
      }
    },
    "images": [
      {
        "id": 1,
        "imageUrl": "http://www.pixnfit.com/image/show/1?width=256&height=256"
      }
    ],
    "postType": {
      "id": 2,
      "name": "dressing"
    },
    "visibility": {
      "id": 1,
      "name": "public"
    },
    "state": {
      "id": 1,
      "name": "active"
    },
    "dateCreated": "2016-02-08T16:01:48Z"
  },
  ...
]
```
