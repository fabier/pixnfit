# ImageTypes API

<a name="index"></a>
## GET /api/v1/imageTypes
Gets all imageTypes
### Params
Yes :
 * **max** (int) : Max results in output, defaults to 10
 * **offset** (int) : Offset for results, defaults to 0

*Ex : /api/v1/imageTypes?max=5&offset=2*
### Authentication
Yes : **HTTP BASIC**
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
  },
  ...
]
```
<a name="show"></a>
## GET /api/v1/imageTypes/:id
Gets an imageType by its Id
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
```json
{
  "id": 1,
  "name": "JPEG",
  "description": null,
  "dateCreated": "2016-02-04T13:44:17Z"
}
```
