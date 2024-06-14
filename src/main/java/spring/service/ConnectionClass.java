package spring.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
	
	public static Connection getConnection()
	{
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/exam", "root", "admin");
		} catch (ClassNotFoundException e) {
			System.out.println("Class file loading : "+ e.getMessage());
		} catch (SQLException e) {
			System.out.println("Getting Connection : "+ e.getMessage());
		}
	
		return con;
		
	}

}
