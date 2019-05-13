----------------------------------------------------------------------------------

Author : Siddharth Nahar
Entry No : 2016csb1043
Use : Basic Connections and Extraction of Meta Data from Database
Date : 18/3/18

----------------------------------------------------------------------------------

For Compilation and Run :

Username:~Dir$ javac -cp mysql-connector.jar CSL310_2016csb1043_3.java
Username:~Dir$ java -cp .:mysql-connector.jar Trial SERVER_PAT_URL

Input:

1.URL of database as command line argument
2.Username to access database 
3.Password when prompted(Nothing will be echoed back on screen)

Ouptut:

Prints all information of Metadata asked 

----------------------------------------------------------------------------------

*Uses DriverManager to register for Database
*Use of DatabaseMetaData class for extraction of Metadata
*GetTables,GetColumns,GetIndexInfo use for rest of information about metadata. 
