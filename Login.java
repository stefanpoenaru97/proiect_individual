package proiect_pi;

import java.util.*;
import java.sql.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;
	
	private boolean isLogged = false;
	private ArrayList<String> account = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setResizable(false);
		setTitle("Login Panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 318, 232);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_username = new JLabel("Username:");
		label_username.setBounds(24, 54, 63, 14);
		contentPane.add(label_username);
		
		JLabel label_password = new JLabel("Password:");
		label_password.setBounds(24, 86, 63, 14);
		contentPane.add(label_password);
		
		username = new JTextField();
		username.setBounds(97, 51, 177, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		JButton login = new JButton("Login");
		login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String user = getUsername();
				String pass = getPassword();
				
				if (user.isEmpty() || pass.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill all the fields!");
					return;
				}
				
				try {
					Database db = new Database();
					ResultSet res = db.getUser(user);
					
					ResultSetMetaData rsmd = res.getMetaData();

					int count = rsmd.getColumnCount();
					
					if (!res.isBeforeFirst() ) {    
						JOptionPane.showMessageDialog(null, "Username is not registered.");
						return;
					} 
				
					while (res.next()) {
						String db_user = res.getString("username");
						String db_pass = res.getString("password");
						
						if (Objects.equals(user, db_user) && Objects.equals(pass, db_pass)) {								
							for(int i=1; i<=count; i++) {
					        	Login.this.account.add(res.getString(i));
					        }
							
							Login.this.isLogged = true;
							
							Login.this.dispose();
							
							Main frame = new Main();
							frame.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(null, "Wrong password!");
						}
					}
				} catch (SQLException|ClassNotFoundException ex) {
					ex.printStackTrace();
				}
			}
		});
		login.setBounds(24, 121, 250, 23);
		contentPane.add(login);
		
		password = new JPasswordField();
		password.setBounds(97, 83, 177, 20);
		contentPane.add(password);
		
		JLabel title = new JLabel("Please login");
		title.setBounds(24, 11, 161, 14);
		contentPane.add(title);
		
		JButton register = new JButton("Register");
		register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Login.this.dispose();
				
				Register frame = new Register();
				frame.setVisible(true);
			}
		});
		register.setBounds(24, 155, 250, 23);
		contentPane.add(register);
	}
	
	private String getPassword() {
        return new String(password.getPassword());
	}
	
	private String getUsername() {
		return new String(username.getText());
	}
	
	public boolean isLogged() {
		return this.isLogged;
	}
	
	public ArrayList<String> getAccount() {
		return this.account;
	}
	
	public void setIsLogged(boolean value) {
		this.isLogged = value;
	}
	
	public void setAccount(ArrayList<String> account) {
		this.account = account;
	}
	
}
