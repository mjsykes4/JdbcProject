package ssa;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class PropertiesDemo {
	static Connection myConn=null;
	static Statement stmt= null;
	static ResultSet rs = null;
	
	public static void main(String[] args) throws SQLException{
		
		
		try{
			//1. Load the properties file
			Properties props = new Properties();
			InputStream input = null;
			
			
			input=(new FileInputStream("demo.properties"));
			props.load(input);
			//props.load(new FileInputStream("d:/
			
			//2. Read the props
			String theUser = props.getProperty("user");
			String thePassword = props.getProperty("password");
			String theDburl = props.getProperty("url");
			
			// Get Connection
			myConn= DriverManager.getConnection(theDburl, theUser, thePassword);
			//create statement
			stmt=myConn.createStatement();
			rs=stmt.executeQuery("select * from student where id=200;");
			
			 System.out.println("id" + "\t" + "First Name" + "\t" + "Last Name" + "\t" + "GPA" + "\t" + "SAT" + "\t" + "major_id");
			 System.out.println("===" + "\t" + "==========" + "\t" + "===========" + "\t" + "===" + "\t" + "====" + "\t" + "========");
			while (rs.next())
				System.out.println( String.format("%-3s", rs.getString("id")) + "\t" + String.format("%-8s", rs.getString("first_name")) +"\t"+ String.format("%-9s", rs.getString("last_name")) 
				+"\t"+ String.format("%-3s", rs.getString("GPA")) +"\t"+ String.format("%-5s", rs.getString("SAT")) +"\t"+  String.format("%-8s", rs.getString("major_id")));
			
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(myConn!=null)
				myConn.close();
			if(stmt!= null)
				stmt.close();
		}
	}
}

