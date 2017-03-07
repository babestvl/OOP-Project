
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Object.ReadFromMenuFile;


public class ShoppingCustomerFood extends JPanel {
	private Restaurant res;
	private ClassLoader loader = this.getClass().getClassLoader();
	private static int numberOfPage = 0;
	private JButton btnNext , btnPrevious;
	private JTable table;
	private String User = "";
	private String Pass = "";
	private String name = "", last = "", ID = "", gender = "", age = "", memberClass = "";
	private JLabel labelName, labelAgeGender;
	private ReadFromMenuFile readMenu; 
	private String[] numSelect;
	private int numAllSelect;
	private JComboBox comboBox;
	private String[] nameHead = {"No","Menu Item","Qty"};
	private JScrollPane scrollPane ;
	private JLabel lblPrice = new JLabel("0");
	private ReadFromMenuFile order;
	

	/**
	 * Create the panel.
	 */
	public ShoppingCustomerFood(Restaurant res) {
		this.res = res;
		order = res.getOrderList();
		setBounds(0, 0, 1280, 800);
		setLayout(null);

		labelName = new JLabel("Name : " + name + "   " + last );
		labelName.setFont(new Font("Sukhumvit Set", Font.PLAIN, 25));
		labelName.setBounds(464, 46, 379, 32);
		add(labelName);

		labelAgeGender = new JLabel("Gender : " + gender + "     Age : " + age);
		labelAgeGender.setFont(new Font("Sukhumvit Set", Font.PLAIN, 25));
		labelAgeGender.setBounds(464, 82, 379, 32);
		add(labelAgeGender);


		JButton btnLogout = new JButton();
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res.setLogin();
				order.getOrderList().clear();
			}
		});

		btnLogout.setBounds(958, 20, 327, 135);
		URL urlLogout = loader.getResource("shopping/LogoutCustomer.png");
		ImageIcon imgLogout = new ImageIcon(urlLogout);
		btnLogout.setOpaque(false);
		btnLogout.setContentAreaFilled(false);
		btnLogout.setBorderPainted(false);
		btnLogout.setBorder(BorderFactory.createEmptyBorder());
		btnLogout.setIcon(imgLogout);
		add(btnLogout);

		JButton btnBasket = new JButton();
		btnBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res.getOrderCustomer().printInfo();
				res.setOrderCustomer();
			}
		});
		btnBasket.setOpaque(false);
		btnBasket.setContentAreaFilled(false);
		btnBasket.setBorderPainted(false);
		btnBasket.setBorder(BorderFactory.createEmptyBorder());
		btnBasket.setIcon(new ImageIcon(Shopping.class.getResource("/shopping/ShoppingBasket.png")));
		btnBasket.setBounds(-4, 528, 327, 97);
		add(btnBasket);

		JButton btnCloseMenu = new JButton("4");
		btnCloseMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res.setShoppingCustomer();
			}
		});
		btnCloseMenu.setBounds(1222, 586, 60, 100);
		btnCloseMenu.setOpaque(false);
		btnCloseMenu.setContentAreaFilled(false);
		btnCloseMenu.setBorderPainted(false);
		btnCloseMenu.setBorder(BorderFactory.createEmptyBorder());
		btnCloseMenu.setIcon(new ImageIcon(Shopping.class.getResource("/shopping/ShoppingCloseIcon.png")));
		add(btnCloseMenu);

		JButton btnDessert = new JButton("2");
		btnDessert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res.setShoppingCustomerDessert();
			}
		});
		btnDessert.setOpaque(false);
		btnDessert.setContentAreaFilled(false);
		btnDessert.setBorderPainted(false);
		btnDessert.setBorder(BorderFactory.createEmptyBorder());
		btnDessert.setIcon(new ImageIcon(Shopping.class.getResource("/shopping/ShoppingDessertIcon.png")));
		btnDessert.setBounds(1222, 348, 60, 100);
		add(btnDessert);

		JButton btnDrink = new JButton("3");
		btnDrink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res.setShoppingCustomerDrink();
			}
		});
		btnDrink.setBounds(1222, 468, 60, 100);
		btnDrink.setOpaque(false);
		btnDrink.setContentAreaFilled(false);
		btnDrink.setBorderPainted(false);
		btnDrink.setBorder(BorderFactory.createEmptyBorder());
		btnDrink.setIcon(new ImageIcon(Shopping.class.getResource("/shopping/ShoppingDrinkIcon.png")));
		add(btnDrink);


		JButton btnShipping = new JButton();
		btnShipping.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res.setPayment();
			}
		});
		btnShipping.setBounds(-4, 637, 327, 97);
		btnShipping.setOpaque(false);
		btnShipping.setContentAreaFilled(false);
		btnShipping.setBorderPainted(false);
		btnShipping.setBorder(BorderFactory.createEmptyBorder());
		btnShipping.setIcon(new ImageIcon(Shopping.class.getResource("/shopping/ShoppingShipping.png")));
		add(btnShipping);

		JLabel lblChoose = new JLabel("Choose number of serving");
		lblChoose.setBounds(804, 525, 261, 27);
		add(lblChoose);
		lblChoose.setFont(new Font("Phosphate", Font.PLAIN, 20));


		JLabel lblTotalPrice = new JLabel("Total Price :");
		lblTotalPrice.setForeground(Color.WHITE);
		lblTotalPrice.setFont(new Font("Phosphate", Font.PLAIN, 20));
		lblTotalPrice.setBounds(42, 468, 137, 16);
		add(lblTotalPrice);

		lblPrice = new JLabel("0");
		lblPrice.setForeground(Color.WHITE);
		lblPrice.setFont(new Font("Phosphate", Font.PLAIN, 20));
		lblPrice.setBounds(164, 468, 137, 16);
		add(lblPrice);
		
		comboBox = new JComboBox();
		comboBox.setBounds(1071, 524, 81, 27);
		add(comboBox);

		JButton btnOrder = new JButton(); //ButtonOrder***********
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!comboBox.getSelectedItem().toString().equals("0")){
					order.order("Food",numberOfPage,comboBox.getSelectedItem().toString());
					setBasket();
					setNumOrder();
				}
				
			}
		});
		btnOrder.setIcon(new ImageIcon(ShoppingFood.class.getResource("/shopping/ShoppingOrderButton.png")));
		btnOrder.setBounds(880, 573, 201, 81);
		btnOrder.setOpaque(false);
		btnOrder.setContentAreaFilled(false);
		btnOrder.setBorderPainted(false);
		btnOrder.setBorder(BorderFactory.createEmptyBorder());
		add(btnOrder);

		JPanel panelFood1 = new JPanel();
		panelFood1.setBackground(Color.WHITE);
		panelFood1.setBounds(387, 184, 789, 497);
		add(panelFood1);
		panelFood1.setLayout(null);
		panelFood1.setOpaque(false);

		JLabel picFood1 = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("food/ceasar.jpg")));
		picFood1.setBounds(49, 0, 347, 497);
		panelFood1.add(picFood1);

		JLabel lblCeasarSalad = new JLabel("Caesar Salad");
		lblCeasarSalad.setFont(new Font("Phosphate", Font.PLAIN, 42));
		lblCeasarSalad.setBounds(456, 6, 265, 98);
		panelFood1.add(lblCeasarSalad);

		JLabel lblRomaineParmesanCheese = new JLabel("<html>Romaine, Parmesan Cheese, Chip, Fresh Black Pepper, Worcestershire sauce Croutons, Anchovy , Grilled Hokkaido Beef</html>");
		lblRomaineParmesanCheese.setFont(new Font("Sukhumvit Set", Font.PLAIN, 18));
		lblRomaineParmesanCheese.setBounds(444, 79, 306, 187);
		panelFood1.add(lblRomaineParmesanCheese);

		JLabel lblPrice1 = new JLabel("- 240 Baht -");
		lblPrice1.setFont(new Font("Phosphate", Font.PLAIN, 35));
		lblPrice1.setBounds(498, 267, 186, 64);
		panelFood1.add(lblPrice1);

		JPanel panelFood2 = new JPanel();
		panelFood2.setBackground(Color.WHITE);
		panelFood2.setBounds(387, 184, 789, 497);
		add(panelFood2);
		panelFood2.setVisible(false);
		panelFood2.setOpaque(false);
		panelFood2.setLayout(null);

		JLabel picFood2 = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("food/spaghettiMeat.jpg")));
		picFood2.setBounds(49, 0, 347, 497);
		panelFood2.add(picFood2);

		JLabel lblSpaghettiMeat = new JLabel("Spaghetti Meat Sauce");
		lblSpaghettiMeat.setFont(new Font("Phosphate", Font.PLAIN, 36));
		lblSpaghettiMeat.setBounds(409, 6, 374, 98);
		panelFood2.add(lblSpaghettiMeat);

		JLabel lblSpaghettiMeatSuggest = new JLabel("<html>Pork Meat with Tomato Sauce, Fresh Black Pepper, Cheese</html>");
		lblSpaghettiMeatSuggest.setFont(new Font("Sukhumvit Set", Font.PLAIN, 18));
		lblSpaghettiMeatSuggest.setBounds(444, 79, 306, 187);
		panelFood2.add(lblSpaghettiMeatSuggest);

		JLabel lblPrice2 = new JLabel("- 260 Baht -");
		lblPrice2.setFont(new Font("Phosphate", Font.PLAIN, 35));
		lblPrice2.setBounds(498, 267, 186, 64);
		panelFood2.add(lblPrice2);

		JPanel panelFood3 = new JPanel();
		panelFood3.setBackground(Color.WHITE);
		panelFood3.setBounds(387, 184, 789, 497);
		add(panelFood3);
		panelFood3.setVisible(false);
		panelFood3.setOpaque(false);
		panelFood3.setLayout(null);

		JLabel picFood3 = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("food/TMBPizza.jpg")));
		picFood3.setBounds(49, 0, 347, 497);
		panelFood3.add(picFood3);

		JLabel lblPizza = new JLabel("TMB Pizza");
		lblPizza.setFont(new Font("Phosphate", Font.PLAIN, 42));
		lblPizza.setBounds(489, 6, 265, 98);
		panelFood3.add(lblPizza);

		JLabel lblPizzaSuggest = new JLabel("<html>TMB Tomato Sauce, Cheese, Fresh Black Pepper, Mushroom, Minced Pork, Black Grapes, Mint</html>");
		lblPizzaSuggest.setFont(new Font("Sukhumvit Set", Font.PLAIN, 18));
		lblPizzaSuggest.setBounds(444, 79, 306, 187);
		panelFood3.add(lblPizzaSuggest);

		JLabel lblPrice3 = new JLabel("- 290 Baht -");
		lblPrice3.setFont(new Font("Phosphate", Font.PLAIN, 35));
		lblPrice3.setBounds(498, 267, 186, 64);
		panelFood3.add(lblPrice3);

		JPanel panelFood4 = new JPanel();
		panelFood4.setBackground(Color.WHITE);
		panelFood4.setBounds(387, 184, 789, 497);
		add(panelFood4);
		panelFood4.setVisible(false);
		panelFood4.setOpaque(false);
		panelFood4.setLayout(null);

		JLabel picFood4 = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("food/mushroomSoup.jpg")));
		picFood4.setBounds(49, 0, 347, 497);
		panelFood4.add(picFood4);

		JLabel lblMushroom = new JLabel("Mushroom Soup");
		lblMushroom.setFont(new Font("Phosphate", Font.PLAIN, 42));
		lblMushroom.setBounds(425, 6, 329, 98);
		panelFood4.add(lblMushroom);

		JLabel lblMushroomSuggest = new JLabel("<html>Champignon Mushroom, Fresh Black Pepper, Cream Soup</html>");
		lblMushroomSuggest.setFont(new Font("Sukhumvit Set", Font.PLAIN, 18));
		lblMushroomSuggest.setBounds(444, 79, 306, 187);
		panelFood4.add(lblMushroomSuggest);

		JLabel lblPrice4 = new JLabel("- 130 Baht -");
		lblPrice4.setFont(new Font("Phosphate", Font.PLAIN, 35));
		lblPrice4.setBounds(498, 267, 186, 64);
		panelFood4.add(lblPrice4);

		JPanel panelFood5 = new JPanel();
		panelFood5.setBackground(Color.WHITE);
		panelFood5.setBounds(387, 184, 789, 497);
		add(panelFood5);
		panelFood5.setVisible(false);
		panelFood5.setOpaque(false);
		panelFood5.setLayout(null);

		JLabel picFood5 = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("food/Steak.jpg")));
		picFood5.setBounds(49, 0, 347, 497);
		panelFood5.add(picFood5);

		JLabel lblSteak = new JLabel("Dugong Steak with Shrimp");
		lblSteak.setFont(new Font("Phosphate", Font.PLAIN, 30));
		lblSteak.setBounds(399, 6, 384, 98);
		panelFood5.add(lblSteak);

		JLabel lblSteakSuggest = new JLabel("<html>Dugong Steak Grilled with Fresh Black Pepper  serve with Shrimp Chili and Sesame Oil</html>");
		lblSteakSuggest.setFont(new Font("Sukhumvit Set", Font.PLAIN, 18));
		lblSteakSuggest.setBounds(444, 79, 306, 187);
		panelFood5.add(lblSteakSuggest);

		JLabel lblPrice5 = new JLabel("- 390 Baht -");
		lblPrice5.setFont(new Font("Phosphate", Font.PLAIN, 35));
		lblPrice5.setBounds(498, 267, 186, 64);
		panelFood5.add(lblPrice5);

		JPanel[] allPageFood = {panelFood1,panelFood2,panelFood3,panelFood4,panelFood5};

		btnPrevious = new JButton("<");
		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				allPageFood[numberOfPage].setVisible(false);
				numberOfPage--;
				allPageFood[numberOfPage].setVisible(true);
				btnNext.setVisible(true);
				if (numberOfPage == 4 )
					btnNext.setVisible(false);
				else if (numberOfPage == 0)
					btnPrevious.setVisible(false);
				
				setNumOrder();
				

			}
		});
		btnPrevious.setBounds(393, 401, 37, 47);
		btnPrevious.setVisible(false);
		add(btnPrevious);

		btnNext = new JButton(">");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				allPageFood[numberOfPage].setVisible(false);
				numberOfPage++;
				allPageFood[numberOfPage].setVisible(true);
				if (numberOfPage > 0 )
					btnPrevious.setVisible(true);
				if (numberOfPage == 4)
					btnNext.setVisible(false);
				
				setNumOrder();
				
			}
		});
		btnNext.setBounds(1136, 401, 37, 47);
		add(btnNext);

		JLabel lblTmbRestaurant = new JLabel("TMB Restaurant");
		lblTmbRestaurant.setFont(new Font("Phosphate", Font.PLAIN, 23));
		lblTmbRestaurant.setBounds(74, 87, 187, 27);
		add(lblTmbRestaurant);

		scrollPane = new JScrollPane(); //********* table
		scrollPane.setBounds(59, 119, 220, 320);
		add(scrollPane);

		String[][] test =  new String[0][0];
		table = new JTable(test,nameHead);
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(23);
		table.getColumnModel().getColumn(1).setPreferredWidth(167);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.setEnabled(false);
		scrollPane.setViewportView(table);


		JLabel background = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("shopping/ShoppingBGFood.jpg")));
		background.setBounds(0, -25, 1280, 800);
		add(background);
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
		String[][] setNewBasket = order.getOrderListForShopping();
		table = new JTable(setNewBasket,nameHead);
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(23);
		table.getColumnModel().getColumn(1).setPreferredWidth(167);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.setEnabled(false);
		lblPrice.setText(order.getTotalPrice());
		scrollPane.setViewportView(table);
	}
	public void setNumOrder(){
		try {
			Scanner scan = new Scanner(new File("src/textMenu/Food.txt"));
			for (int i = 0; i < numberOfPage; i++) {
				scan.nextLine();
			}
			scan.next();
			scan.next();
			numAllSelect = Integer.parseInt(scan.next());
			numSelect = new String [numAllSelect+1];
			for (int j = 0 ; j <= numAllSelect; j++) {
				numSelect[j] = Integer.toString(j);
			}
			scan.close();
		}catch(Exception e1) {
			e1.printStackTrace();
		}
		DefaultComboBoxModel newCombo = new DefaultComboBoxModel(numSelect);
		comboBox.setModel(newCombo);
	}
}
