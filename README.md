# spring-boot-mvc-resort-management-poc


Description
-------------
This Resort Management poc is a web-based application that allows an organization to manage the resorts and its rooms. The system is built using the Spring Boot mvc, which provides a robust and flexible platform for building web applications.

In this system, each resort can have multiple rooms associated with them, such as a resort name, resort's address, and room details. The system allows the organization to store and manage all of these data in a in-memory database, making it easy to update and access the information as needed.

Key Features
-------------
- CRUD (Create, Read, Update, Delete) functionality for managing resorts and its rooms
- Ability to add multiple rooms for each resort
- User-friendly interface for viewing and editing resort and rooms
- Search functionality for quickly finding resorts and rooms


Technology Stack
-------------
- `Spring Boot(version: 2.7.8)` for building the backend of the application
- `Thymeleaf` for rendering views and templates
- `Bootstrap` for responsive UI design
- `h2-database` as the database management system

Prerequisites
-------------
- IDE: Eclipse, Intellij or any ide
- `JDK 1.8` and `JAVA_HOME` environment variable set 

Building the project
--------------------

Clone the repository:

    https://github.com/ArupMathura/spring-boot-mvc-resort-management-poc

Navigate to the newly created folder:

    cd FolderName

Run the project with:

    ./mvnw clean spring-boot:run

Navigate to:

    http://localhost:8080



Package the application
-----------------------

To package the project run:

    ./mvnw clean package






## Screenshots

![home-page](https://github.com/ArupMathura/spring-boot-mvc-resort-management-poc/blob/main/screenshot/Screenshot1-home-page.png)


![list-of-resorts](https://github.com/ArupMathura/spring-boot-mvc-resort-management-poc/blob/main/screenshot/Screenshot2-list-resorts.png)


![update-resort-Form](https://github.com/ArupMathura/spring-boot-mvc-resort-management-poc/blob/main/screenshot/Screenshot3-update-resort.png)


![delete-resort-form](https://github.com/ArupMathura/spring-boot-mvc-resort-management-poc/blob/main/screenshot/Screenshot4-delete-resort.png)


![list-of-rooms](https://github.com/ArupMathura/spring-boot-mvc-resort-management-poc/blob/main/screenshot/Screenshot5-resort-rooms.png)


![add-rooms-in-a-resort](https://github.com/ArupMathura/spring-boot-mvc-resort-management-poc/blob/main/screenshot/Screenshot6-add-room-page.png)


![edit-rooms-in-a-resort](https://github.com/ArupMathura/spring-boot-mvc-resort-management-poc/blob/main/screenshot/Screenshot7-edit-room-page.png)
