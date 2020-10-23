package BF;

import java.sql.*;
// Written By Ralph 
public class MySqlConnection {
	public static Connection connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/passwords","root","");
			return conn;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}
}
