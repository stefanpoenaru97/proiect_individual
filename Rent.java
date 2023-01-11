package proiect_pi;

import java.awt.Color;
import java.awt.EventQueue;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Rent extends JFrame {
	
	/**
	 *  DB VARIABLES
	 */
	
	private Database db = new Database();
	private Map<String, String> account = new HashMap<String, String>();
	private Map<String, String> book = new HashMap<String, String>();
	
	/**
	 *  GUI VARIABLES
	 */

	private JPanel contentPane;
	private JTextField title;
	private JTextField autor;
	private JTextField date;

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
					Rent frame = new Rent(1, 2);
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
	public Rent(int user_id, int book_id) throws SQLException, ClassNotFoundException {
		account = db.getUserByID(user_id);
		book = db.getBookByID(book_id);
		
		setTitle("Rent a book");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 280, 276);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book title");
		lblNewLabel.setBounds(10, 11, 244, 25);
		contentPane.add(lblNewLabel);
		
		title = new JTextField();
		title.setEnabled(false);
		title.setText(book.get("book_title"));
		title.setBounds(10, 31, 244, 25);
		contentPane.add(title);
		title.setColumns(10);
		
		JLabel lblBookAuthor = new JLabel("Book author");
		lblBookAuthor.setBounds(10, 72, 244, 25);
		contentPane.add(lblBookAuthor);
		
		autor = new JTextField();
		autor.setEnabled(false);
		autor.setText(book.get("book_author"));
		autor.setColumns(10);
		autor.setBounds(10, 93, 244, 25);
		contentPane.add(autor);
		
		JLabel lblNewLabel_1_1 = new JLabel("Rent until");
		lblNewLabel_1_1.setBounds(10, 133, 244, 25);
		contentPane.add(lblNewLabel_1_1);
		
		UtilDateModel model = new UtilDateModel();
		Properties props = new Properties();
		props.put("text.today", "Today");
		props.put("text.month", "Month");
		props.put("text.year", "Year");
		JDatePanelImpl datePanel_departure = new JDatePanelImpl(model, props);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel_departure, new DateLabelFormatter("yyyy-MM-dd"));
		datePicker.setBounds(10, 154, 244, 25); 
		contentPane.add(datePicker);
		
		JButton save = new JButton("Save");
		save.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int book_id = Integer.parseInt(book.get("book_id"));
				Date date = (Date) datePicker.getModel().getValue();
				LocalDate from = LocalDate.now();
				LocalDate until = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				
				int result = 0;
				try {
					result = db.setBookRentedBY(user_id, from.toString(), until.toString(), book_id);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if (result == 1) {
					JOptionPane.showMessageDialog(null, "Book sent for aproval!");
				} else {
					JOptionPane.showMessageDialog(null, "Something went wrong!");
				}
				Rent.this.dispose();
			}
		});
		save.setBounds(10, 201, 244, 25);
		contentPane.add(save);
	}
}
