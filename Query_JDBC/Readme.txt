--------------------------------------------------------------------------------------------------------------------

Author : Siddharth Nahar
Entry No. : 2016csb1043
Use : Getting a idea for Using java to connect to database
      and Performing Queries
Date : 9/4/18

--------------------------------------------------------------------------------------------------------------------

For Loading the Schema in Database:

UserName:~DIR$ mysql -u USERNAME -p < Schema.sql

-- Enter Password when Prompted
-- Database name used (CSL310_2016csb1043_4)
--------------------------------------------------------------------------------------------------------------------

For Compilation and Running:

UserName:~DIR$ javac -cp API_Jars/mysql-connector.jar:Classes CSL310_2016csb1043_4.java

UserName:~DIR$ java -cp .:Classes/:API_Jars/mysql-connector.jar:API_Jars/commons-lang3-3.7.jar Banking (-i or -q)

Input Required : 

* DATABASE NAME IS CSL310_2016csb1043_4
* Give Command Line argument as -i or -q for Perfroming Update or Querying for Output.
* For Establishing Connection , SERVER_URL,USERNAME,PASSWORD must be inputed when Prompt
* For Selection of Query enter number as shown in UI to Perform it.
* For Queries MonthName,Account Number must be inputed when prompt.

Output :

* Outputs query as selected by User and Displays Result.

---------------------------------------------------------------------------------------------------------------------

Working:

* Establishing the Connection by Server_Url ,USERNAME and Password.
* If -i is Command line Argument Insert the Valuses as Follows :
  
  -- Contacts Enter 5 Different Cities as (Mumbai, Delhi, Chandigarh, Hyderabad, Banglore) index will change every time.
  -- Enter Random Mobile,Street Adress,Email etc.
  
  -- Account Holder Enter Names as Enum set in Info.properties file. 
  -- Enter Random PAN numbers as collection of Letters and digits (UNIQUE CONSTRAINT is applied).
  -- Enter DOB as Random
  -- Apply Foriegn Key to Account Holder as Contact_Id which will be known
  
  -- Enter Bank_Account ,Opening Date,Account_Type = SAVINGS is chosen at random
  -- Foriegn key of Account_Holder is Known (ACCOUNT_ID is AUTO_INCREMENTED) So Account_No = ACCOUNT_ID
  -- Opening Date is todays Date will be Chosen
  
  -- Entering Transaction at Random. Category Will be Chosen at Random from ENUM set in Schema.
  -- Transaction Time will be Chosen at now() time,When Programs Run.
  -- Four Updates are Required : 
        1. Two Transactions will be inserted as CREDIT one and DEBIT one.
        2. Two Balances WILL be Updated One who Debits and One who Credits.
        
        
* If -q is Command line Argument Input the Values as :

  -- 3 Queries Can be Performed So,Input the Number for Query.
  -- First Query Requires Account_number which is (1...50) Then Two Dates in format dd-mm-yyyy
  -- Second Query requires Account_number and Month Name full
  -- Third Requires CityName(Mumbai, Delhi, Chandigarh, Hyderabad, Banglore) and Month name
   
* Each Account which will have LastUpdate before 2 years will be closed and Declared as Dormant.   
* Each time -i options inserts Transactions for all Active Accounts So please wait till Output Completes.

* Src Files For Query Execution and Connection is Src_File folder 
* API used are in API folder
* Classes contains pre-complied two classes of Execution and Connection

------------------------------------------------------------------------------------------------------------------------
        
