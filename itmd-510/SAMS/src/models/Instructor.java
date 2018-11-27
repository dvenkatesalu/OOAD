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
class Instructor extends Person implements Details<Instructor>
{

	protected String currentTerm;
	protected String departmentName;
	protected HashMap<String,String> taList;
	protected HashMap<String,String> studentList;
	/**
	 * 
	 */
	public Instructor() {
		// TODO Auto-generated constructor stub
	}
	
	public String generatePasscode(Boolean type) 
	{
		return "";
	}
	
	@Override
	public void setInfo() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setCourselist() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Instructor getInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public HashMap<String, String> getCourseList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean login(String uname, String pwd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void getAttendanceReport(String frequency) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkGrades(String subjectCode, List<String> cwid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HashMap<String, String> staffTADetails(Boolean staff, Boolean ta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void bookMeeting(String cwid1, String cwid2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelMeeting(String cwid1, String cwid2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyMeeting(String cwid1, String cwid2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkMeetingSchedule(String cwid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Boolean logout() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void chatRoom() {
		// TODO Auto-generated method stub
		
	}

}
