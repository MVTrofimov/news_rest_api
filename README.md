# News Rest API
![java](https://img.shields.io/badge/java-orange?style=flat-square&logo=java) ![spring](https://img.shields.io/badge/spring-green?style=flat-square&logo=spring) ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Blue?style=flat-square&logo=PostgreSQL&logoColor=blue) ![hibernate](https://img.shields.io/badge/hibernate-red?style=flat-square&logo=Hibernate) ![docker](https://img.shields.io/badge/docker-blue?style=flat-square&logo=Docker) ![maven](https://img.shields.io/badge/maven-yellow?style=flat-square&logo=Maven)
___
## Description:
__A CRUD pet-project written by me as part of consolidating the knowledge gained in the course on creating applications using the Spring framework.__
___
## About project:
__Backend-part of an information service in which users can create their own news and comment on the news of other users.__
___
## Technology stack:
* __Java(17)__
* __Lombok__
* __Spring Framework:__
  * Spring WEB
  * Spring JPA
  * Spring Security
  * Spring AOP
* __Mapstruct__
* __Maven__
* __Hibernate__
* __PostgreSQL__
* __Docker__
___
## Database schema

![img_2.png](img_2.png)
___
## Structure
__Working with data in the application is divided into 3 main levels: the controller level, the service level and the repository level.__

* __Controllers level:__ At this level of work, the validity of the data entered by the user is checked and the access rights for performing the action are checked. The necessary service level methods are also called for DTO conversion.
* __Service level:__ At this level of work, information received from the previous level is processed, repository-level methods are called, and errors are thrown in case of abnormal application behavior.
* __Repository level:__ At this level of work, the database is being worked on using CRUD operations.
___
## Security
__Security in this application is implemented using Spring basic auth. Some security functions are put into separate aspects and are used as annotations. The application supports three types of users: admin, moderator and user. Based on the rights granted to the user, the user can perform only those actions that are allowed to the user with this level of rights.__
___
## Docker
__The application database is deployed on docker using docker-compose.yml file__