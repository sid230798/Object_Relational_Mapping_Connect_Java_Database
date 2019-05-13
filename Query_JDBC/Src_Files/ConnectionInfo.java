/*

Author : Siddharth Nahar
Entry No. : 2016csb1043
Use : A complete Example of Backenend Queries of Banking and Accounts.
      Useing CRUD Operations
Date : 8/4/18  

*/

import java.sql.*;
import java.io.*;
import java.util.*;
import java.lang.*;

class ConnectionInfo{

        /* getConnection takes asks for input of url 
          then asks for Username and Password as Input  and Return Connection object*/
        public Connection getConnection(){

                try{
                     DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                     
                     /*Input UserUrl,name and Password*/
                     Scanner sc = new Scanner(System.in);
                     System.out.print("Enter the URL of Database Server : ");
                     String Server_URL = sc.nextLine();
                     System.out.print("Enter Username for accessing Server : ");
                     String UserName = sc.nextLine();
                     System.out.print("Enter Password for Accessing Server : ");
                     
                     /*Visible Password are always dangerous So input 
                      it without Echoing it using Console Read*/
                     String dbName = Server_URL.substring(Server_URL.lastIndexOf('/')+1);
                     Console con = System.console();
                     char arr[] = con.readPassword();
                     
                     String PassWord = new String(arr);
                     
                     /*Establishing a connection with Server using inputed values*/
                     Connection conn = DriverManager.getConnection(Server_URL,UserName,PassWord);
                        
                     return conn;
                     
                 }catch(Exception e){
                 
                     System.out.println(e.getMessage());   
                     return null;   
                 }

         }


}
