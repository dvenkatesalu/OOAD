package models;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import dao.DBConnect;

/**
 * RegisterModel.java
 * @author Dharanip Priya
 * @author Harika Kuladevi
 * @since Nov 30, 2018
 */
public class RegisterModel extends DBConnect
{

	/**
	 * 
	 */
	public RegisterModel()
	{
		// TODO Auto-generated constructor stub
	}

	public void enterDetails(Person P)
	{
		try
        {
			DBConnect conn = new DBConnect();
			Statement stmt = null;
			
			stmt = conn.getConnection().createStatement();
			PreparedStatement insertRecords = null;
			
			//CHANGE TABLE NAME
			String query = "INSERT INTO dp_personaldetails(cwid, fname, mname, lname, dept, student, "
					+ "instructor, admin, email, password) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			
			insertRecords = conn.getConnection().prepareStatement(query);
	        
	    	insertRecords.setString(1, P.cwid);
	    	insertRecords.setString(2, P.fName);
	    	insertRecords.setString(3, P.mName);
	    	insertRecords.setString(4, P.lName);
	    	insertRecords.setString(5, P.dept);
	    	insertRecords.setBoolean(6, P.isStudent);
	    	insertRecords.setBoolean(7, P.isInstructor);
	    	insertRecords.setBoolean(8, P.isAdmin);
	    	insertRecords.setString(9, P.emailid);
	    	// TODO : hash password before saving
	    	insertRecords.setString(10, P.password); 
	    	
	        insertRecords.executeUpdate();
			
			/*String sql = "INSERT INTO dp_personaldetails(cwid, fname, mname, lname, dept, student, "
					+ "instructor, admin, email, password) VALUES ( '" + P.cwid + "','" + P.fName 
					+ "','" + P.mName + "','" + P.lName + "','" + P.dept + "','" + P.isStudent + "','" 
					+ P.isInstructor + "','" + P.isAdmin + "','" + P.email + "','" + P.password + "');";
			System.out.println("Query : " + sql );
			
			 ResultSet rs = stmt.executeQuery(sql);
			 
			 System.out.println("Inseretd records into the database.");*/
	
			 conn.getConnection().close();
	       
	        System.out.println("Record Inserted successfully!");
        }
        
        catch (SQLException e) 
        {
        	e.printStackTrace();   
        }
	}
}
