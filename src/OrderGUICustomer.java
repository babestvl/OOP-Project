import javax.swing.*;

import Object.ReadFromMenuFile;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderGUICustomer extends JPanel {
	Restaurant res;
	private JTable table;
	private String name = "", last = "", gender = "", age = "";
	private JLabel labelName, labelAgeGender;
	private String[] nameHead = {"No","Menu Item","Qty","Price/Qty","Price All Qty"};
	private ReadFromMenuFile order;
	private JLabel lblTotalPrice = new JLabel("0");
	private JScrollPane scrollPane;
	private JComboBox comboBox;
	private JButton btnCancel , btnChange ;
	private JPanel panelChange;
	private JLabel lblShippingFee;

	/**
	 * Create the panel.
	 */
	public OrderGUICustomer(Restaurant res) {
		this.res = res;
		order = res.getOrderList();
		setBounds(0, 0, 1280, 800);
		setLayout(null);
		
		labelName = new JLabel("Name : " + name + "   " + last );
		labelName.setFont(new Font("Sukhumvit Set", Font.PLAIN, 25));
		labelName.setBounds(797, 599, 379, 32);
		add(labelName);
		
		labelAgeGender = new JLabel("Gender : " + gender + "     Age : " + age);
		labelAgeGender.setFont(new Font("Sukhumvit Set", Font.PLAIN, 25));
		labelAgeGender.setBounds(797, 635, 379, 32);
		add(labelAgeGender);
		
		panelChange = new JPanel();
		panelChange.setBounds(746, 436, 492, 90);
		panelChange.setOpaque(false);
		panelChange.setVisible(false);
		add(panelChange);
		panelChange.setLayout(null);
		
		JButton btnOkChange = new JButton("");
		btnOkChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				order.change(table.getValueAt(table.getSelectedRow(), 1).toString(), Integer.toString(Integer.parseInt(comboBox.getSelectedItem().toString()) - Integer.parseInt(table.getValueAt(table.getSelectedRow(),2).toString())));
				panelChange.setVisible(false);
				btnCancel.setVisible(true);
				btnChange.setVisible(true);
				table.clearSelection();
				setBasket();
			}
		});
		btnOkChange.setIcon(new ImageIcon(OrderGUI.class.getResource("/Order/changeOK.jpg")));
		btnOkChange.setBounds(420, 0, 72, 45);
		panelChange.add(btnOkChange);
		
		JButton btnCancelChange = new JButton("");
		btnCancelChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelChange.setVisible(false);
				btnCancel.setVisible(true);
				btnChange.setVisible(true);
				table.clearSelection();
			}
		});
		btnCancelChange.setIcon(new ImageIcon(OrderGUI.class.getResource("/Order/changeCancel.jpg")));
		btnCancelChange.setBounds(420, 45, 72, 45);
		panelChange.add(btnCancelChange);
		
		comboBox = new JComboBox();
		comboBox.setBounds(290, 35, 98, 27);
		panelChange.add(comboBox);
		
		
		JLabel bgChange = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("Order/changeBG.jpg")));
		bgChange.setBounds(0, 0, 492, 90);
		panelChange.add(bgChange);

		lblTotalPrice = new JLabel("0");
		lblTotalPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalPrice.setFont(new Font("Sukhumvit Set", Font.PLAIN, 35));
		lblTotalPrice.setForeground(Color.white);
		lblTotalPrice.setBounds(899, 224, 176, 33);
		add(lblTotalPrice);

		lblShippingFee = new JLabel("0");
		lblShippingFee.setFont(new Font("Sukhumvit Set", Font.PLAIN, 35));
		lblShippingFee.setHorizontalAlignment(SwingConstants.CENTER);
		lblShippingFee.setForeground(Color.white);
		lblShippingFee.setBounds(899, 377, 176, 33);
		add(lblShippingFee);
		
		JButton btnBackToShopping = new JButton("");
		btnBackToShopping.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res.setShoppingCustomer();
				panelChange.setVisible(false);
				btnCancel.setVisible(true);
				btnChange.setVisible(true);
				table.clearSelection();
			}
		});
		btnBackToShopping.setIcon(new ImageIcon(OrderGUI.class.getResource("/Order/backOrder.png")));
		btnBackToShopping.setBounds(1138, 43, 118, 63);
		add(btnBackToShopping);
		
		btnCancel = new JButton("");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1)
					showDialogSelect();
				else {
					order.removeOrderList(table.getValueAt(table.getSelectedRow(), 1).toString(), table.getValueAt(table.getSelectedRow(), 2).toString());
					setBasket();
					showDialogRemove();
				}
			}
		});
		btnCancel.setIcon(new ImageIcon(OrderGUI.class.getResource("/Order/cancelSelect.png")));
		btnCancel.setBounds(746, 436, 246, 90);
		btnCancel.setOpaque(false);
		btnCancel.setContentAreaFilled(false);
		btnCancel.setBorderPainted(false);
		btnCancel.setBorder(BorderFactory.createEmptyBorder());
		add(btnCancel);

		btnChange = new JButton("");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1)
					showDialogSelect();
				else {
					btnCancel.setVisible(false);
					btnChange.setVisible(false);
					setNumChange(table.getValueAt(table.getSelectedRow(), 1).toString(),table.getValueAt(table.getSelectedRow(), 2).toString());
					panelChange.setVisible(true);
				}
			}
		});
		btnChange.setIcon(new ImageIcon(OrderGUI.class.getResource("/Order/changeSelect.png")));
		btnChange.setOpaque(false);
		btnChange.setContentAreaFilled(false);
		btnChange.setBorderPainted(false);
		btnChange.setBorder(BorderFactory.createEmptyBorder());
		btnChange.setBounds(992, 436, 246, 90);
		add(btnChange);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(170, 146, 490, 534);
		scrollPane.setOpaque(false);
		add(scrollPane);
		
		String[][] test =new String[0][0];
		table = new JTable(test,nameHead){
			public boolean isCellEditable(int row ,int col){
				return false;
			}
		};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelChange.setVisible(false);
				btnCancel.setVisible(true);
				btnChange.setVisible(true);
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(25);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setPreferredWidth(60);
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.setOpaque(false);
		table.setFont(new Font("Sukhumvit Set", Font.PLAIN, 20));
		table.setRowHeight(table.getRowHeight()+20);
		scrollPane.setViewportView(table);
		
		JLabel background = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("Order/orderBG.jpg")));
		background.setBounds(0, -25, 1280, 800);
		add(background);
	}
	
	public void showDialogRemove(){
		JOptionPane.showMessageDialog(this,  "Remove Selected Order Complete!!", "COMPLETE", JOptionPane.PLAIN_MESSAGE);
	}
	public void showDialogSelect(){
		JOptionPane.showMessageDialog(this,  "Please Select Order!!", "ERROR", JOptionPane.PLAIN_MESSAGE);
	}
	public void printInfo() {
		File info = new File("./src/temp/temp.txt");
		try {
			if (info.exists()) {
				Scanner scan = new Scanner(info);
				scan.next(); scan.next();
				name = scan.next();
				last = scan.next();
				scan.next();
				gender = scan.next();
				age = scan.next();
				scan.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		labelName.setText("Name : " + name + "   " + last );
		labelAgeGender.setText("Gender : " + gender + "     Age : " + age);
	}
	public void setBasket(){
		String[][] setNewBasket = order.getOrderListForBasketAndPayment();
		table = new JTable(setNewBasket,nameHead){
			public boolean isCellEditable(int row ,int col){
				return false;
			}
		};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panelChange.setVisible(false);
				btnCancel.setVisible(true);
				btnChange.setVisible(true);
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(25);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.getColumnModel().getColumn(3).setPreferredWidth(60);
		table.getColumnModel().getColumn(4).setPreferredWidth(60);
		table.getTableHeader().setReorderingAllowed(false);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		table.setOpaque(false);
		table.setFont(new Font("Sukhumvit Set", Font.PLAIN, 20));
		table.setRowHeight(table.getRowHeight()+20);
		lblTotalPrice.setText(order.getTotalPrice());
		lblShippingFee.setText(order.getShippingFee());
		scrollPane.setViewportView(table);
	}
	public void setNumChange(String menuSelect , String qtyCurrent){
		File folder = new File("./src/textMenu/");
		File[] listOfFiles = folder.listFiles();
		ArrayList<String> files = new ArrayList<String>();
		String numStock = "" , menuName = "";
		String[] numSelect = null ;
		for (int i = 0; i < listOfFiles.length; i++) {

			if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith(".txt") ) {
				files.add(listOfFiles[i].getName());
			}
		}
		for (int i = 0; i < files.size(); i++) {
			File file = new File("./src/textMenu/" + files.get(i));
			if (file.exists()) {
				try {
					Scanner scan = new Scanner(new FileInputStream(file));
					while (scan.hasNext()) {
						scan.next();
						scan.next();
						numStock = scan.next();
						scan.next();
						scan.next();
						scan.next();
						scan.next();
						scan.next();
						scan.next();
						menuName = scan.nextLine();
						if (menuName.equals(menuSelect)){
							numSelect = new String [Integer.parseInt(numStock)+Integer.parseInt(qtyCurrent)];
							for (int j = 0 ; j < numSelect.length; j++) {
								numSelect[j] = Integer.toString(j+1);
							}
						}
					}
					scan.close();
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		}
		DefaultComboBoxModel newCombo = new DefaultComboBoxModel(numSelect);
		comboBox.setModel(newCombo);
		
	}
}
