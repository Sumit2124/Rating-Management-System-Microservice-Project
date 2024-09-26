Microservice Project Overview
I have created a microservice project using Spring Boot as the main tech stack, implementing a total of six microservices. Before diving into the details of each service, here’s a brief overview of the project.

This microservice system implements a rating system where users can rate any hotel they have visited. Users can rate multiple hotels and provide multiple ratings for a single hotel. The Hotel Service maintains the hotel name and location, while the Rating Service handles the ratings, remarks, user ID of the rater, and hotel ID for the rated hotel.

Detailed Description of Each Microservice
Hotel Service
This service allows users to create hotels, retrieve a list of all hotels, get a specific hotel by its ID, and access details of all hotels in the system. For data storage, I used PostgreSQL.

Rating Service
This service enables users to create ratings for specific hotels. It allows retrieval of ratings by rating ID, as well as fetching all ratings related to particular hotels or users. I utilized MongoDB for storing the ratings.

User Service
This core service facilitates user management, including user creation, retrieval by user ID, and fetching a list of all users. Additionally, it retrieves a user’s ratings for particular hotels along with hotel information. For internal API communication, I used RestTemplate and Feign Client, implementing fault tolerance features such as circuit breakers with Resilience4J. This service also includes a retry mechanism to attempt multiple requests before concluding that a service is down, as well as rate limiting to manage the number of requests over a specified time frame. The database used is MySQL.

Service Registry
I utilized Eureka Discovery Server from Spring Cloud for service registration. All microservices register with the Discovery Server as discovery clients, using their names instead of IP addresses for communication. This setup allows us to identify which services are down by checking their statuses. Configurations for the Eureka Discovery Server are specified in the application.yml file, while other service configurations are stored in the Config Server, using GitHub for managing repetitive configurations.

Config Server
This microservice retrieves configurations from GitHub for the registered microservices. It centralizes configuration management, ensuring that we do not have to duplicate configuration settings across all services.

API Gateway
The API Gateway serves as a single entry point for all microservices. It allows secure access to the microservices, implementing proper authentication and authorization using the OKTA dependency.


