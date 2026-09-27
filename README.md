# Todo App

A simple todo list built with Spring Boot. You can use it from a web page or through a JSON REST API.

## Tech stack

- Java 21
- Spring Boot 4.1 (Web MVC, Data JPA, Validation)
- Thymeleaf for the web page
- Lombok to generate getters, setters and constructors (your IDE needs Lombok support)
- H2 database, stored in a file under `./data`

## Requirements

- JDK 21 or newer
- Maven is not needed; the project includes the Maven wrapper (`./mvnw`)

If `java -version` shows an older version, point `JAVA_HOME` at a JDK 21 install first, for example:

```bash
export JAVA_HOME=/usr/lib/jvm/java-21-openjdk-amd64
```

## Running

```bash
./mvnw spring-boot:run
```

Then open http://localhost:8080.

To build a runnable jar instead:

```bash
./mvnw package
java -jar target/todo-0.0.1-SNAPSHOT.jar
```

## Running tests

```bash
./mvnw test
```

## Web UI

The page at `/` lets you:

- add a todo
- mark a todo as done or not done
- delete a todo

Unfinished todos are listed first, newest on top.

## REST API

Base path: `/api/todos`

| Method | Path              | Description         | Success response   |
|--------|-------------------|---------------------|--------------------|
| GET    | `/api/todos`      | List all todos      | `200` + list       |
| GET    | `/api/todos/{id}` | Get one todo        | `200`, or `404`    |
| POST   | `/api/todos`      | Create a todo       | `201` + new todo   |
| PUT    | `/api/todos/{id}` | Update title/status | `200`, or `404`    |
| DELETE | `/api/todos/{id}` | Delete a todo       | `204`, or `404`    |

A todo looks like this:

```json
{ "id": 1, "title": "Buy milk", "completed": false }
```

`title` is required, can't be blank, and can be at most 200 characters. Invalid input returns `400`.

### Examples

```bash
# Create
curl -X POST localhost:8080/api/todos \
  -H 'Content-Type: application/json' \
  -d '{"title":"Buy milk"}'

# List
curl localhost:8080/api/todos

# Mark as done
curl -X PUT localhost:8080/api/todos/1 \
  -H 'Content-Type: application/json' \
  -d '{"title":"Buy milk","completed":true}'

# Delete
curl -X DELETE localhost:8080/api/todos/1
```

## Database

Todos are saved to an H2 database file in `./data`, so they are kept between restarts. Delete the `data` folder to start fresh.

You can browse the database at http://localhost:8080/h2-console with these settings:

- JDBC URL: `jdbc:h2:file:./data/todo`
- User: `sa`
- Password: leave empty

## Project structure

```
src/main/java/com/example/todo/
├── TodoApplication.java     # entry point
├── Todo.java                # todo entity
├── TodoRepository.java      # database access
├── TodoController.java      # web page routes
└── TodoApiController.java   # REST API
src/main/resources/
├── application.properties   # configuration
└── templates/index.html     # web page
```
