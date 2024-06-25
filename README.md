# Library Management System - Report

## Introduction

This project is a library management application developed in Java. The application allows for managing book inventory, customer records, loan transactions, and includes a user authentication system for access control.

## System Features

### 1. Book Management
- **Add Book**: Allows adding new books to the library inventory.
- **Delete Book**: Allows removing books from the library inventory based on ISBN.
- **Search Book**: Allows searching for books in the inventory by ISBN and displaying their information.

### 2. Customer Management
- **Add Customer**: Allows adding new customers to the system.
- **Delete Customer**: Allows removing customers from the system based on their name.
- **Search Customer**: Allows searching for customers in the system by name and displaying their information.

### 3. Loan Management
- **Add Loan**: Allows registering new book loans to customers.
- **Delete Loan**: Allows removing loan records from the system based on the book's ISBN and the customer's name.
- **Search Loan**: Allows searching for loans in the system and displaying detailed information about them.

### 4. User Authentication
- **User Login**: Login system for user authentication, restricting access to application features based on their credentials.
- **Add User**: Allows adding new users to the system (administrators only).
- **Remove User**: Allows removing users from the system (administrators only).

## Code Structure

### 1. `Livro.java`
Class representing a book with attributes like title, author, ISBN, category, and availability.

### 2. `Cliente.java`
Class representing a customer with attributes like name, address, and contact information.

### 3. `Emprestimo.java`
Class representing a loan with attributes like book, customer, loan date, and return date.

### 4. `CSVUtils.java`
Utility class for handling CSV files, allowing saving, reading, and deleting data for books, customers, and loans.

### 5. `UserAuth.java`
Class responsible for user authentication, maintaining a map of users and passwords, and methods for authentication, adding, and removing users.

### 6. `LibraryGUI.java`
Class implementing the application's graphical interface using Swing. Includes screens for login, book management, customer management, and information display.

## How to Run the Program

1. **Prerequisites**: Make sure you have the JDK (Java Development Kit) installed on your machine.
2. **Compilation**: Compile the Java classes using the command:
   
   `javac *.java`
   
   
3. **Execution**: Run the application with the command:
   
   `java Main.java`
   
4. **Login**: When starting the application, a login screen will be displayed. Use the default credentials:
   - **Username**: `librarian`
   - **Password**: `lib123`
   or
   - **Username**: `admin`
   - **Password**: `admin123`

## Possible Improvements
- **Loan Button**: Include a loan button in the interface options.
- **Input Validation**: Implement input validation to ensure that data provided by users is valid.
- **Reports and Statistics**: Add features for generating reports and statistics on books, customers, and loans.
- **Enhanced Graphical Interface**: Improve the graphical interface for a better user experience.
- **Database Persistence**: Replace CSV files with a database for greater scalability and efficiency.

## Conclusion

This project provides a solid foundation for a library management system with essential features and an intuitive graphical interface. With some improvements and expansions, it can become an even more powerful and useful tool for library management.
