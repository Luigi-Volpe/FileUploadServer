# FileUploadServer

# Project Description

java sdk 11 is used

This project uses H2 database
http://localhost:8080/h2-console/

if you want your files to ber there after a restart you need to adjust your application.properties

change this line
spring.jpa.hibernate.ddl-auto=create-drop 

to this line
spring.jpa.hibernate.ddl-auto=update

and change this line
spring.datasource.url=jdbc:h2:mem:mydb

to this line
spring.datasource.url=jdbc:h2:file:**HERE A PATH TO A FOLDER U HAVE**

username: sa
psw: 

Tis is a project used for demo puposes.
This project is a restAPI server for uploading and downloading files.
It consists out of 2 domains "User" and "Document"

each user can have 0 to multiple files, files of the same user cannot have the same name.

# How to Install and Run the Project
Clone this repo and open the pom.xml as a project.
then run mvn clean install.
