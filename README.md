# WeatherMonitoring
Weather Monitoring App

# Overview
A real-time data processing system to monitor weather conditions and trigger alerts when certain thresholds are breached. 

# Technical Details
Programming Language: Java
Frameworks and Libraries: Spring Boot, Mockito, JUnit
Database: H2 (in-memory data storage)

# Installation and Usage
To run the Weather Alert System in an IDE like IntelliJ IDEA, follow these steps:

1. Clone the Repository:
git clone https://github.com/2022H1030042G/WeatherMonitoring.git

2. Open the Project in IntelliJ IDEA:
Launch IntelliJ IDEA.
Select "Open" and navigate to the cloned repository folder.
Open the project.

3. Import Maven Dependencies:
IntelliJ should automatically detect the pom.xml file and prompt you to import the Maven project. If not, right-click on the pom.xml file and select "Add as Maven Project". All the dependencies will be downloaded.

4. Configure the Application:
You can configure the temperature thresholds and notification settings in the AlertConfig class or through application properties.

5. Run the Application:
Locate the main application class (e.g., WeatherApplication.java).
Right-click on the class and select "Run 'WeatherApplication.main()'".

The APIs can be found inside the controller folder in the WeatherController class: 

(i) After running the application, enter the following url: http://localhost:8080/weather to get weather details for the cities (Delhi, Mumbai, Chennai, Bangalore, Kolkata, Hyderabad) from the table "weather_data"

(ii) The url: http://localhost:8080/calculate-daily-summary can be tested using the postman to save daily weather summary in a table "daily_weather_summary"

To access the database: enter this url: http://localhost:8080/h2-console/login.jsp with the below details:

JDBC URL: jdbc:h2:mem:testdb, 
Username: sa, 
Password: password

# Testing
To run the tests:

1. Open the Test Class:
Navigate to the test classes located in the src/test package.

2. Run Tests:
Right-click on the test class or method and select "Run 'TestClassName'" to execute the tests.
