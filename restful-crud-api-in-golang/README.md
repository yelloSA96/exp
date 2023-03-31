# RESTful CRUD API in golang
## Technology Stack
- Gin Gonic Server
- Postgres SQL Database
## Key Information
RESTful is referred to REpresentational State Transfer. RESTful APIs are stateless, cacheable, scalable, consume less bandwidth, and are generally easier to use compared to prescribed protocols like SOAP (Simple Object Access Protocol).
CRUD stands for Create, Read Update and Delete.

Typically these actions use the following:
- Create Uses the http POST method to add one or more records
- Read uses the HTTP GET method to retrieve one or more records that match the criteria
- Update uses the HTTP PUT or PATCH method to update a record
- Delete uses the HTTP DELETE method to remove a record

GORM is a object relational mapper (ORM) library for Golang.
Gin Gonic is a high performance web framework for GOlang

## Folder Structure


## Commands Ran
```bash
# initialisation
go mod init github.com/yellosa96/restful-crud-api-in-golang
```

## References
[Setup Golang GORM RESTful API project with postgres](https://codevoweb.com/setup-golang-gorm-restful-api-project-with-postgres/)
[Build a RESTful CRUD API in Glonag](https://codevoweb.com/build-restful-crud-api-with-golang/)