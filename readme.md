
# kitchensink-springboot

A modernized migration of the JBoss EAP “kitchensink” quickstart to a Spring Boot multi-module application on Java 21.

---

## Overview

This project migrates the legacy JBoss “kitchensink” application into a Spring Boot–based architecture, leveraging:

- **Java 21**
- **Spring Boot 3.x**
- **Maven** multi-module build
- **Thymeleaf**–powered UI and REST API modules

---

## Prerequisites

- **Java 21** (JDK 21) installed and `JAVA_HOME` set  
- **Maven 3.5+** installed and on your `PATH`

---

## Project Structure

```

src/
├── kitchensink-util/       ← Shared utilities
├── kitchensink-business/   ← Core domain, services, JPA entities & repositories
├── springboot-webui/       ← Spring Boot web module (Thymeleaf UI) — runs on port 8082
└── springboot-api/         ← Spring Boot API module (JSON endpoints & Thymeleaf) — runs on port 8081

````

---

## Build

From the root of this repository:

```bash
cd src/
mvn clean install
````

This will compile all modules, run unit tests, and package each Spring Boot application.

---

## Running the Application

### 1. Web UI (port 8082)

```bash
cd src/springboot-webui
mvn spring-boot:run
```

* Open your browser at: [http://localhost:8082/](http://localhost:8082/)

### 2. API Module (port 8081)

```bash
cd src/springboot-api
mvn spring-boot:run
```

* API base URL: [http://localhost:8081/api/](http://localhost:8081/api/)
* Example endpoints:

    * `GET /api/members`
    * `POST /api/members`

---

## Configuration

Default ports and other properties live in each module’s `src/main/resources/application.properties`. To override at runtime, use JVM system properties:

```bash
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dserver.port=9090"
```

---

## Migration Approach

1. **Modularization**
   Broke out shared code (`kitchensink-util`) and core business logic (`kitchensink-business`) into independent modules to limit blast radius and allow parallel migration.

2. **Spring Boot Autoconfiguration**
   Replaced JBoss-specific wiring with Spring Boot starters (Web, Data JPA, Thymeleaf), reducing boilerplate and leveraging convention-over-configuration.

3. **Incremental Refactoring**

    * Migrated JPA entities and repository interfaces first, with in-memory H2 for local dev
    * Layered on service components (`MemberRegistration`, etc.)
    * Finally plugged in Thymeleaf views and REST controllers in separate modules

4. **Risk Mitigation**

    * Kept legacy and new code paths side-by-side until feature parity
    * Wrote unit tests around critical domain services before UI work
    * Used Maven profiles to switch easily between original JBoss recipe and Spring-based flow


