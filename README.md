# Library Book Checkout System

This is a simple CRUD web application for managing book checkouts in a library. It is built using the Spring Framework with Maven in Java and Thymeleaf for the front-end.

## Features

- **Create**: Add new books and users to the library.
- **Read**: View the list of all books and users available in the library.
- **Update**: Edit the details of existing books.
- **Delete**: Remove books from the library.
- **Assign**: Assign books to certain users.

## Technologies Used

- **Spring Boot**: Backend framework
- **Maven**: Dependency management
- **Thymeleaf**: Template engine for the frontend
- **PostgreSQL Database**: open-source relational database 


## Getting Started

### Prerequisites

- JDK 11 or higher
- Maven 3.6.0 or higher

### Configuration

You can configure the database settings in the `database.properties` file located in the `src/main/resources` directory. Example:

```properties
driver=org.postgresql.Driver
url=jdbc:postgresql://localhost:5432/book_checkout
username=postgres
password=posgres

```
