package dao;

import java.sql.ResultSet;
import java.sql.Statement;

import application.Main;
import models.Course;

/**
 * @author Dharanip Priya
 * @author Harika Kuladevi
 * @since Dec 06, 2018
 */
public class DaoModel {

	/**
	 * 
	 */
	public DaoModel() {
		// TODO Auto-generated constructor stub
	}
	
	public void fetchStudentDetails()
	{	
		String query1 = "select courseDetails.cName, courseDetails.instructorid, courseDetails.taid, studentCourseMap.ccode "
				+ "from courseDetails, studentCourseMap WHERE studentCourseMap.studentid = '"
				+ Main.personObject.cwid + "' AND courseDetails.ccode = studentCourseMap.ccode;";
		
		System.out.println("fetch course details query : " + query1);
		try
		{
			Statement stmt = DBConnect.connection.createStatement();
			ResultSet rs = stmt.executeQuery(query1);
			while( rs.next() )
			{
				System.out.println("Inside query 1");
				//query for grades and add here itself
				Course c = new Course();
				c.cname = rs.getString(1);
				c.instructor = rs.getString(2);
				c.ta = rs.getString(3);
				c.ccode = rs.getString(4);
				getInstructorDetails(c);
				getTADetails(c);
				System.out.println("Instructor Name in query 1: " + c.instructorName );
				
				Main.personObject.courses.add(c);
			}
		}
		
		catch( Exception e )
		{
			System.out.println("Error while executing query : " + e );
		}
	}

	public void getInstructorDetails( Course c )
	{
		try
		{
			Statement stmt = DBConnect.connection.createStatement();
			String query2 = "SELECT fname, lname, email FROM instructor WHERE cwid = '" + c.instructor + "'";
			System.out.println("fetch instructor details query : " + query2);
			ResultSet rs2 = stmt.executeQuery(query2);
			while( rs2.next() )
			{
				c.instructorName = rs2.getString(1) + " " + rs2.getString(2);
				c.iemail = rs2.getString(3);
				System.out.println("Instructor Name : " + c.instructorName );
			}
		}
		catch( Exception e )
		{
			System.out.println("Error while executing query : " + e );
		}
	}
	
	public void getTADetails( Course c )
	{
		try
		{
			Statement stmt = DBConnect.connection.createStatement();
			if( c.ta != "" )
			{
				String query3 = "SELECT fname, lname, email FROM student WHERE cwid = '" + c.ta + "' AND isTA = '1'";
				System.out.println("fetch ta details query : " + query3);
				ResultSet rs3 = stmt.executeQuery(query3);
				//check below code
				while( rs3.next() )
				{
					System.out.println("Inside Query 3");
					c.taName = rs3.getString(1) + " " + rs3.getString(2);
					c.temail = rs3.getString(3);
				}
			}
		}
		catch( Exception e )
		{
			System.out.println("Error while executing query : " + e );
		}
	}
}
