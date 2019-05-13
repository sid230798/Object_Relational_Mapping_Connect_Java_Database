/*
--------------------------------------------------
Author : Siddharth Nahar
Entry No : 2016csb1043
Use : Learning ORM JPA Queries and Update
Date : 16/4/18
--------------------------------------------------
*/

/*
Object Model for Contact Table
Using JPA ,using Annotations
*/

import java.sql.*;
import javax.persistence.*;

enum Status{
        
                Active,Retired,Banned;
                
                /*Get the value by index*/
                
                 private static Status[] list = Status.values();
                 
                 

                 public static int listGetLastIndex() {
                        return list.length - 1;
                 }
                 
                 public static Status getStatus(int i) {
                 
                        if(i>listGetLastIndex())
                                return list[list.length-1];
                        else
                                return list[i];
                 }
}


@Entity
@Table
public class Team{

        /*Creating a Primary Key*/
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)

        private int TeamID;
        private String Name;
        private java.sql.Date DateCreation;
        
        @ManyToOne(cascade = CascadeType.PERSIST)
        private ContactInfo ContactID;
        
        @Enumerated(EnumType.STRING)
        private Status status;         
         
       
        
        public Team(int TeamID,String Name,java.sql.Date DateCreation){
        
                super();
                this.TeamID = TeamID;
                this.Name   = Name;
                this.DateCreation = DateCreation;
        
        }
        
        public Team(){
        
                super();
        }
        
        public int getTeamID(){
        
                return this.TeamID;
        
        }
        
        public void setTeamID(int TeamID){
        
                this.TeamID = TeamID;
        
        }
        
        public String getName(){
        
                return this.Name;
        
        }
        
        public void setName(String Name){
        
                this.Name = Name;
        
        }
        
        public Status getStatus(){
        
                return this.status;
        
        }
        
        public void setStatus(int StatusID){
        
                this.status = Status.getStatus(StatusID);
        
        }
        
        public java.sql.Date getDateCreation(){
        
                return this.DateCreation;
        
        }
        
        public void setDateCreation(java.sql.Date DateCreation){
        
                this.DateCreation = DateCreation;
        
        }
        
        public ContactInfo getContactID(){
        
                return this.ContactID;
        
        }
        
        public void setContactID(ContactInfo ContactID){
        
                this.ContactID = ContactID;
        
        }
        
        @Override
        public String toString() {
                return "Team [TeamID=" + TeamID + ", Name=" + Name + ", DateCreation=" + DateCreation + ", Status=" + status +"]";
        }
}
