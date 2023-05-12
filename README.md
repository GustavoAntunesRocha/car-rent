# Vehicle rental API
Api system to manage an vehicle rental busienes

## Swagger access
- localhost:8080/swagger-ui/index.html

## H2 database console access
- localhost:8080/h2-console
- JDBC Url: jdbc:h2:mem:car

## Methods
API request standards:

### Base URL: /api/v1/customer

| Method | Params | Description |
|---|---|---|
| `GET` | id or name | Return information of customers |
| `POST` || Creates a new record. |
| `PUT` || Updates a record. |
| `DELETE` | id | Deletes a record passing it's ID as a paramether. |

### Base URL: /api/v1/employee

| Method | Params | Description |
|---|---|---|
| `GET` | id or name | Return information of employees|
| `POST` || Creates a new record. |
| `PUT` || Updates a record. |
| `DELETE` | id | Deletes a record passing it's ID as a paramether. |

### Base URL: /api/v1/location

| Method | Params | Description |
|---|---|---|
| `POST /city` || Creates a new city. |
| `POST /state` || Creates a new state. |
| `POST /country` || Creates a new country. |

### Base URL: /api/v1/rental

| Method | Params | Description |
|---|---|---|
| `GET` | id or customerId | Return information of reantals|
| `POST` || Creates a new record. |
| `PUT` || Updates a record. |
| `DELETE` | id | Deletes a record passing it's ID as a paramether. |

### Base URL: /api/v1/vehicle

| Method | Params | Description |
|---|---|---|
| `GET` | id or model | Return information of vehicles|
| `POST` || Creates a new record. |
| `PUT` || Updates a record. |
| `DELETE` | id | Deletes a record passing it's ID as a paramether. |

### Base URL: /api/v1/user

| Method | Params | Description |
|---|---|---|
| `POST /login` | email and password in json body| Authenticates a user returning a token. |


### Built with

- Java
- Spring Boot
- Spring Security
