
# Application based on Spring Boot 3

This application is based on the REST API, calls to the different methods exposed, <br>
to complete the crud of a company management system, then insertion of  <br>
accounts / users / roles, with the account link <-> user so that if a user is deleted his <br>corresponding account is also deleted <br>

*?The login phase is under development?* 

**what you need** : 

    1. maven installed; <br>
    2. xampp installed or in any case a mysql db; <br>
    3. create a db with this name = "springSaceva2Db" , the tables will be created for us by Hibernate<br>

**How to make it work** :

    1. need to have installed maven   
    2. after making sure that maven is correctly installed with  the mvn - version command ;  <br>
    3. then it will be necessary to have a local db for data persistence , in my case I used xampp<br>
    with mysql , but I think mysql app can also be fine as long as it is on port 3306, while to  reach the Spring application I used port 8080 <br>
    4. Then you can run the command mvn spring-boot:run in the project root directory;  <br>
    5. to test it, you can download postaman to send requests to the application and then receive a  <br>response in json format 

***Link :***

guide [MavenInstall](https://mkyong.commavenhow-to-install-maven-in-windows/ "guide maven install") <br>
download [Xampp](https://www.apachefriends.org/it/index.html "Download Xampp")
