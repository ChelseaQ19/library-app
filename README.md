# Library Management System 

## Description

This library Management System allows you to manage books and their corresponding locations within a library. 

## Features 

- Add new books to the library
- List all books
- Update or delete existing books
- Manage book locations
- Count books by genre
- Find books in specific locations
- Save and load data in JSON format

## Technologies Used

- Kotlin
- JSON for storing data
- Gradle and Maven for dependencies

## Project Development Journey

## 1. Initial Setup

- Added skeleton code for the menu
- Added raw strings for the menu
- Added string template to the menu
- Created a UserInput folder within the utils package

**Explanation:**
- I began with implementing the basic skeleton code based on the database of my application, which was a many-to-many database structure. I added some raw strings and began with some simple user input for it too.

---

## 2. Adding Core Models and API files
- Added properties to classes
- Created more API files (BookAPI, LocationAPI, BookLocationAPI)
- Implemented models with corresponding collections

**Explanation:**
- Next, I built models representing key components of the system, such as Books and Locations. I then added corresponding APIs to handle the book management logic like adding, listing and deleting.

---

## 3. Implementing Core Features
- Implemented add and list methods for each model
- Added archive and active testing functionality
- Implemented a find method in BookLocationController

**Explanation:**
-  I created core features like adding books, listing books, adding locations and listing locations. When these were tested using JUnit testing, I then went to make similar methods and merge those methods into BookLocationController. I then again tested these methods using JUnit.

---

## 4. Lambdas and Persistance with JSON and XML
- Implemented JSON and XML serializers
- Created Lambdas expressions for the BookAPI submenu methods, such as counting the genres and listing the title names corresponding to what the user searches for.

---

## 5. Code Documentation and Automation
- Implemented KDoc comments
- Updated GitHub Actions with test reports

**Explanation:**
- I added KDoc to document the project and integrated GitHub Actions for automated testing and build reports. This helped me to maintain continuous integration for my application.

---

## 6. Final Refinements 
- Code Cleanup
- Fixed bugs from testing phases
- Updated README  and GitHub Pages

**Explanation:**
- I refined the project by fixing bugs, enhancing features, and updating documentation through GitHub pages, Wiki and a README file. This made the project more accessible and well-documented.

---



## Why is this project useful ?

### 1. **Efficient Book Management**
- This project provides an organized and efficient way to manage a library's book inventory by leveraging core features such as:
- Adding, listing and searching for books.
- The systems streamlines the process of tracking books their specified locations.

---

### 2. **Data Persistance**
- With suppoort for both JSON and XML formats, this project ensures that data is stored persistently. Books and locations can be saved and reloaded, meaning the system retains its information even after a restart.

---

### 3. **Test-driven Development**
- The project follows a test-driven devlopment (TDD) approach, ensuring that the core features are well-tested and bug-free.

- ---

### 4. **Conclusion**
- Overall, this project serves as an essential tool for book and location management.

## How to get started with this project

### Prequisities 
Before you begin, ensure that you have the following tools and software installed:

1. **Java 16+** - This project is buiolt with Kotlin and requires Java 16 or higher to run.
   - You can download Java from [here](https://www.oracle.com/ie/java/technologies/downloads/)
2. **Gradle** - A build automation tool used for managing dependencies and running tasks.
   - You can download Gradle from [here](https://gradle.org/install/)
3. **Git** - Version control system for cloning the project repository.
   - Install Git from [here](http://git-scm.com/).
  
Then, run your application in IntelliJ using [./gradlew run]

---

## Contributors

This project is made possible by the contribution of thefollowing individuals:

### Primary contributor:
- **Chelsea Quigley** - [GitHub Profile](https://github.com/ChelseaQ19)

### Special Thanks:
- To my lecturer Siobhan for teaching me this subject! 👋

