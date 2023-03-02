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
as shown in the image below; This update takes place at the start of the application and at a <br>
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
```<?xml version="1.0" encoding="UTF-8"?>
<DIPENDENTI>
    <DIPENDENTE>
        <EMAIL>CIAO@CIOA.CIAO</EMAIL>
        <USER_NAME>MarioRed99</USER_NAME>
        <PASSWORD>12345</PASSWORD>
        <NAME>Mario</NAME>
        <SURNAME>rossi</SURNAME>
        <TAX_CODE>SDSNNSJFNSNJ</TAX_CODE>
        <YEARS>33</YEARS>
        <RULE>admin</RULE>
    </DIPENDENTE>
</DIPENDENTI>```

**Csv**<br>
email,userName,password,name,surname,taxCode,years,role

**Json**
```[
    {
        "email": "ciao@ciao.org",
        "userName": "MarioRed99",
        "pass": "44432",
        "name": "Mario",
        "surname": "rossi",
        "years": 56,
        "taxCode": "dsbdsjndj",
        "role": "ceooo2"
    }
]```

***Links :***

guide [MavenInstall](https://mkyong.commavenhow-to-install-maven-in-windows/ "guide maven install") <br>
download [Postamn](https://www.postman.com/downloads/ "Download Postman")<br>
download [Xampp](https://www.apachefriends.org/it/index.html "Download Xampp")
