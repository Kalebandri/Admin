import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class JIFemployee extends JInternalFrame{
	
	public connect conn;
	ResultSet rs;
	
	
	private JLabel lblTitle;
	private JLabel lblUserID;
	private JLabel lblName;
	private JLabel lblGender;
	private JLabel lblAddress;
	private JLabel lblPassword;
	
	private JTextField txtUserId;
	private JTextField txtName;
	private JRadioButton rbmale;
	private JRadioButton rbfemale;
	private JTextField txtAddress;
	private JTextField txtPassword;
	
	private JTable tbMain;
	private DefaultTableModel mdl;
	
	private JButton btnAdd;
	private JButton btnEdit;
	private JButton btnCancel;
	
	
	
	private JPanel pnlTop;
	private JPanel pnlCenter;
		private JPanel pnlEast;
		private JPanel pnlWest;
		private JScrollPane spTable;
	private JPanel pnlBotom;
		private JPanel pnlBotomCenter;
			private JPanel pnlLbl;
				private JPanel pnlLblUserID;
				private JPanel pnlLblName;
				private JPanel pnlLblGender;
				private JPanel pnlLblAddress;
				private JPanel pnlLblPassword;
			private JPanel pnlTxtField;
				private JPanel pnlTxtFieldUserID;
				private JPanel pnlTxtFieldName;
				private JPanel pnlRadBtnGender;
				private JPanel pnlTxtFieldAddress;
				private JPanel pnlTxtFieldPassword;
		private JPanel pnlBtn;
			private JPanel pnlBtnAdd;
			private JPanel pnlBtnEdit;
			private JPanel pnlBtnCancel;
	
	
	public Vector<Vector> fillTable(){
			
			Vector<Vector> contentTable = new Vector<Vector>();		
			
			//data
			try {				
				rs = conn.readTable("SELECT * from Pegawai");				
				while (rs.next()) {					
					Vector<String> data = new Vector<String>();
					data.add(rs.getString(1));
					data.add(rs.getString(3));
					
					if(rs.getString(5).equals("m")){
						data.add("Male");
					}else{
						data.add("Female");
					}
					
					
					data.add(rs.getString(4));
					data.add(rs.getString(2));
					contentTable.add(data);
				}								
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, e);
				return null;
			}					
			return contentTable;			
		}		
	
	public void resetButton(){
		txtUserId.setEnabled(false);
		txtName.setEnabled(false);
		txtAddress.setEnabled(false);
		txtPassword.setEnabled(false);
		rbfemale.setEnabled(false);
		rbmale.setEnabled(false);
		
		txtUserId.setText("");
		txtName.setText("");
		txtAddress.setText("");
		txtPassword.setText("");
		rbfemale.setSelected(false);
		rbmale.setSelected(false);
		
		btnAdd.setEnabled(true);
		btnEdit.setEnabled(true);
	}	
		
	
	public JIFemployee(){
		conn = new connect();
		Container cnt = this.getContentPane();
		cnt.setLayout(new BorderLayout());
		
		
		lblTitle = new JLabel("Employee");
		lblTitle.setFont(new Font("ARIAL",Font.BOLD,14));
		
		lblUserID = new JLabel("User ID");
		lblName = new JLabel("Name");
		lblGender = new JLabel("Gender");
		lblAddress = new JLabel("Address");
		lblPassword = new JLabel("Password");
		
		txtUserId = new JTextField();
		txtUserId.setBounds(0, 0, 150, 20);
		Dimension a = txtUserId.getPreferredSize();
		a.width=150;
		txtUserId.setPreferredSize(a);
		
		
		txtName = new JTextField();
		txtName.setBounds(0, 0, 150, 20);
		txtName.setPreferredSize(a);
		
		rbmale = new JRadioButton("Male");
		
		rbfemale = new JRadioButton("Female");
		
		ButtonGroup gender = new ButtonGroup();
		gender.add(rbfemale);
		gender.add(rbmale);
		
		txtAddress = new JTextField();
		txtAddress.setBounds(0, 0, 150, 40);
		Dimension b = txtAddress.getPreferredSize();
		b.width=150;
		b.height=40;
		txtAddress.setPreferredSize(b);
		
		txtPassword = new JTextField();
		txtPassword.setBounds(0, 0, 150, 20);
		txtPassword.setPreferredSize(a);
		
		btnAdd = new JButton("Create New Employee");
		btnEdit = new JButton("Edit");
		btnCancel = new JButton("Cancel");
		
		
		final Vector<String> headCol = new Vector<String>();
		headCol.add("User ID");
		headCol.add("Name");
		headCol.add("Gender");
		headCol.add("Address");
		headCol.add("Password");
		
		mdl = new DefaultTableModel(fillTable(), headCol){
			
			public boolean isCellEditable(int row,int col) {
				return false;
			}
		};
		
		tbMain = new JTable(mdl);
		tbMain.getTableHeader().setReorderingAllowed(false);		
		tbMain.setCellSelectionEnabled(true);
		fillTable();
		
		
		
		pnlTop = new JPanel();
		cnt.add(pnlTop, BorderLayout.NORTH);
		
			pnlTop.add(lblTitle);
			
		pnlCenter = new JPanel();
		pnlCenter.setLayout(new BorderLayout());
		cnt.add(pnlCenter, BorderLayout.CENTER);
		
			pnlEast = new JPanel();
			pnlCenter.add(pnlEast, BorderLayout.EAST);
			
			pnlWest = new JPanel();
			pnlCenter.add(pnlWest, BorderLayout.WEST);
			
			spTable = new JScrollPane();
			pnlCenter.add(spTable, BorderLayout.CENTER);
			
				spTable.setViewportView(tbMain);
				tbMain.setFillsViewportHeight(true);
				
		pnlBotom =  new JPanel();
		pnlBotom.setLayout(new BorderLayout());
		cnt.add(pnlBotom,BorderLayout.SOUTH);
		
			pnlBotomCenter = new JPanel();
			pnlBotomCenter.setLayout(new GridLayout(1, 2));
			pnlBotom.add(pnlBotomCenter,BorderLayout.CENTER);
		
				pnlLbl = new JPanel();
				pnlLbl.setLayout(new GridLayout(5, 1));
				pnlBotomCenter.add(pnlLbl);
				
					pnlLblUserID = new JPanel();
					pnlLbl.add(pnlLblUserID);
						
						pnlLblUserID.add(lblUserID);
						
					pnlLblName = new JPanel();
					pnlLbl.add(pnlLblName);
						
						pnlLblName.add(lblName);
						
					pnlLblGender = new JPanel();
					pnlLbl.add(pnlLblGender);
						
						pnlLblGender.add(lblGender);
						
					pnlLblAddress = new JPanel();
					pnlLbl.add(pnlLblAddress);
						
						pnlLblAddress.add(lblAddress);
						
					pnlLblPassword = new JPanel();
					pnlLbl.add(pnlLblPassword);
						
						pnlLblPassword.add(lblPassword);
				
				pnlTxtField = new JPanel();
				pnlTxtField.setLayout(new GridLayout(5, 1));
				pnlBotomCenter.add(pnlTxtField);
				
					pnlTxtFieldUserID = new JPanel();
					pnlTxtField.add(pnlTxtFieldUserID);
						
						pnlTxtFieldUserID.add(txtUserId);
						
					pnlTxtFieldName = new JPanel();
					pnlTxtField.add(pnlTxtFieldName);
						
						pnlTxtFieldName.add(txtName);
						
					pnlRadBtnGender = new JPanel();
					pnlTxtField.add(pnlRadBtnGender);
						
						pnlRadBtnGender.add(rbmale);
						pnlRadBtnGender.add(rbfemale);
						
					pnlTxtFieldAddress = new JPanel();
					pnlTxtField.add(pnlTxtFieldAddress);
						
						pnlTxtFieldAddress.add(txtAddress);
						
					pnlTxtFieldPassword = new JPanel();
					pnlTxtField.add(pnlTxtFieldPassword);
						
						pnlTxtFieldPassword.add(txtPassword);	
			
			
			pnlBtn = new JPanel();
			
			pnlBotom.add(pnlBtn,BorderLayout.SOUTH);
			
				pnlBtnAdd = new JPanel();
				pnlBtn.add(pnlBtnAdd);
					
					pnlBtnAdd.add(btnAdd);
					
				pnlBtnEdit = new JPanel();
				pnlBtn.add(pnlBtnEdit);
				
					pnlBtnEdit.add(btnEdit);
					
				pnlBtnCancel = new JPanel();
				pnlBtn.add(pnlBtnCancel);
				
					pnlBtnCancel.add(btnCancel);
				
			
			
		
		setBounds(60, 60, 50, 50);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Employee");
		setSize(600,550);
		setVisible(true);
		setClosable(true);
		
		resetButton();
		
		
		tbMain.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tbMain.rowAtPoint(e.getPoint());
				
				String userid = tbMain.getModel().getValueAt(row, 0).toString();
				String name = tbMain.getModel().getValueAt(row, 1).toString();
				String gender = tbMain.getModel().getValueAt(row, 2).toString();
				String address = tbMain.getModel().getValueAt(row, 3).toString();
				String password = tbMain.getModel().getValueAt(row, 4).toString();
				
				txtUserId.setText(userid);
				txtName.setText(name);	
				if (gender.equals("Male")) {
					rbmale.setSelected(true);
				} else {
					rbfemale.setSelected(true);
				}
				txtAddress.setText(address);
				txtPassword.setText(password);
				
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if (btnEdit.isEnabled()==true) {
					txtUserId.setEnabled(false);
					txtName.setEnabled(true);
					txtAddress.setEnabled(true);
					txtPassword.setEnabled(true);
					rbfemale.setEnabled(true);
					rbmale.setEnabled(true);
					
					txtUserId.setText("");
					txtName.setText("");
					txtAddress.setText("");
					txtPassword.setText("");
					rbfemale.setSelected(false);
					rbmale.setSelected(false);
					
					btnAdd.setEnabled(true);
					btnEdit.setEnabled(false);
				} else {
					
					String name = txtName.getText();
					String gender = "";
					if (rbfemale.isSelected()) {
						gender = "f";
					} else if(rbmale.isSelected()) {
						gender = "m";
					}
					String address = txtAddress.getText();
					String pass = txtPassword.getText();
					
					
					if (name.equals("")) {
						JOptionPane.showMessageDialog(null, "Name must be filled");
					}else if (gender.equals("")) {
						JOptionPane.showMessageDialog(null, "Gender must be selected");
					}else if (address.equals("")) {
						JOptionPane.showMessageDialog(null, "Address must be filled");
					}else if (pass.equals("")) {
						JOptionPane.showMessageDialog(null, "Password must be filled");
					} else {
						try {
							String userId = "E0001";							
							rs = conn.readTable("SELECT userId from pegawai Order By userId desc");
							
							if(!rs.next()){								
								conn.updateTable("INSERT into pegawai values ('"+userId+"','"+pass+"','"+name+"','"+address+"','"+gender+"')");						
							}
							else{						
								
								String kodeTrAkhir = rs.getString(1);
								int intKode = Integer.parseInt(kodeTrAkhir.substring(1, 5));
								intKode++;
								
								userId = "E"+String.format("%04d", intKode);
								
								conn.updateTable("INSERT into pegawai values ('"+userId+"','"+pass+"','"+name+"','"+address+"','"+gender+"')");
							}
							resetButton();
							mdl.setDataVector(fillTable(), headCol);
							
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e);
						}
					}					
				}				
			}
		});
		
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (btnAdd.isEnabled()==true) {
					
					if(txtUserId.getText().equals("")){
						JOptionPane.showMessageDialog(null, "Select data to edit");
					}else{
						txtUserId.setEnabled(false);
						txtName.setEnabled(true);
						txtAddress.setEnabled(true);
						txtPassword.setEnabled(true);
						rbfemale.setEnabled(true);
						rbmale.setEnabled(true);
						
						btnAdd.setEnabled(false);
						btnEdit.setEnabled(true);
					}
					
				} else {
					String userId = txtUserId.getText();
					String name = txtName.getText();
					String gender = "";
					if (rbfemale.isSelected()) {
						gender = "f";
					} else if(rbmale.isSelected()) {
						gender = "m";
					}
					String address = txtAddress.getText();
					String pass = txtPassword.getText();
					
					
					if (name.equals("")) {
						JOptionPane.showMessageDialog(null, "Name must be filled");
					}else if (gender.equals("")) {
						JOptionPane.showMessageDialog(null, "Gender must be selected");
					}else if (address.equals("")) {
						JOptionPane.showMessageDialog(null, "Address must be filled");
					}else if (pass.equals("")) {
						JOptionPane.showMessageDialog(null, "Password must be filled");
					} else {
						try {
							
							conn.updateTable("UPDATE pegawai SET password='"+pass+"', nama = '"+name+"', alamat = '"+address+"', gender = '"+gender+"' WHERE userId like '"+userId+"' ");
							
							resetButton();
							mdl.setDataVector(fillTable(), headCol);
							
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, e);
						}
					}

				}
				
			}
		});
		
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				resetButton();
				
			}
		});
	}
}
