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

@Entity
@Table
public class Person{

        /*Creating a Primary Key*/
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        
        private int PersonID;
        private String First_Name;
        private String Last_Name;
        private java.sql.Date DateOfBirth;

        /*Creating Foreign key for ContactInfo*/
        @ManyToOne(cascade=CascadeType.PERSIST)
        private ContactInfo ContactID;

        public Person(int PersonID,String First_Name,String Last_Name,java.sql.Date DateOfBirth){
        
                super();
                this.PersonID = PersonID;
                this.First_Name = First_Name;
                this.Last_Name = Last_Name;
                this.DateOfBirth = DateOfBirth;
                
        }

        public Person(){
        
                super();
        
        }
        /*--------------------------------------------------------------
          POJO for Contact ID*/          
        public int getPersonID(){
        
                return this.PersonID;
        
        }
               
        public void setPersonID(int PersonID){
        
                this.PersonID = PersonID;
        
        }
        /*--------------------------------------------------------------
          POJO for Contact ID*/   
        public String getFirst_Name(){
        
                return this.First_Name;
        
        }
        
        public void setFirst_Name(String First_Name){
        
                this.First_Name = First_Name;
        
        }
        /*--------------------------------------------------------------
          POJO for Contact ID*/   
        public String getLast_Name(){
        
                return this.Last_Name;
        
        }
        
        public void setLast_Name(String Last_Name){
        
                this.Last_Name = Last_Name;
        
        }
        /*--------------------------------------------------------------
          POJO for Contact ID*/   
        public java.sql.Date getDateOfBirth(){
        
                return this.DateOfBirth;
        
        }
        
        public void setDateOfBirth(java.sql.Date DateOfBirth){
        
                this.DateOfBirth = DateOfBirth;
        
        }
        /*--------------------------------------------------------------
          POJO for Contact ID*/   
        public ContactInfo getContactID(){
        
                return this.ContactID;
        
        }
        
        public void setContactID(ContactInfo ContactID){
        
                this.ContactID = ContactID;
        
        }
        /*---------------------------------------------------------------*/
        
        @Override
        public String toString() {
                return "Person [PersonID=" + PersonID + ", First_Name=" + First_Name + ", Last_Name=" + Last_Name + ", DateOfBirth=" + DateOfBirth +"]";
        }
}
