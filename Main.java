package proiect_pi;

import java.time.temporal.ChronoUnit;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.JFormattedTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.FocusAdapter;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.Font;

public class Main extends JFrame {
	
	/**
	 *  DB VARIABLES
	 */
	
	private Map<String, String> account = new HashMap<String, String>();
	private boolean isLogged = false;
	private Database db = new Database();
	private ArrayList<Map<String, String>> all_books = new ArrayList<Map<String, String>>();
	private ArrayList<Map<String, String>> rented_books = new ArrayList<Map<String, String>>();

	/**
	 *  GUI VARIABLES
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabbedPane;
	private JPanel contentPane;
	private JTextField first_name;
	private JTextField last_name;
	private JTextField username;
	private JTextField email;
	private JPasswordField current_pass;
	private JPasswordField new_pass1;
	private JPasswordField new_pass2;
	private JTextField filter_origin;
	private JTextField filter_destination;
	private JTextField adults;
	private JTable table;
	
	/*
	 * Authentificate variables
	 */
	//login
	private JTextField login_username;
	private JPasswordField login_password;
	
	//register
	private JTextField register_username;
	private JTextField register_email;
	private JPasswordField register_password1;
	private JPasswordField register_password2;
	private JTable table_1;
	private JTable table_2;
	private JTextField textField;
	private JTable table_3;
	private JTable table_4;
	private JTable table_5;
	private JTable table_6;
	private JTable table_7;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		UIManager.put( "control", new Color( 128, 128, 128) );
		UIManager.put( "info", new Color(128,128,128) );
		UIManager.put( "nimbusBase", new Color( 18, 30, 49) );
		UIManager.put( "nimbusAlertYellow", new Color( 248, 187, 0) );
		UIManager.put( "nimbusDisabledText", new Color( 0, 0, 0) );
		UIManager.put( "nimbusFocus", new Color(115,164,209) );
		UIManager.put( "nimbusGreen", new Color(176,179,50) );
		UIManager.put( "nimbusInfoBlue", new Color( 66, 139, 221) );
		UIManager.put( "nimbusLightBackground", new Color( 18, 30, 49) );
		UIManager.put( "nimbusOrange", new Color(191,98,4) );
		UIManager.put( "nimbusRed", new Color(169,46,34) );
		UIManager.put( "nimbusSelectedText", new Color( 255, 255, 255) );
		UIManager.put( "nimbusSelectionBackground", new Color( 104, 93, 156) );
		UIManager.put( "text", new Color( 230, 230, 230) );
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
		      if ("Nimbus".equals(info.getName())) {
		          javax.swing.UIManager.setLookAndFeel(info.getClassName());
		          break;
		      }
		    }
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (javax.swing.UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public Main() throws ClassNotFoundException, SQLException {
		all_books = db.getAvailableBooks();
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\stef_\\eclipse-workspace\\proiect_pi\\proiect_pi\\images\\favico.png"));
		setResizable(false);
		setTitle("Book Booking System");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 344);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 11, 514, 287);
		contentPane.add(tabbedPane);
		
		JPanel rented_book = new JPanel();
		tabbedPane.addTab("Rented Books", null, rented_book, null);
		tabbedPane.setEnabledAt(0, false);
		rented_book.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 489, 237);
		rented_book.add(scrollPane);
		
		table_1 = new JTable();
		table_1.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table_1);
		
		JPanel available_books = new JPanel();
		tabbedPane.addTab("Available Books", null, available_books, null);
		tabbedPane.setSelectedIndex(1);
		available_books.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 35, 489, 213);
		available_books.add(scrollPane_1);
		
		table_2 = new JTable();
		table_2.setDefaultEditor(Object.class, null);
		ArrayList<String> header = new ArrayList<String>();
		header.add("ID");
		header.add("Title");
		header.add("Autor");
		header.add("Genre");
		header.add("Action");
		tableAddBooks2(table_2, header, all_books);
		
		scrollPane_1.setViewportView(table_2);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				filterBooks(table_2);
			}
		});
		textField.setBounds(10, 11, 489, 25);
		available_books.add(textField);
		textField.setColumns(10);
		
		/*
		 * Profile panel
		 */
		// change info
		JPanel profile = new JPanel();
		tabbedPane.addTab("Profile", null, profile, null);
		tabbedPane.setEnabledAt(2, false);
		tabbedPane.setEnabledAt(0, false);
		profile.setLayout(null);
		
		JLabel label_firstname = new JLabel("First Name:");
		label_firstname.setLabelFor(first_name);
		label_firstname.setBounds(37, 22, 86, 14);
		profile.add(label_firstname);
		
		first_name = new JTextField();
		first_name.setBounds(37, 40, 86, 25);
		first_name.setColumns(10);
		profile.add(first_name);
		
		JLabel label_lastname = new JLabel("Last Name:");
		label_lastname.setLabelFor(last_name);
		label_lastname.setBounds(149, 22, 86, 14);
		profile.add(label_lastname);
		
		last_name = new JTextField();
		last_name.setBounds(149, 40, 86, 25);
		last_name.setColumns(10);
		profile.add(last_name);
		
		JLabel label_username = new JLabel("Username:");
		label_username.setLabelFor(label_username);
		label_username.setBounds(37, 71, 198, 14);
		profile.add(label_username);
		
		username = new JTextField();
		username.setBounds(37, 88, 198, 25);
		username.setEditable(false);
		username.setColumns(10);
		profile.add(username);
		
		JLabel label_email = new JLabel("Email:");
		label_email.setBounds(37, 119, 198, 14);
		profile.add(label_email);
		
		email = new JTextField();
		email.setEditable(false);
		email.setColumns(10);
		email.setBounds(37, 136, 198, 25);
		profile.add(email);
		
		JLabel lblGender = new JLabel("Gender:");
		lblGender.setBounds(37, 167, 46, 14);
		profile.add(lblGender);
		
		JRadioButton male = new JRadioButton("Male");
		male.setBounds(37, 182, 62, 23);
		profile.add(male);
		
		JRadioButton female = new JRadioButton("Female");
		female.setBounds(101, 182, 71, 23);
		profile.add(female);
		
		ButtonGroup group_radio = new ButtonGroup();
		group_radio.add(male);
		group_radio.add(female);
		
		JButton save = new JButton("Save");
		save.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String fname = new String(first_name.getText());
				String lname = new String(last_name.getText());
				
				String gender = "";
				for (Enumeration<AbstractButton> buttons = group_radio.getElements(); buttons.hasMoreElements();) {
		            AbstractButton button = buttons.nextElement();

		            if (button.isSelected()) {
		                gender = button.getText();
		            }
		        }
				
				int result = 0;
				try {
					result = db.updateProfile(fname, lname, gender, Integer.parseInt(account.get("user_id")));
					account = db.getUserByID(Integer.parseInt(account.get("user_id")));
				} catch (NumberFormatException | SQLException e1) {
					e1.printStackTrace();
				}
				
				if (result == 1) {
					JOptionPane.showMessageDialog(null, "Profile changed!");
				} else {
					JOptionPane.showMessageDialog(null, "Something went wrong!");
				}
			}
		});
		save.setBounds(37, 212, 198, 25);
		profile.add(save);
		
		//change password
		JLabel label_current_pass = new JLabel("Current Password:");
		label_current_pass.setLabelFor(current_pass);
		label_current_pass.setBounds(269, 22, 198, 14);
		profile.add(label_current_pass);
		
		current_pass = new JPasswordField();
		current_pass.setBounds(269, 40, 198, 25);
		profile.add(current_pass);
		
		JLabel label_new_pass1 = new JLabel("New Password:");
		label_new_pass1.setLabelFor(label_new_pass1);
		label_new_pass1.setBounds(269, 71, 198, 14);
		profile.add(label_new_pass1);
		
		new_pass1 = new JPasswordField();
		new_pass1.setBounds(269, 88, 198, 25);
		profile.add(new_pass1);
		
		JLabel label_new_pass2 = new JLabel("Repeat New Password:");
		label_new_pass2.setLabelFor(label_new_pass2);
		label_new_pass2.setBounds(269, 119, 198, 14);
		profile.add(label_new_pass2);
		
		new_pass2 = new JPasswordField();
		new_pass2.setBounds(269, 136, 198, 25);
		profile.add(new_pass2);
		
		JButton change = new JButton("Change Password");
		change.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String current = new String(current_pass.getPassword());
				String pass1 = new String(new_pass1.getPassword());
				String pass2 = new String(new_pass2.getPassword());
				
				if (current.isEmpty() || pass1.isEmpty() || pass2.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill all the fields!");
					return;
				}
				
				if (!Objects.equals(current, account.get("user_password"))) {
					JOptionPane.showMessageDialog(null, "Wrong password!");
					return;
				}
				
				if (!Objects.equals(pass1, pass2)) {
					JOptionPane.showMessageDialog(null, "The passwords do not match!");
					return;
				}
				
				if (Objects.equals(current, pass2)) {
					JOptionPane.showMessageDialog(null, "The new password cannot be the same as the last one!");
					return;
				}
				
				if (pass1.length() < 4) {
					JOptionPane.showMessageDialog(null, "Password too short!");
					return;
				}
				
				int result = 0;
				try {
					result = db.updatePassword(pass1, Integer.parseInt(account.get("user_id")));
					account = db.getUserByID(Integer.parseInt(account.get("user_id")));
				} catch (NumberFormatException | SQLException e1) {
					e1.printStackTrace();
				}
				
				if (result == 1) {
					JOptionPane.showMessageDialog(null, "Password changed!");
				} else {
					JOptionPane.showMessageDialog(null, "Something went wrong!");
				}
			}
		});
		change.setBounds(269, 212, 198, 25);
		profile.add(change);
		
		/*
		 * Authentificate panel
		 */
		// login
		JPanel login = new JPanel();
		tabbedPane.addTab("Login", null, login, null);
		login.setLayout(null);
		
		JLabel login_title = new JLabel("Please login");
		login_title.setFont(new Font("Tahoma", Font.PLAIN, 31));
		login_title.setBounds(160, 29, 166, 57);
		login.add(login_title);
		
		JLabel login_label_username = new JLabel("Username:");
		login_label_username.setBounds(116, 113, 63, 14);
		login.add(login_label_username);
		
		login_username = new JTextField();
		login_username.setBounds(189, 108, 177, 25);
		login.add(login_username);
		login_username.setColumns(10);
		
		JLabel login_label_password = new JLabel("Password:");
		login_label_password.setBounds(116, 145, 63, 14);
		login.add(login_label_password);
		
		login_password = new JPasswordField();
		login_password.setBounds(189, 140, 177, 25);
		login.add(login_password);
		
		JButton login_button = new JButton("Login");
		login_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String user = new String(login_username.getText());
				String pass = new String(login_password.getPassword());
				
				if (user.isEmpty() || pass.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill all the fields!");
					return;
				}
				
				try {
					Map<String, String> result = new Database().getUserByName(user);
					
					if (result.isEmpty()) {    
						JOptionPane.showMessageDialog(null, "Username is not registered.");
						return;
					} 
				
					String db_user = result.get("user_name");
					String db_pass = result.get("user_password");
					
					if (Objects.equals(user, db_user) && Objects.equals(pass, db_pass)) {
						JOptionPane.showMessageDialog(null, "Welcome back, " + user + "!");
						
						account = result;
						isLogged = true;
						
						updateProfile(group_radio);
						
						tabbedPane.setSelectedIndex(0);
						tabbedPane.setEnabledAt(0, true);
						tabbedPane.setEnabledAt(2, true);
						tabbedPane.setEnabledAt(3, false);
						tabbedPane.setEnabledAt(4, false);
						
						rented_books = db.getBookedBooks(Integer.parseInt(account.get("user_id")));
						ArrayList<String> header = new ArrayList<String>();
						header.add("ID");
						header.add("Title");
						header.add("Autor");
						header.add("Genre");
						header.add("Time left");
						header.add("Action");
						tableAddBooks1(table_1, header, rented_books);
						
						if (Objects.equals(account.get("user_role"), "admin")) {
							mainAdmin();
						}
					} else {
						JOptionPane.showMessageDialog(null, "Wrong password!");
					}
				} catch (SQLException|ClassNotFoundException ex) {
					ex.printStackTrace();
				}
			}
		});
		login_button.setBounds(116, 180, 250, 25);
		login.add(login_button);
		
		//register
		JPanel register = new JPanel();
		tabbedPane.addTab("Register", null, register, null);
		register.setLayout(null);
		
		JLabel title = new JLabel("Please register");
		title.setFont(new Font("Tahoma", Font.PLAIN, 31));
		title.setBounds(134, 11, 209, 49);
		register.add(title);
		
		JLabel register_label_username = new JLabel("Username:");
		register_label_username.setBounds(76, 71, 147, 14);
		register.add(register_label_username);
		
		register_username = new JTextField();
		register_label_username.setLabelFor(register_username);
		register_username.setBounds(233, 66, 177, 25);
		register.add(register_username);
		register_username.setColumns(10);
		
		JLabel register_label_email = new JLabel("Email:");
		register_label_email.setBounds(76, 103, 147, 14);
		register.add(register_label_email);
		
		register_email = new JTextField();
		register_label_email.setLabelFor(register_email);
		register_email.setColumns(10);
		register_email.setBounds(233, 98, 177, 25);
		register.add(register_email);
		
		JLabel label_password1 = new JLabel("Password:");
		label_password1.setLabelFor(register_password1);
		label_password1.setBounds(76, 134, 147, 14);
		register.add(label_password1);
		
		register_password1 = new JPasswordField();
		register_password1.setBounds(233, 129, 177, 25);
		register.add(register_password1);
		
		JLabel label_password2 = new JLabel("Repeat Password:");
		label_password2.setLabelFor(register_password2);
		label_password2.setBounds(76, 165, 147, 14);
		register.add(label_password2);
		
		register_password2 = new JPasswordField();
		register_password2.setBounds(233, 160, 177, 25);
		register.add(register_password2);
		
		JButton register_button = new JButton("Register");
		register_button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String user = new String(register_username.getText());
				String email = new String(register_email.getText());
				String pass1 = new String(register_password1.getPassword());
				String pass2 = new String(register_password2.getPassword());
				
				if (user.isEmpty() || email.isEmpty() || pass1.isEmpty() | pass2.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill all the fields!");
					return;
				}
				
				if (!Objects.equals(pass1, pass2)) {
					JOptionPane.showMessageDialog(null, "Passwords do not match!");
					return;
				}
				
				if (pass1.length() < 4) {
					JOptionPane.showMessageDialog(null, "Password too short!");
					return;
				}
				
				try {
					Database db = new Database();
					int check = db.createUser(email, user, pass1);
					
					if (check == 1) {
						JOptionPane.showMessageDialog(null, "Welcome, " + user + "!");
						
						account = db.getUserByName(user);
						isLogged = true;
						
						updateProfile(group_radio);
						
						tabbedPane.setSelectedIndex(0);
						tabbedPane.setEnabledAt(0, true);
						tabbedPane.setEnabledAt(2, true);
						tabbedPane.setEnabledAt(3, false);
						tabbedPane.setEnabledAt(4, false);
					}

				} catch (ClassNotFoundException|SQLException ex) {
					if (ex instanceof java.sql.SQLIntegrityConstraintViolationException) {
						JOptionPane.showMessageDialog(null, ex.getMessage());
					} else {
						ex.printStackTrace();
					}
				}
			}
		});
		register_button.setBounds(76, 208, 334, 25);
		register.add(register_button);
	}
	
	public void mainAdmin() throws SQLException {
		ArrayList<Map<String, String>> waiting_bookings = db.getUnacceptedBookings();
		ArrayList<Map<String, String>> all_books = db.getAllBooks();
		ArrayList<Map<String, String>> top5Books = db.top5Books();
		ArrayList<Map<String, String>> top5Users = db.top5Users();
		ArrayList<Map<String, String>> topGenres = db.topGenres();
		
		JPanel dashboard = new JPanel();
		tabbedPane.addTab("Dashboard", null, dashboard, null);
		dashboard.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 31, 489, 66);
		dashboard.add(scrollPane_2);
		
		table_3 = new JTable();
		table_3.setDefaultEditor(Object.class, null);
		scrollPane_2.setViewportView(table_3);
		String header_table_3[] = new String[] { "ID", "Title", "Author", "Genre", "Booked count"};
		DefaultTableModel table_3_model = new DefaultTableModel(header_table_3, 0);
		table_3.setModel(table_3_model);
		
		for (Map<String, String> book : top5Books) {
	       String id = book.get("book_id");
    	   String title = book.get("book_title");
    	   String author = book.get("book_author");
    	   String genre = book.get("book_genre");
    	   String booked_count = book.get("book_booked_count");

    	   Object[] data = {id, title, author, genre, booked_count};

    	   table_3_model.addRow(data);
    	}
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 130, 237, 99);
		dashboard.add(scrollPane);
		
		table_5 = new JTable();
		table_5.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table_5);
		String header_table_5[] = new String[] { "ID", "Genre", "Books rented count"};
		DefaultTableModel table_5_model = new DefaultTableModel(header_table_5, 0);
		table_5.setModel(table_5_model);
		
		for (Map<String, String> genre : topGenres){
	       String genre_id = genre.get("genre_id");
    	   String genre_title = genre.get("genre_title");
    	   String genre_count = genre.get("genre_count");

    	   Object[] data = {genre_id, genre_title, genre_count};

    	   table_5_model.addRow(data);
    	}
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(257, 130, 242, 99);
		dashboard.add(scrollPane_1);
		
		table_6 = new JTable();
		table_6.setDefaultEditor(Object.class, null);
		scrollPane_1.setViewportView(table_6);
		String header_table_6[] = new String[] { "ID", "User Name", "Books rented count"};
		DefaultTableModel table_6_model = new DefaultTableModel(header_table_6, 0);
		table_6.setModel(table_6_model);
		
		JLabel lblNewLabel = new JLabel("Top 5 Rented Books:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(10, 4, 163, 25);
		dashboard.add(lblNewLabel);
		
		JLabel lblTopGenres = new JLabel("Top Genres:");
		lblTopGenres.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTopGenres.setBounds(10, 103, 163, 25);
		dashboard.add(lblTopGenres);
		
		JLabel lblTopReading = new JLabel("Top 5 Reading Users:");
		lblTopReading.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTopReading.setBounds(257, 103, 163, 25);
		dashboard.add(lblTopReading);
		
		for (Map<String, String> user : top5Users){
	       String user_id = user.get("user_id");
    	   String user_title = user.get("user_name");
    	   String user_count = user.get("user_books_read");

    	   Object[] data = {user_id, user_title, user_count};

    	   table_6_model.addRow(data);
    	}
		
		JPanel accept_tab = new JPanel();
		tabbedPane.addTab("Accept bookings", null, accept_tab, null);
		accept_tab.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 11, 489, 177);
		accept_tab.add(scrollPane_3);
		
		table_4 = new JTable();
		table_4.setDefaultEditor(Object.class, null);
		scrollPane_3.setViewportView(table_4);
		String header_table_4[] = new String[] { "ID", "Title", "Genre", "Booked by", "From", "To" };
		DefaultTableModel table_4_model = new DefaultTableModel(header_table_4, 0);
	    table_4.setModel(table_4_model);
	    
	    for (Map<String, String> booking : waiting_bookings){
	       String id = booking.get("book_id");
    	   String title = booking.get("book_title");
    	   String genre = booking.get("book_genre");
    	   String booked = booking.get("book_rented_by");
    	   String from = booking.get("book_rented_from");
    	   String to = booking.get("book_rented_until");

    	   Object[] data = {id, title, genre, booked, from, to};

    	   table_4_model.addRow(data);
    	}
		
		JButton accept_booking = new JButton("Accept booking");
		accept_booking.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table_4.getSelectedRow();
				
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Please select one row before!");
				} else {
					int book_id = Integer.parseInt((String) table_4.getValueAt(row, 0));
					String user_name = (String) table_4.getValueAt(row, 3);
					String genre = (String) table_4.getValueAt(row, 2);
					
					int result_1 = 0, result_2 = 0, result_3  = 0;
					try {
						result_1 = db.acceptBooking(book_id);
						result_2 = db.incrementGenre(genre);
						result_3 = db.incrementReadBooks(user_name);
						
					} catch (NumberFormatException | SQLException e1) {
						e1.printStackTrace();
					}
					
					if (result_1 == 1 && result_2 == 1 && result_3 == 1) {
						JOptionPane.showMessageDialog(null, "Booking accepted!");
						((DefaultTableModel)table_4.getModel()).removeRow(row);
					} else {
						JOptionPane.showMessageDialog(null, "Something went wrong!");
					}
				}
			}
		});
		accept_booking.setBounds(10, 199, 221, 25);
		accept_tab.add(accept_booking);
		
		JButton decline_booking = new JButton("Decline booking");
		decline_booking.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table_4.getSelectedRow();
				
				if (row == -1) {
					JOptionPane.showMessageDialog(null, "Please select one row before!");
				} else {
					int book_id = Integer.parseInt((String) table_4.getValueAt(row, 0));
					
					int result_1 = 0;
					try {
						result_1 = db.declineBooking(book_id);
						
					} catch (NumberFormatException | SQLException e1) {
						e1.printStackTrace();
					}
					
					if (result_1 == 1) {
						JOptionPane.showMessageDialog(null, "Booking decliend!");
						((DefaultTableModel)table_4.getModel()).removeRow(row);
					} else {
						JOptionPane.showMessageDialog(null, "Something went wrong!");
					}
				}
			}
		});
		decline_booking.setBounds(278, 199, 221, 25);
		accept_tab.add(decline_booking);
		
		JPanel manage = new JPanel();
		tabbedPane.addTab("Manage books", null, manage, null);
		manage.setLayout(null);
		
		JScrollPane scrollPane_4 = new JScrollPane();
		scrollPane_4.setBounds(10, 11, 489, 148);
		manage.add(scrollPane_4);
		
		table_7 = new JTable();
		table_7.setDefaultEditor(Object.class, null);
		scrollPane_4.setViewportView(table_7);
		String header_table_7[] = new String[] { "ID", "Title", "Autor", "Genre", "Action" };
		DefaultTableModel table_7_model = new DefaultTableModel(header_table_7, 0);
		table_7.setModel(table_7_model);
		
		for (Map<String, String> book : all_books){
	       String book_id = book.get("book_id");
    	   String book_title = book.get("book_title");
    	   String book_author = book.get("book_author");
    	   String book_genre = book.get("book_genre");

    	   Object[] data = {book_id, book_title, book_author, book_genre, "Delete"};

    	   table_7_model.addRow(data);
    	}
		
		Action delete = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	JTable table = (JTable)e.getSource();
		        int row = Integer.valueOf( e.getActionCommand() );

		        int book_id = Integer.parseInt((String) table.getValueAt(row, 0));
		        
		        int res = 0;
				try {
					res = db.removeBookById(book_id);
				} catch (NumberFormatException | SQLException e1) {
					e1.printStackTrace();
				}
				
				if (res == 1) {
					JOptionPane.showMessageDialog(null, "Book deleted!");
					((DefaultTableModel)table.getModel()).removeRow(row);
				} else {
					JOptionPane.showMessageDialog(null, "Something went wrong!");
				}
		    }
		};
		 
		ButtonColumn buttonColumn = new ButtonColumn(table_7, delete, 4);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 201, 136, 25);
		manage.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(156, 201, 116, 25);
		manage.add(textField_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(282, 200, 95, 25);
		manage.add(comboBox);
		
		for (Map<String, String> genre : topGenres) {
			comboBox.addItem(genre.get("genre_title"));
		}
		
		JButton btnNewButton = new JButton("Insert");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String title = textField_1.getText();
				String author = textField_2.getText();
				String genre = (String) comboBox.getSelectedItem();
				
				if (title.isEmpty() || author.isEmpty() || genre.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill all the fields!");
					return;
				}
				
				Map<String, String> res = new HashMap<String, String>();
				try {
					res = db.createBook(title, author, genre);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if (!res.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Book inserted!");
					Object[] data = {res.get("book_id"), res.get("book_title"), res.get("book_author"), res.get("book_genre"), "Delete"};
					((DefaultTableModel)table_7.getModel()).addRow(data);
				} else {
					JOptionPane.showMessageDialog(null, "Something went wrong!");
				}
			}
		});
		btnNewButton.setBounds(387, 200, 112, 25);
		manage.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("Book title:");
		lblNewLabel_1.setBounds(10, 175, 136, 25);
		manage.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Book autor:");
		lblNewLabel_1_1.setBounds(156, 175, 116, 25);
		manage.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Book genre:");
		lblNewLabel_1_1_1.setBounds(282, 175, 95, 25);
		manage.add(lblNewLabel_1_1_1);
	}
	
	private void updateProfile(ButtonGroup group_radio) {
		first_name.setText(account.get("user_firstname"));
		last_name.setText(account.get("user_lastname"));
		username.setText(account.get("user_name"));
		email.setText(account.get("user_email"));
		
		for (Enumeration<AbstractButton> buttons = group_radio.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();
            String value = button.getText();
            
            if (Objects.equals(value, account.get("user_gender"))) {
            	button.setSelected(true);
            }
        }
	}
	
	private void filterBooks(JTable table) {
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		
		ArrayList<Map<String, String>> filtered_books = new ArrayList<Map<String, String>>();
		
		String search = textField.getText().toLowerCase();
		
		for (Map<String, String> book : all_books) {
			String book_id = book.get("book_id").toLowerCase();
			String book_title = book.get("book_title").toLowerCase();
			String book_autor = book.get("book_author").toLowerCase();
			String book_genre = book.get("book_genre").toLowerCase();
			
			if (book_id.contains(search) || book_title.contains(search) || book_autor.contains(search) || book_genre.contains(search)) {
				filtered_books.add(book);
			}
		}
		
		ArrayList<String> header = new ArrayList<String>();
		header.add("ID");
		header.add("Title");
		header.add("Autor");
		header.add("Genre");
		header.add("Action");
		tableAddBooks2(table, header, filtered_books);
	}
	
	private void tableAddBooks1(JTable table, ArrayList<String> header, ArrayList<Map<String, String>> data) {
		DefaultTableModel model = new DefaultTableModel();
		table.setModel(model);
		
		for (String column : header) {
	        model.addColumn(column);
		}
		
		for (Map<String, String> book : data) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate date1 = LocalDate.parse(book.get("book_rented_from"), formatter);
			LocalDate date2 = LocalDate.parse(book.get("book_rented_until"), formatter);
		    long days = ChronoUnit.DAYS.between(date1, date2);
			
			model.addRow(new Object[]{ book.get("book_id"), book.get("book_title"), book.get("book_author"), book.get("book_genre"), days + " days", "Increase time" });
		}
		
		Action delete = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	if (account.isEmpty()) {
		    		JOptionPane.showMessageDialog(null, "Please login first!");
		    	} else {
			        JTable table = (JTable)e.getSource();
			        int row = Integer.valueOf( e.getActionCommand() );
	
			        int book_id = Integer.parseInt((String) table.getValueAt(row, 0));
			        int user_id = Integer.parseInt(account.get("user_id"));

					try {
						Rent rent = new Rent(user_id, book_id);
						rent.setVisible(true);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    	}
		    }
		};
		 
		ButtonColumn buttonColumn = new ButtonColumn(table, delete, 5);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
	}
	
	private void tableAddBooks2(JTable table, ArrayList<String> header, ArrayList<Map<String, String>> data) {
		DefaultTableModel model = new DefaultTableModel();
		table.setModel(model);
		
		for (String column : header) {
	        model.addColumn(column);
		}
		
		for (Map<String, String> book : data) {
			model.addRow(new Object[]{ book.get("book_id"), book.get("book_title"), book.get("book_author"), book.get("book_genre"), "Book" });
		}
		
		Action delete = new AbstractAction() {
		    public void actionPerformed(ActionEvent e) {
		    	if (account.isEmpty()) {
		    		JOptionPane.showMessageDialog(null, "Please login first!");
		    	} else {
			        JTable table = (JTable)e.getSource();
			        int row = Integer.valueOf( e.getActionCommand() );
	
			        int book_id = Integer.parseInt((String) table.getValueAt(row, 0));
			        int user_id = Integer.parseInt(account.get("user_id"));

					try {
						Rent rent = new Rent(user_id, book_id);
						rent.setVisible(true);
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		    	}
		    }
		};
		 
		ButtonColumn buttonColumn = new ButtonColumn(table, delete, 4);
		buttonColumn.setMnemonic(KeyEvent.VK_D);
	}
}
