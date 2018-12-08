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

	private static String url = "jdbc:mysql://www.papademas.net:3307/fp510?autoReconnect=true&useSSL=false";
	private static String username = "fpuser";
	private static String password = "510";
	
	public static Connection connection;

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
