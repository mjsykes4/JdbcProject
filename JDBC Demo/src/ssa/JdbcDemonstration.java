package ssa;

import java.sql.*;

public class JdbcDemonstration {
	public static final String userName= "root";
	public static final String pass="Realm4";
	static String url="jdbc:mysql://localhost:3306/tiy?autoReconnect=true&useSSL=false";
	static Connection myConn=null;
	static Statement stmt= null;
	static ResultSet rs = null;
	
	public static void main(String[] args) throws SQLException{
		
		insertData();
		update();
		deleteRecord();
		
		try{
			//1. Get Connection to database
			myConn = DriverManager.getConnection(url, userName, pass);
			
			//2. Create statement
				stmt= myConn.createStatement();
				stmt.executeQuery("select * from student");
			//3. Execute SQL query
				 rs=stmt.getResultSet();
				
			//4. Process the Result
				 System.out.println("id" + "\t" + "First Name" + "\t" + "Last Name" + "\t" + "GPA" + "\t" + "SAT" + "\t" + "major_id");
				while(rs.next()){
					System.out.println(rs.getInt("id") + "\t" + rs.getString("first_name") +"\t\t"+ rs.getString("last_name") 
					+"\t\t"+ rs.getInt("GPA") +"\t"+ rs.getInt("SAT") +"\t"+ rs.getInt("major_id"));
				
				}	
				
		}catch(Exception exc){
			exc.printStackTrace();
			
		}finally{
			if(myConn!=null)
				myConn.close();
			if(stmt!= null)
				stmt.close();
		}
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
	//1. Get connection to database
	myConn=(Connection) DriverManager.getConnection(url,userName,pass);
	
	//2. Create a Statement	
	stmt= myConn.createStatement();
	
	//3.Execute SQL query
	String sql= "update student set gpa= 3.5, sat= 1450, major_id= 1  where id=200";
	int rowAffected= stmt.executeUpdate(sql);
	
	System.out.println("Row Affected" + rowAffected);
	
	//4. Process the result set

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
		String query="insert into student (first_name,last_name, GPA, SAT) values ('George', 'Washington', '4.0', '1600')";
		int rowAffected= stmt.executeUpdate(query);
		System.out.println("Row Affected= "+ rowAffected);
		
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
		String query="delete from student where last_name= 'Washington' and sat= 1450";
		int rowAffected= stmt.executeUpdate(query);
		System.out.println(rowAffected + "Row is affected");
				
	}catch(Exception ex){
		ex.printStackTrace();
	}finally{
		if(myConn != null)
			myConn.close();
		if(stmt != null)
			stmt.close();
	}

}
private static void displayResults() {
	System.out.println(rowAffected + "Row is affected");
	
}
}
