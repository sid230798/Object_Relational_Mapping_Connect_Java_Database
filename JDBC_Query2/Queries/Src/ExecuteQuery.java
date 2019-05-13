/*
--------------------------------------------------
Author : Siddharth Nahar
Entry No : 2016csb1043
Use : Learning ORM JPA Queries and Update
Date : 16/4/18
--------------------------------------------------
*/


/*Executing Query from given Constraints*/

import java.lang.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import javax.persistence.*;

public class ExecuteQuery{

        private PrintStream ps = System.out;
        
        private EntityManagerFactory emfactory;
        //private EntityManager entitymanager ; 

        /*Public Constructor for ExecuteQuery*/
        public ExecuteQuery(EntityManagerFactory emfactory){
        
                //System.setOut(new PrintStream("Log.txt"));
                this.emfactory = emfactory;
                //this.entitymanager = emfactory.createEntityManager( );
        
        }        

        /*Query for TeamMembers having Salary between two values and Name by input*/
        public void PrintTeamMembers(){

                try{
                        /*Taking the Required Input Values*/
                        EntityManager entitymanager = emfactory.createEntityManager( );
                        System.setOut(ps);
                        Scanner sc = new Scanner(System.in);
                        System.out.print("Please Input the Team Name : ");
                        String Team_Name = sc.nextLine();
                        System.out.print("Please Input the Salary Value DownLimit : ");
                        double Salary_b = sc.nextDouble();
                        System.out.print("Please Input the Salary Value UpLimit : ");
                        double Salary_a = sc.nextDouble();
                        
                        System.setOut(new PrintStream(new File("Output.txt"),"UTF-8"));
                        /*Creating Query to execute Remember Refrence of Refrence is used so Careful*/
                        Query query = entitymanager.createQuery("SELECT p,tm FROM TeamMember tm , Person p , Team t WHERE tm.PersonID.PersonID = p.PersonID AND tm.TeamID.TeamID = t.TeamID AND tm.role = Role.Player AND t.Name = :Name AND tm.Salary > :low AND tm.Salary < :high");

                        
                        /*Set the Parameters*/
                        query.setParameter("Name",Team_Name);
                        query.setParameter("low",Salary_b);
                        query.setParameter("high",Salary_a);
                        
                        /*get Two Objects Pair List and Output in Proper format*/
                        List<Object[]> list = (List<Object[]>)query.getResultList();
                        System.setOut(ps);
                        System.out.println("Team Name : "+Team_Name);
                        System.out.printf("%-30s %-20s %-20s\n","Name","Salary","Role"); 
                        
                        
                        /*Iterate through List*/
                        for(Object[] arr:list){
                        
                                Person p = (Person)arr[0];
                        
                                TeamMember tm = (TeamMember)arr[1];
                                
                                String name = p.getFirst_Name() + " "+ p.getLast_Name();
                                String role = tm.getRole().toString();
                               // String name = p.getName();
                                double Salary = tm.getSalary();
                                System.out.printf("%-30s %-20.2f %-20s\n",name,Salary,role);
                                
                        }
                }catch(Exception e){
                
                        System.setOut(ps);
                        System.out.println(e.getMessage());
                
                }              

        }
        
        /*Query for Roll wise numbrs of Team Members*/
        public void RoleWisePrint(){
        
                try{
                        /*Take the Input from User for Team Name*/
                        EntityManager entitymanager = emfactory.createEntityManager( );
                        System.setOut(ps);
                        Scanner sc = new Scanner(System.in);
                        System.out.print("Please Input the Team Name : ");
                        String Team_Name = sc.nextLine();
                        
                        /*Getting Ascending Order List by Member ID of Team Member*/
                        System.setOut(new PrintStream(new File("Output.txt"),"UTF-8"));
                        Query query = entitymanager.createQuery("SELECT p,tm FROM TeamMember tm , Person p , Team t WHERE tm.PersonID.PersonID = p.PersonID AND tm.TeamID.TeamID = t.TeamID AND t.Name = :Name ORDER BY tm.MemberID");
                
                        /*Set the Parameters*/
                        query.setParameter("Name",Team_Name);
                        
                        /*Get the Object List*/
                       List<Object[]> list = (List<Object[]>)query.getResultList();
                       System.setOut(ps);
                       System.out.println("Team Name : "+Team_Name);
                
                       System.out.printf("%-30s %-20s %-20s\n","Name","ID","Role");
                       
                       /*Iterate thorugh list and print */
                       for(Object[] arr:list){
                       
                                Person p = (Person)arr[0];
                                TeamMember tm = (TeamMember)arr[1];
                                String name = p.getFirst_Name() + " "+ p.getLast_Name();
                                int id = tm.getMemberID();
                                String role = tm.getRole().toString();
                                System.out.printf("%-30s %-20d %-20s\n",name,id,role);
                       }
                       
                       
                }catch(Exception e){
                
                        System.setOut(ps);
                        System.out.println(e.getMessage());
                
                }  
        }
 
        public void PrintAvgSalary(){
        
                try{
                        /*Take the Input from User for Team Name*/
                        EntityManager entitymanager = emfactory.createEntityManager( );
                        System.setOut(ps);
                        Scanner sc = new Scanner(System.in);
                        System.out.print("Please Input the State Name : ");
                        String State_Name = sc.nextLine();
                
                        System.setOut(new PrintStream(new File("Output.txt"),"UTF-8"));
                        Query query = entitymanager.createQuery("SELECT AVG(tm.Salary) FROM TeamMember tm, Person p, ContactInfo c WHERE tm.PersonID.PersonID = p.PersonID AND p.ContactID.ContactID = c.ContactID AND tm.role = Role.Player AND c.State = :State GROUP BY c.State");
                        
                        /*Set the Parameters*/
                        query.setParameter("State",State_Name);
                        
                        double Avg_Salary = (double)query.getSingleResult();
                        System.setOut(ps);
                        System.out.println("State : "+State_Name);
                        System.out.printf("Average Salary : %10.2f\n",Avg_Salary);
        
                }catch(Exception e){
                
                        System.setOut(ps);
                        System.out.println(e.getMessage());
                
                }    
        }               
}
