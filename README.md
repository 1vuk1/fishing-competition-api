Fishing Competition API


Fishing Competition is a REST API application developed using Java Spring Boot that tracks fishing competitions, teams, and their catches. 
The application allows you to manage competitions, add teams, record catches, and track the competition results.

Technologies:

-Java 17
-Spring Boot
-Maven
-MySQL (or any other compatible database)

Features:

-Create competitions with a list of teams.
-Add teams that participate in the competition.
-Record catches for each team during the competition.
-Display details about competitions, including total catch weight, number of catches, heaviest catch, etc.
-Update and delete competitions, teams, and catches.

Entities:

-Competition: Tracks information about the competition, including the name, date, participating teams, total number of catches, average weight of the fish caught, the      
 heaviest fish caught, and the name of the team that caught the heaviest fish. Additionally, it sorts the list of teams by their ranking based on the total weight of fish caught.
-Team: Tracks information about the team, such as the team name and number of members.
-Catch: Tracks data about each catch, including the species and weight of the fish.


Endpoints

Here are all the available endpoints:

Competition:

Create: POST /api/v1/competition
Get Details: GET /api/v1/competition/{competitionId}
Update: PUT /api/v1/competition/{competitionId}
Delete: DELETE /api/v1/competition/{competitionId}

Team:

Add: POST /api/v1/teams
Get Team: GET /api/v1/teams/{teamId}
Update: PUT /api/v1/teams/{teamId}
Delete: DELETE /api/v1/teams/{teamId}

Catch:

Add Catch: POST /api/v1/catches/{competitionId}/{teamId}
Update Catch: PUT /api/v1/catches/{catchId}
Delete Catch: DELETE /api/v1/catches/{catchId}


Using the API:

You can use the API with cURL commands, Postman, Insomnia, or any other HTTP client. Here are some basic requests to interact with the API

If you want to test the API, here is the sequence of requests you can make:

1. Add a Team

Endpoint: POST /api/v1/teams

Request Body:

{
  "name": "TEAM A",
  "numberOfMembers": 2
}

(You can enter any number of teams)

2. Create a Competition

Endpoint: POST /api/v1/competition

Request Body:

{
  "name": "CARP CUP",
  "date": "2024-06-26",
  "type": "Teams",
  "teamList": [
    { "id": 1 },
    { "id": 2 },
    { "id": 3 },
    { "id": 4 },
    { "id": 5 },
    { "id": 6 }
  ]
}

(The team list depends on the number of teams you entered.)

3. Add a Catch
Endpoint: POST /api/v1/catches/{competitionId}/{teamId}

Request Body:
{
  "species": "Carp",
  "weight": 2.25
}


4. Get Competition Details
Endpoint: GET /api/v1/competition/{competitionId}

Displays all competition data, including teams and their catches.




-Before running the application, make sure you have a MySQL database.

-Update the application.properties or application.yml file with your database configuration (URL, username, password).

-Once started, the API will be available at http://localhost:8080.