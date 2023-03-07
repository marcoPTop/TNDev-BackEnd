# Application based on Spring Boot 3

This application is based on the REST API, calls to the different exposed methods, <br>
complete crud of a company management, then inserting <br>
accounts / users / roles, with the account <-> user link so that if a user is deleted <br>his <br>corresponding account is also deleted <br>
<br>

**~~?Is the login phase under development?~~**

***~~Update 02-16-23 Login & Password Encryption~~*** :smile:

***Update 01-03-23 updates the user database automatically on application startup and at a scheduled time on Db***<br>
<br>
Added DB update via three file types (XML, CSV and JSON) <br>
as shown below; This update takes place on application startup and in one hour <br>
specification saved in the DB
<br>

**what you need** :

1. Maven installed; <br>
2. xampp installed or in any case a download of the mysql db [Xampp](https://www.apachefriends.org/it/index.html "Download Xampp"); <br>
3. create a db with this name = "springSaceva2Db" , the tables will be created for us by Hibernate;<br>
4. Postman installed, download [Postman](https://www.postman.com/downloads/ "Download Postaman")<br>

**How to make it work** :

1. you must have installed the maven guide [MavenInstall](https://phoenixnap.com/kb/install-maven-windows "guide maven install");
2. after making sure maven is correctly installed with :<br>
    mvn -version command
3. then it will be necessary to have a local db for data persistence, in my case I used xampp<br>
    with mysql , but I think mysql app can also work as long as it's on port 3306, while to reach the Spring application I used port 8080; <br>
4. Then you can run the command mvn spring-boot:run in the root directory of the project; <br>
5. to test it, you can download postaman to send requests to the application and then receive a <br>response in json format;

***Example of file type :***

**xml**

https://github.com/marcoPTop/TNDev-BackEnd/blob/6b4595623271456b19871b1bd668dcd5f2c57bde/File%20test/dipendenti.xml_2023-02-21#lL1-L13

**Csv**

https://github.com/marcoPTop/TNDev-BackEnd/blob/6b4595623271456b19871b1bd668dcd5f2c57bde/File%20test/dipendenti_2023-03-01.csv#L1-L2

**json**

https://github.com/marcoPTop/TNDev-BackEnd/blob/6b4595623271456b19871b1bd668dcd5f2c57bde/File%20test/dipendenti.json_2023-03-01#L1-L12


***Links :***

guide [MavenInstall](https://phoenixnap.com/kb/install-maven-windows "maven installation guide") <br>
download [Postamn](https://www.postman.com/downloads/ "Download Postman")<br>
download [Xampp](https://www.apachefriends.org/it/index.html "Download Xampp")

***Note :***
The application has been set to try to update the database automatically by reading a <br>
path passed to it from the db every 15 seconds, this is in the development phase to see any errors,<br>
but it goes without saying that it is not a suitable choice in a "real" case.