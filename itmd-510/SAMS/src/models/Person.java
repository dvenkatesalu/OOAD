/**
 * @author Dharanip Priya
 *
 */

package models;

import java.util.ArrayList;

public class Person 
{
	public String fName,mName,lName;
	public String cwid;
	public String emailid;
	public String dept;
	public Boolean isStudent;
	public Boolean isAdmin;
	public Boolean isInstructor;
	public String email;
	public String password;
	public ArrayList<Course> courses;
	public String currTerm;
	public String level;
	public Boolean isTa;
	
	public Person()
	{
		fName = "";
		mName = "";
		lName = "";
		cwid = "";
		emailid = "";
		dept = "";
		isStudent = false;
		isAdmin = false;
		isInstructor = false;
		password = "";
		email = "";
		courses = new ArrayList<>();
		currTerm = "";
		level = "";
		isTa = false;
	}
}
