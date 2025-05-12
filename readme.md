
# kitchensink-springboot

A modernized migration of the JBoss EAP “kitchensink” quickstart to a Spring Boot multi-module application on Java 21.

---

## Overview

This project migrates the legacy JBoss “kitchensink” application into a Spring Boot–based architecture, leveraging:

- **Java 21**
- **Spring Boot 3.x**

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


## Migration Approach

1. **Modularization**
   Broke out shared code (`kitchensink-util`) and core business logic (`kitchensink-business`) into independent modules to limit blast radius and allow parallel migration.

2. **Spring Boot Autoconfiguration**
   Replaced JBoss-specific wiring with Spring Boot starters (Web, Data JPA, Thymeleaf), reducing boilerplate and leveraging convention-over-configuration.

3. **Incremental Refactoring**
    * Step 1: Migrated (`kitchensink-util`) (JAR targeting Java 21) first while keeping anything else to the legacy (`kitchensink`) app.
    * Step 2: Migrated (`kitchensink-business`) (JAR targeting Java 21) containing the core business logic and the data layer while keeping anything else to the legacy (`kitchensink`) app.
    * Step 3: Spring Boot migration. The legacy (`kitchensink`) at this point containing only the web app and the API whas split into two different project and migrated to Spring Boot

Find a detailed step-by-step guide of the process in the [doc](https://github.com/gsantopaolo/kitchensink-modern/tree/main/docs) folder.

4. **Risk Mitigation**

    * Kept legacy and new code paths side-by-side until feature parity
    * Wrote unit tests around critical domain services before UI work
    * Used Maven profiles to switch easily between original JBoss recipe and Spring-based flow


