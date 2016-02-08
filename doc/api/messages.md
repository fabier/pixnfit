# Messages API

<a name="show"></a>
## GET /api/v1/messages/:id
Gets a message by its Id
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
```json
{
    "id": 1,
    "name": null,
    "description": "Un petit message pour mon pote",
    "creator": {
        "id": 1,
        "username": "Thomas Bull",
        "imageUrl": null
    },
    "recipient": {
        "id": 2,
        "username": "Theodore Cummings",
        "imageUrl": "http://www.pixnfit.com/image/show/29"
    },
    "dateCreated": "2016-02-08T20:03:07Z"
}
```

<a name="create"></a>
## POST /api/v1/messages
Creates a new message
### Params
```json
{
    "description" : "Contenu du message",
    "recipientId" : 2
}
```
### Authentication
Yes : **HTTP BASIC**
### Output
```json
{
  "id": 1,
  "name": null,
  "description": "Contenu du message",
  "creator": {
    "id": 1,
    "username": "Thomas Bull",
    "imageUrl": null
  },
  "recipient": {
    "id": 2,
    "username": "Theodore Cummings",
    "imageUrl": "http://www.pixnfit.com/image/show/29"
  },
  "dateCreated": "2016-02-08T20:03:07Z"
}
```
