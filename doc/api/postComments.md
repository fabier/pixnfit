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
  "postId": 1,
  "creator": {
    "id": 4,
    "username": "Marvin Hoppe",
    "imageUrl": "http://www.pixnfit.com/image/show/58"
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
    "postCommentId": 1,
    "creator": {
      "id": 1,
      "username": "Thomas Bull",
      "imageUrl": null
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
  "postCommentId": 1,
  "creator": {
    "id": 1,
    "username": "Thomas Bull",
    "imageUrl": null
  },
  "dateCreated": "2016-02-08T20:46:46Z"
}
```