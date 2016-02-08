# pixnfit
PixnFit project made with Grails

2 parts in this document :
 * [API Documentation](#API) : Documentation made for API users.
 * [Contributor Documentation](#Contrib) : Documentation made for developpers aiming to edit and commit code for this project.

<a name="API"></a>
# API Documentation

 **All calls must be made using HTTP BASIC Authentication.**

| Method | URL | Description |
|---|---|---|
|| **Authentication**  ||
| POST | [/api/v1/auth](doc/api/auth) | Test a login/password |
||  **Image**  ||
| GET | [/api/v1/images/${id}](doc/api/images#show) |  by its Id |
| POST | [/api/v1/images](doc/api/images#save) | Creates a new image |
| PUT | [/api/v1/images/${id}](doc/api/images#update) | Updates an image |
| DELETE | [/api/v1/images/${id}](doc/api/images#delete) | Deletes an image |
| GET | [/api/v1/images/${id}/data](doc/api/image#data) | Gets an image data by its Id|
||  **Message**  ||
| GET | [/api/v1/messages/${id}](doc/api/messages#show) | Gets a message by its Id |
| POST | [/api/v1/messages](doc/api/messages#save) | Creates a new message |
||  **Post**  ||
| POST | [/api/v1/posts](doc/api/post) | Creates a new post |
| GET | [/api/v1/posts/${id}](doc/api/post) | Gets a post by its Id |
| PUT | [/api/v1/posts/${id}](doc/api/post) | Updates a post |
| DELETE | [/api/v1/posts/${id}](doc/api/post) | Deletes a post |
| GET | [/api/v1/posts/${id}/comments](doc/api/post) | Gets comments on a post |
| POST | [/api/v1/posts/${id}/comments](doc/api/post) | Creates a new comment on a post |
| GET | [/api/v1/posts/${id}/votes](doc/api/post) | Gets votes on a post |
| POST | [/api/v1/posts/${id}/votes](doc/api/post) | Creates a vote on a post |
| GET | [/api/v1/posts/help](doc/api/post) | Gets posts needing help and votes |
| GET | [/api/v1/posts/featured](doc/api/post) | Gets featured posts |
||  **PostComment**  ||
| GET | [/api/v1/postComments/${id}](doc/api/postComment) | Gets a comment by its Id |
| GET | [/api/v1/postComment/${id}/votes](doc/api/postComment) | Gets votes on a comment |
| POST | [/api/v1/postComment/${id}/votes](doc/api/postComment) | Creates a vote on a comment |
||  **User**  ||
| POST | [/api/v1/user](doc/api/user) | Creates a new user |
| GET | [/api/v1/user/${id}](doc/api/user) | Gets a user by its Id |
| PUT | [/api/v1/user/${id}](doc/api/user) | Updates a user |
| DELETE | [/api/v1/user/${id}](doc/api/user) | Deletes a user |
| GET | [/api/v1/user/${id}/incomingMessages](doc/api/user) | Gets incoming messages for a user |
| GET | [/api/v1/user/${id}/outgoingMessages](doc/api/user) | Gets outgoing messages for a user |
| GET | [/api/v1/user/${id}/posts](doc/api/user) | Gets all posts created by this user |
| GET | [/api/v1/user/${id}/postComments](doc/api/user) | Gets all comments created by this user |
| GET | [/api/v1/user/${id}/postVotes](doc/api/user) | Gets all votes created by this user |
| GET | [/api/v1/user/${id}/followers](doc/api/user) | Gets all user's followers |
| POST | [/api/v1/user/${id}/follow](doc/api/user) | Follow this user |
| DELETE | [/api/v1/user/${id}/follow](doc/api/user) | Unfollow this user |
| GET | [/api/v1/user/${id}/followedUsers](doc/api/user) | Gets users followed by this user |
| GET | [/api/v1/user/${id}/blacklistedUsers](doc/api/user) | Gets users blaclisted by this user |
| GET | [/api/v1/user/${id}/blacklistedBy](doc/api/user) | Gets users that blacklisted this user |
| POST | [/api/v1/user/${id}/blacklist](doc/api/user) | Blacklist this user |
| DELETE | [/api/v1/user/${id}/blacklist](doc/api/user) | Un-blacklist this user |



| Method | URL | Description |
|---|---|---|
||  **BodyType**  ||
| GET | [/api/v1/bodyTypes](doc/api/bodyTypes#index) | Gets all body types |
| GET | [/api/v1/bodyTypes/${id}](doc/api/bodyTypes#show) | Gets a body type by ts Id|
||  **Country**  ||
| GET | [/api/v1/countries](doc/api/countries) | Gets all countries |
| GET | [/api/v1/countries/${id}](doc/api/countries) | Gets a country by ts Id |
||  **FashionStyle**  ||
| GET | [/api/v1/fashionStyles](doc/api/fashionStyles#index) | Gets all fashion styles |
| GET | [/api/v1/fashionStyles/${id}](doc/api/fashionStyles#show) | Gets a fashion style by its Id|
||  **Gender**  ||
| GET | [/api/v1/genders](doc/api/genders#index) | Gets all genders |
| GET | [/api/v1/genders/${id}](doc/api/genders#show) | Gets a gender by its Id |
||  **ImageType**  ||
| GET | [/api/v1/imageTypes](doc/api/) | Gets all image types |
| GET | [/api/v1/imageTypes/${id}](doc/api/) | Gets an image type by its Id |
||  **Language**  ||
| GET | [/api/v1/languages](doc/api/) | Gets all languages |
| GET | [/api/v1/languages/${id}](doc/api/) | Gets a language by its Id |
||  **PostType**  ||
| GET | [/api/v1/postType](doc/api/) | Gets all post types |
| GET | [/api/v1/postType/${id}](doc/api/) | Gets a post type by its Id |
||  **State**  ||
| GET | [/api/v1/state](doc/api/) | Gets all states |
| GET | [/api/v1/state/${id}](doc/api/) | Gets a state by its Id |
||  **Visibility**  ||
| GET | [/api/v1/visibilities](doc/api/) | Gets all visibilities |
| GET | [/api/v1/visibilities/${id}](doc/api/) | Gets a visibility by its Id |
||  **VoteReason**  ||
| GET | [/api/v1/voteReasons](doc/api/) | Gets all vote reasons |
| GET | [/api/v1/voteReasons/${id}](doc/api/) | Gets a vote reason by its Id |



 <a name="Contrib"></a>
**PLEASE READ CAREFULLY, THERE ARE THINGS TO DO BEFORE LAUNCHING APPLICATION**

# Things to do BEFORE launching app
## Configuration
You need to create a file in <code>/grails-app/conf</code> named <code>passwords.properties</code>

**YOU MUST CHANGE THE VALUES BEFORE STARTING**
<br/>
This file must contain the following keys.

> passwords.properties

```
# Admin user
admin.username=My Name // This is NOT the real admin name, change it !
admin.email=email@somesite.com // <= This is NOT the real admin email, change it !
admin.password=5Fn7Mcs1dU9i1s9u1H69t // <= This is NOT the real password, change it !

# Email account to send emails
grails.mail.username=noreply@somesite.com // <= This is NOT the real email account, change it !
grails.mail.password=CW9M4R92OGH75sny533cT // <= This is NOT the real password, change it !

# Database password
dataSource.username=someuser // <= This is NOT the real username, change it !
dataSource.password=4g2ws366gM875MQB478EU // <= This is NOT the real password, change it !
```
