package ssa;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcDemonstration {
	public static final String userName= "root";
	public static final String pass="Realm4";
	static String url="jdbc:mysql://localhost:3306/tiy?autoReconnect=true&useSSL=false";
	static Connection myConn=null;
	static Statement stmt= null;
	static ResultSet rs = null;
	
	public static void main(String[] args) throws SQLException{
		
		insertData();
		printRecord();
		System.out.println();
		update();
		printRecord();
		System.out.println();
		deleteRecord();
		printRecord();
		System.out.println();
		
	}
		public static void close(Connection myConn, Statement stmt, ResultSet rs) throws SQLException{
			
			if(myConn!=null)
				myConn.close();
			if(stmt!= null)
				stmt.close();
			if(rs !=null)
				rs.close();	}	
		
public static void update() throws SQLException{
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
		
	//3. Get connection to database
	myConn=(Connection) DriverManager.getConnection(theDburl,theUser,thePassword);
	
	//4. Create a Statement	
	stmt= myConn.createStatement();
	
	//5.Execute SQL query
	String sql= "update student set gpa= 3.5, sat= 1450, major_id= 1  where first_name='George'";
	int rowAffected= stmt.executeUpdate(sql);
	
	
	//6. Process the result set

	}catch(Exception ex){
		ex.printStackTrace();
	}finally{
	if(myConn != null)
		myConn.close();
	if(stmt != null)
		stmt.close();	
	}
}
		
public static void insertData() throws SQLException{
	try{
		//1. Making connection
		myConn= DriverManager.getConnection(url,userName,pass);
		
		//2. Create Statement
		stmt= myConn.createStatement();
		//3. Execute SQL
		String query="insert into student (id, first_name,last_name, gpa, sat) values (200,'George', 'Washington', 4.0, 1600)";
		int rowAffected= stmt.executeUpdate(query);
		
	}catch(Exception ex){
		ex.printStackTrace();
	}finally{
		if(myConn != null)
			myConn.close();
		if(stmt != null)
			stmt.close();
		}
	}	
public static void deleteRecord() throws SQLException{
	try{
		//1. Making connection
		myConn= DriverManager.getConnection(url,userName,pass);
		
		//2. Create Statement
		stmt= myConn.createStatement();
		//3. Execute SQL
		String query="delete from student where last_name= 'Washington' && sat= 1450";
		int rowAffected= stmt.executeUpdate(query);
				
	}catch(Exception ex){
		ex.printStackTrace();
	}finally{
		if(myConn != null)
			myConn.close();
		if(stmt != null)
			stmt.close();
	}

}
public static void printRecord() throws SQLException{
	
	
	try{
		Properties props = new Properties();
		InputStream input = null;
		
		
		input=(new FileInputStream("demo.properties"));
		props.load(input);
		//props.load(new FileInputStream("d:/
		
		//2. Read the props
		String theUser = props.getProperty("user");
		String thePassword = props.getProperty("password");
		String theDburl = props.getProperty("url");
		int Record = 0;
		// Get Connection
		myConn= DriverManager.getConnection(theDburl, theUser, thePassword);
		//create statement
		stmt=myConn.createStatement();
		rs=stmt.executeQuery("select * from student where id=200");
		
		 System.out.println("id" + "\t" + "First Name" + "\t" + "Last Name" + "\t" + "GPA" + "\t" + "SAT" + "\t" + "major_id");
		 System.out.println("===" + "\t" + "==========" + "\t" + "===========" + "\t" + "===" + "\t" + "====" + "\t" + "========");
		while (rs.next()){
			Record++;
			System.out.print( String.format("%-3s", rs.getString("id")) + "\t" + String.format("%-8s", rs.getString("first_name")) +"\t"+ String.format("%-9s", rs.getString("last_name")) 
			+"\t"+ String.format("%-3s", rs.getString("GPA")) +"\t"+ String.format("%-5s", rs.getString("SAT")) +"\t"+  String.format("%-8s", rs.getString("major_id")));
		}
		if(Record==0)
			System.out.println("No record could be found!");
		//1. Making connection
		myConn= DriverManager.getConnection(url,userName,pass);
		
		//2. Create Statement
		stmt= myConn.createStatement();
		//3. Execute SQL
		String query="select * from student where last_name= 'Washington' and sat= 1450";
		ResultSet rowAffected= stmt.executeQuery(query);
				
	}catch(Exception ex){
		ex.printStackTrace();
	}finally{
		if(myConn != null)
			myConn.close();
		if(stmt != null)
			stmt.close();
	}

}	
}

