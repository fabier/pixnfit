# Messages API

<a name="show"></a>
## GET /api/v1/messages/:id
Gets a message by its Id
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
**HTTP 200 OK**
```json
{
    "id": 1,
    "name": null,
    "description": "Un petit message pour mon pote",
    "creator": {
        "id": 1,
        "username": "Thomas Bull",
        "image": {
          "id": 95,
          "imageUrl": "http://www.pixnfit.com/image/show/95?width=128&height=128"
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
**HTTP 201 Created**
```json
{
  "id": 1,
  "name": null,
  "description": "Contenu du message",
  "creator": {
    "id": 1,
    "username": "Thomas Bull",
    "image": {
      "id": 95,
      "imageUrl": "http://www.pixnfit.com/image/show/95?width=128&height=128"
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
}
```
