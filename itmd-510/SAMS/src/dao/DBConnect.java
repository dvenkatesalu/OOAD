/*
 * DBConnect.java
 * @author Dharanip Priya
 * @author Harika Kuladevi
 * @since Nov 30,2018
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect  
{

	/*public Connection getConnection() 
	{
		return connection;
	}*/

	private static String url = "jdbc:mysql://localhost:3306/sams";
	private static String username = "root";
	private static String password = "Dipp@1106";
	
	public static Connection connection;
	
	//private static String url = "jdbc:mysql://www.papademas.net:3307/510labs?autoReconnect=true&useSSL=false";

	public DBConnect() 
	{
		try 
		{
			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Opening connection");
		} 
		
		catch (SQLException e) 
		{
			System.out.println("Error creating connection to database: " + e);
			System.exit(-1);
		}
	}
	
	public void finalize()
	{
		try 
		{
			if( !connection.isClosed() )
			{
				System.out.println("Closing connection");
				connection.close();
			}
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
}
