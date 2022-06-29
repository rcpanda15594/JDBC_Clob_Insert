package com.nt.jdbc;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ClobInsert {
private static final String Student_Query="INSERT INTO STUDENTALL VALUES(?,?,?,?)";
	public static void main(String[] args) {
		Scanner scn=null;
		int sno=0;
		String sname=null,saddr=null,resumePath=null;
		Connection con=null;
		PreparedStatement ps=null;
		File file=null;
		Reader reader=null;
		int result=0;
		try {
			scn=new Scanner(System.in);
			if(scn!=null)
				System.out.println("Enter Student No:");
			    sno=scn.nextInt();
			    System.out.println("Enter Student Name:");
			    sname=scn.next();
			    System.out.println("Enter Student Address:");
			    saddr=scn.next();
			    System.out.println("Enter Resume Path:");
			    resumePath=scn.next();
			    
			    
//register driver
	    Class.forName("oracle.jdbc.driver.OracleDriver");
						    
//Establish the connection
	  con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger"); 

//create preparedstatment
	  if(con!=null)
	  ps=con.prepareStatement(Student_Query);
	
//create reader object hold resume(CLOB value)
	  file=new File("C:\\Users\\HCL\\Desktop\\New folder (3)\\abc.txt");
	  
	  reader= new FileReader(file);
	  
//set values to query param
	  if(ps!=null)
		  ps.setInt(1,sno);
	      ps.setString(2,sname);
	      ps.setString(3,saddr);
	      ps.setCharacterStream(4, reader,(int)file.length());
	      
	      
	      
//execute the query
	      if(ps!=null) 
	    	  result=ps.executeUpdate();
	    	  
	    	//process the result set
			   if(result!=0)
				   System.out.println("Record not inserted..");
			   else
				   System.out.println("Record inserted..");
	      }//try   
			   
			   catch(SQLException se) {
			se.printStackTrace();
			System.out.println("Record insertion failed");
		}
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Record insertion failed");
		}
		finally {
			
			//close jdbc objects
						
						try {
							
							if(ps!=null)
								ps.close();
							
						}catch(SQLException se) {
							se.printStackTrace();
						}//catch
						
						
			            try {
							
							if(con!=null)
								con.close();
							
						}catch(SQLException se) {
							se.printStackTrace();
						}//catch
						
						
			             try {
							
							if(scn!=null)
								scn.close();
							
						}catch(Exception se) {
							se.printStackTrace();
						}//catch
			             
			             

			             try {
							
							if(reader!=null)
								reader.close();
							
						}catch(Exception se) {
							se.printStackTrace();
						}//catch
			             
					}//finally
	
	}//method

}//class
