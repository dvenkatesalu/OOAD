/**
 * @author Dharanip Priya
 *
 */

package models;


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
	}
}
