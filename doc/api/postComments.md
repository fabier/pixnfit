# PostComments API

<a name="show"></a>
## GET /api/v1/postComments/:id
Gets a postComment by its Id
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
```json
{
  "id": 1,
  "name": null,
  "description": "Non dolorum itaque in est fuga qui aliquam. Culpa eum molestiae eveniet aut blanditiis.",
  "post": {
    "id": 4,
    "name": "Culpa eum molestiae"
  },
  "creator": {
    "id": 4,
    "username": "Marvin Hoppe",
    "image": {
      "id": 58,
      "imageUrl": "http://www.pixnfit.com/image/show/58?width=128&height=128"
    }
  },
  "dateCreated": "2016-02-08T16:01:59Z"
}
```

<a name="votes"></a>
## GET /api/v1/postComments/:id/votes
Gets votes for a comment
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
```json
[
  {
    "id": 1047,
    "vote": true,
    "postComment": {
      "id": 1,
      "name": null,
      "description": "Non dolorum itaque in est fuga qui aliquam. Culpa eum molestiae eveniet aut blanditiis.",
    },
    "creator": {
      "id": 1,
      "username": "Thomas Bull",
      "image": {
        "id": 95,
        "imageUrl": "http://www.pixnfit.com/image/show/95?width=128&height=128"
      }
    },
    "dateCreated": "2016-02-08T20:46:46Z"
  },
  ...
]
```

<a name="addVote"></a>
## POST /api/v1/postComments/:id/votes
Adds a vote for a comment
### Params
```json
{
    "vote": true
}
```
### Authentication
Yes : **HTTP BASIC**
### Output
HTTP 201 Created
```json
{
  "id": 1047,
  "vote": true,
  "postComment": {
    "id": 1,
    "name": null,
    "description": "Non dolorum itaque in est fuga qui aliquam. Culpa eum molestiae eveniet aut blanditiis.",
  },
  "creator": {
    "id": 1,
    "username": "Thomas Bull",
    "image": {
      "id": 95,
      "imageUrl": "http://www.pixnfit.com/image/show/95?width=128&height=128"
    }
  },
  "dateCreated": "2016-02-08T20:46:46Z"
}
```