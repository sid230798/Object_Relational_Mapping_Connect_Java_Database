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
import java.text.*;
import org.apache.commons.lang3.*;

class ExecuteQuery{

        private Connection conn;
        private Statement Query;
        String FirstNames[],LastNames[],Emails[],City[],States[],Address[];
        /*Assigining Connection Object to Class
          Connection object*/
        public ExecuteQuery(Connection conn){

                try{     
                        /*Get Connection and Statement Objects*/
                        this.conn = conn;
                        this.Query = conn.createStatement();
                        
                        GenerateArrays();
                        
                }catch(SQLException e){
                
                        System.out.println(e.getMessage());
                
                }
        }


        void GenerateArrays(){
        
                try{
                        Properties Props = new Properties();
                        FileInputStream in = new FileInputStream("Info.properties");
                
                        Props.load(in);
                        String first = Props.getProperty("FirstName");
                        String last  = Props.getProperty("LastName");
                        String email = Props.getProperty("Email");
                        String Cities = Props.getProperty("City");
                        String States_1 = Props.getProperty("State");
                        String add = Props.getProperty("Address1"); 
                        FirstNames = first.split(",");
                        LastNames  = last.split(",");
                        Emails   =   email.split(",");
                        City = Cities.split(",");
                        States = States_1.split(",");
                        Address = add.split(",");
                        //LastNames  = last.split(",");
                        //System.out.println(FirstNames[0]+" "+FirstNames[1]+" "+FirstNames[9]);
                        
                }catch(Exception e){
                
                        System.out.println(e.getMessage());
                }
                
        }
        /*Gives Cuurent Balance for each Account no in transaction*/
        public double getBalance(int i){
        
        
                String s = "Select Current_Balance from Bank_Account where Account_ID = "+Integer.toString(i)+" and Status = 'Active' ";
                
                try{
                        ResultSet rs = Query.executeQuery(s);
                        
                        double Balance = 0;
                        if(rs.next())
                                Balance = rs.getDouble(1);
                
                     
                        return Balance;
                }catch(SQLException e){
                
                        System.out.println(e.getMessage());
                        return 0;
                }
        
        }
        
        /*Print Result Set in Output*/
        public void TransactionStatement(){
        
                try{
                        Scanner sc = new Scanner(System.in);
                        System.out.print("Enter the Account number for Transaction statement : ");
                        int Account_ID = sc.nextInt();
                        System.out.print("Enter Date as DD-MM-YYYY to see Transaction After it : ");
                        sc.nextLine();
                        String Start_Date = sc.nextLine();
                        
                        System.out.print("Enter Date as DD-MM-YYYY to see Transaction Before it : ");
                        String Last_Date  = sc.nextLine();
                        
                        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                        java.util.Date parsed1 = format.parse(Start_Date);
                        java.util.Date parsed2 = format.parse(Last_Date);
                        java.sql.Timestamp Start = new java.sql.Timestamp(parsed1.getTime());
                        java.sql.Timestamp Last = new java.sql.Timestamp(parsed2.getTime());
                        
                        String sql = "select Account_ID,First_Name,Last_Name,Transaction_Type,Category,Balance_Remaining,Amount,Transaction_Time,PAN_ID from (Bank_Account join Account_Holder using(Person_ID)) join Account_Transaction using(Account_ID) where Account_ID = '"+Account_ID+"' and Transaction_Time >= '"+Start+"' and Transaction_Time <= '"+Last+"'";
                        
                        ResultSet rs = Query.executeQuery(sql);
                        int index = 1;
                        
                        while(rs.next()){
                        
                                if(index++ == 1){
                        
                                        System.out.println("\nAccount Number = " + Account_ID);
                                        System.out.println("Name of Account Holder = "+rs.getString(2)+" "+rs.getString(3));
                                        System.out.println("Pan number of Person = "+rs.getString(9));
                                        System.out.println("Following is the Statement : ");
                                        System.out.printf("%-20s %-20s %-20s %-20s %-50s\n","Transaction_Type","Category","Amount","Balance_Remaining","Transaction_Time"); 
                                }
                                
                                System.out.printf("%-20s %-20s %-20.2f %-20.2f %-50s\n",rs.getString(4),rs.getString(5),rs.getDouble(7),rs.getDouble(6),rs.getString(8));
                                
                                
                        
                        }
        
                }catch(Exception e){
                
                        System.out.println(e.getMessage());
                }
        
        
        }
        
