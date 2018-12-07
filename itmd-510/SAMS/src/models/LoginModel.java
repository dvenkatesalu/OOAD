package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DBConnect;

/**
 * LoginModel.java
 * @author Dharanip Priya
 * @author Harika Kuladevi
 * @since Nov 30, 2018
 */
public class LoginModel
{
	public Boolean chckCredentials(String username, String password, Person P)
	{
			PreparedStatement stmt;
        	String query = "SELECT * FROM login WHERE emailid = ? and password = ?;";
            try
            {
               stmt = DBConnect.connection.prepareStatement(query);
               stmt.setString(1, username);
               stmt.setString(2, password);
               ResultSet rs = stmt.executeQuery();
                if(rs.next()) 
                { 
                	if(password.equals(rs.getString("password")) && username.equals(rs.getString("emailid"))) 
                	{
                		query = "SELECT fname, mname, lname,cwid, admin, student, instructor FROM personalDetails WHERE email = ?;";
                		stmt = DBConnect.connection.prepareStatement(query);
                		stmt.setString(1, username);
                		rs = stmt.executeQuery();
                        if( rs.next() )
                        {
		                	P.isAdmin = rs.getBoolean("admin");
		                	P.isStudent = rs.getBoolean("student");
		                	P.isInstructor = rs.getBoolean("instructor");
		                	P.cwid = rs.getString("cwid");
		                	P.fName = rs.getString("fname");
		                	P.mName = rs.getString("mname");
		                	P.lName = rs.getString("lname");
		                	return true;
                        }
                	}
                }
            }
            catch (SQLException e) 
            {
            	e.printStackTrace();   
            }
			return false;
    }

}
