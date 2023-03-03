# Application based on Spring Boot 3

This application is based on the REST API, calls to the different methods exposed, <br>
to complete the crud of a company management system, then insertion of  <br>
accounts / users / roles, with the account link <-> user so that if a user is deleted his <br>corresponding account is also deleted <br>
<br>

**~~?The login phase is under development?~~**

***~~Update 16-02-23 Login and encrypting passowrd work~~*** :smile:

***Update 01-03-23 update database of User automatic when start application and a scheduling time on Db***<br>
<br>
Added the update of the DB through three file types (XML, CSV and JSON) with the shape format <br>
as shown below; This update takes place at the start of the application and at a <br>
specific time saved in DB 
<br>

**what you need** : 

1. maven installed; <br>
2. xampp installed or in any case a mysql db download [Xampp](https://www.apachefriends.org/it/index.html "Download Xampp"); <br>
3. create a db with this name = "springSaceva2Db" , the tables will be created for us by Hibernate;<br>
4. postman installed , download [Postman](https://www.postman.com/downloads/ "Download Postaman")<br>

**How to make it work** :

1. need to have installed maven  guide [MavenInstall](https://mkyong.commavenhow-to-install-maven-in-windows/ "guide maven install"); 
2. after making sure that maven is correctly installed with  the : 
    mvn -version command  <br>
3. then it will be necessary to have a local db for data persistence , in my case I used xampp<br>
with mysql , but I think mysql app can also be fine as long as it is on port 3306, while to  reach the Spring application I used port 8080; <br>
4. Then you can run the command mvn spring-boot:run in the project root directory; <br>
5. to test it, you can download postaman to send requests to the application and then receive a  <br>response in json format; 

***Example file type :***

**Xml**
https://github.com/marcoPTop/TNDev-BackEnd/blob/25bacb00c5f05443073f2123f0ac533c22bcd250/File%20test/dipendenti.xml_2023-02-21#L1-L13

**Csv**<br>
https://github.com/marcoPTop/TNDev-BackEnd/blob/25bacb00c5f05443073f2123f0ac533c22bcd250/File%20test/dipendenti_2023-03-01.csv#L1-L2

**Json**
https://github.com/marcoPTop/TNDev-BackEnd/blob/25bacb00c5f05443073f2123f0ac533c22bcd250/File%20test/dipendenti.json_2023-03-01#L1-L12

***Links :***

guide [MavenInstall](https://mkyong.commavenhow-to-install-maven-in-windows/ "guide maven install") <br>
download [Postamn](https://www.postman.com/downloads/ "Download Postman")<br>
download [Xampp](https://www.apachefriends.org/it/index.html "Download Xampp")
