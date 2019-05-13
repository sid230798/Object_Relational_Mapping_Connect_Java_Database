-- -----------------------------------------------------------

-- Author : Siddharth Nahar
-- Entry No : 2016csb1043
-- Use : Basic Development of Backend using Java
-- Date : 1/4/2018

-- -----------------------------------------------------------


-- Dropping Schema if already existed 
DROP SCHEMA IF EXISTS CSL310_2016csb1043_4;
CREATE DATABASE CSL310_2016csb1043_4;
USE CSL310_2016csb1043_4;


-- Creating Contact Info Table
-- Primary key as contactID

CREATE TABLE Contact_Info(

        Contact_ID INT UNSIGNED NOT NULL AUTO_INCREMENT,
        Street_Address TEXT,
        City varchar(20),
        State varchar(20),
        Country varchar(20),
        Postal_Code INT UNSIGNED,
        Phone numeric(12,0),
        Mobile numeric(12,0),
        Email varchar(50) default '123.com',
        PRIMARY KEY (Contact_ID)
        
);
-- Creating Account Holder Table As given in schema
-- Primary key as PersonId and foriegn key as ContactID

CREATE TABLE Account_Holder(

        Person_ID INT UNSIGNED NOT NULL AUTO_INCREMENT,
        PAN_ID varchar(30) NOT NULL, 
        First_Name varchar(30) NOT NULL,
        Last_Name varchar(30) NOT NULL,
        DOB Date NOT NULL,
        Contact_ID INT UNSIGNED,
        PRIMARY KEY (Person_ID),
        UNIQUE KEY (PAN_ID),
        FOREIGN KEY (Contact_ID) REFERENCES Contact_Info(Contact_ID)
        
);

-- Creating Bank Account as given in schema
-- Primary key as Account ID,Foreign key as Account
-- holder name

CREATE TABLE Bank_Account(

        Account_ID INT UNSIGNED NOT NULL AUTO_INCREMENT,
        Date_Open DATETIME NOT NULL,
        Date_Close DATETIME,
        Status ENUM('Active','Dormant'),
        Person_ID INT UNSIGNED,
        Account_Type ENUM('Current','Savings'),
        Current_Balance numeric(12,4) check(Current_Balance >= 0.0),
        LastTransaction DATETIME,
        PRIMARY KEY (Account_ID,Person_ID),
        FOREIGN KEY (Person_ID) REFERENCES Account_Holder(Person_ID)
        
);

-- Creating Table of Transactions
-- Primary key as Transaction Id
-- Foriegn key as Account Id

CREATE TABLE Account_Transaction(

        Transaction_ID INT UNSIGNED NOT NULL AUTO_INCREMENT,
        Transaction_Type ENUM('Credit','Debit'),
        Transaction_Time DATETIME,
        Amount numeric(12,4),
        Balance_Remaining numeric(12,4),
        Account_ID INT UNSIGNED,
        Category ENUM('Tax', 'Salary', 'Grocery', 'Medical', 'Phonebill', 'Dining', 'Entertainment', 'Money_Transfer'),
        Remarks TEXT,
        PRIMARY KEY (Transaction_ID),
        FOREIGN KEY (Account_ID) REFERENCES Bank_Account(Account_ID)
        
);

