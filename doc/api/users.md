# Users API

<a name="save"></a>
## POST /api/v1/users
*User account creation step 1 - required*
Creates a new user
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
User must have an locked account, meaning user never clicked on email "account creation confirmation" link.
### Output
**HTTP 201 Created**
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
  "image": null,
  "country": null,
  "language": null,
  "fashionStyles": null,
  "points": 0,
  "postCount": 1,
  "followersCount": 0,
  "followedCount": 0,
  "dateCreated": "2016-02-12T10:18:06Z"
}
```


<a name="initImage"></a>
## POST /api/v1/users/:id/initImage
*User account creation step 2 - optional*
Initializes a User profile image by uploading it.
### Params
Data file must be pushed, in HTML this can be a <code>form</code> like this :
```HTML
<form action="/api/v1/users/:id/initImage">
  <input name="data" type="file"></input>
  <button type="submit">Submit</button>
</form>
```
### Authentication
**No authentication to set user image**<br/>
User must have an locked account, meaning user never clicked on email "account creation confirmation" link.
### Output
**HTTP 200 OK**
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
  "image": {
    "id": 415,
    "imageUrl": "http://www.pixnfit.com/image/show/415?width=128&height=128"
  },
  "country": null,
  "language": null,
  "fashionStyles": null,
  "points": 0,
  "postCount": 0,
  "followersCount": 0,
  "followedCount": 0,
  "dateCreated": "2016-02-12T10:18:06Z"
}
```


<a name="initProfile"></a>
## POST /api/v1/users/:id/initProfile
*User account creation step 3 - required*
Initializes a user profile
An email is automatically sent to the user to validate its account.
The user must click on the link to validate the account creation (spam prevention).
### Params
```json
{
    "description": "lorem ipsum...",
    "bodyTypeId": 2,
    "genderId": 1,
    "birthdate": "1985-02-01T00:00:00Z",
    "height": 185,
    "weight": 83,
    "countryId": 3,
    "languageId": 2
}
```
### Authentication
**No authentication set initial user profile**<br/>
User must have an locked account, meaning user never clicked on email "account creation confirmation" link.
### Output
**HTTP 200 OK**
```json
{
  "id": 8,
  "username": "John Doe",
  "description": "lorem ipsum...",
  "bodyType": {
    "id": 2,
    "name": "average"
  },
  "gender": {
    "id": 1,
    "name": "male"
  },
  "birthdate": "1985-02-01T00:00:00Z",
  "height": 185,
  "weight": 83,
  "image": {
    "id": 415,
    "imageUrl": "http://www.pixnfit.com/image/show/415?width=128&height=128"
  },
  "country": {
    "id": 3,
    "name": "USA"
  },
  "language": {
    "id": 2,
    "name": "English"
  },
  "fashionStyles": [{id: 1, name: "Rock"}, ...],
  "points": 0,
  "postCount": 0,
  "followersCount": 0,
  "followedCount": 0,
  "dateCreated": "2016-02-12T10:18:06Z"
}
```


<a name="show"></a>
## GET /api/v1/users/:id
Gets a user by its Id
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
**HTTP 200 OK**
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
  "image": {
    "id": 65,
    "imageUrl": "http://www.pixnfit.com/image/show/65?width=128&height=128"
  },
  "country": {
    "id": 3,
    "name": "USA"
  },
  "language": {
    "id": 2,
    "name": "English"
  },
  "fashionStyles": [{id: 1, name: "Rock"}, ...],
  "points": 0,
  "postCount": 0,
  "followersCount": 0,
  "followedCount": 0,
  "dateCreated": "2016-02-08T16:00:53Z"
}
```

<a name="update"></a>
## PUT /api/v1/users/:id
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
**HTTP 200 OK**
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
  "image": {
    "id": 65,
    "imageUrl": "http://www.pixnfit.com/image/show/65?width=128&height=128"
  },
  "country": {
    "id": 1,
    "name": "France"
  },
  "language": {
    "id": 1,
    "name": "French"
  },
  "fashionStyles": [{id: 1, name: "Rock"}, ...],
  "points": 0,
  "postCount": 0,
  "followersCount": 0,
  "followedCount": 0,
  "dateCreated": "2016-02-08T16:00:53Z"
}
```

