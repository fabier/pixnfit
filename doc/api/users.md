# Users API

<a name="save"></a>
## POST /api/v1/user
Creates a new user.
An email is automatically sent to the user to validate its account.
The user must click on the link to validate the account creation (spam prevention). 
### Params
```json
{
    "username" : "John Doe",
    "email":"john.doe@mail.com",
    "password":"1234"
}
```
### Authentication
**No authentication to create an account**<br/>
TODO : There should be a captcha to avoid automatic user account creation 
### Output
```json
{
  "id": 8,
  "username": "John Doe",
  "description": null,
  "bodyType": null,
  "gender": null,
  "birthdate": null,
  "height": null,
  "weight": null,
  "imageUrl": null,
  "country": null,
  "language": null,
  "dateCreated": "2016-02-12T10:18:06Z"
}
```

<a name="show"></a>
## GET /api/v1/user/:id
Gets a user by its Id
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
```json
{
  "id": 1,
  "username": "John Doe",
  "description": null,
  "bodyType": {
    "id": 2,
    "name": "average"
  },
  "gender": {
    "id": 1,
    "name": "male"
  },
  "birthdate": null,
  "height": null,
  "weight": null,
  "imageUrl": "http://www.pixnfit.com/image/show/65",
  "country": {
    "id": 3,
    "name": "USA"
  },
  "language": {
    "id": 2,
    "name": "English"
  },
  "dateCreated": "2016-02-08T16:00:53Z"
}
```

<a name="update"></a>
## PUT /api/v1/user/:id
Updates a user.
Currently, you can't change password or email because of security issues.
If users wants to change its password, he can do it so using the website.
Changing someone's email is not possible at all at the moment.
### Params
```json
{
    "username" : "Administrator",
    "description": "lorem ipsum...",
    "bodyTypeId": 1,
    "genderId": 1,
    "birthdate": "1985-02-01T00:00:00Z",
    "height": 185,
    "weight": 83,
    "countryId": 1,
    "languageId": 1
}
```
### Authentication
Yes : **HTTP BASIC**
### Output
```json
{
  "id": 1,
  "username": "Administrator",
  "description": "lorem ipsum...",
  "bodyType": {
    "id": 1,
    "name": "skinny"
  },
  "gender": {
    "id": 1,
    "name": "male"
  },
  "birthdate": "1985-02-01T00:00:00Z",
  "height": 185,
  "weight": 83,
  "imageUrl": "http://www.pixnfit.com/image/show/65",
  "country": {
    "id": 1,
    "name": "France"
  },
  "language": {
    "id": 1,
    "name": "French"
  },
  "dateCreated": "2016-02-08T16:00:53Z"
}
```

<a name="incomingMessages"></a>
## GET /api/v1/user/:id/incomingMessages
Gets incoming messages for a user
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
    "description": "Un petit message pour mon pote",
    "creator": {
      "id": 1,
      "username": "PixnFit Administrator",
      "imageUrl": "http://www.pixnfit.com/image/show/65"
    },
    "recipient": {
      "id": 2,
      "username": "Theodore Cummings",
      "imageUrl": "http://www.pixnfit.com/image/show/29"
    },
    "dateCreated": "2016-02-08T20:03:07Z"
  }
]
```

<a name="outgoingMessages"></a>
## GET /api/v1/user/:id/outgoingMessages
Gets outgoing messages for a user
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
```json
[
  {
    "id": 2,
    "name": null,
    "description": "Un petit message pour mon pote",
    "creator": {
      "id": 1,
      "username": "PixnFit Administrator",
      "imageUrl": "http://www.pixnfit.com/image/show/65"
    },
    "recipient": {
      "id": 2,
      "username": "Theodore Cummings",
      "imageUrl": "http://www.pixnfit.com/image/show/29"
    },
    "dateCreated": "2016-02-08T20:05:19Z"
  }
]
```

<a name="posts"></a>
## GET /api/v1/user/:id/posts
Gets all posts created by this user
### Params
None (Id is included in URL)
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
      "imageUrl": "http://localhost:8080/pixnfit/image/show/29"
    },
    "images": [
      {
        "id": 1,
        "imageUrl": "http://localhost:8080/pixnfit/image/show/1"
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
}
```

<a name="postComments"></a>
## GET /api/v1/user/:id/postComments
Gets all comments created by this user
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
```json
[
  {
    "id": 143,
    "name": null,
    "description": "Facilis et iure autem ex. Dolore quisquam voluptatibus illo et ratione explicabo.",
    "postId": 7,
    "creator": {
      "id": 2,
      "username": "Theodore Cummings",
      "imageUrl": "http://localhost:8080/pixnfit/image/show/29"
    },
    "dateCreated": "2016-02-08T16:01:59Z"
  },
  ...
}
```

<a name="postVotes"></a>
## GET /api/v1/user/:id/postVotes
Gets all votes created by this user
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
```json
[
  {
    "id": 2,
    "vote": true,
    "voteReason": null,
    "post": {
      "id": 1,
      "name": "Aut tempore repellat vero et sed alias."
    },
    "creator": {
      "id": 2,
      "username": "Theodore Cummings",
      "imageUrl": "http://localhost:8080/pixnfit/image/show/29"
    },
    "dateCreated": "2016-02-08T16:01:59Z"
  },
  ...
}
```

