# Team-C-P2 - E-Commerce SPA

## JUnit Testing Status

[![junit_tests](https://github.com/220328-Java-Full-Stack-AWS/Team-C-P2/actions/workflows/junit_tests.yml/badge.svg?branch=auto-tester)](https://github.com/220328-Java-Full-Stack-AWS/Team-C-P2/actions/workflows/junit_tests.yml)

run test

## Executive Summary
The E-Commerce Single-Page Application will allow consumers to browse, search, and buy products. Users will be able to browse our catelog of products, or search for a specific item, and add these to a cart. Users can register accounts which includes a profile. There are a number of stretch goals to choose from. Please update this text to summarize additional features. This text is fairly generic, your project will be unique, this text should be re-written to reflect your final deliverable.


# Tech Stack
 - Languages
   - Java
   - JavaScript
   - TypeScript
 - Data Persistence
   - PostgreSQL
   - Hibernate
 - AWS
   - RDS
   - S3
   - CodeBuild & CodePipeline
   - ElasticBeanstalk
 - Spring Framework
   - Spring Boot
   - Spring MVC
 - Angular

## Forbidden Abstractions:
 - Spring Data JPA - Spring Data JPA uses Hibernate as an ORM provider. Before we get to that level of abstraction, we will use hibernate directly ourselves.


# Functional Requirements
### Required:
 - Domain objects persisted in relational database via ORM
 - All CRUD functionality accessible via RESTful API
 - Single page web UI to consume RESTful API
 - Workflows to complete all user stories
 - Validate all user input
 - Unit test coverage for service-layer classes

### Stretch Goals:
 - Application is merged, tested, and deployed with a fully functional CI/CD Pipeline

The persistence layer shall use Hibernate ORM to translate between the database and the application server. The API layer shall abstract away the low-level servlets with Spring Web MVC. The client shall use Angular to produce an SPA which is styled to be functional and readable. The server should follow a proper layered architecture, and have adequate unit testing of the service layer. The client and server should communicate in a RESTful manner, and the server should be stateless. 


## User Stories
### Requirements:
#### Guest
 - As a guest, I can register for an account.
 - As a guest, I can log in to my account.

#### User:
 - As a user, I have a profile which I can view.
 - As a user, I can browse products and add them to my cart.
 - As a user, I can remove items from my cart.
 - As a user, I can checkout to purchase the items in my cart.

### Stretch Goals:
 - As a User, I should be able to select an amount of an item to add to my cart as I am adding an item
 - As a User, I should be able to search the product list to better find the item(s) I am interested in
 - As a User, I should be able to see and purchase items that are on sale for a lower price.
 - As a User, I should be able to see a list of featured products on the main page of the application
 - As a User, my session should be maintained until I log out.
 - As a User, I should be able to reset my password.
 - As a User, I should be able to change the color scheme from the normal mode to a dark mode option.
 - As a User, I should receive notifications when a transaction has occurred or a transfer has been completed.

# Submission
### Due Date: Thursday 5/26/2022 at 9:00 AM CST
Your project needs to be pushed into the main branch of your team's P2 repository no later than the due data and time above. Commits after this deadline will not be considered. On the due date there will be a presentation. You will be expected to briefly cover your project, and should be prepared to discuss it with QC.

