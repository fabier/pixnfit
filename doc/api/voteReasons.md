# VoteReasons API

<a name="index"></a>
## GET /api/v1/voteReasons
Gets all voteReasons
### Params
Yes :
 * **max** (int) : Max results in output, defaults to 10
 * **offset** (int) : Offset for results, defaults to 0

*Ex : /api/v1/voteReasons?max=5&offset=2*
### Authentication
Yes : **HTTP BASIC**
### Output
```json
[
  {
    "id": 1,
    "name": "style",
    "description": null,
    "dateCreated": "2016-02-01T16:50:57Z"
  },
  {
    "id": 2,
    "name": "color",
    "description": null,
    "dateCreated": "2016-02-01T16:50:57Z"
  },
  {
    "id": 3,
    "name": "size",
    "description": null,
    "dateCreated": "2016-02-01T16:50:57Z"
  },
  {
    "id": 4,
    "name": "shape",
    "description": null,
    "dateCreated": "2016-02-01T16:50:58Z"
  },
  ...
]
```
<a name="show"></a>
## GET /api/v1/voteReasons/:id
Gets a voteReason by its Id
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
```json
{
  "id": 1,
  "name": "style",
  "description": null,
  "dateCreated": "2016-02-01T16:50:57Z"
}
```