        /*Get Spendings of Person by Category and Month*/
        public void CategorySpendings(){
        
                try{
                
                        Scanner sc = new Scanner(System.in);
                        System.out.print("Enter the Account number for Transaction statement : ");
                        int Account_ID = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter the complete Month Name : ");
                        String Month = sc.nextLine();
                        
                        String sql1 = "Select First_Name,Last_Name,PAN_ID from Bank_Account join Account_Holder using(Person_ID) where Account_ID = '"+Integer.toString(Account_ID)+"'";
                        
                        ResultSet s = Query.executeQuery(sql1);
                        if(s.next()){
                                
                                System.out.println("\nAccount Number = " + Account_ID);
                                System.out.println("Name of Account Holder = "+s.getString(1)+" "+s.getString(2));
                                System.out.println("Pan number of Person = "+s.getString(3));        
                        
                                System.out.println("Here is Total Category Wise Spendings in Given Month : ");
                                
                        }
                        
                        String sql2 = "select Category,sum(Amount) from Bank_Account join Account_Transaction using(Account_ID) where Account_ID = '"+Integer.toString(Account_ID)+"' and Transaction_Type = 'Debit' and monthname(Transaction_Time) like '%"+Month+"%' group by Category";
                        

                        ResultSet rs = Query.executeQuery(sql2);
                        int index = -1;
                        while(rs.next()){
                        
                                if(index++ == -1)
                                        System.out.printf("\n%-20s %15s\n","Category","Spendings");
                                        
                                System.out.println("---------------------------------------");
                        
                                System.out.printf("%-20s %15.4f\n",rs.getString(1),rs.getDouble(2));
                        
                        }
                        
                        if(index == -1)
                                System.out.println("No Transactions taken in this Month");
                
                }catch(Exception e){
                
                        System.out.println(e.getMessage());
                
                }
        
        }
        
        public void CityCategorySpendings(){
        
        
                try{
                
                        Scanner sc = new Scanner(System.in);
                        System.out.print("Enter the City Name You have to check Overall Spendings : ");
                        String City = sc.nextLine();
                        System.out.print("Enter the Month You have to check the Spendings : ");
                        String Month = sc.nextLine();
                        
                        String sql = "select Category,sum(Amount) from (Account_Holder join Contact_Info using(Contact_ID)) join (Bank_Account join Account_Transaction using(Account_ID)) using(Person_ID) where Transaction_Type = 'Debit' and monthname(Transaction_Time) like '%"+Month+"%' and City like '%"+City+"%' group by Category";

                        System.out.println("In Month "+Month+" in City "+City+" Overall Spendings are : ");
                        ResultSet rs = Query.executeQuery(sql);
                        int index = -1;
                        while(rs.next()){
                        
                                if(index++ == -1)
                                        System.out.printf("%-20s %15s\n","Category","Spendings");
                                
                                System.out.printf("%-20s %15.4f\n",rs.getString(1),rs.getDouble(2));
                        
                        }
                        
                        if(index == -1)
                                System.out.println("No Such City Exists Or Transactions are zero in given Month");                
                
                }catch(SQLException e){
                
                        System.out.println(e.getMessage());
                        
                }
        
        }
        
