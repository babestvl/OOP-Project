import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.Scanner;

import Object.*;
public class StoreGUI extends JPanel {
	private JTextField txtSdf;
	private Restaurant res;
	private JTable table;
	private String[][] listSearch ;
	private String[] headTable = {"No.","Menu Item","Number of Stock"};
	private String[][] listAllMenu;
	private ReadFromMenuFile file;
	private JLabel labelRevenue = new JLabel("0");
	private JLabel labelExpense = new JLabel("0");
	private JLabel labelProfit = new JLabel("0");
	private JScrollPane scrollPane ;
	/**
	 * Create the panel.
	 */
	public StoreGUI(Restaurant res) {
		this.res = res;

		setBounds(0, 0, 1280, 800);
		setLayout(null);

		JPanel SearchWindow = new JPanel();
		SearchWindow.setBounds(19, 201, 987, 526);
		add(SearchWindow);
		SearchWindow.setLayout(null);
		SearchWindow.setOpaque(false);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 0, 975, 520);
		SearchWindow.add(scrollPane);

		file = new ReadFromMenuFile();
		listAllMenu = file.allMenu();
		String[][] newlistAllMenu = new String[15][3];
		for (int i = 0 ; i < listAllMenu.length ; i++){
			newlistAllMenu[i][0] = (i+1)+"";
			newlistAllMenu[i][1] = listAllMenu[i][3];
			newlistAllMenu[i][2] = listAllMenu[i][0];
		}
		table = new JTable(newlistAllMenu,headTable){
			public boolean isCellEditable(int row ,int col){
				return false;
			}
		};
		table.setFont(new Font("Sukhumvit Set", Font.PLAIN, 20));
		table.setRowHeight(table.getRowHeight()+20);
		table.getTableHeader().setFont(new Font("Sukhumvit Set", Font.BOLD, 20));
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(600);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		scrollPane.setViewportView(table);

