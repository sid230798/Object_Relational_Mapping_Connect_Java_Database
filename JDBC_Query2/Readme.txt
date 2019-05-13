-----------------------------------------------------------------------

Author : Siddharth Nahar
Entry No : 2016csb1043
Use : Used JPA library for Creating Schema and Executing Queries through Java
Date : 21/4/18

----------------------------------------------------------------------

For Just to Create Empty Database :
Username:~PATH$ mysql -u root -p
USERNAME:~PATH$ create database CSL310_2016csb1043_5

---------------------------------------------------------------------

Configuration Settings : 

* META-INF/persistence.xml File : 

Please input your Username,URL,Passwprd at : 

<property name="javax.persistence.jdbc.url" value="ENTER DATABASE URL"/>
<property name="javax.persistence.jdbc.user" value="ENTER USERNAME"/>
<property name="javax.persistence.jdbc.password" value="ENTER PASSWORD"/>

----------------------------------------------------------------------

For Running and Compilaton : 

Username:~PATH$ javac -cp ObjectTables/jpa.jar:Queries/Classes:. Main.java
Username:~PATH$ java -cp .:ObjectTables/Classes/:ObjectTables/jpa.jar:ObjectTables/mysql-connector.jar:eclipselink.jar:Queries/Classes/ Main OPTION_OF_EXECUTION

Input : 

* OPTION_OF_EXECUTION : -i or -q
  
  -i for Insertion of Dummy Data
  -q for Execution
  
* For -q Option Input when Prompted.

* All Libraries Commandline Prints have been moved to Output.txt
* Only Main things will be printed on command line

-----------------------------------------------------------------------

Directory Structure : 

* ObjectTables/Src Folder Contains all Entities which will be converted to Tables.
* ObjectTables/Classes Contains its corresponding classes
* Queries/Src contains Insert Query Algo and Quering Algo Details will be discussed in Design Document
* Queries/Classes Corresponding classes 
* Jars are Kept in specific places 

------------------------------------------------------------------------

Working :

* Use Persisitence file to create Schema ,Insertion and Querying
* EntityManager,Factory used for Quering and Accessing Database
* Annotations used For mapping Field to Table Fields

----------------------------------------------------------------------------
