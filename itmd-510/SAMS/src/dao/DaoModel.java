package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import application.Main;
import models.Course;
import models.Instructor;
import models.NewCourseDetails;
import models.Person;

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
		String query1 = "select courseDetails.cName, courseDetails.instructorid, courseDetails.taid, studentCourseMap.ccode, "
				+ " studentCourseMap.grade from courseDetails, studentCourseMap WHERE studentCourseMap.studentid = '"
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
				c.grade = rs.getString(5);
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
				//String query3 = "SELECT fname, lname, email FROM sams_student WHERE cwid = '" + c.ta + "' AND isTA = '1'";
				String query3 = "SELECT fname, lname, email FROM student WHERE cwid = '" + c.ta + "' AND isTA = '1'";
				System.out.println("fetch ta details query : " + query3);
				ResultSet rs3 = stmt.executeQuery(query3);

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
	
	public ArrayList<String> getStudentListForACourse( Instructor i )
	{
		ArrayList<String> cwids = new ArrayList<>();
		String ccode = "";
		try
		{
			Statement stmt = DBConnect.connection.createStatement();
			String query = "SELECT ccode FROM coursedetails WHERE instructorid = '" + Main.personObject.cwid + "';";
			ResultSet rs = stmt.executeQuery(query);
			while( rs.next() )
			{
				System.out.println("Inside CCode query : " );
				ccode = rs.getString(1);
			}
		}
		catch( Exception e )
		{
			System.out.println("Error while executing query : " + e );
		}
			
		i.ccode = ccode;
			
		try
		{
			Statement stmt = DBConnect.connection.createStatement();
			String query2 = "SELECT studentid FROM studentcoursemap WHERE ccode='"+ ccode + "';";;
			System.out.println("fetch student cwid details query : " + query2);
			ResultSet rs3 = stmt.executeQuery(query2);

			while( rs3.next() )
			{
				System.out.println("Inside Query 2");
				cwids.add(rs3.getString(1));
			}
		}
		catch( Exception e )
		{
			System.out.println("Error while executing query : " + e );
		}
		return cwids;
	}
	
	public Boolean generatePasscodeForAttendance( String pcode, Instructor i )
	{
		System.out.println("Inside gen passcode : ");
		Calendar c = Calendar.getInstance();
		String date = ((Integer)c.get(Calendar.MONTH)).toString() 
				+ ((Integer)c.get(Calendar.DAY_OF_MONTH)).toString() 
				+ ((Integer)c.get(Calendar.YEAR)).toString();
		System.out.println(date);
		String sessionCode = pcode + date;
		String query = "INSERT INTO passcode(ccode, passcode, sessioncode) VALUES('" 
				+ i.ccode + "','"+ pcode + "','" + sessionCode +"');";
		System.out.println("generate passcode query : " + query);
		try
		{
			Statement stmt = DBConnect.connection.createStatement();
			int rs = stmt.executeUpdate(query);
			if( rs == 1 )
				return true;
		}
		catch( Exception e )
		{
			System.out.println("Error while executing query : " + e );
		}
		return false;
	}
	
	public String enterStudentAttendance( String pcode,  String ccode, String cwid )
	{
		System.out.println("Inside enter passcode : ");
		Calendar c = Calendar.getInstance();
		String date = ((Integer)c.get(Calendar.MONTH)).toString() 
				+ ((Integer)c.get(Calendar.DAY_OF_MONTH)).toString() 
				+ ((Integer)c.get(Calendar.YEAR)).toString();
		String sessionCode = pcode + date;
		
		String query1 = "SELECT passcode FROM passcode WHERE sessioncode = '" + sessionCode + "' AND ccode = '" + ccode + "';";
		try
		{
			Statement stmt = DBConnect.connection.createStatement();
			ResultSet rs = stmt.executeQuery(query1);
			if( !rs.next() )
				return ("Invalid Passcode!");
		}
		catch( Exception e )
		{
			System.out.println("Error while executing query : " + e );
		}
		
		String query = "INSERT INTO attendance(cwid, passcode, sessioncode, ccode, present) VALUES('" 
				+ cwid + "','"+ pcode + "','" + sessionCode +"','" + ccode + "','1');";
		System.out.println("Enter passcode query : " + query);
		try
		{
			Statement stmt = DBConnect.connection.createStatement();
			int rs = stmt.executeUpdate(query);
			if( rs == 1 )
				return "Attendace Marked!";
		}
		catch( Exception e )
		{
			System.out.println("Error while executing query : " + e );
		}
		return "Attendance entry failed. Please try again!";
	}
	
	public Boolean enterGrades( char grade, String cwid, String ccode )
	{
		String query = "UPDATE studentcoursemap SET grade = '" + grade 
				+ "' WHERE studentid = '" + cwid + "' AND ccode = '" 
				+ ccode + "';";

		System.out.println("ENter grades query : " + query);
		try
		{
			Statement stmt = DBConnect.connection.createStatement();
			int rs = stmt.executeUpdate(query);
			if( rs == 1 )
				return true;
		}
		catch( Exception e )
		{
			System.out.println("Error while executing query : " + e );
		}
		return false;
	}
	
	public Boolean enterCourseDetails( NewCourseDetails c )
	{
		String query = "INSERT INTO coursedetails(ccode, cname, dept, schedule) VALUES (?,?,?,?);";
		
		try
		{
			PreparedStatement insertRecords = null;
			insertRecords = DBConnect.connection.prepareStatement(query);
			insertRecords.setString(1, c.ccode);
			insertRecords.setString(2, c.cName);
			insertRecords.setString(3, c.dept);
			insertRecords.setString(4, c.schedule);
			System.out.println("Insert course Details query : " + insertRecords.toString());
			int count = insertRecords.executeUpdate();
		       
			 if( count == 1 )
			 {
				 System.out.println("Record Inserted successfully!");
			     return true;
			 }
			 
			 return false;
		}
		catch( Exception e )
		{
			System.out.println("Error while executing query : " + e );
		}
		return false;
	}
	
	public boolean enterStudentDetailsByAdmin( Person p )
	{
		/*String query = "INSERT INTO sams_student( fname, lname, cwid, email, isTA, level, currTerm) VALUES('" 
				+ p.fName + "','" + p.lName + "','" + p.cwid + "','" + p.email + "'," + p.isTa + ",'" + p.level + 
				"','" + p.currTerm + "');";*/
		
		String query = "INSERT INTO student( fname, lname, cwid, email, isTA, level, currTerm) VALUES('" 
				+ p.fName + "','" + p.lName + "','" + p.cwid + "','" + p.email + "'," + p.isTa + ",'" + p.level + 
				"','" + p.currTerm + "');";
		System.out.println("ENter student details query : " + query);
		try
		{
			Statement stmt = DBConnect.connection.createStatement();
			int rs = stmt.executeUpdate(query);
			if( rs != 1 )
				return false;
		}
		catch( Exception e )
		{
			System.out.println("Error while executing query : " + e );
			return false;
		}
		
		System.out.println("Courses size : " + p.courses.size());
		
		for( int i = 0; i < p.courses.size(); i++ )
		{
			query = "INSERT INTO studentcoursemap(ccode, studentid) VALUES('" + p.courses.get(i).ccode + "','" +
					p.cwid + "');" ;
			System.out.println("ENter studentcourse details query : " + query);
			try
			{
				Statement stmt = DBConnect.connection.createStatement();
				int rs = stmt.executeUpdate(query);
				if( rs != 1 )
					return false;
			}
			catch( Exception e )
			{
				System.out.println("Error while executing query : " + e );
				return false;
			}
			if( p.isTa )
			{
					enterTADetails(p.courses.get(i).ccode, p.cwid);
			}
		}
		return true;
		
	}
	
	public void enterTADetails( String ccode, String cwid )
	{
		String query = "INSERT INTO coursedetails( TAid ) VALUES('" + cwid + "') WHERE ccode = '" + ccode + "';";
		System.out.println("ENter ta details query : " + query);
		try
		{
			Statement stmt = DBConnect.connection.createStatement();
			int rs = stmt.executeUpdate(query);
			if( rs == 1 )
				return;
		}
		catch( Exception e )
		{
			System.out.println("Error while executing query : " + e );
			return;
		}
	}
	
	public Boolean enterInstructorDetailsByAdmin( Instructor i )
	{
		String query = "INSERT INTO instructor( cwid, courseid, email, fname, lname ) VALUES('" + i.cwid
				+ "','" + i.ccode + "','" + i.email + "','" + i.fname + "','" + i.lname +"');";
		System.out.println("Enter Instructor Details : " + query);
		try
		{
			Statement stmt = DBConnect.connection.createStatement();
			int rs = stmt.executeUpdate(query);
			if( rs != 1 )
				return false;
		}
		catch( Exception e )
		{
			System.out.println("Error while executing query : " + e );
			return false;
		}
		
		query = "UPDATE coursedetails SET instructorid ='" + i.cwid +"' WHERE ccode = '" + i.ccode + "';";
		System.out.println("Enter Instructor Details : " + query);
		try
		{
			Statement stmt = DBConnect.connection.createStatement();
			int rs = stmt.executeUpdate(query);
			if( rs != 1 )
				return false;
		}
		catch( Exception e )
		{
			System.out.println("Error while executing query : " + e );
			return false;
		}
		return true;
	}
	
	public ArrayList<String> getCourseListPerDept( String dept )
	{
		String query = "SELECT ccode FROM coursedetails WHERE dept = '" + dept + "';";
		ArrayList<String> ccodes = new ArrayList<>();
		try
		{
			Statement stmt = DBConnect.connection.createStatement();
			System.out.println("fetch coursecode query : " + query);
			ResultSet rs3 = stmt.executeQuery(query);

			while( rs3.next() )
			{
				System.out.println("Inside Query ");
				ccodes.add(rs3.getString(1));
			}
		}
		catch( Exception e )
		{
			System.out.println("Error while executing query : " + e );
		}
		return ccodes;
	}
	
	public NewCourseDetails getCourseDetails( String ccode )
	{
		NewCourseDetails c = new NewCourseDetails();
		try
		{
			Statement stmt = DBConnect.connection.createStatement();

			String query = "SELECT ccode, cname, dept, schedule FROM coursedetails WHERE ccode = '" + ccode + "';";
			System.out.println("fetch course details query : " + query);
			ResultSet rs = stmt.executeQuery(query);

			if( rs.next() )
			{
				c.ccode = rs.getString(1);
				c.cName = rs.getString(2);
				c.dept = rs.getString(3);
				c.schedule = rs.getString(4);
			}

		}
		catch( Exception e )
		{
			System.out.println("Error while executing query : " + e );
		}
		return c;
	}
}
