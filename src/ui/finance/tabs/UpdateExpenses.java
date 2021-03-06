package ui.finance.tabs;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;

import ui.components.KTab;
import utils.common.database.Database;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import java.awt.ScrollPane;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class UpdateExpenses extends KTab {
	private JTextField search;
	private JTextField t1;
	private JTable jtable1;
	private JTextField t2;
	private JTextField t4;
	private JTextField t6;
	

    
    
	//ResultSet rs = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UpdateExpenses frame = new UpdateExpenses();
					frame.setVisible(true);
				} catch (Exception e) {
				
					e.printStackTrace();
				}
			}
		});
	}

	
	
	
	public void tableLoad(){
	    
        try{
           
        	String sql = "select ExpenseID,Description,Date,NetExpense,Type,EmployeeID from expenses ORDER BY ExpenseID";
          
            
            PreparedStatement stmt = Database.getConnection().prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
                
            
            jtable1.setModel(Database.resultSetToTableModel(rs));
            
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
    
    }
	
	
	
	
	/**
	 * Create the frame.
	 */
	public UpdateExpenses() {
		
		super("Update Expenses ");
		
	
		JDateChooser t3 = new JDateChooser();
		t3.setBounds(250, 454, 147, 24);
		getContentPane().add(t3);
		
		
		JComboBox t5 = new JComboBox();
		t5.setModel(new DefaultComboBoxModel(new String[] {"Select the Type", "", "Administration", "Financial", "Sales and Distribution", "Others"}));
		t5.setBounds(602, 347, 147, 24);
		getContentPane().add(t5);
		
		
		JLabel lblNewLabel = new JLabel("Search by Type");
		lblNewLabel.setBounds(166, 38, 114, 14);
		getContentPane().add(lblNewLabel);
		
		search = new JTextField();
		search.setBounds(327, 34, 138, 23);
		getContentPane().add(search);
		search.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = search.getText();
		        
		        try{
		            String sql = "Select ExpenseID,Description,Date,NetExpense,Type,EmployeeID from expenses where Type like '%"+name+"%' ";
		            
		            PreparedStatement ps = Database.getConnection().prepareStatement(sql);
		            ResultSet rs = ps.executeQuery();
		            
		            
		            jtable1.setModel(Database.resultSetToTableModel(rs));
		            
		        }catch(Exception e1){
		            e1.printStackTrace();
		        }
				
			}
		});
		btnNewButton.setBounds(543, 34, 89, 23);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Expenses Id");
		lblNewLabel_2.setBounds(95, 351, 89, 14);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblDescription = new JLabel("Description ");
		lblDescription.setBounds(95, 407, 89, 14);
		getContentPane().add(lblDescription);
		
		JLabel lblDates = new JLabel("Date");
		lblDates.setBounds(95, 464, 74, 14);
		getContentPane().add(lblDates);
		
		JLabel lblSalesAndDistribution = new JLabel("Net Epenses");
		lblSalesAndDistribution.setBounds(95, 521, 89, 14);
		getContentPane().add(lblSalesAndDistribution);
		
		JLabel lblAdministrativeExpenses = new JLabel("Types");
		lblAdministrativeExpenses.setBounds(488, 351, 56, 14);
		getContentPane().add(lblAdministrativeExpenses);
		
		t1 = new JTextField();
		t1.setBounds(250, 346, 147, 24);
		getContentPane().add(t1);
		t1.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
 int x = JOptionPane.showConfirmDialog(null,"Do you really want to update");
				 
				 if(x==0) {
					 
					 	String expensId = t1.getText();
						String dscrp = t2.getText();
						Date dat = t3.getDate();
						String NetExpens = t4.getText();
						String typ = t5.getSelectedItem().toString();
						String empid = t6.getText();
						
				
	                
						
						

			Pattern patrn1111 = Pattern.compile("\\d{3}||\\d{4}||\\d{5}");
			Matcher matc1111 = patrn1111.matcher(empid);
            
			 Pattern patrn = Pattern.compile("\\d{3}||\\d{4}||\\d{5}");
			Matcher matc = patrn.matcher(expensId);
            
		
				
			 Pattern patrn11 = Pattern.compile("\\d+.*");
			 Matcher matc11 = patrn11.matcher(NetExpens);
			
			
			 
			
			 
			if(!matc.matches()||expensId.equals("")) {
				JOptionPane.showMessageDialog(null,"Please enter a valid ExpensId");
			}else if(dscrp.equals("")) {
				 JOptionPane.showMessageDialog(null,"Please Enter a valid Description");
			}else if(dat==null) {
				JOptionPane.showMessageDialog(null,"Date field is empty");
			}else if(!matc11.matches()) {
				JOptionPane.showMessageDialog(null,"Please Enter a valid Net Expenses");
			}else if(typ == "Select the Type") {
				JOptionPane.showMessageDialog(null,"Please Select a valid Type");
			}else if(!matc1111.matches()||empid.equals("")) {
				 JOptionPane.showMessageDialog(null,"Please Enter valid Employee Id");
			}
						
						else {
						
						DateFormat format = new SimpleDateFormat("YYYY-MM-dd");
	                    String updat = format.format(dat);
						
						try {
							
							String sql = "Update expenses set Description='"+dscrp+"',EmployeeID='"+empid+"',Date='"+updat+"',NetExpense='"+NetExpens+"',Type='"+typ+"'where ExpenseID='"+expensId+"'";
							
					
							
							PreparedStatement ps = Database.getConnection().prepareStatement(sql);
							ps.executeUpdate();
			            
			            tableLoad();
			                
			              
							
						}catch(Exception e1) {
							
							e1.printStackTrace();
						}
					}
				 }
				
			}
		});
		btnNewButton_1.setBounds(488, 511, 107, 34);
		getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("Employee Id");
		lblNewLabel_3.setBounds(485, 407, 86, 14);
		getContentPane().add(lblNewLabel_3);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int x = JOptionPane.showConfirmDialog(null,"Do you want to delete this");
		        
		        if(x == 0){
		            
		            String expensid = t1.getText();
		            
		            if(expensid.equals("")) {
		            	JOptionPane.showMessageDialog(null,"Please enter the Expense ID");
		            }else {
		            
		            try{
		                
		                String sql = "Delete from expenses where ExpenseID='"+expensid+"'";
		                
		                PreparedStatement ps = Database.getConnection().prepareStatement(sql);
						ps.executeUpdate();
		                
		                tableLoad();
		                
		            	
		            }catch(Exception e1){
		                e1.printStackTrace();
		            }
		        }
		        }
			}
		});
		btnDelete.setBounds(655, 511, 107, 34);
		getContentPane().add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(42, 73, 886, 247);
		getContentPane().add(scrollPane);
		
		jtable1 = new JTable();
		jtable1.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Expense Id", "Description", "Date", "Net Expenses", "Type", "Employee Id"
			}
		));
		jtable1.getColumnModel().getColumn(0).setPreferredWidth(90);
		jtable1.getColumnModel().getColumn(1).setPreferredWidth(90);
		jtable1.getColumnModel().getColumn(2).setPreferredWidth(90);
		jtable1.getColumnModel().getColumn(3).setPreferredWidth(90);
		jtable1.getColumnModel().getColumn(4).setPreferredWidth(90);
		jtable1.getColumnModel().getColumn(5).setPreferredWidth(90);
		jtable1.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int r = jtable1.getSelectedRow();
		        
		        String expensid = jtable1.getValueAt(r, 0).toString();
		        String dscrp = jtable1.getValueAt(r, 1).toString();
		        Date dat =  (Date) jtable1.getValueAt(r, 2);
		        String NetExpens = jtable1.getValueAt(r, 3).toString();
		        String typ = jtable1.getValueAt(r, 4).toString();
		        String empid = jtable1.getValueAt(r, 5).toString();
		        
		        t1.setText(expensid);
		        t2.setText(dscrp);
		        t3.setDate(dat);
		        t4.setText(NetExpens);
		        t5.setSelectedItem(typ);
		        t6.setText(empid);
		     
				
			}
		});
		scrollPane.setViewportView(jtable1);
		tableLoad();
		
		JButton btnReset = new JButton("Reset/Refresh");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				t1.setText(null);
				t2.setText(null);
				t3.setDate(null);
				t4.setText(null);
				t5.setSelectedItem("Select the Type");
				t6.setText(null);
				
				tableLoad();
				
			}
		});
		btnReset.setBounds(821, 511, 107, 34);
		getContentPane().add(btnReset);
		
		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(250, 402, 147, 24);
		getContentPane().add(t2);
		
		t4 = new JTextField();
		t4.setColumns(10);
		t4.setBounds(250, 517, 147, 23);
		getContentPane().add(t4);
		
		t6 = new JTextField();
		t6.setColumns(10);
		t6.setBounds(602, 403, 147, 23);
		getContentPane().add(t6);
		
		

	}
}
