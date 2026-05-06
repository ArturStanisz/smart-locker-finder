# Smart Locker Finder

Smart Locker Finder is my application written in Spring Boot, which
uses the InPost API and allows searching for parcel lockers in Poland.

I created this project as a backend training project to learn:
- Spring Boot,
- REST API,
- working with external APIs,
- DTO,
- unit testing.

## What the application can do

- download data from the InPost API,
- search for parcel lockers,
- filter only operating parcel lockers,
- search by city,
- search by city and province,
- show alternative parcel lockers,
- calculate a simple parcel locker score,
- return a simplified API response.

## Technologies

- Java 21
- Spring Boot
- Maven
- REST API
- JUnit 5

## Endpoints

Health check:

GET /api/health

Get parcel lockers:

GET /api/lockers/find?limit=5

Search by city:

GET /api/lockers/city?city=Adamów

Search by city and province:

GET /api/lockers/city-province?city=Adamów&province=lubelskie

Alternative parcel lockers:

GET /api/lockers/alternatives?name=ADA01M

## Example response

    [
      {
        "name": "ADA01M",
        "city": "Adamów",
        "province": "lubelskie",
        "address": "Kościuszki 27, 21-412 Adamów",
        "status": "Operating",
        "open247": true,
        "paymentAvailable": true,
        "recommendedAlternatives": [
          "ADA01N"
        ],
        "score": 100
      }
    ]

## Running the project

Run the application:

mvn spring-boot:run

Run tests:

mvn test

The application runs on:

http://localhost:8080

## Project structure

controller/ -> REST endpoints

service/ -> application logic and API communication

dto/ -> DTO classes

test/ -> unit tests

Artur Stanisz