<a name="incomingMessages"></a>
## GET /api/v1/users/:id/incomingMessages
Gets incoming messages for a user
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
**HTTP 200 OK**
```json
[
  {
    "id": 1,
    "name": null,
    "description": "Un petit message pour mon pote",
    "creator": {
      "id": 1,
      "username": "PixnFit Administrator",
      "image": {
        "id": 65,
        "imageUrl": "http://www.pixnfit.com/image/show/65?width=128&height=128"
      }
    },
    "recipient": {
      "id": 2,
      "username": "Theodore Cummings",
      "image": {
        "id": 29,
        "imageUrl": "http://www.pixnfit.com/image/show/29?width=128&height=128"
      }
    },
    "dateCreated": "2016-02-08T20:03:07Z"
  },
  ...
]
```

<a name="outgoingMessages"></a>
## GET /api/v1/users/:id/outgoingMessages
Gets outgoing messages for a user
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
**HTTP 200 OK**
```json
[
  {
    "id": 2,
    "name": null,
    "description": "Un petit message pour mon pote",
    "creator": {
      "id": 1,
      "username": "PixnFit Administrator",
      "image": {
        "id": 65,
        "imageUrl": "http://www.pixnfit.com/image/show/65?width=128&height=128"
      }
    },
    "recipient": {
      "id": 2,
      "username": "Theodore Cummings",
      "image": {
        "id": 29,
        "imageUrl": "http://www.pixnfit.com/image/show/29?width=128&height=128"
      }
    },
    "dateCreated": "2016-02-08T20:05:19Z"
  },
  ...
]
```

<a name="posts"></a>
## GET /api/v1/users/:id/posts
Gets all posts created by this user
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
**HTTP 200 OK**
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
}
```


<a name="dressingPosts"></a>
## GET /api/v1/users/:id/posts/dressing
Gets only dressing posts created by this user
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
**HTTP 200 OK**
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
}
```


<a name="helpPosts"></a>
## GET /api/v1/users/:id/posts/help
Gets only help posts created by this user
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
**HTTP 200 OK**
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
}
```

<a name="postComments"></a>
## GET /api/v1/users/:id/postComments
Gets all comments created by this user
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
**HTTP 200 OK**
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
      "image": {
        "id": 29,
        "imageUrl": "http://www.pixnfit.com/image/show/29?width=128&height=128"
      }
    },
    "dateCreated": "2016-02-08T16:01:59Z"
  },
  ...
}
```

<a name="postVotes"></a>
## GET /api/v1/users/:id/postVotes
Gets all votes created by this user
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
**HTTP 200 OK**
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
      "image": {
        "id": 29,
        "imageUrl": "http://www.pixnfit.com/image/show/29?width=128&height=128"
      }
    },
    "dateCreated": "2016-02-08T16:01:59Z"
  },
  ...
}
```

<a name="followers"></a>
## GET /api/v1/users/:id/followers
Gets all user's followers
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
**HTTP 200 OK**
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
    "image": {
      "id": 65,
      "imageUrl": "http://www.pixnfit.com/image/show/65?width=128&height=128"
    },
    "country": {
      "id": 1,
      "name": "France"
    },
    "language": {
      "id": 1,
      "name": "French"
    },
    "fashionStyles": [{id: 1, name: "Rock"}, ...],
    "points": 0,
    "postCount": 0,
    "followersCount": 0,
    "followedCount": 0,
    "dateCreated": "2016-02-08T16:00:53Z"
  },
  ...
]
```

<a name="follow"></a>
## POST /api/v1/users/:id/follow
Follow this user
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
**HTTP 200 OK**
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
    "image": {
      "id": 65,
      "imageUrl": "http://www.pixnfit.com/image/show/65?width=128&height=128"
    },
    "country": {
      "id": 1,
      "name": "France"
    },
    "language": {
      "id": 1,
      "name": "French"
    },
    "fashionStyles": [{id: 1, name: "Rock"}, ...],
    "points": 0,
    "postCount": 0,
    "followersCount": 0,
    "followedCount": 0,
    "dateCreated": "2016-02-08T16:00:53Z"
  },
  ...
]
```

<a name="unfollow"></a>
## DELETE /api/v1/users/:id/follow
Unfollow this user
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
**HTTP 200 OK**
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
    "image": {
      "id": 65,
      "imageUrl": "http://www.pixnfit.com/image/show/65?width=128&height=128"
    },
    "country": {
      "id": 1,
      "name": "France"
    },
    "language": {
      "id": 1,
      "name": "French"
    },
    "fashionStyles": [{id: 1, name: "Rock"}, ...],
    "points": 0,
    "postCount": 0,
    "followersCount": 0,
    "followedCount": 0,
    "dateCreated": "2016-02-08T16:00:53Z"
  },
  ...
]
```

