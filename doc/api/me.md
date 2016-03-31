# Me API

<a name="show"></a>
## GET /api/v1/me
Gets informations about authenticated user
### Params
None
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


<a name="addToFashionStyles"></a>
## POST /api/v1/me/fashionStyles
Adds fashion styles for User
### Params
List of fashionStyle ids
```json
{
  fashionStyles : [
    {
      id: 1
    },
    ...
  ]
}
```
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

<a name="removeFromFashionStyles"></a>
## DELETE /api/v1/me/fashionStyles
Removes fashion styles for User
### Params
List of fashionStyle ids
```json
{
  fashionStyles : [
    {
      id: 1
    },
    ...
  ]
}
```
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
