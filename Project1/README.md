# Project Overview   
## Requirements and Roadmap  
Your tech lead is excited to work with you and has laid out a development roadmap with requirements and milestones. They tell you that there are three layers of the application you need to implement:  
* The back-end with Spring Boot   
* The front-end with Thymeleaf  
* Application tests with Selenium  

## SuperDuperDrive Cloud Storage  
You have been hired by SuperDuperDrive, which is a brand new company aiming to make a dent in the Cloud Storage market and is already facing stiff competition from rivals like Google Drive and Dropbox. That hasn't dampened their spirits at all, however. They want to include personal information management features in their application to differentiate them from the competition, and the minimum viable product includes three user-facing features:  

* Simple File Storage: Upload/download/remove files
* Note Management: Add/update/remove text notes
* Password Management: Save, edit, and delete website credentials.  
* SuperDuperDrive wants you to focus on building the web application with the skills you acquired in this course. That means you are responsible for developing the server, website, and tests, but other tasks like deployment belong to other teams at the company.
# Project Directions
## The Back-End
1. Managing User Access with Spring Security
  * SecurityAdapter.java
2. Handling Front-End Calls with Controllers
  * AuthenticationController.java
  * CredentialController.java
  * FileController.java
  * HomeController.java
  * NoteController.java
  * SessionController.java
  * SignupController.java
3. Making Calls to the Database with MyBatis Mappers
  * Since you were provided with a database schema to work with, you can design Java classes to match the data in the database. These should be POJOs (Plain Old Java Objects) with fields that match the names and data types in the schema, and you should create one class per database table. These classes typically are placed in a model or entity package.
  * To connect these model classes with database data, implement MyBatis mapper interfaces for each of the model types. These mappers should have methods that represent specific SQL queries and statements required by the functionality of the application. They should support the basic CRUD (Create, Read, Update, Delete) operations for their respective models at the very least. You can place these classes in (you guessed it!) the mapper package.
* CredentialMapper.java
* FileMapper.java
* NoteMapper.java
* UserMapper.java
## The Front-End
1. Login Page
2. Signup Page
3. Home Page
## Testing
1. Write Tests for User Signup, Login, and Unauthorized Access Restrictions.
Write a test that verifies that an unauthorized user can only access the login and signup pages.
Write a test that signs up a new user, logs in, verifies that the home page is accessible, logs out, and verifies that the home page is no longer accessible.
2. Write Tests for Note Creation, Viewing, Editing, and Deletion.
Write a test that creates a note, and verifies it is displayed.
Write a test that edits an existing note and verifies that the changes are displayed.
Write a test that deletes a note and verifies that the note is no longer displayed.
3. Write Tests for Credential Creation, Viewing, Editing, and Deletion.
Write a test that creates a set of credentials, verifies that they are displayed, and verifies that the displayed password is encrypted.
Write a test that views an existing set of credentials, verifies that the viewable password is unencrypted, edits the credentials, and verifies that the changes are displayed.
Write a test that deletes an existing set of credentials and verifies that the credentials are no longer displayed.
