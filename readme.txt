Smart Locker Finder

Smart Locker Finder to moja aplikacja napisana w Spring Boot, która
korzysta z API InPost i pozwala wyszukiwać paczkomaty w Polsce.

Projekt zrobiłem jako backendowy projekt treningowy do nauki: - Spring
Boota, - REST API, - pracy z zewnętrznym API, - DTO, - testów
jednostkowych.

Co potrafi aplikacja

-   pobiera dane z API InPost,
-   wyszukuje paczkomaty,
-   filtruje tylko działające paczkomaty,
-   wyszukuje po mieście,
-   wyszukuje po mieście i województwie,
-   pokazuje alternatywne paczkomaty,
-   wylicza prosty score paczkomatu,
-   zwraca uproszczoną odpowiedź API.

Technologie

-   Java 21
-   Spring Boot
-   Maven
-   REST API
-   JUnit 5

Endpointy

Health check:

GET /api/health

Pobranie paczkomatów:

GET /api/lockers/find?limit=5

Wyszukiwanie po mieście:

GET /api/lockers/city?city=Adamów

Wyszukiwanie po mieście i województwie:

GET /api/lockers/city-province?city=Adamów&province=lubelskie

Alternatywne paczkomaty:

GET /api/lockers/alternatives?name=ADA01M

Przykładowa odpowiedź

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

Uruchomienie projektu

Uruchomienie aplikacji:

mvn spring-boot:run

Uruchomienie testów:

mvn test

Aplikacja działa na:

http://localhost:8080

Struktura projektu

controller/ -> endpointy REST

service/ -> logika aplikacji i komunikacja z API

dto/ -> klasy DTO

test/ -> testy jednostkowe

Artur Stanisz
