# ImageTypes API

<a name="index"></a>
## GET /api/v1/languages
Gets all languages
### Params
Yes :
 * **max** (int) : Max results in output, defaults to 10
 * **offset** (int) : Offset for results, defaults to 0

*Ex : /api/v1/languages?max=5&offset=2*
### Authentication
Yes : **HTTP BASIC**
### Output
```json
[
  {
    "id": 2,
    "name": "English",
    "description": null,
    "nativeName": "English",
    "dateCreated": "2016-02-01T16:50:57Z"
  },
  {
    "id": 1,
    "name": "French",
    "description": null,
    "nativeName": "Français",
    "dateCreated": "2016-02-01T16:50:57Z"
  }
]
```
<a name="show"></a>
## GET /api/v1/languages/:id
Gets a language by its Id
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
```json
{
  "id": 1,
  "name": "French",
  "description": null,
  "nativeName": "Français",
  "dateCreated": "2016-02-01T16:50:57Z"
}
```
