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
public class LoginModel extends DBConnect
{
	public Boolean chckCredentials(String username, String password, Person P)
	{
           
        	String query = "SELECT * FROM dp_personalDetails WHERE email = ? and password = ?;";
            try(PreparedStatement stmt = connection.prepareStatement(query)) 
            {
               stmt.setString(1, username);
               stmt.setString(2, password);
               ResultSet rs = stmt.executeQuery();
                if(rs.next()) 
                { 
                	if(password.equals(rs.getString("password")) && username.equals(rs.getString("email"))) 
                	{
	                	P.isAdmin = rs.getBoolean("admin");
	                	P.isStudent = rs.getBoolean("student");
	                	P.isInstructor = rs.getBoolean("instructor");
	                	return true;
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
