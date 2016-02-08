# Auth API

<a name="login"></a>
## POST /api/v1/auth
Checks if a login password combinaison is valid.
Login/Password are sent using HTTP BASIC Authentication.
### Params
None
### Authentication
Yes : **HTTP BASIC**
### Output
HTTP 200 OK
```json
{
  "id": 1,
  "username": "Thomas Bull",
  "description": null,
  "bodyType": {
    "id": 2,
    "name": "average"
  },
  "gender": {
    "id": 1,
    "name": "male"
  },
  "birthdate": null,
  "height": null,
  "weight": null,
  "imageUrl": null,
  "country": {
    "id": 1,
    "name": "France"
  },
  "language": {
    "id": 1,
    "name": "French"
  },
  "dateCreated": "2016-02-08T16:00:53Z"
}
```