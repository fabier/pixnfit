# pixnfit
PixnFit project made with Grails

2 parts in this document :
 * [API Documentation](#API) : Documentation made for API users.
 * [Contributor Documentation](#Contrib) : Documentation made for developpers aiming to edit and commit code for this project.

<a name="API"></a>
# API Documentation

 **All calls must be made using HTTP BASIC Authentication.**


## Dynamic Datas

| Method | URL | Description |
|---|---|---|
| | **Authentication**  |  |
| POST | [/api/v1/auth](doc/api/auth.md#login) | Test a login/password |
| | **Image**  | |
| GET | [/api/v1/images/:id](doc/api/images.md#show) | Gets an image by its Id |
| POST | [/api/v1/images](doc/api/images.md#save) | Creates a new image |
| PUT | [/api/v1/images/:id](doc/api/images.md#update) | Updates an image |
| DELETE | [/api/v1/images/:id](doc/api/images.md#delete) | Deletes an image |
| GET | [/api/v1/images/:id/data](doc/api/images.md#data) | Gets an image data by its Id|
| | **Message**  | |
| GET | [/api/v1/messages/:id](doc/api/messages#show) | Gets a message by its Id |
| POST | [/api/v1/messages](doc/api/messages#save) | Creates a new message |
| | **Post**  | |
| POST | [/api/v1/posts](doc/api/posts.md#save) | Creates a new post |
| GET | [/api/v1/posts/:id](doc/api/posts.md#show) | Gets a post by its Id |
| PUT | [/api/v1/posts/:id](doc/api/posts.md#update) | Updates a post |
| DELETE | [/api/v1/posts/:id](doc/api/posts.md#delete) | Deletes a post |
| GET | [/api/v1/posts/:id/me](doc/api/posts.md#me) | Gets user-related information about a post |
| POST | [/api/v1/posts/:id/favorite](doc/api/posts.md#addToFavorite) | Adds a post to favorite list |
| DELETE | [/api/v1/posts/:id/favorite](doc/api/posts.md#removeFromFavorite) | Removes a post from favorite list |
| POST | [/api/v1/posts/:id/images](doc/api/posts.md#addImage) | Adds an existing image to a post |
| GET | [/api/v1/posts/:id/comments](doc/api/posts.md#comments) | Gets comments on a post |
| POST | [/api/v1/posts/:id/comments](doc/api/posts.md#addComment) | Creates a new comment on a post |
| GET | [/api/v1/posts/:id/votes](doc/api/posts.md#votes) | Gets votes on a post |
| POST | [/api/v1/posts/:id/votes](doc/api/posts.md#addVote) | Creates a vote on a post |
| GET | [/api/v1/posts/help](doc/api/posts.md#help) | Gets posts needing help and votes |
| GET | [/api/v1/posts/featured](doc/api/posts.md#featured) | Gets featured posts |
| | **PostComment**  | |
| GET | [/api/v1/postComments/:id](doc/api/postComments.md#show) | Gets a comment by its Id |
| GET | [/api/v1/postComments/:id/votes](doc/api/postComments.md#votes) | Gets votes on a comment |
| POST | [/api/v1/postComments/:id/votes](doc/api/postComments.md#addVote) | Creates a vote on a comment |
| | **User**  | **About me** |
| GET | [/api/v1/me](doc/api/me.md#show) | Gets authenticated user informations |
| GET | [/api/v1/me/fashionStyles](doc/api/me.md#fashionStyles) | Gets fashion styles for User |
| POST | [/api/v1/me/fashionStyles](doc/api/me.md#addToFashionStyles) | Adds fashion styles for User |
| DELETE | [/api/v1/me/fashionStyles](doc/api/me.md#removeFromFashionStyles) | Removes fashion styles for User |
| | **User**  | **Creation process** |
| POST | [/api/v1/users](doc/api/users.md#save) | Creates a new user (step 1) |
| POST | [/api/v1/users/:id/initImage](doc/api/users.md#initImage) | Upload User Image (step 2) |
| POST | [/api/v1/users/:id/initProfile](doc/api/users.md#initProfile) | Creates a user profile (step 3) |
| | **User** | **Other methods** |
| GET | [/api/v1/users/:id](doc/api/users.md#show) | Gets a user by its Id |
| PUT | [/api/v1/users/:id](doc/api/users.md#update) | Updates a user |
| GET | [/api/v1/users/:id/incomingMessages](doc/api/users.md#incomingMessages) | Gets incoming messages for a user |
| GET | [/api/v1/users/:id/outgoingMessages](doc/api/users.md#outgoingMessages) | Gets outgoing messages for a user |
| GET | [/api/v1/users/:id/posts](doc/api/users.md#posts) | Gets all posts created by this user |
| GET | [/api/v1/users/:id/posts/dressing](doc/api/users.md#dressingPosts) | Gets only dressing posts created by this user |
| GET | [/api/v1/users/:id/posts/help](doc/api/users.md#helpPosts) | Gets only help posts created by this user |
| GET | [/api/v1/users/:id/postComments](doc/api/users.md#comments) | Gets all comments created by this user |
| GET | [/api/v1/users/:id/postVotes](doc/api/users.md#votes) | Gets all votes created by this user |
| GET | [/api/v1/users/:id/followers](doc/api/users.md#followers) | Gets all user's followers |
| POST | [/api/v1/users/:id/follow](doc/api/users.md#follow) | Follow this user |
| DELETE | [/api/v1/users/:id/follow](doc/api/users.md#unfollow) | Unfollow this user |
| GET | [/api/v1/users/:id/followedUsers](doc/api/users.md#followedUsers) | Gets users followed by this user |
| GET | [/api/v1/users/:id/blacklistedUsers](doc/api/users.md#blacklistedUsers) | Gets users blaclisted by this user |
| GET | [/api/v1/users/:id/blacklistedBy](doc/api/users.md#blacklistedBy) | Gets users that blacklisted this user |
| POST | [/api/v1/users/:id/blacklist](doc/api/users.md#blacklist) | Blacklist this user |
| DELETE | [/api/v1/users/:id/blacklist](doc/api/users.md#unblacklist) | Un-blacklist this user |
| POST | [/api/v1/users/:id/image](doc/api/users.md#image) | Updates User image |

## Static Datas

| Method | URL | Description |
|---|---|---|
| |  **BodyType**  | |
| GET | [/api/v1/bodyTypes](doc/api/bodyTypes.md#index) | Gets all body types |
| GET | [/api/v1/bodyTypes/:id](doc/api/bodyTypes.md#show) | Gets a body type by ts Id|
| |  **Country**  | |
| GET | [/api/v1/countries](doc/api/countries.md#index) | Gets all countries |
| GET | [/api/v1/countries/:id](doc/api/countries.md#show) | Gets a country by ts Id |
| |  **FashionStyle**  | |
| GET | [/api/v1/fashionStyles](doc/api/fashionStyles.md#index) | Gets all fashion styles |
| GET | [/api/v1/fashionStyles/:id](doc/api/fashionStyles.md#show) | Gets a fashion style by its Id|
| |  **Gender**  | |
| GET | [/api/v1/genders](doc/api/genders.md#index#index) | Gets all genders |
| GET | [/api/v1/genders/:id](doc/api/genders.md#show) | Gets a gender by its Id |
| |  **ImageType**  | |
| GET | [/api/v1/imageTypes](doc/api/imageTypes.md#index) | Gets all image types |
| GET | [/api/v1/imageTypes/:id](doc/api/imageTypes.md#show) | Gets an image type by its Id |
| |  **Language**  | |
| GET | [/api/v1/languages](doc/api/languages.md#index) | Gets all languages |
| GET | [/api/v1/languages/:id](doc/api/languages.md#show) | Gets a language by its Id |
| |  **PostType**  | |
| GET | [/api/v1/postType](doc/api/postTypes.md#index) | Gets all post types |
| GET | [/api/v1/postType/:id](doc/api/postTypes.md#show) | Gets a post type by its Id |
| |  **State**  | |
| GET | [/api/v1/state](doc/api/states.md#index) | Gets all states |
| GET | [/api/v1/state/:id](doc/api/states.md#show) | Gets a state by its Id |
| |  **Visibility**  | |
| GET | [/api/v1/visibilities](doc/api/visibilities.md#index) | Gets all visibilities |
| GET | [/api/v1/visibilities/:id](doc/api/visibilities.md#show) | Gets a visibility by its Id |
| |  **VoteReason**  | |
| GET | [/api/v1/voteReasons](doc/api/voteReasons.md#index) | Gets all vote reasons |
| GET | [/api/v1/voteReasons/:id](doc/api/voteReasons.md#show) | Gets a vote reason by its Id |


 <a name="Contrib"></a>
**PLEASE READ CAREFULLY, THERE ARE THINGS TO DO BEFORE LAUNCHING APPLICATION**

# Things to do BEFORE launching app
## Prerequisites
You must have the following installed on your computer
 * [Ubuntu 14.04](doc/developer/install.md#ubuntu)
 * [Java 7](doc/developer/install.md#java)
 * [PostgreSQL 9.4](doc/developer/install.md#postgresql)
 * [Grails 2.4.5](doc/developer/install.md#grails)
 * [IntellijIDEA 14.1.6](doc/developer/install.md#intellijidea)
 * [Git 1.9.1](doc/developer/install.md#git)
 * [Source code](doc/developer/install.md#source)

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
