# SME todo-list app

SME Finance business TODO list app services.

This allows you to start a Spring Boot and a mysql db server by using only a single command in your linux system.
Both, the application and the database will be started and hosted on docker constainers.

As this is an assignment so didn't make the DB data persistent. For production deploy, this config need to be updated.

Application JPA is configured with bellow properties.

```properties
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.properties.hibernate.connection.CharSet=utf8mb4
spring.jpa.properties.hibernate.connection.characterEncoding=utf8
spring.jpa.properties.hibernate.connection.useUnicode=true
spring.jpa.properties.hibernate.dialect.storage_engine=innodb

spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.generate_statistics=false
spring.jpa.open-in-view=false
```

So when application starts, **DB schema** is generated and after application exits it automatically drop all DB data, this makes testing easier. But you can change this behavior by tweaking this field

```properties
spring.jpa.hibernate.ddl-auto=
```


## How to?

In order to start the process, all you need to do is:

- Follow this [installation guide](https://docs.docker.com/compose/install) to install both docker and docker compose
- Open your command-line terminal in the root directory of this repository where **deploy.sh** file is located.
- Execute the **deploy.sh** inside your terminal. This script uses **sudo** inside.
- **CTRL+C** will terminate the docker compose. Note: To make continuous logging in visual mode, we didn't run it in detached.

To create **jacoco code coverage** you need to follow this steps:

- Run this script from terminal in root directory of the project **./run_test_and_generate_jacoco_code_coverage.sh**
- This will generate **HTML UI** with latest coverage details in path **/app/target/site/jacoco**.
- You can open the **index.html** to see the **jacoco code coverage**

## The Application

Application is hosted on a docker container which is accessible at http://localhost:8080/.

## Swagger

This application also provide **swagger ui** support, which is an OpenAPI definition for the REST API.
You can access this on http://localhost:8080/swagger-ui/index.html?configUrl=/api-docs/swagger-config#/.
From here you can also try to access the API with valid request.

## Postman

Postman collection exported file is added to **postman-collection** folder.
It can be imported to portman with support of **schema v2.1.0**.
Fairly recent postman should be ok like **Postman v8.12.5+**

## Sample TODO lists

```text
- Personal (todo)
  - Goto gym (task)
  - Repair my laptop (task)
  - Pick up sister from school (task)
- Official (todo)
  - Complete todo webapp assignment (task)
  - Submit tax acknowlegement to HR (task)
...
```

## Out of scope
- Authentication
- Extensive logging
- Restricting any update on completed task
- Extensive unit testing
