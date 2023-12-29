# CSCI 6617: Java Programming - Final Project
The objective of this project was to test my knowledge of Java and JavaFX. In this project, I was asked to create a simple employee payroll portal where both employees and managers could log in to access the appropriate data. The program is written in Java and the user interface is written using JavaFX.

## Project Overview
The finished application simulates a simple payroll system for a company with a manager. Each time the program is run, it looks for a specified database file. If it fails to find the file, it will automatically create a file and prompt the user to create the manager account. All executions afterward will load the login page. From here any employee can access the system, but only managers will have full functionality.

Phase 1 and Phase 2 of this project were designed to complete all backend functionality, so the control structure was menu-based input to the console. Phase 3 later shifted the control to an event-based structure so that a GUI can be created to register user click events. For reference, the menu-based control structure is outlined below:

- Menu-Based Control Structure
  - Log In
  - Add Employee
  - List Employees
  - Update Employee
  - Terminate Employee
  - Pay Employees
  - Exit
 
> [!NOTE]
> Preview the GUI: <br/>
> [Login Page](https://i.ibb.co/kKGrsS0/Login-Scene.png), [Employee List (Manager View)](https://i.ibb.co/rbc0JHY/Employee-Scene-Manager-Access.png), [Employee List (Employee View)](https://i.ibb.co/B2VnJJR/Employee-Scene-Employee-Access.png), [Create Employee](https://i.ibb.co/B2k5x8L/Create-Employee-Scene.png), [Payroll Report](https://i.ibb.co/vHC4PjK/Pay-Report-Scene.png)

## Deliverables
There was one major deliverable for this project, split into three separate phases.

> [!IMPORTANT]
> This repository contains only the final deliverable and updated since the initial project submission. It does not contain version control before this point. I intend to document initial submissions inside the project folder.

### Phase 1: Initializing the project
The project was initialized in this stage. The two main classes, Employee and Payroll, were created. Below are the changes in this phase:
- The **Employee** class was created to store all information related to the employee.
- The **Payroll** class was created to initialize the payroll system and manage employees.
- A **menu-based** control structure was created in the console so a user can access the system.
- The employee database was implemented as an unencrypted .txt file.
- Created functionality to add new employees and generate an employee ID number.

### Phase 2: Extending BackEnd Functionality
This is the last stage of the menu-driven application. Below are the changes in this phase:
- Utilized inheritance, polymorphism, and abstraction to create **Salaried** and **Hourly** subclasses of the **Employee** class.
- The employee database was migrated to an encrypted .object file.
- Added functionality to track employees that have resigned or been terminated.
- Added functionality to list either all employees or a single employee, depending on access.
- Added functionality to issue payroll and create a payroll summary report.

### Phase 3: Creating a FrontEnd JavaFX GUI
The requirements of the final deliverable call for a simple GUI using JavaFX. This will be the primary interface for a user who wants to access the database. The current menu-driven structure was not suitable for this type of application, so it was changed to be event-driven. Below are the changes in this phase:
- A password field was added to the **Employee** class to store their SHA-256 encrypted hash. 
- A getNewPassword() function was created in the **Payroll** class to set and encrypt a user's password.
- A GUI was created with four scenes to handle all operations of the database (login, employee list, update employee, and pay report)
- The menu-driven structure was converted to an event-driven structure.

## Future Development Ideas
- **Cloud Database** - Instead of hosting the database file locally, it should be migrated to a cloud solution so that it can be accessed from different locations.

## Skills Exercised
- Java
- JavaFX
- CSS (JavaFX)
- File Management
- File Serialization
- Inheritance, Polymorphism, Abstraction
- Git
