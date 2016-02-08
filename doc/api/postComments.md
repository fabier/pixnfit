# PostComments API

<a name="index"></a>
## GET /api/v1/postComments
Gets all postComments
### Params
Yes :
 * **max** (int) : Max results in output, defaults to 10
 * **offset** (int) : Offset for results, defaults to 0

*Ex : /api/v1/postComments?max=5&offset=2*
### Authentication
Yes : **HTTP BASIC**
### Output
```json
[
  {
    "id": 1,
    "name": "male",
    "description": null,
    "dateCreated": "2016-02-01T16:50:58Z"
  },
  {
    "id": 2,
    "name": "female",
    "description": null,
    "dateCreated": "2016-02-01T16:50:58Z"
  }
]
```
<a name="show"></a>
## GET /api/v1/postComments/:id
Gets a postComment by its Id
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
```json
{
  "id": 1,
  "name": "male",
  "description": null,
  "dateCreated": "2016-02-01T16:50:58Z"
}
```

<a name="delete"></a>
## DELETE /api/v1/postComments/:id
Deletes an existing comment
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
HTTP 204 No Content
