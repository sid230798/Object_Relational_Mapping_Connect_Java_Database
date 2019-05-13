/*
--------------------------------------------------
Author : Siddharth Nahar
Entry No : 2016csb1043
Use : Learning ORM JPA Queries and Update
Date : 16/4/18
--------------------------------------------------
*/


/*Inserting Random Sensible Data through some Constraints*/

import java.lang.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import javax.persistence.*;

public class InsertQuery{

        private EntityManagerFactory emfactory;
         
        private String FirstNames[],LastNames[],Emails[],City[],States[],Address[];
        
        /*Constructor for InsertQuery Creates ManagerFactory Object and Generate Random Arrays to be used*/
        public InsertQuery(EntityManagerFactory emfactory){
        
                try{
                        System.setOut(new PrintStream(new File("Output.txt"),"UTF-8"));
                        this.emfactory = emfactory;
                        //this.entitymanager = emfactory.createEntityManager( );
                        GenerateRandomArrays();
                        
                }catch(Exception e){
                
                        System.setOut(new PrintStream(System.out));
                        System.out.println(e.getMessage());
                
                }  
        }

        /*Generates Random Arrays to be used*/
        private void GenerateRandomArrays(){
        
                try{
                        /*Loading file as Properties file For Easy Reading*/
                        Properties Props = new Properties();
                        FileInputStream in = new FileInputStream("Info.properties");
                        
                        Props.load(in);
                        FirstNames = Props.getProperty("FirstName").split(",");
                        LastNames  = Props.getProperty("LastName").split(",");
                        Emails = Props.getProperty("Email").split(",");
                        City = Props.getProperty("City").split(",");
                        States = Props.getProperty("State").split(",");        
                        Address = Props.getProperty("Address").split(",");
                        
                        System.out.println(FirstNames[0]+" "+FirstNames[3]);
        
                }catch(Exception e){
                
                        System.out.println(e.getMessage());
                }
                
        
        }
        
        /*Genrate Random ContactInfo Object*/
        private ContactInfo CreateContactObj(){
        
                Random rand = new Random();
                int CityID = rand.nextInt(City.length-2);
                int PlotNo = rand.nextInt(99);
                int AddressID = rand.nextInt(Address.length-2);
                int _ContactID = Math.abs(rand.nextInt());  
                String _Address = "Plot No. "+Integer.toString(PlotNo)+" , "+Address[AddressID];
                String _City = City[CityID];
                String _State = States[CityID];
                int _PostalCode = (int)(rand.nextDouble()*100000);
                long _Mobile = (long)(rand.nextDouble()*10000000000L);

                return new ContactInfo(_ContactID,_Address,_City,_State,"India",_PostalCode,_Mobile,"dummy");        
        
        }
        
        private Person CreatePersonObj(){
        
                Random rand = new Random();
                
                long minDate = 152318233191L;
                long maxDate = 982318233191L;
                long time = (long)(rand.nextDouble()*(maxDate-minDate+1) + minDate);
                int  PersonID =  Math.abs(rand.nextInt());
                int FirstID = rand.nextInt(FirstNames.length-2);
                int LastID = rand.nextInt(LastNames.length-2);
                java.sql.Date DateOfBirth = new java.sql.Date(time);
                String _First = FirstNames[FirstID];
                String _Last = LastNames[LastID];
                
                return new Person(PersonID,_First,_Last,DateOfBirth);
        
        
        }
        
