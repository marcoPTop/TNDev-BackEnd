
# Application based on Spring Boot 3

This application is based on the REST API, calls to the different methods exposed, 
to complete the crud of a company management system, then insertion of  
accounts / users / roles, with the account link <-> user so that if a user is deleted his corresponding account is also deleted 

*?The login phase is under development?* 

**what you need** : 

    I maven installed; 
    II xampp installed or in any case a mysql db; 
    III create a db with this name = "springSaceva2Db" , the tables will be created for us by Hibernate

**How to make it work** :

    I need to have installed maven ( guide https://mkyong.com/maven/how-to-install-maven-in-windows/ );II after making sure that maven is correctly installed with the mvn - version command ;
    III then it will be necessary to have a local db for data persistence , in my case I used xamp with mysql , but I think mysql app can also be fine as long as it is on port 3306, while to reach the Spring application I used port 8080
    IV Then you can run the command mvn spring-boot:run in the project root directory; 
    V to test it, you can download postaman to send requests to the application and then receive a response in json format