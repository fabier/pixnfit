# BodyTypes API

<a name="index"></a>
## GET /api/v1/bodyTypes
Gets all bodyTypes
### Params
Yes :
 * **max** (int) : Max results in output, defaults to 10
 * **offset** (int) : Offset for results, defaults to 0

*Ex : /api/v1/bodyTypes?max=5&offset=2*
### Authentication
Yes : **HTTP BASIC**
### Output
**HTTP 200 OK**
```json
[
  {
    "id": 3,
    "name": "big",
    "description": null,
    "dateCreated": "2016-02-01T16:50:57Z"
  },
  {
    "id": 2,
    "name": "average",
    "description": null,
    "dateCreated": "2016-02-01T16:50:57Z"
  },
  {
    "id": 1,
    "name": "skinny",
    "description": null,
    "dateCreated": "2016-02-01T16:50:57Z"
  },
  ...
]
```


<a name="show"></a>
## GET /api/v1/bodyTypes/:id
Gets a bodyType by its Id
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
**HTTP 200 OK**
```json
{
  "id": 1,
  "name": "skinny",
  "description": null,
  "dateCreated": "2016-02-01T16:50:57Z"
}
```
