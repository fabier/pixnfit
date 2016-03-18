# Images API

<a name="show"></a>
## GET /api/v1/images/:id
Gets a image by its Id
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
```json
{
  "id": 1,
  "name": "selfie fashion (256x256)",
  "description": null,
  "imageUrl": "http://www.pixnfit.com/image/show/1?width=256&height=256",
  "creator": {
    "id": 2,
    "username": "Keith Nitzsche",
    "image": {
      "id": 95,
      "imageUrl": "http://www.pixnfit.com/image/show/95?width=128&height=128"
    }
  },
  "filename": "loremflickr_selfie_fashion.jpg",
  "dateCreated": "2016-02-01T16:52:41Z"
}
```

<a name="save"></a>
## POST /api/v1/images
Creates an Image
### Params
Data file must be pushed, in HTML this can be a <code>form</code> like this :
```HTML
<form action="/api/v1/images">
  <input name="data" type="file"></input>
  <button type="submit">Submit</button>
</form>
```
### Authentication
Yes : **HTTP BASIC**
### Output
**HTTP 201 Created**
```json
{
  "id": 1,
  "name": "loremflickr_selfie_fashion.jpg",
  "description": null,
  "imageUrl": "http://www.pixnfit.com/image/show/1?width=256&height=256",
  "creator": {
    "id": 2,
    "username": "Keith Nitzsche",
    "image": {
      "id": 95,
      "imageUrl": "http://www.pixnfit.com/image/show/95?width=128&height=128"
    }
  },
  "filename": "loremflickr_selfie_fashion.jpg",
  "dateCreated": "2016-02-01T16:52:41Z"
}
```

<a name="update"></a>
## PUT /api/v1/images/:id
Updates an existing Image metadata
### Params
```json
{
  "name": "Some random name",
  "description": "A nice random picture",
  "filename": "PIC210.jpg"
}
```
### Authentication
Yes : **HTTP BASIC**
### Output
```json
{
  "id": 1,
  "name": "Some random name",
  "description": "A nice random picture",
  "imageUrl": "http://www.pixnfit.com/image/show/1?width=256&height=256",
  "creator": {
    "id": 2,
    "username": "Keith Nitzsche",
    "image": {
      "id": 95,
      "imageUrl": "http://www.pixnfit.com/image/show/95?width=128&height=128"
    }
  },
  "filename": "PIC210.jpg",
  "dateCreated": "2016-02-01T16:52:41Z"
}
```

<a name="delete"></a>
## DELETE /api/v1/images/:id
Deletes an existing Image
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
HTTP 204 No Content

<a name="data"></a>
## GET /api/v1/images/:id/data
Gets image data by its Id
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
```json
{
    "id": 1,
    "md5": "1b36fb1060dd421eee1ea1f6310f1d52",
    "width": 256,
    "height": 256,
    "imageType": {
      "id": 1,
      "name": "JPEG"
    },
    "dateCreated": "2016-02-01T16:52:41Z"
}
```