<a name="followers"></a>
## GET /api/v1/user/:id/followers
Gets all user's followers
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
```json
[
  {
    "id": 1,
    "username": "Administrator",
    "description": "lorem ipsum...",
    "bodyType": {
      "id": 1,
      "name": "skinny"
    },
    "gender": {
      "id": 1,
      "name": "male"
    },
    "birthdate": "1985-02-01T00:00:00Z",
    "height": 185,
    "weight": 83,
    "imageUrl": "http://www.pixnfit.com/image/show/65",
    "country": {
      "id": 1,
      "name": "France"
    },
    "language": {
      "id": 1,
      "name": "French"
    },
    "dateCreated": "2016-02-08T16:00:53Z"
  },
  ...
]
```

<a name="follow"></a>
## POST /api/v1/user/:id/follow
Follow this user
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
```json
[
  {
    "id": 1,
    "username": "Administrator",
    "description": "lorem ipsum...",
    "bodyType": {
      "id": 1,
      "name": "skinny"
    },
    "gender": {
      "id": 1,
      "name": "male"
    },
    "birthdate": "1985-02-01T00:00:00Z",
    "height": 185,
    "weight": 83,
    "imageUrl": "http://www.pixnfit.com/image/show/65",
    "country": {
      "id": 1,
      "name": "France"
    },
    "language": {
      "id": 1,
      "name": "French"
    },
    "dateCreated": "2016-02-08T16:00:53Z"
  },
  ...
]
```

<a name="unfollow"></a>
## DELETE /api/v1/user/:id/follow
Unfollow this user
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
```json
[
  {
    "id": 1,
    "username": "Administrator",
    "description": "lorem ipsum...",
    "bodyType": {
      "id": 1,
      "name": "skinny"
    },
    "gender": {
      "id": 1,
      "name": "male"
    },
    "birthdate": "1985-02-01T00:00:00Z",
    "height": 185,
    "weight": 83,
    "imageUrl": "http://www.pixnfit.com/image/show/65",
    "country": {
      "id": 1,
      "name": "France"
    },
    "language": {
      "id": 1,
      "name": "French"
    },
    "dateCreated": "2016-02-08T16:00:53Z"
  },
  ...
]
```

<a name="followedUsers"></a>
## GET /api/v1/user/:id/followedUsers
Gets users followed by this user
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
```json
[
  {
    "id": 1,
    "username": "Administrator",
    "description": "lorem ipsum...",
    "bodyType": {
      "id": 1,
      "name": "skinny"
    },
    "gender": {
      "id": 1,
      "name": "male"
    },
    "birthdate": "1985-02-01T00:00:00Z",
    "height": 185,
    "weight": 83,
    "imageUrl": "http://www.pixnfit.com/image/show/65",
    "country": {
      "id": 1,
      "name": "France"
    },
    "language": {
      "id": 1,
      "name": "French"
    },
    "dateCreated": "2016-02-08T16:00:53Z"
  },
  ...
]
```

<a name="blacklistedUsers"></a>
## GET /api/v1/user/:id/blacklistedUsers
Gets users blaclisted by this user
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
```json
[
  {
    "id": 1,
    "username": "Administrator",
    "description": "lorem ipsum...",
    "bodyType": {
      "id": 1,
      "name": "skinny"
    },
    "gender": {
      "id": 1,
      "name": "male"
    },
    "birthdate": "1985-02-01T00:00:00Z",
    "height": 185,
    "weight": 83,
    "imageUrl": "http://www.pixnfit.com/image/show/65",
    "country": {
      "id": 1,
      "name": "France"
    },
    "language": {
      "id": 1,
      "name": "French"
    },
    "dateCreated": "2016-02-08T16:00:53Z"
  },
  ...
]
```

<a name="blacklistedBy"></a>
## GET /api/v1/user/:id/blacklistedBy
Gets users that blacklisted this user
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
```json
[
  {
    "id": 1,
    "username": "Administrator",
    "description": "lorem ipsum...",
    "bodyType": {
      "id": 1,
      "name": "skinny"
    },
    "gender": {
      "id": 1,
      "name": "male"
    },
    "birthdate": "1985-02-01T00:00:00Z",
    "height": 185,
    "weight": 83,
    "imageUrl": "http://www.pixnfit.com/image/show/65",
    "country": {
      "id": 1,
      "name": "France"
    },
    "language": {
      "id": 1,
      "name": "French"
    },
    "dateCreated": "2016-02-08T16:00:53Z"
  },
  ...
]
```

<a name="blacklist"></a>
## POST /api/v1/user/:id/blacklist
Blacklist this user
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
```json
[
  {
    "id": 1,
    "username": "Administrator",
    "description": "lorem ipsum...",
    "bodyType": {
      "id": 1,
      "name": "skinny"
    },
    "gender": {
      "id": 1,
      "name": "male"
    },
    "birthdate": "1985-02-01T00:00:00Z",
    "height": 185,
    "weight": 83,
    "imageUrl": "http://www.pixnfit.com/image/show/65",
    "country": {
      "id": 1,
      "name": "France"
    },
    "language": {
      "id": 1,
      "name": "French"
    },
    "dateCreated": "2016-02-08T16:00:53Z"
  },
  ...
]
```

<a name="unblacklist"></a>
## DELETE /api/v1/user/:id/blacklist
Un-blacklist this user
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
```json
[
  {
    "id": 1,
    "username": "Administrator",
    "description": "lorem ipsum...",
    "bodyType": {
      "id": 1,
      "name": "skinny"
    },
    "gender": {
      "id": 1,
      "name": "male"
    },
    "birthdate": "1985-02-01T00:00:00Z",
    "height": 185,
    "weight": 83,
    "imageUrl": "http://www.pixnfit.com/image/show/65",
    "country": {
      "id": 1,
      "name": "France"
    },
    "language": {
      "id": 1,
      "name": "French"
    },
    "dateCreated": "2016-02-08T16:00:53Z"
  },
  ...
]
```

