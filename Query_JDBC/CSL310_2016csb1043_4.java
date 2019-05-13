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



class Banking{


         public static void main(String args[]){
                
                /*Get Object of Conecton Class using a function*/
                ConnectionInfo GetObj = new ConnectionInfo();
                
                /*Helps to get modularity in the code*/
                Connection conn = GetObj.getConnection();
                
                if(conn == null)
                        System.out.println("Invalid Input Data");
                else{
                        System.out.println("Established Connection Correctly");
                        
                        String Input_Params = args[0];
                        ExecuteQuery obj = new ExecuteQuery(conn);
                        /*Check the Input Parameters for Query Option*/
                        
                        if(Input_Params.equals("-i")){
                        
                                obj.Insert();
                                                       
                        }else{
                        
                        
                                System.out.println("Press 1 for Getting Transaction Statement of Given Account Number and between two dates");
                                System.out.println("Press 2 for Getting Category Wise Spendings of Person in given Month");
                                System.out.println("Press 3 for Getting Category Wise Spendings of all Persons in Given City in given Month");
                                System.out.print("Enter Your Option : ");
                                Scanner sc  = new Scanner(System.in);
                                int q = sc.nextInt();
                                
                                if(q == 1)
                                        obj.TransactionStatement();
                                else if(q == 2)
                                        obj.CategorySpendings();
                                else
                                        obj.CityCategorySpendings();                
                        
                        }
                }
        }


}
