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
interface Details <T extends Person>
{
	void setInfo();
	void setCourselist();
	T getInfo();
	HashMap<String,String> getCourseList();
	Boolean login( String uname, String pwd );
	void getAttendanceReport(String frequency);
	void checkGrades(String subjectCode,List<String> cwid);
	HashMap <String, String> staffTADetails(Boolean staff, Boolean ta);
	void chatRoom();
	void bookMeeting(String cwid1, String cwid2 );
	void cancelMeeting(String cwid1, String cwid2);
	void modifyMeeting(String cwid1, String cwid2);
	void checkMeetingSchedule(String cwid);
	Boolean logout();
}
