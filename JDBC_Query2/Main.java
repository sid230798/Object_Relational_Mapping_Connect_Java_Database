/*
--------------------------------------------------
Author : Siddharth Nahar
Entry No : 2016csb1043
Use : Learning ORM JPA Queries and Update
Date : 16/4/18
--------------------------------------------------
*/

import java.io.*;
import java.util.*;
import java.lang.*;
import javax.persistence.*;

public class Main{


        private static PrintStream ps = System.out;
        
        public static void main(String[] args)throws Exception{

                System.setOut(new PrintStream(new File("Output.txt"),"UTF-8"));
                EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "Eclipselink_JPA" );
                
                if(args[0].equals("-i")){
                
                        System.setOut(ps);
                        System.out.println("Inserting Data Wait for Some Time .....");
                        InsertQuery obj = new InsertQuery(emfactory);
                
                        obj.InsertTeams();
                        System.setOut(ps);
                        //Thread.sleep(10000);
                        System.out.println("Inserted Successfully");
                
                }
                else{
                        System.setOut(ps);
                        Scanner sc = new Scanner(System.in);
                        ExecuteQuery obj = new ExecuteQuery(emfactory);
                        System.out.println("Press 1 for TeamMembers Players Salary Info for Given Team");
                        System.out.println("Press 2 for Getting RoleWise List of members  of Team");
                        System.out.println("Press 3 for Getting Average Salary of Players from Given State");
                        System.out.print("Enter Your Choice : ");
                        int choice = sc.nextInt();
                
                        if(choice == 1)
                                obj.PrintTeamMembers();
                        else if(choice == 2)
                                obj.RoleWisePrint();
                        else
                                obj.PrintAvgSalary();
                }


        }

}
