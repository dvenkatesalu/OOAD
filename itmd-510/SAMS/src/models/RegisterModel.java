package models;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.DBConnect;

/**
 * RegisterModel.java
 * @author Dharanip Priya
 * @author Harika Kuladevi
 * @since Nov 30, 2018
 */
public class RegisterModel
{

	/**
	 * 
	 */
	public RegisterModel()
	{
		// TODO Auto-generated constructor stub
	}

	public boolean enterDetails(Person P)
	{
		try
        {
			
			PreparedStatement insertRecords = null;
			
			//CHANGE TABLE NAME
			String query = "INSERT INTO personalDetails(cwid, fname, mname, lname, dept, student, "
					+ "instructor, admin, email, password) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			
			insertRecords = DBConnect.connection.prepareStatement(query);
	        
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
	    	
	    	System.out.println("Insert personDetails query : " + insertRecords.toString());
	    	
	        int count = insertRecords.executeUpdate();
	       
			 if( count == 1 )
			 {
				 System.out.println("Record Inserted successfully!");
				 String query2 = "INSERT INTO login VALUES (?, ?);";
				 insertRecords = DBConnect.connection.prepareStatement(query2);
				 insertRecords.setString(1, P.emailid);
			     insertRecords.setString(2, P.password);
			     System.out.println("Insert login query : " + insertRecords.toString());
			     count = insertRecords.executeUpdate();
			     if( count == 1 )
			    	 return true;
			 }
			 
			 
			 System.out.println("Problem with Record Insertion!");
			 return false;
			 
        }
        
        catch (SQLException e) 
        {
        	e.printStackTrace();   
        }
		
		return false;
	}
}
