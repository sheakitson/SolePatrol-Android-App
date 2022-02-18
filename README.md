# DigitalTransformationProject - Sole Patrol
![SolePatrol](https://ibb.co/7WZrcXt)

# Instuctions on how to run the project! Steps: 
Database Startup and Creation
1. Download MySql database server for your local machine. 
2. Create a database called SolePatrol. 

Spring Startup 
1. Open the Spring Boot Application with an envrionment such as Intellj. 
2. Install the Libraries and Dependancies 
3. Run the Spring Boot Service 

Android Studio
1. Download Android Studio. 
2. Download a Google Pixel 3A/4A Emulator. 
3. Open the SolePatrol2 project and install dependancies.
4. Run the app using the emulators provided. 


## SQL - Database
The SolePatrol Database contains the Lighting and User tables. These tables store a username and password. A scenario has been identified where we may need to store more information on our users. However, in the demo version, we will be only using the fields currently identified in the database design.
|Commands|

|LOAD SCRIPT: mysql -u root -p SolePatrol < PATHTO/digitaltransformation-project/SQL/CreateScript.sql  |

|TURN SERVER ON: sudo /usr/local/mysql/support-files/mysql.server start

|TURN SERVER OFF: sudo /usr/local/mysql/support-files/mysql.server stop

|LOGIN: mysql -u root -p

## Android Studio Front End Links 
### API Portal: 
https://console.cloud.google.com/home/dashboard
### APIS - Maps: 
https://developers.google.com/maps/documentation/android-sdk/overview 
### APIS - Directions: 
https://developers.google.com/maps/documentation/directions/overview 

