# GitHub Repositories API

A REST API built with Java 25 and Spring Boot 4 that retrieves all non-fork repositories from a GitHub user, including their branches and the latest commit SHA for each branch.

## Overview

This application integrates with the GitHub REST API and exposes an endpoint that:

- Retrieves all public repositories for a given GitHub user
- Filters out forked repositories
- Returns:
  - Repository name
  - Owner login
  - Branch name
  - Latest commit SHA for each branch

## Technologies

- Java 25
- Spring Boot 4
- Gradle
- GitHub REST API

## Architecture

```text
Controller
    ↓
Service
    ↓
GitHub Client
    ↓
GitHub REST API
```

## Project Structure

```text
src
├── main
│   ├── java
│   │   └── com.test.adam
│   │       ├── controller
│   │       │   └── RepositoryController.java
│   │       │
│   │       ├── service
│   │       │   └── RepositoryServiceImpl.java
│   │       │
│   │       ├── client
│   │       │   └── GithubClient.java
│   │       │
│   │       ├── dto
│   │       │   ├── github
│   │       │   │   ├── GithubRepositoryDto.java
│   │       │   │   ├── GithubOwnerDto.java
│   │       │   │   ├── GithubBranchDto.java
│   │       │   │   └── GithubCommitDto.java
│   │       │   │
│   │       │   └── response
│   │       │       ├── RepositoryResponse.java
│   │       │       ├── BranchResponse.java
│   │       │       └── ErrorResponse.java
│   │       │
│   │       ├── exception
│   │       │   ├── UserNotFoundException.java
│   │       │   └── GlobalExceptionHandler.java
│   │       │
│   │       ├── interfaces
│   │       │   └── RepositoryService.java
│   │       ├── config
│   │       │   └── RestClientConfig.java
│   │       │
│   │       └── AdamApplication.java
│   │
│   └── resources
│       └── application.properties
```

## API Endpoint

### Get User Repositories

```http
GET api/v1/repositories/{username}
```

### Example Request

```http
GET api/v1/repositories/birobiro
```

### Success Response

**HTTP 200 OK**

```json
[
  {
    "name": "Hello-World",
    "owner": "birobiro",
    "branches": [
      {
        "name": "main",
        "commit": "7fd1a60b01f91b314f59955a4e4d7d4d3f2f3f01"
      }
    ]
  }
]
```

## Error Handling

### User Not Found

**HTTP 404 Not Found**

```json
{
  "status": 404,
  "message": "GitHub user 'unknown-user' not found"
}
```

## GitHub API Integration

The application consumes the following GitHub REST API endpoints:

### List User Repositories

```http
GET https://api.github.com/users/{username}/repos
```

### List Repository Branches

```http
GET https://api.github.com/repos/{owner}/{repository}/branches
```

## Building the Application

### Prerequisites

- Java 25

Verify your installation:

```bash
java --version
```

### Clone the Repository

```bash
git clone https://github.com/your-username/github-repositories-api.git
cd github-repositories-api
```

### Build

```bash
./gradlew clean 
```

### Run

```bash
./gradlew bootRun
```

The application will start on:

```text
http://localhost:8080
```

## Running Tests

```bash
./gradlew test
```

## Example Using cURL

```bash
curl http://localhost:8080/repositories/octocat
```

## Design Decisions

- Layered architecture (Controller, Service, Client)
- Separation between GitHub DTOs and API response DTOs
- Global exception handling using `@RestControllerAdvice`
- Java Records used for immutable DTOs
- Spring RestClient used for GitHub integration

## Future Improvements

- GitHub authentication using Personal Access Tokens
- Response caching
- OpenAPI / Swagger documentation
- Docker support
- Integration tests with WireMock
- Rate limit handling
- Observability and metrics

## References

- GitHub REST API: https://docs.github.com/en/rest
-build Spring Boot: https://spring.io/projects/spring-boot

## Author

Developed as a technical challenge solution using Java 25, Spring Boot 4, and the GitHub REST API.