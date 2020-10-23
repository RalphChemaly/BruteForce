package BF;

import java.sql.*;
//Written by Ralph
public class SqlQueries {
	public static int login (String username, String password) {
		try {
			Connection conn = MySqlConnection.connect();
			Statement stmt = conn.createStatement();
			System.out.println(username);
			System.out.println(password);
			String sql = "select id from login_admission where username = '" + username + "' and password = '" + password + "' ";
			ResultSet rset = stmt.executeQuery(sql);
			rset.next();
			return rset.getInt("id");
			}
		catch (Exception e) {
			return -1;
		}
	}
}
