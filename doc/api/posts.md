# Posts API

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
    "imageUrl": "http://www.pixnfit.com/image/show/29"
  },
  "images": [
    {
      "id": 1,
      "imageUrl": "http://www.pixnfit.com/image/show/1"
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
      "imageUrl": "http://www.pixnfit.com/image/show/29"
    },
    "images": [
      {
        "id": 1,
        "imageUrl": "http://www.pixnfit.com/image/show/1"
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


<a name="dressing"></a>
## GET /api/v1/posts/dressing
Gets dressing posts
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
      "imageUrl": "http://www.pixnfit.com/image/show/29"
    },
    "images": [
      {
        "id": 1,
        "imageUrl": "http://www.pixnfit.com/image/show/1"
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
    "imageUrl": "http://www.pixnfit.com/image/show/29"
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
    "imageUrl": "http://www.pixnfit.com/image/show/29"
  },
  "images": [
      {
        "id": 1,
        "imageUrl": "http://www.pixnfit.com/image/show/1"
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
      "imageUrl": "http://www.pixnfit.com/image/show/58"
    },
    "dateCreated": "2016-02-08T16:01:59Z"
  }
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
    "imageUrl": "http://www.pixnfit.com/image/show/29"
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
      "imageUrl": "http://localhost:8080/pixnfit/image/show/62"
    },
    "dateCreated": "2016-02-08T16:01:59Z"
  },
  ...
]
```

<a name="votes"></a>
## POST /api/v1/posts/:id/votes
Gets all votes on a post
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
    "imageUrl": "http://www.pixnfit.com/image/show/62"
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
    "imageUrl": "http://www.pixnfit.com/image/show/62"
  },
  "dateCreated": "2016-02-08T16:01:59Z"
}
```
