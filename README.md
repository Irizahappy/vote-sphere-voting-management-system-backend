# VoteSphere Voting Management System

**Student:** Iriza Happy
**Student ID:** 27462
**Project:** VoteSphere Voting Management System (vote-sphere-voting-management-system)
**Course:** INSY 8322 Web Technology and Internet

## How to run the API

1. Install Java 17 and PostgreSQL.
2. Create an empty PostgreSQL database named `votesphere_db`.
3. Open `src/main/resources/application.properties` and write your own PostgreSQL password in `spring.datasource.password`.
4. Open the project in VS Code, open `VoteSphereVotingManagementSystemApplication.java` and click Run. You can also run `mvnw.cmd spring-boot:run` in the terminal on Windows.
5. The API starts on `http://localhost:8080`. Hibernate creates the tables automatically.
6. Import `vote-sphere-voting-management-system-midterm.postman_collection.json` into Postman to test the endpoints.