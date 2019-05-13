/*
Author : Siddharth Nahar
Entry No : 2016csb1043
Use : Basic Connections and Extraction of Meta Data from Database
Date : 18/3/18
*/
import java.sql.*;
import java.util.*;
import java.io.*;
import java.util.*;

class Trial{

      /*Max and Min value for long data type*/
      static long Min_Value = Long.MIN_VALUE;
      static long Max_Value = Long.MAX_VALUE;
      
      /*Compare function for Type specifying INT and BLOB*/
      static boolean Compare(String Type,String Actual){
      
             /*
             int index = Type.indexOf(Actual);
             */
             if(Type.equals(Actual))
               return true;
             else
               return false;
      
      
      }
      
      
      
      public static void main(String[] args){

          try{
           
             /*Create an instance of Driver at runtime here 
               So Directory structure must be used with class path*/           
             DriverManager.registerDriver(new com.mysql.jdbc.Driver());
             
             /*Input UserUrl,name and Password*/
             Scanner sc = new Scanner(System.in);
            // System.out.print("Enter the URL of Database Server : ");
             String Server_URL = args[0];
             System.out.print("Enter Username for accessing Server : ");
             String UserName = sc.nextLine();
             System.out.print("Enter Password for Accessing Server : ");
             
             /*Visible Password are always dangerous So input 
              it without Echoing it using Console Read*/
             String dbName = Server_URL.substring(Server_URL.lastIndexOf('/')+1);
             Console con = System.console();
             char arr[] = con.readPassword();
             
             String PassWord = new String(arr);
             
             /*Establishing a connection with Server using inputed values*/
             Connection conn = DriverManager.getConnection(Server_URL,UserName,PassWord);
             
             /*Gets Metadata object from connection*/
             DatabaseMetaData MetaData = conn.getMetaData();
             String name[] = new String[1];
             name[0] = "Table";
             /*Get Resultset of Tables and traverse for each table
               Inialization of Variables needed to run code
               Sum,Max,min and Total for each identifier*/
             ResultSet Tables = MetaData.getTables(null, null, "%", name);
             double SumTablesCount=0;
             long SumColumnsCount=0,SumRowCount=0,SumBlobCount=0,SumIntCount=0,SumFKCount=0,SumIndexCount=0,SumAutoCount=0;
             long MinRows = Max_Value,MaxRows = Min_Value;
             long MinColumns = Max_Value,MaxColumns = Min_Value;
             long MinBlob = Max_Value,MaxBlob = Min_Value;
             long MinInt = Max_Value,MaxInt = Min_Value;
             long MinFK = Max_Value,MaxFK = Min_Value;
             long MinIndex = Max_Value,MaxIndex = Min_Value;
             long MinAuto = Max_Value,MaxAuto = Min_Value;
             
             /*Traversing for each table in Database and get each dta*/
             while (Tables.next()) {
             
                   /*Iniailization of variables and store their value*/
                   long ColumnsCount=0,RowCount=0,BlobCount=0,IntCount=0,FKCount=0,IndexCount=0,AutoCount=0;      
                   SumTablesCount++;
                   /*Get result of query for calculating Rows*/
                   Statement smt = conn.createStatement();
                   
                   String query = "select count(*) as rows from " + Tables.getString(3);
                   //ResultSet result = MetaData.getImportedKeys(null,null,Tables.getString(3));
                   ResultSet result = smt.executeQuery(query);
                   
                   /*Get row count and sum it*/
                   while(result.next()){
                        RowCount = result.getInt("rows");
                        SumRowCount += RowCount;
                   }
                   
                   if(MaxRows <= RowCount)
                      MaxRows = RowCount;
                   
                   if(MinRows >= RowCount)
                      MinRows = RowCount;
                   
                   /*Get meta data for Columns for each data table*/
                   ResultSet Columns = MetaData.getColumns(null,null,Tables.getString(3),null);
                   
                   while(Columns.next()){
                        
                        
                        SumColumnsCount++;
                        ColumnsCount++;
                       
                       /*Get TYPE of Data it is at index 6
                         Get is_AUTOINCREMENT boolean at index 23*/
                        String Type = Columns.getString(6);
                        
                        
                        if(Compare(Type,"BLOB") == true){
                           BlobCount++;
                           SumBlobCount++;
                        }
                        
                        if(Compare(Type,"INT") == true){
                           IntCount++;
                           SumIntCount++;
                        }
                        
                        if(Compare(Columns.getString(23),"YES") == true){
                          AutoCount++;
                          SumAutoCount++;
                        }
                        
                   }
                   
                   /*Calculate max and min of each data value*/
                   if(MaxColumns <= ColumnsCount)
                     MaxColumns = ColumnsCount;
                     
                   if(MaxInt <= IntCount)
                     MaxInt = IntCount;
                   
                   if(MaxBlob <= BlobCount)
                     MaxBlob = BlobCount;
                   
                   if(MaxAuto <= AutoCount)
                     MaxAuto = AutoCount;
                   
                   if(MinColumns >= ColumnsCount)
                     MinColumns = ColumnsCount;
                     
                   if(MinInt >= IntCount)
                     MinInt = IntCount;
                   
                   if(MinBlob >= BlobCount)
                     MinBlob = BlobCount;
                   
                   if(MinAuto >= AutoCount)
                     MinAuto = AutoCount;  
                    
                   /*Get Indices info for Index definition*/ 
                   ResultSet Index = MetaData.getIndexInfo(null,null,Tables.getString(3),false,false);
                   while(Index.next()){
                   
                        IndexCount++;
                        SumIndexCount++;
                   }
                   
                   if(MaxIndex <= IndexCount)
                      MaxIndex = IndexCount;
                   
                   if(MinIndex >= IndexCount)
                      MinIndex = IndexCount;
                      
                   /*Get Foreign key info for each table using Imported keys function*/
                   ResultSet Fk = MetaData.getImportedKeys(null,null,Tables.getString(3));
                   
                   while(Fk.next()){
                   
                        FKCount++;
                        SumFKCount++;
                   }
                   
                   if(MaxFK <= FKCount)
                      MaxFK = FKCount;
                      
                   if(MinFK >= FKCount)
                      MinFK = FKCount;
                   
             }
             
             /*Prints Info for Database as required*/
             System.out.printf("======================================================Information for %s Database================================================\n",dbName);
             System.out.printf("%-80s %-10s %8.1f\n","Total number of Tables",":",SumTablesCount);
             System.out.printf("%-80s %-10s %8d,%8d,%8.1f\n","Min,Max and Average number of Columns per table",":",MinColumns,MaxColumns,(SumColumnsCount/SumTablesCount));
             System.out.printf("%-80s %-10s %8d,%8d,%8.1f\n","Min,Max and Average number of Rows per table",":",MinRows,MaxRows,(SumRowCount/SumTablesCount));
             System.out.printf("%-80s %-10s %8d,%8d,%8.1f\n","Min,Max and Average number of FK's per table",":",MinFK,MaxFK,(SumFKCount/SumTablesCount));
             System.out.printf("%-80s %-10s %8d,%8d,%8.1f\n","Min,Max and Average number of indexes per table",":",MinIndex,MaxIndex,(SumIndexCount/SumTablesCount));
             System.out.printf("%-80s %-10s %8d,%8d,%8.1f\n","Min,Max and Average number of auto incremented columns per table",":",MinAuto,MaxAuto,(SumAutoCount/SumTablesCount));
             System.out.printf("%-80s %-10s %8d,%8d,%8.2f\n","Min,Max and Average number of BLOB columns per table",":",MinBlob,MaxBlob,(SumBlobCount/SumTablesCount));
             System.out.printf("%-80s %-10s %8d,%8d,%8.1f\n","Min,Max and Average number of Integer columns per table",":",MinInt,MaxInt,(SumIntCount/SumTablesCount));

          }
          catch(Exception e){
             System.out.println(e.getMessage());
          
          }
      }

}