		JButton btnSearchStock = new JButton("");
		btnSearchStock.setOpaque(false);
		btnSearchStock.setContentAreaFilled(false);
		btnSearchStock.setBorderPainted(false);
		btnSearchStock.setBorder(BorderFactory.createEmptyBorder());
		btnSearchStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				file = new ReadFromMenuFile(txtSdf.getText());
				listSearch = file.fromFile();
				String[][] newList = new String[listSearch.length][3];
				for (int i = 0 ; i < listSearch.length ; i++){
					newList[i][0] = (i+1)+"";
					newList[i][1] = listSearch[i][3];
					newList[i][2] = listSearch[i][0];
				}
				table = new JTable(newList,headTable){
					public boolean isCellEditable(int row ,int col){
						return false;
					}
				};
				table.setFont(new Font("Sukhumvit Set", Font.PLAIN, 20));
				table.getTableHeader().setFont(new Font("Sukhumvit Set", Font.BOLD, 20));
				table.setRowHeight(table.getRowHeight()+20);
				table.getColumnModel().getColumn(0).setPreferredWidth(50);
				table.getColumnModel().getColumn(1).setPreferredWidth(600);
				table.getColumnModel().getColumn(2).setPreferredWidth(100);
				table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
				table.getTableHeader().setReorderingAllowed(false);
				scrollPane.setViewportView(table);
			}
		});
		btnSearchStock.setIcon(new ImageIcon(StoreGUI.class.getResource("/store/SearchStockBTN.png")));
		btnSearchStock.setBounds(1016, 86, 249, 74);
		add(btnSearchStock);


		JButton btnRestock = new JButton("");
		btnRestock.setOpaque(false);
		btnRestock.setContentAreaFilled(false);
		btnRestock.setBorderPainted(false);
		btnRestock.setBorder(BorderFactory.createEmptyBorder());
		btnRestock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table.getSelectedRow() != -1){
					file.restock(table.getValueAt(table.getSelectedRow(), 1).toString());
					showDialodRestock();
					ReadFromMenuFile fileMenu = new ReadFromMenuFile();
					listAllMenu = fileMenu.allMenu();
					String[][] newlistAllMenu = new String[15][3];
					for (int i = 0 ; i < listAllMenu.length ; i++){
						newlistAllMenu[i][0] = (i+1)+"";
						newlistAllMenu[i][1] = listAllMenu[i][3];
						newlistAllMenu[i][2] = listAllMenu[i][0];
					}
					table = new JTable(newlistAllMenu,headTable){
						public boolean isCellEditable(int row ,int col){
							return false;
						}
					};
					table.setFont(new Font("Sukhumvit Set", Font.PLAIN, 20));
					table.setRowHeight(table.getRowHeight()+20);
					table.getTableHeader().setFont(new Font("Sukhumvit Set", Font.BOLD, 20));
					table.getTableHeader().setReorderingAllowed(false);
					table.getColumnModel().getColumn(0).setPreferredWidth(50);
					table.getColumnModel().getColumn(1).setPreferredWidth(600);
					table.getColumnModel().getColumn(2).setPreferredWidth(100);
					table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
					setMoney();
					scrollPane.setViewportView(table);
				} else {
					showDialodSelected();
				}
			}
		});
		btnRestock.setIcon(new ImageIcon(StoreGUI.class.getResource("/store/StockRestockBTN.png")));
		btnRestock.setBounds(1028, 612, 222, 76);
		add(btnRestock);

		txtSdf = new JTextField();
		txtSdf.setFont(new Font("Sukhumvit Set", Font.PLAIN, 42));
		txtSdf.setBounds(530, 96, 464, 61);
		add(txtSdf);
		txtSdf.setColumns(10);

		JButton btnBack = new JButton("");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res.setMenu();
			}
		});
		btnBack.setIcon(new ImageIcon(StoreGUI.class.getResource("/store/StoreBack.png")));
		btnBack.setBackground(new Color(205, 92, 92));
		btnBack.setFont(new Font("Phosphate", Font.PLAIN, 15));
		btnBack.setBounds(1160, 27, 89, 47);
		add(btnBack);

		labelRevenue = new JLabel("0");
		labelRevenue.setHorizontalAlignment(SwingConstants.CENTER);
		labelRevenue.setFont(new Font("Sukhumvit Set", Font.PLAIN, 30));
		labelRevenue.setBounds(1028, 292, 222, 31);
		add(labelRevenue);


		labelExpense = new JLabel("0");
		labelExpense.setHorizontalAlignment(SwingConstants.CENTER);
		labelExpense.setFont(new Font("Sukhumvit Set", Font.PLAIN, 30));
		labelExpense.setBounds(1028, 414, 222, 27);
		add(labelExpense);

		labelProfit = new JLabel("0");
		labelProfit.setHorizontalAlignment(SwingConstants.CENTER);
		labelProfit.setFont(new Font("Sukhumvit Set", Font.PLAIN, 30));
		labelProfit.setBounds(1028, 541, 222, 27);
		add(labelProfit);

		JButton btnClear = new JButton("");
		btnClear.setIcon(new ImageIcon(StoreGUI.class.getResource("/store/clearSearch.png")));
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				file = new ReadFromMenuFile();
				listAllMenu = file.allMenu();
				String[][] newlistAllMenu = new String[15][3];
				for (int i = 0 ; i < listAllMenu.length ; i++){
					newlistAllMenu[i][0] = (i+1)+"";
					newlistAllMenu[i][1] = listAllMenu[i][3];
					newlistAllMenu[i][2] = listAllMenu[i][0];
				}
				table = new JTable(newlistAllMenu,headTable){
					public boolean isCellEditable(int row ,int col){
						return false;
					}
				};
				table.setFont(new Font("Sukhumvit Set", Font.PLAIN, 20));
				table.setRowHeight(table.getRowHeight()+20);
				table.getTableHeader().setFont(new Font("Sukhumvit Set", Font.BOLD, 20));
				table.getTableHeader().setReorderingAllowed(false);
				table.getColumnModel().getColumn(0).setPreferredWidth(50);
				table.getColumnModel().getColumn(1).setPreferredWidth(600);
				table.getColumnModel().getColumn(2).setPreferredWidth(100);
				table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
				scrollPane.setViewportView(table);
				txtSdf.setText("");
			}
		});
		btnClear.setBounds(1016, 27, 117, 47);
		add(btnClear);

		JLabel imgBG = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("store/storeBG.jpg")));
		imgBG.setBounds(0, -25, 1280, 800);
		add(imgBG);

	}
	public void setMoney(){
		String revenue = "" , expense = "";
		File money = new File("./src/money/money.txt");
		try {
			if (money.exists()) {
				Scanner scan = new Scanner(money);
				revenue = scan.next();
				expense = scan.next();
				scan.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		labelRevenue.setText(revenue);
		labelExpense.setText(expense);
		labelProfit.setText(Integer.toString(Integer.parseInt(revenue)-Integer.parseInt(expense)));
	}
	public void setStore(){
		file = new ReadFromMenuFile();
		listAllMenu = file.allMenu();
		String[][] newlistAllMenu = new String[15][3];
		for (int i = 0 ; i < listAllMenu.length ; i++){
			newlistAllMenu[i][0] = (i+1)+"";
			newlistAllMenu[i][1] = listAllMenu[i][3];
			newlistAllMenu[i][2] = listAllMenu[i][0];
		}
		table = new JTable(newlistAllMenu,headTable){
			public boolean isCellEditable(int row ,int col){
				return false;
			}
		};
		table.setFont(new Font("Sukhumvit Set", Font.PLAIN, 20));
		table.setRowHeight(table.getRowHeight()+20);
		table.getTableHeader().setFont(new Font("Sukhumvit Set", Font.BOLD, 20));
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(600);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table);
		txtSdf.setText("");
	}
	public void showDialodSelected(){
		JOptionPane.showMessageDialog(this,  "Please Select Order to Restock!!", "ERROR", JOptionPane.PLAIN_MESSAGE);
	}
	public void showDialodRestock(){
		JOptionPane.showMessageDialog(this,  "ReStock Complete!!", "COMPLETE", JOptionPane.PLAIN_MESSAGE);
	}
}
