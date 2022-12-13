package proiect_pi;

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
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JTextField email;
	private JPasswordField password1;
	private JPasswordField password2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setResizable(false);
		setTitle("Register Panel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 384, 273);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_username = new JLabel("Username:");
		label_username.setBounds(24, 54, 147, 14);
		contentPane.add(label_username);
		
		JLabel label_email = new JLabel("Email:");
		label_email.setBounds(24, 86, 147, 14);
		contentPane.add(label_email);
		
		username = new JTextField();
		label_username.setLabelFor(username);
		username.setBounds(181, 51, 177, 20);
		contentPane.add(username);
		username.setColumns(10);
		
		JButton login = new JButton("Login");
		login.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Register.this.dispose();
				
				Login frame = new Login();
				frame.setVisible(true);
			}
		});
		login.setBounds(27, 191, 144, 23);
		contentPane.add(login);
		
		JLabel title = new JLabel("Please register");
		title.setBounds(24, 11, 161, 14);
		contentPane.add(title);
		
		JButton register = new JButton("Register");
		register.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String user = getUsername();
				String email = getEmail();
				String pass1 = getPassword1();
				String pass2 = getPassword2();
				
				if (user.isEmpty() || email.isEmpty() || pass1.isEmpty() | pass2.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please fill all the fields!");
					return;
				}
				
				if (!Objects.equals(pass1, pass2)) {
					JOptionPane.showMessageDialog(null, "Passwords do not match!");
					return;
				}
				try {
					Database db = new Database();
					int check = db.createUser(email, user, pass1);
					
					System.out.println(check);
					
					if (check == 1) {
						ResultSet res = db.getUser(user);
						
						ResultSetMetaData rsmd = res.getMetaData();

						int count = rsmd.getColumnCount();
						
						ArrayList<String> info = new ArrayList<String>();
						while (res.next()) {
							for(int i=1; i<=count; i++) {
								info.add(res.getString(i));
					        }
						}
						Login login = new Login();
						login.setAccount(info);
						login.setIsLogged(true);
						
						Register.this.dispose();
						
						Main frame = new Main();
						frame.setVisible(true);
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
		register.setBounds(199, 191, 144, 23);
		contentPane.add(register);
		
		email = new JTextField();
		label_email.setLabelFor(email);
		email.setColumns(10);
		email.setBounds(181, 83, 177, 20);
		contentPane.add(email);
		
		password1 = new JPasswordField();
		password1.setBounds(181, 114, 177, 20);
		contentPane.add(password1);
		
		password2 = new JPasswordField();
		password2.setBounds(181, 145, 177, 20);
		contentPane.add(password2);
		
		JLabel label_password1 = new JLabel("Password:");
		label_password1.setLabelFor(password1);
		label_password1.setBounds(24, 117, 147, 14);
		contentPane.add(label_password1);
		
		JLabel label_password2 = new JLabel("Repeat Password:");
		label_password2.setLabelFor(password2);
		label_password2.setBounds(24, 148, 147, 14);
		contentPane.add(label_password2);
	}
	
	private String getUsername() {
		return new String(username.getText());
	}
	
	private String getEmail() {
		return new String(email.getText());
	}
	
	private String getPassword1() {
        return new String(password1.getPassword());
	}
	
	private String getPassword2() {
        return new String(password2.getPassword());
	}
}
