package proiect_pi;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
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
	private Map<String, String> account = new HashMap<String, String>();

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
					account = new Database().getUserByName(user);
					
					if (account.isEmpty()) {    
						JOptionPane.showMessageDialog(null, "Username is not registered.");
						return;
					} 
				
					String db_user = account.get("user_name");
					String db_pass = account.get("user_password");
					
					if (Objects.equals(user, db_user) && Objects.equals(pass, db_pass)) {							
						isLogged = true;
						
						Login.this.dispose();
					} else {
						JOptionPane.showMessageDialog(null, "Wrong password!");
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
	
	private String getUsername() {
		return new String(username.getText());
	}
	
	private String getPassword() {
		return new String(password.getPassword());
	}
	
	public boolean isLogged() {
		return isLogged;
	}
	
	public Map<String, String> getAccount() {
		return account;
	}	
}
