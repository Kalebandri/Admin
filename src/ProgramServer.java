import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JDesktopPane;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuListener;

public class ProgramServer extends JFrame{
	
	
		Connection con;
		Statement st;
		ResultSet rs;
		
		public void connect(){
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","");
				st = con.createStatement();
				
				st.executeQuery("USE db_Uts");

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	
	
	private JDesktopPane dp;
	private JMenuBar mnBar;
	private JMenu menuLogin;
	private JMenu menuEmployee;
	private JMenu menuItem;
	private JMenuItem menuAddItem;
	private JMenuItem menuAddStock;

	private JLabel welcome;	
	
	public String userID;
	
	

	public void loginState(Boolean state){
		menuLogin.setVisible(!state);
		menuEmployee.setVisible(state);
		menuItem.setVisible(state);
	}
	
	
	public ProgramServer(){
		Container cnt = this.getContentPane();
		cnt.setLayout(new BorderLayout());
		
		
		userID = "";
		
		dp = new JDesktopPane();
		mnBar = new JMenuBar();
		menuLogin = new JMenu("Login");
		menuEmployee = new JMenu("Employee");
		menuItem = new JMenu("Item");
		menuAddItem = new JMenuItem("Add Item");
		menuAddStock  = new JMenuItem("Add Stock");
		
		
		
		add(dp,BorderLayout.CENTER);
		setJMenuBar(mnBar);
		mnBar.add(menuEmployee);
		mnBar.add(menuItem);
		menuItem.add(menuAddItem);
		menuItem.add(menuAddStock);
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("ADMIN");
		setBounds(650, 25, 0,0);
		setSize(700, 700);
		setVisible(true);
		
		
		menuEmployee.addMenuListener(new MenuListener() {
			
			@Override
			public void menuSelected(MenuEvent arg0) {
				JIFemployee emp = new JIFemployee();
				emp.setVisible(true);
				dp.add(emp);
				
			}
			
			@Override
			public void menuDeselected(MenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void menuCanceled(MenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		menuAddItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JIFaddItem addItem = new JIFaddItem();
				addItem.setVisible(true);
				dp.add(addItem);
				
			}
		});
		
		menuAddStock.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JIFaddStock addStock = new JIFaddStock();
				addStock.setVisible(true);
				dp.add(addStock);
				
			}
		});
		
		
		
		
	
		
		
	}
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				new ProgramServer();
			}
		});
		
		

	}
	
}
	
	
	
	
	
	
	
	
