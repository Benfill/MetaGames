# E-sports Tournament Management Application

## Project Description
This management application was developed for an e-sports organization to facilitate the organization and tracking of video game tournaments. It allows for the management of players, teams, and tournaments, with a focus on flexibility and ease of use.

## General Objective of the Application
The goal of the application is to provide a centralized platform for player registration, team creation and management, as well as tournament planning, while offering real-time event tracking and statistics.

## Technologies Used
- **Language**: Java 8
- **Frameworks**: Spring (Core, IoC, DI), Hibernate (JPA)
- **Database**: H2 (in-memory DB)
- **Build Tool**: Maven
- **Testing**: JUnit 5, Mockito, JaCoCo (for code coverage)
- **Application Server**: Apache Tomcat

## Project Structure
- `src/`: Contains the application's source code
  - `model/`: JPA entities (Player, Team, Tournament, Game)
  - `dao/`: Data access layer following the DAO pattern
  - `service/`: Business logic layer, managing tournament, team, and player logic
  - `util/`: Utility classes (validation, calculations)
  - `console/`: Console-based user interface (menu)
- `resources/`: Configuration files (applicationContext.xml, persistence.xml)
- `tests/`: Unit tests for all layers

## Brief Description of the Architecture
The application follows a layered architecture with a clear separation of responsibilities:
- **Model Layer**: Represents database entities (JPA).
- **DAO (Repository) Layer**: Handles data access via JPA/Hibernate.
- **Service Layer**: Implements business logic for managing tournaments, teams, and players.
- **Presentation Layer**: Console menu for user interaction.
- **Utility Layer**: Support classes for calculations, validations, and other functionalities.

## Installation and Usage Instructions

### Prerequisites
- Java 8 or higher
- Apache Maven
- Apache Tomcat
- An IDE of your choice (IntelliJ, Eclipse, etc.)

### Installation Steps:
1. Clone the project via Git:
   ```bash
   git clone https://github.com/benfill/MetaGames.git
   ```
2. Compile and package the project using Maven:
   ```bash
   mvn clean install
   ```

## Steps to Configure the Database
The application uses H2 as an in-memory database, so no additional database configuration is required. The schema is auto-generated at startup.

1. Ensure that the `persistence.xml` is properly configured for H2.
2. For modifying configurations, access the `applicationContext.xml` for Spring configurations.

## How to Run the Application
1. Open your terminal or command prompt.
2. Navigate to the project directory where the compiled JAR file is located.
3. Run the application using the following command:
   ```bash
   java -jar metagames.jar
   ```

## Screenshots


## Future Possible Improvements
- Integration with streaming APIs to broadcast live matches.
- Adding a graphical dashboard for tournament statistics.
- Managing sponsors and partnerships for teams and tournaments.
- Notification system for players and spectators.
- Optimizing algorithms for duration and ranking calculations.

## Ideas to Extend or Improve the Project
- Create a web-based user interface with **Spring MVC** and **Thymeleaf**.
- Integrate an internal messaging system for communication between teams.
- Add league and division management for tournaments.
- Use a relational database like **PostgreSQL** for long-term persistence.

## Author and Contact
**Author**: Anass Benfillous  
**Contact**: benfianass@gmail.com
LinkedIn: [LinkedIn Profile](https://www.linkedin.com/in/benfill)