# ImageTypes API

<a name="index"></a>
## GET /api/v1/fashionStyles
Gets all fashionStyles
### Params
Yes :
 * **max** (int) : Max results in output, defaults to 10
 * **offset** (int) : Offset for results, defaults to 0

*Ex : /api/v1/fashionStyles?max=5&offset=2*
### Authentication
Yes : **HTTP BASIC**
### Output
```json
[
  {
    "id": 1,
    "name": "bohemian",
    "description": null,
    "dateCreated": "2016-02-01T16:50:57Z"
  },
  {
    "id": 2,
    "name": "arty",
    "description": null,
    "dateCreated": "2016-02-01T16:50:57Z"
  },
  {
    "id": 3,
    "name": "chic",
    "description": null,
    "dateCreated": "2016-02-01T16:50:57Z"
  },
  {
    "id": 4,
    "name": "classic",
    "description": null,
    "dateCreated": "2016-02-01T16:50:57Z"
  },
  {
    "id": 5,
    "name": "exotic",
    "description": null,
    "dateCreated": "2016-02-01T16:50:57Z"
  },
  {
    "id": 6,
    "name": "flamboyant",
    "description": null,
    "dateCreated": "2016-02-01T16:50:57Z"
  },
  {
    "id": 7,
    "name": "glamourous",
    "description": null,
    "dateCreated": "2016-02-01T16:50:57Z"
  },
  {
    "id": 8,
    "name": "romantic",
    "description": null,
    "dateCreated": "2016-02-01T16:50:57Z"
  },
  {
    "id": 9,
    "name": "sexy",
    "description": null,
    "dateCreated": "2016-02-01T16:50:57Z"
  },
  {
    "id": 10,
    "name": "sophisticated",
    "description": null,
    "dateCreated": "2016-02-01T16:50:57Z"
  }
]
```
<a name="show"></a>
## GET /api/v1/fashionStyles/:id
Gets a fashionStyle by its Id
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
```json
{
  "id": 1,
  "name": "bohemian",
  "description": null,
  "dateCreated": "2016-02-01T16:50:57Z"
}
```
