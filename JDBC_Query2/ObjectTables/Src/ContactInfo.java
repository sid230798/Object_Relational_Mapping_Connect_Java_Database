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

@Entity
@Table
public class ContactInfo{

        /*Creating a Primary Key*/
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)

        /*Entities in Contact Table*/
        private int ContactID;
        private String Address;
        private String City;
        private String State;
        private String Country;
        private int PostalCode;
        private long Mobile;
        private String Email;
        
        /*Public Constructor taking Entities as input*/
        public ContactInfo(int ContactID,String Address,String City,String State,String Country,int PostalCode,long Mobile,String Email){
        
                super();
                this.ContactID  = ContactID;
                this.Address    = Address;
                this.City       = City;
                this.State      = State;
                this.Country    = Country; 
                this.PostalCode = PostalCode;
                this.Mobile     = Mobile;
                this.Email      = Email;      
        
        }
        
        public ContactInfo(){

                super();                
                
        }
        /*--------------------------------------------------------------
          POJO for Contact ID*/       
        public int getContactID(){
        
                return this.ContactID;
        
        }
        
        public void setContactID(int ContactID){
        
                this.ContactID  = ContactID;
        
        }
        /*--------------------------------------------------------------
        POJO for Addresss*/
        
        public String getAddress(){
        
                return this.Address;
                
        }
        
        public void setAddress(String Address){
        
                this.Address    = Address; 
        
        }
        /*--------------------------------------------------------------
          POJO for City*/
        public String getCity(){
        
                return this.City;
                
        }
        
        public void setCity(String City){
        
                this.City       = City; 
        
        }
        /*-------------------------------------------------------------
         POJO for State*/
        public String getState(){
        
                return this.State;
                
        }
        
        public void setState(String State){
        
                this.State      = State; 
        
        }
        /*---------------------------------------------------------------
         POJO for Country*/
        public String getCountry(){
        
                return this.Country;
                
        }
        
        public void setCountry(String Country){
        
                this.Country    = Country; 
        
        }
        /*---------------------------------------------------------------
         POJO for PostalCode*/
        public int getPostalCode(){
        
                return this.PostalCode;
        
        }
        
        public void setPostalCode(int PostalCode){
        
                this.PostalCode = PostalCode;        
        }
        /*---------------------------------------------------------------
         POJO for Mobile*/
        public long getMobile(){
        
                return this.Mobile;
        
        }
        
        public void setMobile(long Mobile){
        
                this.Mobile     = Mobile;
        
        }
        /*----------------------------------------------------------------
         POJO for Email*/
        public String getEmail(){
        
                return this.Email;
        
        }
        
        public void setEmail(String Email){
        
                this.Email      = Email;
        
        }
        /*---------------------------------------------------------------*/
        @Override
        public String toString() {
                return "ContactInfo [ContactID=" + ContactID + ", Address=" + Address + ", City=" + City + ", State=" + State + ", Country="+Country+", PostalCode="+PostalCode+", Mobile="+Mobile+", Email="+Email+"]";
   }
}
