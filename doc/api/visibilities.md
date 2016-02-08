# ImageTypes API

<a name="index"></a>
## GET /api/v1/visibilities
Gets all visibilities
### Params
None
### Output
```json
[
  {
    "id": 1,
    "name": "JPEG",
    "description": null,
    "dateCreated": "2016-02-04T13:44:17Z"
  },
  {
    "id": 2,
    "name": "PNG",
    "description": null,
    "dateCreated": "2016-02-04T13:44:17Z"
  },
  {
    "id": 3,
    "name": "GIF",
    "description": null,
    "dateCreated": "2016-02-04T13:44:17Z"
  }
]
```
<a name="show"></a>
## GET /api/v1/visibilities/:id
Gets a visibility by its Id
### Params
None (Id is included in URL)
### Authentication
YES : HTTP BASIC
### Output
```json
{
  "id": 1,
  "name": "JPEG",
  "description": null,
  "dateCreated": "2016-02-04T13:44:17Z"
}
```
