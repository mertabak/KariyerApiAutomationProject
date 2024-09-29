
Kariyer.net API Test Automation Project

Project Overview
This project focuses on the automated testing of the Kariyer.net API, specifically targeting the search endpoint. 
The API allows job seekers and employers to search for job postings on the platform. 
This project is designed to ensure the proper functioning and validation of the search feature using various test scenarios, developed with Rest Assured, Java, and TestNG.

The project has been structured with a focus on clean code practices, ensuring readability, maintainability, and scalability. 
The main components are designed using Object-Oriented Programming principles to provide a modular, reusable, and extendable architecture.

Tools & Libraries

The project utilizes the following tools and libraries:
Java 17: The programming language used to develop the test cases.
TestNG: A testing framework to manage and run test cases.
Rest Assured: For making API requests and validating responses.
Maven: For project management and building dependencies.
Allure Reports: For generating detailed test reports.

Project Structure

Config: Contains ConfigManager for loading and managing configuration properties (such as the base URL) from TestData.properties.

Handlers:
ApiRequestManager: This class handles sending POST requests to the Kariyer.net API.
ApiResponseManager: This class is responsible for validating the responses of the API.

Helpers:
JSONObjectBuilder: A helper class to construct JSON objects for API requests.

Models:
RequestPayload: Represents the payload structure used in API requests.

Test Cases:
KariyerSearchTestCases: Contains the test cases for various scenarios such as sending valid and invalid parameters, validating pagination, and boundary checks.

Test Scenarios
The following scenarios are automated as part of this project:

Valid Parameters: Sending valid parameters and verifying a successful response.
Invalid Member ID: Sending an invalid memberId and verifying error handling.
Invalid Size: Testing with an invalid size parameter and validating job count.
Dont Show Applied Jobs: Verifying that applied jobs are hidden as expected.
Empty Keyword: Sending an empty keyword and validating job count.
Invalid Language: Sending an invalid language parameter and validating the response.
Valid Pagination: Verifying pagination with multiple pages of results.
Max Size Parameter: Boundary testing for the size parameter.
Max Current Page Parameter: Boundary testing for the currentPage parameter.

Test Data
The configuration properties, such as the base URL for the API, are managed in the TestData.properties file. 
This allows flexibility in changing the environment without modifying the codebase.


Conclusion
This project serves as a demonstration of API test automation using Rest Assured with a clean, modular, and extensible structure. 
The use of Allure provides enhanced reporting, and the tests cover a wide range of scenarios to ensure the reliability of the Kariyer.net search API.