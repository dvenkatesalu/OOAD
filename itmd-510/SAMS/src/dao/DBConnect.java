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

	protected Connection connection;
	public Connection getConnection() 
	{
		return connection;
	}

//	private static String url = "jdbc:mysql://www.papademas.net:3307/fp510";
//	private static String username = "fpuser";
//	private static String password = "510";
	
	private static String url = "jdbc:mysql://www.papademas.net:3307/510labs?autoReconnect=true&useSSL=false";

	public DBConnect() 
	{
		try 
		{
			connection = DriverManager.getConnection(url, "db510", "510");
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
				connection.close();
		} 
		
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}

	
}
