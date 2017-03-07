import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.URL;
import java.util.ArrayList;
import Object.*;

public class CustomerSearch extends JPanel {

	private JTextField TextUsername;
	private JTextField TextFirstName;
	private JTextField TextLastName;
	private JTextField textClass;
	private JTextField textPersonID;
	private JButton btnA1;
	private JButton btSearch;
	private Restaurant res;
	private JComboBox ComboGender;
	private String[] gender = {"-Select-","Male","Female"};
	private ArrayList<String> info = new ArrayList<String>();
	private JPanel SearchWindow;
	private JList<String> list_1;
	private JButton btnCancel;
	private JPanel ShowHistory;
	private JScrollPane scrollPaneSearch;
	private JTable table;
	private String[][] historyList;
	private String[] headHistory = {"No.","Menu Name","Qty"};
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public CustomerSearch(Restaurant res) {
		this.res = res;
		setBounds(0, 0, 1280, 800);
		setLayout(null);

		JPanel WindowSearch = new JPanel();
		WindowSearch.setBounds(372, 164, 488, 353);
		WindowSearch.setLayout(null);
		WindowSearch.setOpaque(false);
		add(WindowSearch);
		WindowSearch.setVisible(false);

		scrollPaneSearch = new JScrollPane();
		scrollPaneSearch.setBounds(33, 0, 455, 301);
		WindowSearch.add(scrollPaneSearch);
		
		
		table = new JTable();
		scrollPaneSearch.setViewportView(table);

		JButton btnCloseHistory = new JButton("");
		btnCloseHistory.setIcon(new ImageIcon(CustomerSearch.class.getResource("/customerIMG/CloseHistory.png")));
		btnCloseHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WindowSearch.setVisible(false);
			}
		});
		btnCloseHistory.setBounds(0, 0, 33, 353);
		btnCloseHistory.setOpaque(false);
		btnCloseHistory.setContentAreaFilled(false);
		btnCloseHistory.setBorderPainted(false);
		btnCloseHistory.setBorder(BorderFactory.createEmptyBorder());
		WindowSearch.add(btnCloseHistory);
		
		JLabel lalTotalHis = new JLabel("0");
		lalTotalHis.setFont(new Font("Sukhumvit Set", Font.PLAIN, 30));
		lalTotalHis.setBounds(217, 313, 240, 34);
		lalTotalHis.setForeground(Color.white);
		WindowSearch.add(lalTotalHis);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(CustomerSearch.class.getResource("/customerIMG/totalPriceHis.jpg")));
		lblNewLabel.setBounds(33, 299, 455, 54);
		WindowSearch.add(lblNewLabel);

		TextUsername = new JTextField("");
		TextUsername.setBounds(385, 188, 419, 47);
		TextUsername.setFont(new Font("Sukhumvit Set", Font.PLAIN, 25));
		add(TextUsername);
		TextUsername.setColumns(10);

		TextFirstName = new JTextField("");
		TextFirstName.setBounds(385, 268, 419, 47);
		TextFirstName.setFont(new Font("Sukhumvit Set", Font.PLAIN, 25));
		add(TextFirstName);
		TextFirstName.setColumns(10);

		TextLastName = new JTextField("");
		TextLastName.setBounds(385, 353, 419, 47);
		TextLastName.setFont(new Font("Sukhumvit Set", Font.PLAIN, 25));
		add(TextLastName);
		TextLastName.setColumns(10);

		btSearch = new JButton("");
		btSearch.setBounds(937, 600, 277, 99);
		ClassLoader loaderS = this.getClass().getClassLoader();
		URL urlS = loaderS.getResource("customerIMG/search.png");
		ImageIcon imgS = new ImageIcon(urlS);
		btSearch.setOpaque(false);
		btSearch.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileSearchAndRemove search = new FileSearchAndRemove(TextUsername.getText(), TextFirstName.getText(), TextLastName.getText(), textPersonID.getText(), gender[ComboGender.getSelectedIndex()], textClass.getText());
				info = search.printInfo();
				DefaultListModel<String> list = new DefaultListModel<String>();
				for (int i = 0 ; i < info.size() ; i++){
					list.addElement(info.get(i));
				}
				list_1.setModel(list);
				SearchWindow.setVisible(true);
			}
		});

		textPersonID = new JTextField("");
		textPersonID.setBounds(385, 436, 419, 47);
		textPersonID.setFont(new Font("Sukhumvit Set", Font.PLAIN, 25));
		textPersonID.setColumns(10);
		add(textPersonID);

		btSearch.setContentAreaFilled(false);
		btSearch.setBorderPainted(false);
		btSearch.setBorder(BorderFactory.createEmptyBorder());
		btSearch.setIcon(imgS);
		add(btSearch);

		textClass = new JTextField("");
		textClass.setBounds(385, 602, 164, 47);
		textClass.setFont(new Font("Sukhumvit Set", Font.PLAIN, 25));
		add(textClass);

		btnA1 = new JButton();
		btnA1.setBounds(255, 37, 218, 81);
		btnA1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res.setAdd();
				SearchWindow.setVisible(false);
				WindowSearch.setVisible(false);
				ComboGender.setSelectedIndex(0);
			}
		});
		btnA1.setOpaque(false);
		btnA1.setContentAreaFilled(false);
		btnA1.setBorderPainted(false);
		btnA1.setBorder(BorderFactory.createEmptyBorder());
		btnA1.setIcon(new ImageIcon(CustomerSearch.class.getResource("/customerIMG/A.png")));
		add(btnA1);

		JButton btnBack = new JButton();
		btnBack.setBounds(1150, 47, 94, 85);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res.setMenu();
				SearchWindow.setVisible(false);
				WindowSearch.setVisible(false);
			}
		});
		btnBack.setIcon(new ImageIcon(CustomerSearch.class.getResource("/customerIMG/btnBack.png")));
		btnBack.setOpaque(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.setBorder(BorderFactory.createEmptyBorder());
		btnBack.setFont(new Font("Phosphate", Font.PLAIN, 16));
		add(btnBack);


		ComboGender = new JComboBox(gender);
		ComboGender.setBounds(385, 521, 109, 50);
		add(ComboGender);

		SearchWindow = new JPanel();
		SearchWindow.setBounds(854, 159, 355, 412);
		SearchWindow.setOpaque(false);
		add(SearchWindow);
		SearchWindow.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 343, 305);
		SearchWindow.add(scrollPane);

		list_1 = new JList();  //NameAll
		list_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				WindowSearch.setVisible(false);
			}
		});
		scrollPane.setViewportView(list_1);

		JButton btnRemoveAccountSelected = new JButton("");    //Remove
		btnRemoveAccountSelected.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list_1.getSelectedIndex() == -1)
					showDialogUnselect();
				else {
					WindowSearch.setVisible(false);
//					System.out.println(list_1.getSelectedValue());    //check by me
					String getnameCus = list_1.getSelectedValue().substring(0, list_1.getSelectedValue().indexOf(" "));
//					System.out.println(getnameCus);
//					FileSearchAndRemove remove = new FileSearchAndRemove (list_1.getSelectedIndex());
					FileSearchAndRemove remove = new FileSearchAndRemove (getnameCus);
					remove.removeUser();
					FileSearchAndRemove search = new FileSearchAndRemove(TextUsername.getText(), TextFirstName.getText(), TextLastName.getText(), textPersonID.getText(), gender[ComboGender.getSelectedIndex()], textClass.getText());
					info = search.printInfo();
					DefaultListModel<String> list = new DefaultListModel<String>();
					for (int i = 0 ; i < info.size() ; i++){
						list.addElement(info.get(i));
					}
					list_1.setModel(list);
				}
			}
		});
		btnRemoveAccountSelected.setIcon(new ImageIcon(CustomerSearch.class.getResource("/customerIMG/CustomerRemove.png")));
		btnRemoveAccountSelected.setBounds(7, 355, 291, 51);
		SearchWindow.add(btnRemoveAccountSelected);

		btnCancel = new JButton("");
		btnCancel.setIcon(new ImageIcon(CustomerSearch.class.getResource("/customerIMG/CustomerCloseSearch.png")));
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchWindow.setVisible(false);
				WindowSearch.setVisible(false);
				ComboGender.setSelectedIndex(0);
			}
		});
		btnCancel.setBounds(298, 355, 51, 51);
		SearchWindow.add(btnCancel);

		JButton btnShowHistory = new JButton("");
		btnShowHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list_1.getSelectedIndex() == -1)
					showDialogUnShowHis();
				else {
					String getnameCus = list_1.getSelectedValue().substring(0, list_1.getSelectedValue().indexOf(" "));
					HistoryList showHis = new HistoryList (getnameCus);
					historyList= showHis.getHistoryList();
					String[][] newHistoryList = new String[historyList.length][3];
					for (int i = 0 ; i < historyList.length ; i++){
						newHistoryList[i][0] = (i+1)+"";
						newHistoryList[i][1] = historyList[i][1];
						newHistoryList[i][2] = historyList[i][0];
					}
					table = new JTable(newHistoryList,headHistory){
						public boolean isCellEditable(int row ,int col){
							return false;
						}
					};
					table.getColumnModel().getColumn(0).setPreferredWidth(40);
					table.getColumnModel().getColumn(1).setPreferredWidth(350);
					table.getColumnModel().getColumn(2).setPreferredWidth(90);
					table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
					scrollPaneSearch.setViewportView(table);
					lalTotalHis.setText(showHis.getTotalPrice());
					WindowSearch.setVisible(true);
				}
			}
		});
		btnShowHistory.setIcon(new ImageIcon(CustomerSearch.class.getResource("/customerIMG/customerShowHistory.png")));
		btnShowHistory.setBounds(6, 308, 343, 51);
		SearchWindow.add(btnShowHistory);

		SearchWindow.setVisible(false);

		JLabel imgBG = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("customerIMG/imgSearch.jpg")));
		imgBG.setBounds(0, -25, 1280, 800);
		add(imgBG);

	}
	public void showDialogUnselect(){
		JOptionPane.showMessageDialog(this,  "Please Select Account You want to remove.", "ERROR", JOptionPane.PLAIN_MESSAGE);
	}
	public void showDialogUnShowHis(){
		JOptionPane.showMessageDialog(this,  "Please Select Account You want to show history.", "ERROR", JOptionPane.PLAIN_MESSAGE);
	}
}