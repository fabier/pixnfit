# Users API

<a name="save"></a>
## POST /api/v1/user
Creates a new user
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
```json
{
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
}
```

<a name="update"></a>
## PUT /api/v1/user/:id
Updates a user
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
```json
{
}
```

<a name="delete"></a>
## DELETE /api/v1/user/:id
Deletes a user
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
```json
{
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
{
}
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
{
}
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
{
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
{
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
{
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
{
}
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
{
}
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
{
}
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
{
}
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
{
}
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
{
}
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
{
}
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
{
}
```

