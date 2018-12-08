/**
 * 
 */
package models;

import java.util.HashMap;
import java.util.List;

/**
 * @author Dharanip Priya
 *
 */
public class Instructor extends Person 
{

	public String ccode;
	public String fname, mname, lname;
	public String cwid, dept, email;
	/**
	 * 
	 */
	public Instructor() 
	{
		ccode = "";
	}
	
	public String generatePasscode(Boolean type) 
	{
		return "";
	}

}