        private TeamMember CreateTeamMemberObj(){
        
                Random rand = new Random();
                long min = 10000L;
                long max = 1000000L;
                double Salary = rand.nextDouble()*(max-min+1)+min;
                
                long minOpen =1026546232642L;
                java.util.Date now = new java.util.Date();  
                long maxOpen = now.getTime();        
        
                long time = (long)(rand.nextDouble()*(maxOpen-minOpen+1) + minOpen);
                
                java.sql.Date HireDate = new java.sql.Date(time);
                 int  TeamMemberID =  Math.abs(rand.nextInt());
                 
                 return new TeamMember(TeamMemberID,Salary,HireDate);
                 
        }
        
        
        /*Insert Players using Type as mentioned*/
        private void InsertMembers(int Role,int Number,Team DummyTeam){
        
                EntityManager entitymanager = emfactory.createEntityManager( );
                
                Random rand  = new Random();
                                
 
                for(int i = 0;i < Number;i++){
                        
                        
                         entitymanager.getTransaction( ).begin( );
                         
                         /*Generate Random Mail Server name*/
                         String EmailID = Emails[rand.nextInt(Emails.length-2)];
                         
                         /*Create Person Object*/
                         Person DummyPerson = CreatePersonObj();
                         
                         /*Create Contact Object and Update its Email*/                             
                         ContactInfo DummyContact = CreateContactObj();      
                         String email = DummyPerson.getFirst_Name()+"."+DummyPerson.getLast_Name()+"@"+EmailID;
                         DummyContact.setEmail(email);
                         
                         /*Persist The Contact Class*/
                         entitymanager.persist(DummyContact);
                         
                         /*Persist the Person class*/
                         DummyPerson.setContactID(DummyContact);
                         entitymanager.persist(DummyPerson);
                         
                         /*Create Team Member Object and commit the result Create new Team and its Office*/
                         
                         TeamMember DummyMember = CreateTeamMemberObj();
                                                  
                         DummyMember.setRole(Role);
                         DummyMember.setPersonID(DummyPerson);
                         DummyMember.setTeamID(DummyTeam);
                         
                         entitymanager.persist(DummyMember);
        
                         entitymanager.getTransaction( ).commit( );        
                }
                
                entitymanager.close( );       
        
        
        }
        
        /*Generate Random Team Name*/
        private String GenerateTeam(){
        
                Random rand = new Random();
                int length = rand.nextInt(1)+3;
                String team = "";
                
                for(int i=0;i<length;i++){
                
                        char _char = (char)(rand.nextInt(26)+65);
                        team += _char;
                
                }
                
                return team;
        
        }
        
        /*Create Teams by Insertion*/
        public void InsertTeams(){
        
        
                
                EntityManager entitymanager = emfactory.createEntityManager( );
                
                Random rand  = new Random();
                
                for(int i=0;i<20;i++){
        
                        entitymanager.getTransaction( ).begin( );
                        String Name = GenerateTeam();
                        String EmailID = Emails[rand.nextInt(Emails.length-2)];
                        
                        String Email = Name+"@"+EmailID;
                        /*Create Contact Office By Default*/
                        ContactInfo DummyOffice = CreateContactObj();
                        DummyOffice.setEmail(Email);
                        entitymanager.persist(DummyOffice);
                        
                        /*Creating Team Class By other members*/
                        long minDate = 1000;
                        long maxDate = 982318233191L;
                        long time = (long)(rand.nextDouble()*(maxDate-minDate+1)+minDate);
                        java.sql.Date DateCreation = new java.sql.Date(time);
                        int TeamMemberID = Math.abs(rand.nextInt());
                        
                        int Status = rand.nextInt(2);
                        /*Create Team obj*/
                        Team DummyTeam = new Team(TeamMemberID,Name,DateCreation);
                        DummyTeam.setContactID(DummyOffice);
                        DummyTeam.setStatus(Status);
                        
                        entitymanager.persist(DummyTeam);
                        entitymanager.getTransaction( ).commit( );
                        
                        int Role;
                        //int Role = rand.nextInt(3);
                        for(Role=0;Role<4;Role++){
                        
                                int Number = 0;
                                if(Role == 0){
                                        Number = rand.nextInt(5)+10;
                                }else if(Role == 1 || Role == 2){
                                        Number = rand.nextInt(3)+2;
                                }else
                                        Number = rand.nextInt(4);
                                
                                InsertMembers(Role,Number,DummyTeam);
                       }        
                        
                
                }
                
                entitymanager.close( );
                emfactory.close( ); 
        
        }


}
