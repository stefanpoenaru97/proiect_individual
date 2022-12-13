package proiect_pi;

import java.sql.*;

public class Database {
	private static String url = "jdbc:mysql://localhost:3306/db1";
	private static String uname = "root";
	private static String pass = "@Qwerty101";
	
	private static Connection con;
	
	public Database() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Database.con = DriverManager.getConnection(Database.url, Database.uname, Database.pass);
	}
	
	public static void main(String[] args) throws SQLException {
		Database.con = DriverManager.getConnection(Database.url, Database.uname, Database.pass);
	}
	
	public ResultSet getUser(String username) throws SQLException {
		PreparedStatement pstmt = Database.con.prepareStatement("SELECT * FROM users WHERE username = ?");
		pstmt.setString(1, username);
		
		ResultSet res = pstmt.executeQuery();    
		
		return res;
	}
	
	public int createUser(String email, String username, String password) throws SQLException {
		PreparedStatement pstmt = Database.con.prepareStatement("INSERT INTO users (email, username, password) VALUES (?, ?, ?)");
		pstmt.setString(1, email);
		pstmt.setString(2, username);
		pstmt.setString(3, password);
		
		int res = pstmt.executeUpdate();
		
		return res;
	}
}
