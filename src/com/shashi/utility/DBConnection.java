package com.shashi.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ResourceBundle;
import java.sql.SQLException;

public class DBConnection {
	private static Connection con;
	private DBConnection() {};
	static {
		
		ResourceBundle rb = ResourceBundle.getBundle("com.shashi.utility.database");
		
		try {
			Class.forName(rb.getString("driverName"));
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		try {
			con = DriverManager.getConnection(rb.getString("connectionString"),rb.getString("username"),rb.getString("password"));
			System.out.println("Connected");
		} catch (SQLException e) {

			e.printStackTrace();
			System.out.println("Failed");
		}
	}
	public static Connection getCon()
	{
		return con;
	}
}
