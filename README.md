# User service API

Service defined over an hexagonal and clean architecture approach, used for register a new user and init session

In this project, I applied the hexagonal and Clean Architecture approach. This approach defines separate modules, such
as the Domain module (which includes business logic, models, and use cases) and the Infrastructure module (which
includes adapters, entry points, REST clients, etc.). In the business layer, I create contracts that define what the API
should do, but not how it should be done. The infrastructure layer then implements these contracts.

## Table of Contents

- [Installation](#installation)
- [Relational model diagram](#diagram)
- [Endpoints](#endpoints)
- [Contributing](#contributing)
- [License](#license)

## Installation

1. Execute scripts database
   1.1. Execute script in `scripts/sdscrits.sql` to create and populate initial records
   1.2. Go to develop branch and run the application

Url base de API: `http://localhost:8081`.

## Relational model diagram

![img.png](img.png)

## Endpoints

### 1. POST - Register a user

`http://localhost:8081/api/v1/users/register`

**Request:**

```json
{
  "documentId": "1018424001",
  "name": "Customer01",
  "lastname": "lname",
  "mobile": "3197882610",
  "email": "cus001@correo.com",
  "roleId": 2,
  "password": "123456"
}
```

**Response (HTTP 200):**

```json
{
  "id": 3
}
```

### 2. POST - login

`http://localhost:8081/api/v1/users/login`

**Request:**

```json
{
  "email": "cus000@correo.com",
  "password": "123456"
}
```

**Response (HTTP 200):**

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwicm9sZSI6IkNVU1RPTUVSIiwiZW1haWwiOiJjdXMwMDBAY29ycmVvLmNvbSIsImlhdCI6MTc2MTU2NDM5NiwiZXhwIjoxNzYxNTY3OTk2fQ.IXfP2xWrBtikYYb-7Y_h13Jl-7sEtSeWEAVzEKy_ikU",
  "type": "Bearer",
  "expires": 3600000
}
```

### 3. GET - user profile

`http://localhost:8081/api/v1/users/userprofile`

**Response:**

```json
{
  "id": 2,
  "name": "Customer00",
  "email": "cus000@correo.com",
  "roleId": "2",
  "orders": []
}
```


