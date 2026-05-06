# Smart Locker Finder

## Author

- **Name:** Artur Stanisz
- **Email:** arturstanisz95@gmail.com

## Overview

Smart Locker Finder is a backend application created in Spring Boot that integrates with the InPost API and allows searching for parcel lockers in Poland.

The application downloads real parcel locker data, filters active lockers, searches by city and province, and calculates a simple locker score based on selected features.

## Demo & Description

The project was created as a training backend application for learning Spring Boot, REST API development, working with external APIs, DTO mapping, and unit testing.

The application connects with the public InPost API and downloads parcel locker data in JSON format. The raw API response is then converted into simplified DTO objects that are easier to use inside the application.

Main features:
- downloading parcel locker data from InPost API,
- searching parcel lockers,
- filtering only active lockers,
- searching by city,
- searching by city and province,
- showing recommended alternative lockers,
- calculating a simple parcel locker score,
- returning simplified API responses.

The project uses a layered structure:
- controller layer for REST endpoints,
- service layer for business logic and API communication,
- DTO classes for transferring data,
- unit tests for scoring logic.

The application currently works locally and exposes REST endpoints that can be tested in the browser or Postman.

## Technologies

- Java 21
- Spring Boot
- Maven
- REST API
- JUnit 5

I decided to use Spring Boot because I wanted to practice modern Java backend development and REST API creation. Maven was used for dependency management and project build automation.

## How to run

### Prerequisites

You need:
- Java 21
- Maven
- Internet connection (required for InPost API requests)

### Build & run

```bash
git clone <your-repository-url>

cd smart-locker-finder

mvn spring-boot:run
```

Application starts on:

```bash
http://localhost:8080
```

Example endpoints:

```bash
GET /api/health

GET /api/lockers/find?limit=5

GET /api/lockers/city?city=Adamów

GET /api/lockers/city-province?city=Adamów&province=lubelskie

GET /api/lockers/alternatives?name=ADA01M
```

Run tests:

```bash
mvn test
```

## What I would do with more time

If I had more time, I would:
- add Swagger/OpenAPI documentation,
- create a frontend interface,
- add caching for API responses,
- improve exception handling,
- add integration tests,
- allow searching by GPS coordinates,
- deploy the application online.

The first thing I would improve would be exception handling and API documentation because they are very important in backend applications.

## AI usage

I used ChatGPT during the project mainly for:
- helping with DTO structure,
- fixing errors,
- understanding REST API integration.

I always tested and verified generated code manually and adapted it to my own project structure and requirements.

## Anything else?

This project was mainly created as a learning project to improve my backend Java skills and better understand how REST APIs and external API integrations work in Spring Boot.
