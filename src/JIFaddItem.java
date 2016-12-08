import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

public class JIFaddItem extends JInternalFrame{

	
	public connect conn;
	ResultSet rs;
	
	
	private JLabel lblTitle;
	private JLabel lblItemName;
	private JLabel lblPrice;
	private JLabel lblStok;
	
	private JTextField txtName;
	private JTextField txtPrice;
	private JSpinner spQty;
	
	private JButton btnAdd;
	
	private JTable tbMain;
	private DefaultTableModel mdl;
	
	
	
	private JPanel pnlTop;
	private JPanel pnlCenter;
		private JPanel pnlEast;
		private JPanel pnlWest;
		private JScrollPane spTable;
	private JPanel pnlBotom;
		private JPanel pnlLbl;
			private JPanel pnlLblName;
			private JPanel pnlLblPrice;
			private JPanel pnlLblStok;
		private JPanel pnlTxtField;
			private JPanel pnlTxtFieldName;
			private JPanel pnlTxtFieldPrice;
			private JPanel pnlTxtFieldStok;
		private JPanel pnlBtn;
	
		
	public Vector<Vector> fillTable(){
		
		Vector<Vector> contentTable = new Vector<Vector>();		
		
		
		try {
			
			rs = conn.readTable("SELECT * from item");
			
			while (rs.next()) {
				
				Vector<String> data = new Vector<String>();
				data.add(rs.getString(1));
				data.add(rs.getString(2));
				data.add(rs.getString(3));
				data.add(rs.getString(4));
				contentTable.add(data);
			}
						
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}		
		return contentTable;		
	}
	
	public JIFaddItem(){
		conn = new connect();
		Container cnt = this.getContentPane();
		cnt.setLayout(new BorderLayout());
		
		
		lblTitle = new JLabel("Add Item");
		lblTitle.setFont(new Font("ARIAL",Font.BOLD,14));
		
		lblItemName = new JLabel("Item Name");
		lblPrice = new JLabel("Price");
		lblStok = new JLabel("Stock");
		
		txtName = new JTextField();
			txtName.setBounds(0, 0, 170, 20);
			Dimension a = txtName.getPreferredSize();
			a.width=150;
			txtName.setPreferredSize(a);
			
		txtPrice = new JTextField();
			txtPrice.setBounds(0, 0, 170, 20);
			Dimension b = txtPrice.getPreferredSize();
			b.width=150;
			txtPrice.setPreferredSize(b);
			
		spQty = new JSpinner();
			Dimension wide = spQty.getPreferredSize();
			wide.width=50;
			spQty.setPreferredSize(wide);		
			spQty.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		
		btnAdd = new JButton("Add New Item");
		
		
			final Vector<String> headCol = new Vector<String>();
			headCol.add("Item Code");
			headCol.add("Item Name");
			headCol.add("Price");
			headCol.add("Qty");
			
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
		pnlBotom.setLayout(new GridLayout(1, 3));
		cnt.add(pnlBotom,BorderLayout.SOUTH);
			
			pnlLbl = new JPanel();
			pnlLbl.setLayout(new GridLayout(3, 1));
			pnlBotom.add(pnlLbl);
			
				pnlLblName = new JPanel();
				pnlLbl.add(pnlLblName);
					
					pnlLblName.add(lblItemName);
					
				pnlLblPrice = new JPanel();
				pnlLbl.add(pnlLblPrice);
					
					pnlLblPrice.add(lblPrice);
						
				pnlLblStok = new JPanel();
				pnlLbl.add(pnlLblStok);
					
					pnlLblStok.add(lblStok);
					
			pnlTxtField = new JPanel();
			pnlTxtField.setLayout(new GridLayout(3, 1));
			pnlBotom.add(pnlTxtField);
			
				pnlTxtFieldName = new JPanel();
				pnlTxtField.add(pnlTxtFieldName);
					
					pnlTxtFieldName.add(txtName);
					
				pnlTxtFieldPrice = new JPanel();
				pnlTxtField.add(pnlTxtFieldPrice);
					
					pnlTxtFieldPrice.add(txtPrice);
						
				pnlTxtFieldStok = new JPanel();
				pnlTxtField.add(pnlTxtFieldStok);
					
					pnlTxtFieldStok.add(spQty);	
					
			pnlBtn = new JPanel();
			pnlBotom.add(pnlBtn);
			
				pnlBtn.add(btnAdd);
		
		
		
		setBounds(60, 60, 50, 50);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Add Item");
		setSize(600,550);
		setVisible(true);
		setClosable(true);
		
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String newItemName = txtName.getText();
				int newItemPrice = 0;
				int stok = (Integer)spQty.getValue();
				
				boolean priceIsAngka = true;
				try {
					newItemPrice = Integer.parseInt(txtPrice.getText()) ;
					
				} catch (Exception e2) {
					priceIsAngka = false;
				}
				
				if (newItemName.equals("")) {
					JOptionPane.showMessageDialog(null, "Please input Item Name");
				}
				else if(newItemPrice==0){
					JOptionPane.showMessageDialog(null, "Please input Item price");
				}
				else if(priceIsAngka==false){
					JOptionPane.showMessageDialog(null, "Please input Item price in number only");
				}
				else {
					try {
						String kodeItem = "I00001";
						
						rs = conn.readTable("SELECT kodeBarang from item Order By kodeBarang desc");
						
						if(!rs.next()){
							
							conn.updateTable("INSERT into item values ('"+kodeItem+"','"+newItemName+"','"+newItemPrice+"','"+stok+"')");
						
						}
						else{						
							
							String kodeTrAkhir = rs.getString(1);
							int intKode = Integer.parseInt(kodeTrAkhir.substring(1, 6));
							intKode++;
							
							kodeItem = "I"+String.format("%05d", intKode);
							
							conn.updateTable("INSERT into item values ('"+kodeItem+"','"+newItemName+"','"+newItemPrice+"','"+stok+"')");
						}
						
						txtName.setText("");
						txtPrice.setText("");
						spQty.setValue(0);
						mdl.setDataVector(fillTable(), headCol);
						
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, e2);
					}

				}
				
				
			}
		});
		
		
	}
}