<a name="followedUsers"></a>
## GET /api/v1/users/:id/followedUsers
Gets users followed by this user
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
**HTTP 200 OK**
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
    "image": {
      "id": 65,
      "imageUrl": "http://www.pixnfit.com/image/show/65?width=128&height=128"
    },
    "country": {
      "id": 1,
      "name": "France"
    },
    "language": {
      "id": 1,
      "name": "French"
    },
    "fashionStyles": [{id: 1, name: "Rock"}, ...],
    "points": 0,
    "postCount": 0,
    "followersCount": 0,
    "followedCount": 0,
    "dateCreated": "2016-02-08T16:00:53Z"
  },
  ...
]
```

<a name="blacklistedUsers"></a>
## GET /api/v1/users/:id/blacklistedUsers
Gets users blaclisted by this user
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
**HTTP 200 OK**
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
    "image": {
      "id": 65,
      "imageUrl": "http://www.pixnfit.com/image/show/65?width=128&height=128"
    },
    "country": {
      "id": 1,
      "name": "France"
    },
    "language": {
      "id": 1,
      "name": "French"
    },
    "fashionStyles": [{id: 1, name: "Rock"}, ...],
    "points": 0,
    "postCount": 0,
    "followersCount": 0,
    "followedCount": 0,
    "dateCreated": "2016-02-08T16:00:53Z"
  },
  ...
]
```

<a name="blacklistedBy"></a>
## GET /api/v1/users/:id/blacklistedBy
Gets users that blacklisted this user
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
**HTTP 200 OK**
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
    "image": {
      "id": 65,
      "imageUrl": "http://www.pixnfit.com/image/show/65?width=128&height=128"
    },
    "country": {
      "id": 1,
      "name": "France"
    },
    "language": {
      "id": 1,
      "name": "French"
    },
    "fashionStyles": [{id: 1, name: "Rock"}, ...],
    "points": 0,
    "postCount": 0,
    "followersCount": 0,
    "followedCount": 0,
    "dateCreated": "2016-02-08T16:00:53Z"
  },
  ...
]
```

<a name="blacklist"></a>
## POST /api/v1/users/:id/blacklist
Blacklist this user
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
**HTTP 200 OK**
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
    "image": {
      "id": 65,
      "imageUrl": "http://www.pixnfit.com/image/show/65?width=128&height=128"
    },
    "country": {
      "id": 1,
      "name": "France"
    },
    "language": {
      "id": 1,
      "name": "French"
    },
    "fashionStyles": [{id: 1, name: "Rock"}, ...],
    "points": 0,
    "postCount": 0,
    "followersCount": 0,
    "followedCount": 0,
    "dateCreated": "2016-02-08T16:00:53Z"
  },
  ...
]
```

<a name="unblacklist"></a>
## DELETE /api/v1/users/:id/blacklist
Un-blacklist this user
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
**HTTP 200 OK**
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
    "image": {
      "id": 65,
      "imageUrl": "http://www.pixnfit.com/image/show/65?width=128&height=128"
    },
    "country": {
      "id": 1,
      "name": "France"
    },
    "language": {
      "id": 1,
      "name": "French"
    },
    "fashionStyles": [{id: 1, name: "Rock"}, ...],
    "points": 0,
    "postCount": 0,
    "followersCount": 0,
    "followedCount": 0,
    "dateCreated": "2016-02-08T16:00:53Z"
  },
  ...
]
```


<a name="initImage"></a>
## POST /api/v1/users/:id/image
Updaes a user profile image by uploading it.
### Params
Data file must be pushed, in HTML this can be a <code>form</code> like this :
```HTML
<form action="/api/v1/users/:id/image">
  <input name="data" type="file"></input>
  <button type="submit">Submit</button>
</form>
```
### Authentication
Yes : **HTTP BASIC**
### Output
**HTTP 200 OK**
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
  "image": {
    "id": 415,
    "imageUrl": "http://www.pixnfit.com/image/show/415?width=128&height=128"
  },
  "country": null,
  "language": null,
  "fashionStyles": null,
  "points": 0,
  "postCount": 0,
  "followersCount": 0,
  "followedCount": 0,
  "dateCreated": "2016-02-12T10:18:06Z"
}
```

<a name="fashionStyles"></a>
## GET /api/v1/users/:id/fashionStyles
Gets fashion styles for User
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
**HTTP 200 OK**
```json
[
  {
    id: 1,
    name: "Rock"
  },
  ...
]
```
