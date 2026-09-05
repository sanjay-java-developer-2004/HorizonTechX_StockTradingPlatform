# HorizonTechX - Stock Trading Platform (Backend)

Backend service for the Stock Trading Platform, built with **Spring Boot** and **MySQL**.

## Tech Stack

- **Java**: 25
- **Spring Boot**: 4.1.1
- **Database**: MySQL
- **Build Tool**: Maven / Gradle (based on your project setup)

## Prerequisites

Before running this project, make sure you have:

- JDK 25 installed
- MySQL server running locally (or accessible remotely)
- Maven or Gradle installed

## Installation

Follow these steps to get the project set up on your machine:

1. **Clone the repository**
```bash
git clone https://github.com/sanjay-java-developer-2004/HorizonTechX_StockTradingPlatform.git
```

2. **Navigate into the project folder**
```bash
cd HorizonTechX_StockTradingPlatform
```

3. **Install dependencies**

If using Maven:
```bash
mvn clean install
```

If using Gradle:
```bash
./gradlew build
```

4. **Configure the database** — see the [Database Setup](#database-setup) and [application.properties](#applicationproperties) sections below before running the app.

## Database Setup

1. Open MySQL and create a database (schema):

```sql
CREATE DATABASE stock_trading_db;
```

2. Update the schema name below to match whatever you created.

## application.properties

Add the following configuration in `src/main/resources/application.properties`:

```properties
spring.application.name=StockTrading
spring.datasource.url=jdbc:mysql://localhost:3306/enter_your_schema_name
spring.datasource.username=enter_your_db_username
spring.datasource.password=enter_your_db_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> ⚠️ **Important:**
> - Replace `enter_your_schema_name` with your actual MySQL schema name
> - Replace `enter_your_db_username` with your MySQL username
> - Replace `enter_your_db_password` with your actual MySQL password
>
> Note: `server.port` is not set here since Spring Boot's embedded Tomcat server already defaults to port `8080` — no extra config needed.
>
> Avoid committing real credentials to GitHub. Use a `.gitignore` entry or an `application-local.properties` file for sensitive values instead.

## Running the Application

Once the database and `application.properties` are configured, run the app using one of the following:

### Using Maven
```bash
mvn spring-boot:run
```

### Using Gradle
```bash
./gradlew bootRun
```

### Using the built JAR
```bash
java -jar target/StockTrading-0.0.1-SNAPSHOT.jar
```

If everything is set up correctly, you should see Spring Boot's startup logs ending with something like:
```
Tomcat started on port 8080 (http)
Started StockTradingApplication in X.XXX seconds
```

The application will be available at `http://localhost:8080`.

## Testing with Postman

You can test the API endpoints using **Postman** once the application is running:

1. Open Postman.
2. Set the base URL to:
```
http://localhost:8080
```
3. Add your specific endpoint paths after the base URL, for example:
```
http://localhost:8080/api/stocks
http://localhost:8080/api/users
```
4. Choose the correct HTTP method (`GET`, `POST`, `PUT`, `DELETE`) depending on the endpoint.
5. For `POST`/`PUT` requests, go to the **Body** tab → select **raw** → choose **JSON**, and pass the required request payload.
6. Click **Send** and check the response status and body.

> Tip: If your app requires authentication (like JWT tokens), remember to add the token in the **Authorization** tab or as a header (`Authorization: Bearer <token>`) before sending requests.

## Project Structure

```
StockTrading/
├── src/
│   ├── main/
│   │   ├── java/          # Java source code
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml (or build.gradle)
└── README.md
```

## Notes

- Since Java 25 and Spring Boot 4.1.1 are both recent versions, verify that your Maven/Gradle wrapper is compatible with them.
- The database password shown above is a placeholder for demonstration. In a real project, use **environment variables** or a secrets manager (like **Spring Cloud Config** or **Vault**) instead of storing plaintext credentials.
