# API Documentation

## Home API
- `/?username={username}`
  - Returns "Welcome to myBook!"
  - If username is provide, will insert username into the reply string.

## Book APIs
Book JSON
```JSON
{
  "id": 0,
  "title": "title",
  "externalLinks": [
      {
          "id": 0,
          "name": "name",
          "link": "link"
      }
  ]
}
```

- `/book/delete/{id}`
  - Deletes a Book by its id.
  - Path Variable: Book Id.
  - Return: HttpStatus.OK.
- `/book/{id}`
  - Returns a Book by its id.
  - Path Variable: Book Id.
  - Return: Book JSON.
- `/book/roleAccess/add/{externalAccessId}/{roleId}`
  - Add a Role to an External Link's role access.
  - Path Variable: External Link's Id to add the role access to.
  - Path Variable: Role's Id .
  - Return: External Link JSON.
- `/book/roleAccess/remove/{externalAccessId}/{roleId}`
  - Removes a Role to an External Link's role access.
  - Path Variable: External Link's Id to remove the role access from.
  - Path Variable: Role's Id .
  - Return: External Link JSON.
- `/book/save`
  - Saves a Book
  - Request Body: Book JSON.

## Book To User APIs
Book To User JSON
```JSON
[
    {
        "id": 0,
        "book": {
            // Book JSON
        },
        "user": {
            // User JSON
        },
        "role": {
            // Role JSON
        }
    }
]
```

- `/bookToUser/create/{bookId}/{userId}/{roleId}`
  - Creates a Book to User association for a Book and User, with a given role.
  - Path Variable: Book's Id.
  - Path Variable: User's Id.
  - Path Variable: Roles's Id.
  - Return: Book To User JSON.
- `/bookToUser/delete/{id}`
  - Deletes a Book to User association by its Id.
  - Path Variable: Book to User's Id.
  - Return: HttpStatus.OK.
- `/bookToUser/bookToUsers/bookId/{id}`
  - Returns a Book To User associations for a book id.
  - Path Variable: Book's Id.
  - Return: A list of Book To User JSONs.
- `/bookToUser/bookToUsers/userId/{id}`
  - Returns a Book To User associations for a user id.
  - Path Variable: User's Id.
  - Return: A list of Book To User JSONs.

## User APIs
User JSON
```JSON
{
    "id": 0,
    "username": "username"
}
```

Role JSON
```JSON
{
    "id": 0,
    "title": "title"
}
```

- `/user/delete/{id}`
  - Deletes a User by its id.
  - Path Variable: User Id.
  - Return: HttpStatus.OK.
- `/user/role/delete/{id}`
  - Deletes a Role by its id.
  - Path Variable: Role Id.
  - Return: HttpStatus.OK.
- `/user/roles`
  - Returns all Roles.
  - Return: List of Role JSON.
- `/user/{id}`
  - Returns a User by its id.
  - Path Variable: User Id.
  - Return: User JSON.
- `/user/findByUsername/{username}`
  - Returns a User by its username.
  - Path Variable: User username.
  - Return: User JSON.
- `/user/save`
  - Saves a User
  - Request Body: User JSON.
- `/user/role/save"`
  - Saves a Role
  - Request Body: Role JSON.