        public void Insert(){
        
                try{
                        /*Insert into Contact Table 5 cities and each city with 10 different address*/
                
                        String Query1 = "select count(distinct(City)) from Contact_Info";
                        
                        String Query2 = "select count(Contact_ID) from Contact_Info";
                        /*Get Number of Cities already Present*/
                        ResultSet rs = Query.executeQuery(Query1);
                        
                        
                        int count = -1;
                        
                        if(rs.next())
                                 count = rs.getInt(1);
                        
                        rs = Query.executeQuery(Query2);
                        
                        int Contact_id = 1;
                        if(rs.next())
                                Contact_id += rs.getInt(1);
                           
                        int Person_id = Contact_id;     
                        Random rand = new Random();        
                        
                        /*Creation of Random Address,Emails,Phone ,Mobile etc as dummy Data*/
                        //String State = "State" + Integer.toString(count/5);
                        int check = 0;
                        
                        for(int i = 0;i < 5;i++){
                        
                                //int City_Index = rand.nextInt();
                                String City_1 = City[i];
                                String State = States[i];
                                 long PostalCode = (long)(rand.nextDouble()*100000L);
                                
                                for(int j = 1;j <= 10; j++){
                                
                                        int Plot_No = rand.nextInt(99);
                                        int Address_No = rand.nextInt(Address.length-2);
                                        String StreetAddress = "Plot No. "+Integer.toString(Plot_No)+" ,"+Address[Address_No];
                                        
                                       
                                        long Mobile = (long)(rand.nextDouble()*10000000000L);                       
                                        
                                        /*Sql Query for Updates In Table*/
                                        String sql = "Insert Into Contact_Info(Contact_ID,Street_Address,City,State,Country,Postal_Code,Mobile) Values('"+Contact_id+"','"+StreetAddress+"','"+City_1+"','"+State+"','India','"+PostalCode+"','"+Mobile+"')";
                                        
                                        int x = Query.executeUpdate(sql);
                                        
                                        if(x <= 0)
                                                check = 1;
                                        
                                          
                                        Contact_id++;
                                        
                                }
                        
                        }
                        
                        if(check == 0)
                                System.out.println("Contacts Inserted Successfully");
                
                        //int Person_id = count+1;
                        /*Creating Random Dates from this milliseconds Option*/
                        long minDate = 152318233191L;
                        long maxDate = 982318233191L;
                        long minOpen     = 1026546232642L;
                        long maxOpen = 1296546232642L;;
                        
                        for(int i = 1;i <= 50;i++){
                        
                        
                                /*Created Random names,Balance and Opening date as now*/
                                int First_Index = rand.nextInt(FirstNames.length-2);
                                int Last_Index  = rand.nextInt(LastNames.length-2);
                                int Email_Index  = rand.nextInt(Emails.length-2);
                                String first_name = FirstNames[First_Index].trim();
                                String last_name = LastNames[Last_Index].trim();
                                String Email = first_name+"."+last_name+"@"+Emails[Email_Index].trim();
                                String Pan = (RandomStringUtils.random(10,true,true)).toUpperCase();
                                long dob = (long)(rand.nextDouble()*(maxDate-minDate+1) + minDate);
                                long DateOpen = (long)(rand.nextDouble()*(maxOpen-minOpen+1)+minOpen);
                                
                                
                                java.sql.Date obj = new java.sql.Date(dob);
                                java.util.Date now = new java.util.Date();
                                java.sql.Timestamp GetTime = new java.sql.Timestamp(DateOpen);
                                int balance = rand.nextInt(90000)+1000;
                                
                                
                                /*Sql Query for Account Holders insert rando name,dob,Pan number*/
                                String sql = "Insert Into Account_Holder(Person_ID,PAN_ID,First_Name,Last_Name,DOB,Contact_ID) values('"+Person_id+"','"+Pan+"','"+first_name+"','"+last_name+"','"+obj+"','"+Person_id+"')";
                                
                                String sql3 = "Update Contact_Info Set Email = '"+Email+"' Where Contact_ID = '"+Person_id+"'";
                                
                                /*Sql Query for Bank Account insert random balance*/
                                String sql2 = "Insert Into Bank_Account(Account_ID,Date_Open,Status,Person_ID,Account_Type,Current_Balance,LastTransaction) values('"+Person_id+"','"+GetTime+"','Active','"+Person_id+"','Savings','"+balance+"','"+GetTime+"')";
                                
                                int x = Query.executeUpdate(sql);
                                int x1  =Query.executeUpdate(sql2);
                                int x2 = Query.executeUpdate(sql3);        
                                        if(x <= 0)
                                                check = 1;
                                                //System.out.println("Not Cooreesct");
                                        if(x1 <= 0)
                                                check = 1;
                                        if(x2 <= 0)
                                                check = 1;
                                        
                                Person_id++;
                        
                        }
                        
                        if(check == 0)
                                System.out.println("Account Holders Inserted Successfully and Bank Accounts also");
                        
                        System.out.println("Wait till Transactions Dummy Data is Inserted ..........");
                        /*Create Dummy Transaction for each Account*/
                        for(int i = 1; i < Contact_id ; i++){
                        
                                /*Get Random Recievers Account No.*/
                                
                                
                                java.util.Date now = new java.util.Date();
                                
                                long maxTransac = now.getTime();
                                long minTransac = 1326546232642L;
                                long day  = 24*60*60*1000;
                                
                                long start_Day = (long)(rand.nextDouble()*(maxTransac-minTransac+1) + minTransac);
                                
                                
                                int day_n = 10;
                                int index_Count = 1;
                                
                                while(((start_Day+index_Count*day) < maxTransac) && (index_Count<day_n)){
                                        int Account_No = rand.nextInt(count+49) + 1;
                                        
                                        if(Account_No == i)
                                                Account_No += 1;
                                                
                                        int category = rand.nextInt(6) + 1;
                                        double frac = (1.0f)*rand.nextDouble();
                                        
                                        double currentBalanceSender = getBalance(i);
                                        double currentBalanceRecev = getBalance(Account_No);
                                        
                                        double Amt = frac*currentBalanceSender; 
                                        java.sql.Timestamp GetTime = new java.sql.Timestamp(start_Day+index_Count*day);
                                        if(currentBalanceSender > 0){
                                        
                                                //System.out.println("Yes");
                                                double FinalBalanceSender = currentBalanceSender - Amt;
                                                double FinalBalanceRecev  = currentBalanceRecev + Amt;
                                                
                                                /*Insert into Account Transaction Amount of transfer and Category and Accoutn ID*/
                                                String sql1 = "Insert Into Account_Transaction(Transaction_Type,Transaction_Time,Amount,Balance_Remaining,Account_ID,Category) values ('Debit','"+GetTime+"','"+Amt+"','"+FinalBalanceSender+"','"+i+"','"+category+"')";
                                                /*Insert Same Aount as Credit for other Account*/
                                                String sql2 = "Insert Into Account_Transaction(Transaction_Type,Transaction_Time,Amount,Balance_Remaining,Account_ID,Category) values ('Credit','"+GetTime+"','"+Amt+"','"+FinalBalanceRecev+"','"+Account_No+"','"+category+"')";
                                                 
                                                
                                                
                                                /*Update the Last Transaction Dates of Account and thier Balanace*/
                                                String sql3 = "Update Bank_Account Set Current_Balance = '"+FinalBalanceSender+"' Where Account_ID = '"+i+"'";
                                                String sql4 = "Update Bank_Account Set Current_Balance = '"+FinalBalanceRecev+"' Where Account_ID = '"+Account_No+"'";  
                                                 String sql5 = "Update Bank_Account Set LastTransaction = '"+GetTime+"' where Account_ID = '"+i+"' and LastTransaction < '"+GetTime+"'";
                                                 String sql6 = "Update Bank_Account Set LastTransaction = '"+GetTime+"' where Account_ID = '"+Account_No+"' and LastTransaction < '"+GetTime+"'";
                                                 /*Execute Queries for Transaction for Updates of Accounts and Transactions*/
                                                 int x = Query.executeUpdate(sql1);
                                                 x  = Query.executeUpdate(sql2);
                                                 x  = Query.executeUpdate(sql3);
                                                 x = Query.executeUpdate(sql4);
                                                 x = Query.executeUpdate(sql5);
                                                 x = Query.executeUpdate(sql6);
                                                 if(x <= 0)
                                                        check = 1;
                                                        //System.out.println("Not correct");
                                        }
                                        
                                        index_Count++;
                                        
                                }
                                System.out.println("Inserted Transactions for Account No : "+i);
                        }
                        
                        if(check == 0)
                                System.out.println("Transactions are Updated");
                        //System.out.println(count);
                        //long maxLastUpdate = 1429546232642L;
                        //java.util.Date now = new java.util.Date();
                        Calendar now = Calendar.getInstance();
                        now.add(Calendar.YEAR, -2);
                        long maxLastUpdate = now.getTimeInMillis();
                        java.sql.Timestamp Account  = new java.sql.Timestamp(maxLastUpdate);
                        
                        /*Close the Account whose LastTransaction is 3 years back*/
                        for(int i = 1; i < Contact_id;i++){
                        
                                String sql = "Update Bank_Account Set `Status` = 'Dormant' , Date_Close = now() Where LastTransaction < '"+Account+"'";
                                 
                                int x = Query.executeUpdate(sql);
                                if(x <= 0)
                                        System.out.println("Not Correct");
                        
                        }
                         
                        
                }catch(SQLException e){
                
                        System.out.println(e.getMessage());
                
                }
                
        
        }

}
