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

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

public class JIFaddStock extends JInternalFrame{

	
	public connect conn;
	ResultSet rs;
	
	
	private JLabel lblTitle;
	private JLabel lblItemCode;
	private JLabel lblItemCodeIsi;
	private JLabel lblItemName;
	private JLabel lblItemNameIsi;
	private JLabel lblQty;
	
	private JTable tbMain;
	private DefaultTableModel mdl;
	
	private JSpinner spQty;
	
	private JButton btnAdd;
	
	
	private JPanel pnlTop;
	private JPanel pnlCenter;
		private JPanel pnlEast;
		private JPanel pnlWest;
		private JScrollPane spTable;
	private JPanel pnlBottom;
		private JPanel pnlInfo;
			private JPanel pnlItemCode;
			private JPanel pnlItemCodeIsi;
			private JPanel pnlItemName;
			private JPanel pnlItemNameIsi;
		private JPanel pnlInput;
	
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
	
	
	public JIFaddStock(){
		conn = new connect();
		Container cnt = this.getContentPane();
		cnt.setLayout(new BorderLayout());
		
		
		lblTitle = new JLabel("Add Stock");
		lblTitle.setFont(new Font("ARIAL",Font.BOLD,14));
		
		lblItemCode = new JLabel("Item Code");
		lblItemCodeIsi = new JLabel("-");
		lblItemName = new JLabel("Item Name");
		lblItemNameIsi = new JLabel("-");
		lblQty = new JLabel("Quantity");
		
		spQty = new JSpinner();
			Dimension wide = spQty.getPreferredSize();
			wide.width=50;
			spQty.setPreferredSize(wide);		
			spQty.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		
		btnAdd = new JButton("Add Stock");
		
		
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
		cnt.add(pnlTop,BorderLayout.NORTH);
			
			pnlTop.add(lblTitle);
		
		pnlCenter = new JPanel();
		cnt.add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new BorderLayout());
			
			pnlEast = new JPanel();
			pnlCenter.add(pnlEast, BorderLayout.EAST);
			
			pnlWest = new JPanel();
			pnlCenter.add(pnlWest, BorderLayout.WEST);
			
			spTable = new JScrollPane();
			pnlCenter.add(spTable, BorderLayout.CENTER);
			
				spTable.setViewportView(tbMain);
				tbMain.setFillsViewportHeight(true);
		
		pnlBottom = new JPanel();
		cnt.add(pnlBottom, BorderLayout.SOUTH);
		pnlBottom.setLayout(new GridLayout(2,1));
		
			pnlInfo = new JPanel();
			pnlBottom.add(pnlInfo);
			pnlInfo.setLayout(new GridLayout(2, 2));
				
				pnlItemCode = new JPanel();
				pnlInfo.add(pnlItemCode);
					
					pnlItemCode.add(lblItemCode);
				
				pnlItemCodeIsi = new JPanel();
				pnlInfo.add(pnlItemCodeIsi);
				
					pnlItemCodeIsi.add(lblItemCodeIsi);
				
				pnlItemName = new JPanel();
				pnlInfo.add(pnlItemName);
				
					pnlItemName.add(lblItemName);
				
				pnlItemNameIsi = new JPanel();
				pnlInfo.add(pnlItemNameIsi);
				
					pnlItemNameIsi.add(lblItemNameIsi);
			
			pnlInput = new JPanel();
			pnlBottom.add(pnlInput);
			
				pnlInput.add(lblQty);
				pnlInput.add(spQty);
				pnlInput.add(btnAdd);
				
		
		
		setBounds(60, 60, 50, 50);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Add Stock");
		setSize(600,550);
		setVisible(true);
		setClosable(true);
		
		
		
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
				
				String itCode = tbMain.getModel().getValueAt(row, 0).toString();
				String itName = tbMain.getModel().getValueAt(row, 1).toString();
				
				lblItemCodeIsi.setText(itCode);
				lblItemNameIsi.setText(itName);
				
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String itCode = lblItemCodeIsi.getText();
				int qtyAdded = (Integer)spQty.getValue();
				
				if (itCode.equals("-")) {
					JOptionPane.showMessageDialog(null, "Select Item First");
				}
				else if(qtyAdded==0){
					JOptionPane.showMessageDialog(null, "Input Quantity");
					
				}
				else {
					conn.updateTable("UPDATE item SET stok = stok+"+qtyAdded+" WHERE kodeBarang LIKE '"+itCode+"'");
					JOptionPane.showMessageDialog(null, "Added");
					mdl.setDataVector(fillTable(), headCol);

				}
				
			}
		});

	}
}
