package ssa;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class SamplePreparedStmt {
	
	public static Connection myConn=null;
	public static PreparedStatement myStmt=null;
	public static ResultSet myRs=null;
	
	
	public static void main(String[] args) throws SQLException {

		try{
			
			Properties prop= new Properties();
			prop.load(new FileInputStream("demo.properties"));
			String theUser = prop.getProperty("user");
			String thePassword = prop.getProperty("password");
			String theDburl = prop.getProperty("url");
			myConn= DriverManager.getConnection(theDburl, theUser, thePassword);
			myStmt= myConn.prepareStatement("select * from student where gpa > ? && sat > ?");
			
			//Set Parameter values
			myStmt.setDouble(1,2.0);
			myStmt.setInt(2,1000);
			
			//Execute the Query
			myRs= myStmt.executeQuery();
			
			//Process the First Result Set
			System.out.println("++++++++++++++++++++++++++++++");
			System.out.println("First Result Set");
			System.out.println("++++++++++++++++++++++++++++++");
			display();
			
			//statement reuse
			//set Parameter Values
			System.out.println("++++++++++++++++++++++++++++++");
			System.out.println("Second Result Set");
			System.out.println("++++++++++++++++++++++++++++++");
			myStmt.setDouble(1,3.0);
			myStmt.setInt(2,900);
			myRs= myStmt.executeQuery();
			display();
			
			
			while (myRs.next()){
				String fName= myRs.getString("first_name");
				String lName= myRs.getString("last_name");
				double gpa= myRs.getDouble("gpa");
				int sat= myRs.getInt("sat");
				
				System.out.printf("\n%-8s %-9s %3.1f %-2d", fName, lName, gpa, sat);
				}
			
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			if(myConn!=null)
				myConn.close();
			if(myStmt!= null)
				myStmt.close();
		}

	}
	public static void display() throws SQLException{
		while (myRs.next()){
			String fName= myRs.getString("first_name");
			String lName= myRs.getString("last_name");
			double gpa= myRs.getDouble("gpa");
			int sat= myRs.getInt("sat");
			
			System.out.printf("\n%-8s %-9s %3.1f %-2d", fName, lName, gpa, sat);
			System.out.println();
		}
	}
}
