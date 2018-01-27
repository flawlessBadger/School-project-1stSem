package guilayer;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import controllayer.Session;
import guilayer.layers.MainMenu;

import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.JPanel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MainWindow {

	private JFrame mainFrame;
	
	private JTextField username;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JPasswordField passwordField;
	private MainMenu mainMenu = new MainMenu();
	
	private NotificationWindow notification = new NotificationWindow();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		mainFrame = new JFrame();
		mainFrame.setResizable(false);
		mainFrame.setBounds(100, 100, 1000, 800);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new CardLayout(0, 0));
		
		//mainFrame.getContentPane().setLayout(null);
		
		JPanel loginPane = new JPanel();
		loginPane.setBounds(0, 0, 1000, 771);
		mainFrame.getContentPane().add(loginPane);
		loginPane.setLayout(null);
		
		mainFrame.getContentPane().add(loginPane, "login");
		mainFrame.getContentPane().add(mainMenu, "main");
		menuStateChange("login");
		
		JPanel logLeftPanel = new JPanel();
		logLeftPanel.setBackground(new Color(255, 235, 205));
		logLeftPanel.setBounds(0, 0, 500, 771);
		loginPane.add(logLeftPanel);
		logLeftPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(MainWindow.class.getResource("/pics/xlbyg.png")));
		lblNewLabel.setBounds(100, 270, 264, 264);
		logLeftPanel.add(lblNewLabel);
		
		JPanel logRightPanel = new JPanel();
		logRightPanel.setBackground(Color.DARK_GRAY);
		logRightPanel.setBounds(500, 0, 500, 771);
		loginPane.add(logRightPanel);
		logRightPanel.setLayout(null);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(120, 345, 300, 2);
		logRightPanel.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(120, 425, 300, 2);
		logRightPanel.add(separator_1);
		
		username = new JTextField();
		username.setSelectedTextColor(new Color(255, 235, 205));
		username.setCaretColor(new Color(255, 235, 205));
		username.setBorder(null);
		username.setForeground(Color.WHITE);
		username.setBackground(Color.DARK_GRAY);
		username.setBounds(120, 315, 300, 30);
		logRightPanel.add(username);
		username.setColumns(15);
		
		passwordField = new JPasswordField();
		passwordField.setSelectedTextColor(new Color(255, 235, 205));
		passwordField.setCaretColor(new Color(255, 235, 205));
		passwordField.setForeground(Color.WHITE);
		passwordField.setBorder(null);
		passwordField.setBackground(Color.DARK_GRAY);
		passwordField.setBounds(120, 395, 300, 30);
		logRightPanel.add(passwordField);
		passwordField.setColumns(15);
		
		lblPassword = new JLabel("PASSWORD");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(120, 370, 300, 14);
		logRightPanel.add(lblPassword);
		lblPassword.setFont(new Font("Dubai", Font.BOLD, 14));
		
		lblUsername = new JLabel("USERNAME");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setBounds(120, 290, 300, 14);
		logRightPanel.add(lblUsername);
		lblUsername.setFont(new Font("Dubai", Font.BOLD, 14));
		
		JPanel btnLogin2 = new JPanel();
		btnLogin2.setBackground(new Color(255, 235, 205));
		btnLogin2.setBounds(120, 455, 150, 45);
		logRightPanel.add(btnLogin2);
		btnLogin2.setLayout(null);
		
		JLabel lblLogin = new JLabel("LogIn");
		lblLogin.setForeground(Color.WHITE);
		lblLogin.setFont(new Font("Dubai", Font.BOLD, 14));
		lblLogin.setBounds(58, 0, 34, 44);
		btnLogin2.add(lblLogin);
		
		btnLogin2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(username.getText().length() <= 0 && passwordField.getPassword().length <= 0)
					notification.errorWindow("Username and Password is missing!","Error");
				else if(username.getText().length() <= 0) 
					notification.errorWindow("Username is missing!","Error");
				else if(passwordField.getPassword().length <= 0) 
					notification.errorWindow("Password is missing!","Error");
				else if(username.getText().length() > 0 && passwordField.getPassword().length > 0)
					if(userLogin(username.getText(),String.valueOf(passwordField.getPassword()))) {
						menuStateChange("main");
					}else {
						notification.errorWindow("Wrong Username or Password!","Error");
					}
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				btnLogin2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnLogin2.setBorder(null);
			}
		});
	}
	
	private boolean userLogin(String username, String password) {
		try {
			if (Session.getInstance().login(username, password, "DIY")) {
				return true;
			}
		} catch (Exception ignored) {
            	
		}
		return false;
	}
	
	private void menuStateChange(String menuName) {
	    CardLayout cl = (CardLayout) mainFrame.getContentPane().getLayout();   
	    cl.show(mainFrame.getContentPane(), menuName);
	}
}
