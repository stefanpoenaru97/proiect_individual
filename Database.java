package proiect_pi;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Database {
	private static String url = "jdbc:mysql://localhost:3306/db1";
	private static String uname = "root";
	private static String pass = "";
	
	private static Connection con;
	
	public Database() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Database.con = DriverManager.getConnection(Database.url, Database.uname, Database.pass);
	}
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		Database db = new Database();
		System.out.println(db.incrementGenre("fantasy"));
		System.out.println(db.incrementReadBooks("st3f101"));
	}
	
	private Map<String, String> toMap(ResultSet res) throws SQLException {
		Map<String, String> data = new HashMap<String, String>();
		
		if (!res.isBeforeFirst()) {    
			return data;
		}
		
		ResultSetMetaData rsmd = res.getMetaData();
		while (res.next()) {
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				String key = rsmd.getColumnName(i);
	            String val = res.getString(key);
	            data.put(key, val);
	        }
		}
		
		return data;
	}
	
	private ArrayList<Map<String, String>> toMaps(ResultSet res) throws SQLException {
		ArrayList<Map<String, String>> data = new ArrayList<Map<String, String>>();
		
		if (!res.isBeforeFirst()) {    
			return data;
		}
		
		ResultSetMetaData rsmd = res.getMetaData();
		while (res.next()) {
			Map<String, String> row = new HashMap<String, String>();
			
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				String key = rsmd.getColumnName(i);
	            String val = res.getString(key);
	            row.put(key, val);
	        }
			
			data.add(row);
		}
		
		
		return data;
	}
	
	public Map<String, String> getUserByID(int value) throws SQLException {
		PreparedStatement pstmt = Database.con.prepareStatement("SELECT * FROM users WHERE user_id = ?");
		pstmt.setInt(1, value);
		
		ResultSet res = pstmt.executeQuery();
		
		return toMap(res);
	}
	
	public Map<String, String> getUserByName(String value) throws SQLException {
		PreparedStatement pstmt = Database.con.prepareStatement("SELECT * FROM users WHERE user_name = ?");
		pstmt.setString(1, value);
		
		ResultSet res = pstmt.executeQuery();
		
		return toMap(res);
	}

	public int createUser(String email, String username, String password) throws SQLException {
		PreparedStatement pstmt = Database.con.prepareStatement("INSERT INTO users (user_email, user_name, user_password) VALUES (?, ?, ?)");
		pstmt.setString(1, email);
		pstmt.setString(2, username);
		pstmt.setString(3, password);
		
		int res = pstmt.executeUpdate();
		
		return res;
	}
	
	public int updateProfile(String first_name, String last_name, String gender, int user_id) throws SQLException {
		PreparedStatement pstmt = Database.con.prepareStatement("UPDATE users SET user_firstname = ?, user_lastname = ?, user_gender = ? WHERE user_id = ?");
		pstmt.setString(1, first_name);
		pstmt.setString(2, last_name);
		pstmt.setString(3, gender);
		pstmt.setInt(4, user_id);
		
		int res = pstmt.executeUpdate();
		
		return res;
	}
	
	public int updatePassword(String password, int user_id) throws SQLException {
		PreparedStatement pstmt = Database.con.prepareStatement("UPDATE users SET user_password = ? WHERE user_id = ?");
		pstmt.setString(1, password);
		pstmt.setInt(2, user_id);
		
		int res = pstmt.executeUpdate();
		
		return res;
	}
	
	public ArrayList<Map<String, String>> getBookedBooks(int user_id) throws SQLException {
		PreparedStatement pstmt = Database.con.prepareStatement("SELECT * FROM books WHERE book_rented = 1 AND book_rented_by = ?");
		pstmt.setInt(1, user_id);
		
		ResultSet res = pstmt.executeQuery();
				
		return toMaps(res);
	}
	
	public Map<String, String> createBook(String title, String autor, String genre) throws SQLException {
		PreparedStatement pstmt = Database.con.prepareStatement("INSERT INTO books (book_title, book_author, book_genre) VALUES (?, ?, ?)");
		pstmt.setString(1, title);
		pstmt.setString(2, autor);
		pstmt.setString(3, genre);
		
		int res = pstmt.executeUpdate();
		
		if (res == 1) {
			ResultSet row = pstmt.executeQuery("SELECT * FROM books ORDER BY book_id DESC LIMIT 1;");
			
			return toMap(row);
		}
		
		return null;
	}
	
	public int removeBookById(int id) throws SQLException {
		PreparedStatement pstmt = Database.con.prepareStatement("DELETE FROM books WHERE book_id = ?");
		pstmt.setInt(1, id);
		
		int res = pstmt.executeUpdate();
		
		return res;
	}
	
	public ArrayList<Map<String, String>> getUnacceptedBookings() throws SQLException {
		Statement pstmt = Database.con.createStatement();
		ResultSet res = pstmt.executeQuery("SELECT book_id, book_title, book_genre, (select user_name from users where user_id = b.book_rented_by) as book_rented_by, book_rented_from, book_rented_until FROM books as B WHERE book_rented = 0 AND book_rented_by != 0");
		
		return toMaps(res);
	}
	
	public int acceptBooking(int id) throws SQLException {
		PreparedStatement pstmt = Database.con.prepareStatement("UPDATE books SET book_rented = 1, book_booked_count = book_booked_count + 1 WHERE book_id = ?");
		pstmt.setInt(1, id);
		
		int res = pstmt.executeUpdate();
		
		return res;
	}
	
	public int declineBooking(int id) throws SQLException {
		PreparedStatement pstmt = Database.con.prepareStatement("UPDATE books SET book_rented = 0, book_rented_by = 0, book_rented_from = NULL, book_rented_until = NULL WHERE book_id = ?");
		pstmt.setInt(1, id);
		
		int res = pstmt.executeUpdate();
		
		return res;
	}
	
	public int incrementGenre(String genre) throws SQLException {
		PreparedStatement pstmt = Database.con.prepareStatement("UPDATE genres SET genre_count = genre_count + 1 WHERE genre_title = ?");
		pstmt.setString(1, genre);
		
		int res = pstmt.executeUpdate();
		
		return res;
	}
	
	public int incrementReadBooks(String user_name) throws SQLException {
		PreparedStatement pstmt = Database.con.prepareStatement("UPDATE users SET user_books_read = user_books_read + 1 WHERE user_name = ?");
		pstmt.setString(1, user_name);
		
		int res = pstmt.executeUpdate();
		
		return res;
	}
	
	public ArrayList<Map<String, String>> getAvailableBooks() throws SQLException {
		Statement pstmt = Database.con.createStatement();
		ResultSet res = pstmt.executeQuery("SELECT * FROM books WHERE book_rented = 0 AND book_rented_by = 0");
		
		return toMaps(res);
	}
	
	public ArrayList<Map<String, String>> getAllBooks() throws SQLException {
		Statement pstmt = Database.con.createStatement();
		ResultSet res = pstmt.executeQuery("SELECT * FROM books");
		
		return toMaps(res);
	}
	
	public Map<String, String> getBookByID(int id) throws SQLException {
		PreparedStatement pstmt = Database.con.prepareStatement("SELECT * FROM books WHERE book_id = ?");
		pstmt.setInt(1, id);
		
		ResultSet res = pstmt.executeQuery();
		
		return toMap(res);
	}
	
	public int setBookRentedBY(int by, String from, String until, int id) throws SQLException {
		PreparedStatement pstmt = Database.con.prepareStatement("UPDATE books SET book_rented = 0, book_rented_by = ?, book_rented_from = ?, book_rented_until = ? WHERE book_id = ?");
		pstmt.setInt(1, by);
		pstmt.setString(2, from);
		pstmt.setString(3, until);
		pstmt.setInt(4, id);
		
		int res = pstmt.executeUpdate();
		
		return res;
	}
	
	
	public ArrayList<Map<String, String>> top5Users() throws SQLException {
		Statement pstmt = Database.con.createStatement();
		ResultSet res = pstmt.executeQuery("SELECT user_id, user_name, user_books_read FROM users ORDER by user_books_read DESC LIMIT 5");
		
		return toMaps(res);
	}
	
	public ArrayList<Map<String, String>> top5Books() throws SQLException {
		Statement pstmt = Database.con.createStatement();
		ResultSet res = pstmt.executeQuery("SELECT book_id, book_title, book_author, book_genre, book_booked_count FROM books ORDER by book_booked_count DESC LIMIT 5");
		
		return toMaps(res);
	}
	
	public ArrayList<Map<String, String>> topGenres() throws SQLException {
		Statement pstmt = Database.con.createStatement();
		ResultSet res = pstmt.executeQuery("SELECT * FROM genres ORDER by genre_count DESC");
		
		return toMaps(res);
	}
}
