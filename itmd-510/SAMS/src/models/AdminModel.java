package models;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import dao.DBConnect;

/**
 * AdminModel.java
 * @author Dharanip Priya
 * @author Harika Kuladevi
 * @since Dec 02, 2018
 */

public class AdminModel
{

	/**
	 * 
	 */
	public AdminModel() 
	{
		// TODO Auto-generated constructor stub
	}

	public void enroll() 
	{
		
	}
	
	public void generateInstructorrepo() 
	{
		
	}
	public void generateStudentrepo() 
	{
		
	}
	
	public ArrayList<String> getCWIDs( String dept, String personType)
	{
		ArrayList<String> cwids = new ArrayList<>();
		String query = "SELECT cwid from personalDetails WHERE dept = '" + dept 
				 + "' AND ";
		
		if( personType.equals("Instructor"))
		{
			query += "instructor = 1";
		}
		else
		{
			query += "student = 1";
		}
		
		System.out.println("GwtCWID query : " + query);
		try
		{
			Statement stmt = DBConnect.connection.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while( rs.next() )
			{
				cwids.add(rs.getString(1));
			}
		}
		
		catch( Exception e )
		{
			System.out.println("Error while executing query : " + e );
		}
		return cwids;
	}
	
	/*public ArrayList<String> getCourseIDs( String dept )
	{
		
	}*/
}
