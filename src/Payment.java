import javax.swing.*;

import Object.ReadFromMenuFile;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Payment extends JPanel {
	Restaurant res;
	private JButton btnRemove, btnChange ;

	private String[] nameHead = {"No","Menu Item","Qty","Price/Qty","Price All Qty"};
	private JScrollPane scrollPane;
	private ReadFromMenuFile order;
	private JTable table;
	private JLabel lblTotalPriceOrder ;
	private JLabel lblShipping;
	private JComboBox comboBox ;
	private JButton btnCancel;
	private JPanel panelChange ;
	private JLabel lblTotal;

	/**
	 * Create the panel.
	 */
	public Payment(Restaurant res) {
		this.res = res;
		order = res.getOrderList();
		setBounds(0, 0, 1280, 750);
		setLayout(null);

		panelChange = new JPanel();
		panelChange.setBounds(0, 96, 767, 94);
		add(panelChange);
		panelChange.setVisible(false);
		panelChange.setOpaque(false);
		panelChange.setLayout(null);

		btnCancel = new JButton("");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelChange.setVisible(false);
				btnRemove.setVisible(true);
				btnChange.setVisible(true);
				table.clearSelection();
			}
		});
		btnCancel.setBounds(662, 0, 105, 94);
		panelChange.add(btnCancel);
		btnCancel.setOpaque(false);
		btnCancel.setContentAreaFilled(false);
		btnCancel.setBorderPainted(false);
		btnCancel.setBorder(BorderFactory.createEmptyBorder());
		btnCancel.setIcon(new ImageIcon(Payment.class.getResource("/Payment/changeCancelPayment.png")));

		JButton btnOk = new JButton("");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				order.change(table.getValueAt(table.getSelectedRow(), 1).toString(), Integer.toString(Integer.parseInt(comboBox.getSelectedItem().toString()) - Integer.parseInt(table.getValueAt(table.getSelectedRow(),2).toString())));
				panelChange.setVisible(false);
				btnChange.setVisible(true);
				btnRemove.setVisible(true);
				table.clearSelection();
				setBasket();
				panelChange.setVisible(false);
			}
		});
		btnOk.setIcon(new ImageIcon(Payment.class.getResource("/Payment/changeOkPayment.png")));
		btnOk.setBounds(557, 0, 105, 94);
		panelChange.add(btnOk);

		comboBox = new JComboBox();
		comboBox.setBounds(339, 37, 121, 27);
		panelChange.add(comboBox);

		JLabel bgChange = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("Payment/changeQtyPayment.png")));
		bgChange.setBounds(0, 0, 767, 94);
		panelChange.add(bgChange);

		JButton btnPay = new JButton();
		btnPay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showDialog();
				comfirmPayment();
				res.setLogin();
			}
		});
		btnPay.setBounds(847, 458, 412, 292);
		btnPay.setOpaque(false);
		btnPay.setContentAreaFilled(false);
		btnPay.setBorderPainted(false);
		btnPay.setBorder(BorderFactory.createEmptyBorder());
		btnPay.setIcon(new ImageIcon(Payment.class.getResource("Payment/PayButton.png")));
		add(btnPay);

		btnRemove = new JButton();
		btnRemove.addActionListener(new ActionListener() {
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
		btnRemove.setOpaque(false);
		btnRemove.setContentAreaFilled(false);
		btnRemove.setBorderPainted(false);
		btnRemove.setBorder(BorderFactory.createEmptyBorder());
		btnRemove.setIcon(new ImageIcon(Payment.class.getResource("/Payment/PaymentRemove.png")));
		btnRemove.setBounds(651, 249, 116, 192);
		add(btnRemove);

		btnChange = new JButton();
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() == -1)
					showDialogSelect();
				else {
					btnRemove.setVisible(false);
					btnChange.setVisible(false);
					setNumChange(table.getValueAt(table.getSelectedRow(), 1).toString(),table.getValueAt(table.getSelectedRow(), 2).toString());
					panelChange.setVisible(true);
				}
			}
		});
		btnChange.setIcon(new ImageIcon(Payment.class.getResource("/Payment/PaymentChange.png")));
		btnChange.setOpaque(false);
		btnChange.setContentAreaFilled(false);
		btnChange.setBorderPainted(false);
		btnChange.setBorder(BorderFactory.createEmptyBorder());
		btnChange.setBounds(651, 440, 116, 192);
		add(btnChange);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(85, 270, 566, 344);
		add(scrollPane);

		String[][] list = new String[0][0];
		table = new JTable(list,nameHead);
		scrollPane.setViewportView(table);

		lblTotalPriceOrder = new JLabel("0");
		lblTotalPriceOrder.setFont(new Font("Sukhumvit Set", Font.PLAIN, 25));
		lblTotalPriceOrder.setBounds(1068, 291, 160, 46);
		lblTotalPriceOrder.setForeground(Color.white);
		add(lblTotalPriceOrder);

		JLabel lblTotalPriceOrderText = new JLabel("Total Price Order :");
		lblTotalPriceOrderText.setBounds(871, 291, 216, 46);
		lblTotalPriceOrderText.setFont(new Font("Sukhumvit Set", Font.PLAIN, 25));
		lblTotalPriceOrderText.setForeground(Color.white);
		add(lblTotalPriceOrderText);

		JLabel lblShippingText = new JLabel("Shipping fee :");
		lblShippingText.setForeground(Color.WHITE);
		lblShippingText.setFont(new Font("Sukhumvit Set", Font.PLAIN, 25));
		lblShippingText.setBounds(871, 349, 160, 46);
		add(lblShippingText);

		lblShipping = new JLabel("0");
		lblShipping.setForeground(Color.WHITE);
		lblShipping.setFont(new Font("Sukhumvit Set", Font.PLAIN, 25));
		lblShipping.setBounds(1030, 349, 160, 46);
		add(lblShipping);

		JLabel lblTotalPaid = new JLabel("Total Paid");
		lblTotalPaid.setFont(new Font("Phosphate", Font.PLAIN, 30));
		lblTotalPaid.setForeground(Color.WHITE);
		lblTotalPaid.setBounds(1045, 52, 160, 46);
		add(lblTotalPaid);

		lblTotal = new JLabel("0");
		lblTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotal.setForeground(Color.WHITE);
		lblTotal.setFont(new Font("Sukhumvit Set", Font.PLAIN, 35));
		lblTotal.setBounds(1030, 96, 198, 46);
		add(lblTotal);

		JLabel background = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("Payment/PaymentBG.jpg")));
		background.setBounds(0, 0, 1280, 750);
		add(background);


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
		lblTotalPriceOrder.setText(order.getTotalPrice());
		lblShipping.setText(order.getShippingFee());
		lblTotal.setText(Integer.toString(Integer.parseInt(order.getTotalPrice())+Integer.parseInt(order.getShippingFee())));
		scrollPane.setViewportView(table);
	}

	public void showDialog() {
		JOptionPane.showMessageDialog(this, "Thank you for using our service, please come back again. =^^= ", "TMB Restaurant", JOptionPane.PLAIN_MESSAGE);
	}
	public void showDialogRemove(){
		JOptionPane.showMessageDialog(this,  "Remove Selected Order Complete!!", "COMPLETE", JOptionPane.PLAIN_MESSAGE);
	}
	public void showDialogSelect(){
		JOptionPane.showMessageDialog(this,  "Please Select Order!!", "ERROR", JOptionPane.PLAIN_MESSAGE);
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
	public void comfirmPayment(){
		ArrayList<String> keepOrderHistory = new ArrayList<String>();
		String keepInformation = "", totalPrice = "0" , username = "";
		File info = new File("./src/temp/temp.txt");
		try {
			if (info.exists()) {
				Scanner scan = new Scanner(info);
				username = scan.next();
				scan.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		File printHistory = new File("./src/user/" + (username) + ".txt");
		try {
			if (printHistory.exists()) {
				Scanner scan = new Scanner(printHistory);
				keepInformation = scan.nextLine();
				totalPrice = scan.nextLine();
				while(scan.hasNextLine()){
					keepOrderHistory.add(scan.nextLine());
				}
				scan.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		totalPrice = Integer.toString(Integer.parseInt(totalPrice) + Integer.parseInt(lblTotal.getText()));
		ArrayList<String> orderList = order.getOrderList();
		String[][] newOrderList = new String[orderList.size()][2];
		for (int i = 0 ; i < orderList.size() ; i++){
			newOrderList[i] = orderList.get(i).split("@");
			keepOrderHistory.add(String.format("%s%s", newOrderList[i][2],newOrderList[i][0]));
		}
		
		if (!username.equals("admin")){
			printHistory.delete();
			PrintStream toFile = null;
			if (!printHistory.exists()){
				printHistory.getParentFile().mkdirs();
				try {
					printHistory.createNewFile();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}

				try {
					toFile = new PrintStream(new FileOutputStream(printHistory, true));
				} catch (FileNotFoundException fnfe) {
					fnfe.printStackTrace();
				}
				toFile.println(keepInformation);
				toFile.println(totalPrice);
				for (int k = 0 ; k < keepOrderHistory.size() ; k++){
					toFile.println(keepOrderHistory.get(k));
				}
				toFile.close();
			}
		}
		String revenue = "" , expense = "";
		File scanMoney = new File ("./src/money/money.txt");
		if (scanMoney.exists()){
			try{
				Scanner scan = new Scanner(scanMoney);
				revenue = scan.next();
				expense = scan.next();
				scan.close();
			} catch (Exception e){
				e.printStackTrace();
			}
		}
		scanMoney.delete();
		revenue = Integer.toString(Integer.parseInt(revenue) + Integer.parseInt(lblTotal.getText()));
		PrintStream toFile = null;
		File addMoney = new File ("./src/money/money.txt");
		if (!addMoney.exists()){
			addMoney.getParentFile().mkdirs();
			try {
				addMoney.createNewFile();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}

			try {
				toFile = new PrintStream(new FileOutputStream(addMoney, true));
			} catch (FileNotFoundException fnfe) {
				fnfe.printStackTrace();
			}
			toFile.println(revenue + " ");
			toFile.println(expense);
			toFile.close();
		}
		order.getOrderList().clear();
	}
}
