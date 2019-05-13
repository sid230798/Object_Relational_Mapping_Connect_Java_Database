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

import javax.persistence.*;
import java.sql.*;

enum Role{
        
                 Player,Manager,Owner,Other;
                
                 /*Get the value by index*/
                
                 private static Role[] list = Role.values();
                 
                 public static Role getRole(int i) {
                        return list[i];
                 }

                 public static int listGetLastIndex() {
                        return list.length - 1;
                 }
}
        
        
@Entity
@Table
public class TeamMember {

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)

        private int MemberID;
        private double Salary;
        private java.sql.Date HireDate;
        
        @Enumerated(EnumType.STRING)
        private Role role;
        
        /*Creating Foreign key for Person as Team is unique Person*/        
        @OneToOne
        private Person PersonID;
        
        /*Creating Foreign key for Team as 1 memeber can be only in one team*/
        @OneToOne
        private Team TeamID;
        
        public TeamMember(int MemberID,double Salary,java.sql.Date HireDate){
        
                super();
                this.MemberID = MemberID;
                this.Salary   = Salary;
                this.HireDate = HireDate;
                //this.StatusID = StatusID;
        
        }
        
        public TeamMember(){
        
                super();
        }
        
        
        
        
        //private int StatusID;
        
        
        /*--------------------------------------------------------------
          POJO for Contact ID*/         
        public int getMemberID(){
        
                return this.MemberID;
        
        }
        
        public void setMemberID(int MemberID){
        
                this.MemberID = MemberID;
        
        }
        /*--------------------------------------------------------------
          POJO for Contact ID*/ 
                  
        public double getSalary(){
        
                return this.Salary;
        
        }
        
        public void setSalary(double Salary){
        
                this.Salary = Salary;
        
        }
        
        
        /*--------------------------------------------------------------
          POJO for Contact ID*/ 
        public Role getRole(){
        
                return this.role;
        
        }
        
        public void setRole(int RoleID){
        
                //this.StatusID = StatusID;
                this.role = Role.getRole(RoleID);
        
        }
        
        /*--------------------------------------------------------------
          POJO for Contact ID*/         
        public java.sql.Date getHireDate(){
        
                return this.HireDate;
        
        }
        
        public void setHireDate(java.sql.Date HireDate){
        
                this.HireDate = HireDate;
        
        }

        /*--------------------------------------------------------------
          POJO for Contact ID*/         
        public Person getPersonID(){
        
                return this.PersonID;
        
        }
        
        public void setPersonID(Person PersonID){
        
                this.PersonID = PersonID;
        
        }
        /*--------------------------------------------------------------
          POJO for Contact ID*/         
        public Team getTeamID(){
        
                return this.TeamID;
        
        }
        
        public void setTeamID(Team TeamID){
        
                this.TeamID = TeamID;
        
        }
        /*------------------------------------------------*/       
}
