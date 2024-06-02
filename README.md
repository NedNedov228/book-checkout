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
- **PostGres Database**: In-memory database for development and testing
- **Spring Data JPA**: ORM for database operations

## Getting Started

### Prerequisites

- JDK 11 or higher
- Maven 3.6.0 or higher

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/library-book-checkout.git
    cd library-book-checkout
    ```

2. Build the project using Maven:
    ```bash
    mvn clean install
    ```

3. Run the application:
    ```bash
    mvn spring-boot:run
    ```

4. Access the application at `http://localhost:8080`.

### Configuration

You can configure the database settings in the `database.properties` file located in the `src/main/resources` directory. Example:

```properties
driver=org.postgresql.Driver
url=jdbc:postgresql://localhost:5432/book_checkout
username=postgres
password=posgres

```
