# ImageTypes API

<a name="index"></a>
## GET /api/v1/visibilities
Gets all visibilities
### Params
Yes :
 * **max** (int) : Max results in output, defaults to 10
 * **offset** (int) : Offset for results, defaults to 0

*Ex : /api/v1/visibilities?max=5&offset=2*
### Authentication
Yes : **HTTP BASIC**
### Output
```json
[
  {
    "id": 1,
    "name": "public",
    "description": null,
    "dateCreated": "2016-02-01T16:50:57Z"
  },
  {
    "id": 2,
    "name": "followers",
    "description": null,
    "dateCreated": "2016-02-01T16:50:57Z"
  },
  {
    "id": 3,
    "name": "private",
    "description": null,
    "dateCreated": "2016-02-01T16:50:57Z"
  }
]
```
<a name="show"></a>
## GET /api/v1/visibilities/:id
Gets a visibility by its Id
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
```json
{
  "id": 1,
  "name": "public",
  "description": null,
  "dateCreated": "2016-02-01T16:50:57Z"
}
```
