# Countries API

<a name="index"></a>
## GET /api/v1/countries
Gets all countries
### Params
Yes :
 * **max** (int) : Max results in output, defaults to 10
 * **offset** (int) : Offset for results, defaults to 0

*Ex : /api/v1/countries?max=5&offset=2*
### Authentication
Yes : **HTTP BASIC**
### Output
**HTTP 200 OK**
```json
[
  {
    "id": 2,
    "name": "England",
    "description": null,
    "nativeName": "England",
    "isoCode31661": "EN",
    "dateCreated": "2016-02-01T16:50:57Z"
  },
  {
    "id": 1,
    "name": "France",
    "description": null,
    "nativeName": "France",
    "isoCode31661": "FR",
    "dateCreated": "2016-02-01T16:50:57Z"
  },
  {
    "id": 3,
    "name": "USA",
    "description": null,
    "nativeName": "USA",
    "isoCode31661": "US",
    "dateCreated": "2016-02-01T16:50:57Z"
  },
  ...
]
```


<a name="show"></a>
## GET /api/v1/countries/:id
Gets a country by its Id
### Params
None (Id is included in URL)
### Authentication
Yes : **HTTP BASIC**
### Output
**HTTP 200 OK**
```json
{
  "id": 1,
  "name": "France",
  "description": null,
  "nativeName": "France",
  "isoCode31661": "FR",
  "dateCreated": "2016-02-01T16:50:57Z"
}
```
