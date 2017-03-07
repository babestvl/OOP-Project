
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


public class ShoppingDrink extends JPanel {
	private Restaurant res;
	private ClassLoader loader = this.getClass().getClassLoader();
	private static int numberOfPage = 0;
	private JButton btnNext , btnPrevious;
	private JTable table;
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
	public ShoppingDrink(Restaurant res) {
		this.res = res;
		order = res.getOrderList();
		setBounds(0, 0, 1280, 800);
		setLayout(null);

		JLabel lblAdmin = new JLabel("Admin");
		lblAdmin.setFont(new Font("Phosphate", Font.PLAIN, 60));
		lblAdmin.setBounds(547, 44, 205, 70);
		add(lblAdmin);

		JButton btnBack = new JButton();
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res.setMenu();
			}
		});
		btnBack.setBounds(958, 20, 327, 135);
		URL urlBack = loader.getResource("shopping/ShoppingBack.png");
		ImageIcon imgBack = new ImageIcon(urlBack);
		btnBack.setOpaque(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.setBorder(BorderFactory.createEmptyBorder());
		btnBack.setIcon(imgBack);
		add(btnBack);

		JButton btnBasket = new JButton();
		btnBasket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res.setOrder();
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
				res.setShopping();
			}
		});
		btnCloseMenu.setBounds(1222, 586, 60, 100);
		btnCloseMenu.setOpaque(false);
		btnCloseMenu.setContentAreaFilled(false);
		btnCloseMenu.setBorderPainted(false);
		btnCloseMenu.setBorder(BorderFactory.createEmptyBorder());
		btnCloseMenu.setIcon(new ImageIcon(Shopping.class.getResource("/shopping/ShoppingCloseIcon.png")));
		add(btnCloseMenu);

		JButton btnFood = new JButton("1");
		btnFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res.setShoppingFood();
			}
		});
		URL urlFood = loader.getResource("shopping/ShoppingFoodIcon.png");
		ImageIcon imgFood = new ImageIcon(urlFood);
		btnFood.setOpaque(false);
		btnFood.setContentAreaFilled(false);
		btnFood.setBorderPainted(false);
		btnFood.setBorder(BorderFactory.createEmptyBorder());
		btnFood.setIcon(imgFood);
		btnFood.setBounds(1222, 230, 60, 100);
		add(btnFood);

		JButton btnDessert = new JButton("2");
		btnDessert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res.setShoppingDessert();
			}
		});
		btnDessert.setOpaque(false);
		btnDessert.setContentAreaFilled(false);
		btnDessert.setBorderPainted(false);
		btnDessert.setBorder(BorderFactory.createEmptyBorder());
		btnDessert.setIcon(new ImageIcon(Shopping.class.getResource("/shopping/ShoppingDessertIcon.png")));
		btnDessert.setBounds(1222, 348, 60, 100);
		add(btnDessert);

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

		JButton btnOrder = new JButton();
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!comboBox.getSelectedItem().toString().equals("0")){
					order.order("Drink",numberOfPage,comboBox.getSelectedItem().toString());
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

		JPanel panelDrink1 = new JPanel();
		panelDrink1.setBackground(Color.WHITE);
		panelDrink1.setBounds(387, 184, 789, 497);
		add(panelDrink1);
		panelDrink1.setLayout(null);
		panelDrink1.setOpaque(false);

		JLabel picDrink1 = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("drink/CreamyBear.jpg")));
		picDrink1.setBounds(49, 0, 347, 497);
		panelDrink1.add(picDrink1);

		JLabel lblLatte = new JLabel("Latte Milk Creamy Bear");
		lblLatte.setFont(new Font("Phosphate", Font.PLAIN, 35));
		lblLatte.setBounds(397, 6, 386, 98);
		panelDrink1.add(lblLatte);

		JLabel lblLatteSuggest = new JLabel("<html>Latte with Bear face Creamy Milk</html>");
		lblLatteSuggest.setFont(new Font("Sukhumvit Set", Font.PLAIN, 18));
		lblLatteSuggest.setBounds(444, 79, 306, 187);
		panelDrink1.add(lblLatteSuggest);

		JLabel lblPrice1 = new JLabel("- 95 Baht -");
		lblPrice1.setFont(new Font("Phosphate", Font.PLAIN, 35));
		lblPrice1.setBounds(498, 267, 186, 64);
		panelDrink1.add(lblPrice1);

		JPanel panelDrink2 = new JPanel();
		panelDrink2.setBackground(Color.WHITE);
		panelDrink2.setBounds(387, 184, 789, 497);
		add(panelDrink2);
		panelDrink2.setVisible(false);
		panelDrink2.setOpaque(false);
		panelDrink2.setLayout(null);

		JLabel picDrink2 = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("drink/Lemon.jpg")));
		picDrink2.setBounds(49, 0, 347, 497);
		panelDrink2.add(picDrink2);

		JLabel lblLemon = new JLabel("Lemon Soda Drink");
		lblLemon.setFont(new Font("Phosphate", Font.PLAIN, 36));
		lblLemon.setBounds(431, 6, 374, 98);
		panelDrink2.add(lblLemon);

		JLabel lblLemonSuggest = new JLabel("<html>Japanese Lemon with Soda and Mint</html>");
		lblLemonSuggest.setFont(new Font("Sukhumvit Set", Font.PLAIN, 18));
		lblLemonSuggest.setBounds(444, 79, 306, 187);
		panelDrink2.add(lblLemonSuggest);

		JLabel lblPrice2 = new JLabel("- 120 Baht -");
		lblPrice2.setFont(new Font("Phosphate", Font.PLAIN, 35));
		lblPrice2.setBounds(498, 267, 186, 64);
		panelDrink2.add(lblPrice2);

		JPanel panelDrink3 = new JPanel();
		panelDrink3.setBackground(Color.WHITE);
		panelDrink3.setBounds(387, 184, 789, 497);
		add(panelDrink3);
		panelDrink3.setVisible(false);
		panelDrink3.setOpaque(false);
		panelDrink3.setLayout(null);

		JLabel picDrink3 = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("drink/punch.jpg")));
		picDrink3.setBounds(49, 0, 347, 497);
		panelDrink3.add(picDrink3);

		JLabel lblPunch = new JLabel("Punch with Lemon");
		lblPunch.setFont(new Font("Phosphate", Font.PLAIN, 35));
		lblPunch.setBounds(424, 6, 375, 98);
		panelDrink3.add(lblPunch);

		JLabel lblOrange = new JLabel("& Orange");
		lblOrange.setFont(new Font("Phosphate", Font.PLAIN, 35));
		lblOrange.setBounds(506, 76, 243, 62);
		panelDrink3.add(lblOrange);

		JLabel lblPunchSuggest = new JLabel("<html>Mixed Fruits with Rum</html>");
		lblPunchSuggest.setFont(new Font("Sukhumvit Set", Font.PLAIN, 18));
		lblPunchSuggest.setBounds(444, 79, 306, 187);
		panelDrink3.add(lblPunchSuggest);

		JLabel lblPrice3 = new JLabel("- 105 Baht -");
		lblPrice3.setFont(new Font("Phosphate", Font.PLAIN, 35));
		lblPrice3.setBounds(498, 267, 186, 64);
		panelDrink3.add(lblPrice3);

		JPanel panelDrink4 = new JPanel();
		panelDrink4.setBackground(Color.WHITE);
		panelDrink4.setBounds(387, 184, 789, 497);
		add(panelDrink4);
		panelDrink4.setVisible(false);
		panelDrink4.setOpaque(false);
		panelDrink4.setLayout(null);

		JLabel picDrink4 = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("drink/Matcha.jpg")));
		picDrink4.setBounds(49, 0, 347, 497);
		panelDrink4.add(picDrink4);

		JLabel lblMatcha = new JLabel("Hot Matcha");
		lblMatcha.setFont(new Font("Phosphate", Font.PLAIN, 42));
		lblMatcha.setBounds(460, 6, 329, 98);
		panelDrink4.add(lblMatcha);

		JLabel lblGreenTea = new JLabel("Green Tea");
		lblGreenTea.setFont(new Font("Phosphate", Font.PLAIN, 42));
		lblGreenTea.setBounds(492, 64, 329, 98);
		panelDrink4.add(lblGreenTea);

		JLabel lblMatchaSuggest = new JLabel("<html>Hokkaido Match Green Tea mixed with Creamy Milk</html>");
		lblMatchaSuggest.setFont(new Font("Sukhumvit Set", Font.PLAIN, 18));
		lblMatchaSuggest.setBounds(444, 79, 306, 187);
		panelDrink4.add(lblMatchaSuggest);

		JLabel lblPrice4 = new JLabel("- 150 Baht -");
		lblPrice4.setFont(new Font("Phosphate", Font.PLAIN, 35));
		lblPrice4.setBounds(498, 267, 186, 64);
		panelDrink4.add(lblPrice4);

		JPanel panelDrink5 = new JPanel();
		panelDrink5.setBackground(Color.WHITE);
		panelDrink5.setBounds(387, 184, 789, 497);
		add(panelDrink5);
		panelDrink5.setVisible(false);
		panelDrink5.setOpaque(false);
		panelDrink5.setLayout(null);

		JLabel picDrink5 = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("drink/DrakChoc.jpg")));
		picDrink5.setBounds(49, 0, 347, 497);
		panelDrink5.add(picDrink5);

		JLabel lblDark = new JLabel("Ice Dark Chocolate");
		lblDark.setFont(new Font("Phosphate", Font.PLAIN, 39));
		lblDark.setBounds(405, 6, 384, 98);
		panelDrink5.add(lblDark);

		JLabel lblDarkSuggest = new JLabel("<html>Dark Chocolate topping with Chocolate chip and Whipped Cream</html>");
		lblDarkSuggest.setFont(new Font("Sukhumvit Set", Font.PLAIN, 18));
		lblDarkSuggest.setBounds(444, 79, 306, 187);
		panelDrink5.add(lblDarkSuggest);

		JLabel lblPrice5 = new JLabel("- 110 Baht -");
		lblPrice5.setFont(new Font("Phosphate", Font.PLAIN, 35));
		lblPrice5.setBounds(498, 267, 186, 64);
		panelDrink5.add(lblPrice5);

		JPanel[] allPageFood = {panelDrink1,panelDrink2,panelDrink3,panelDrink4,panelDrink5};

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

		scrollPane = new JScrollPane();
		scrollPane.setBounds(59, 119, 220, 320);
		add(scrollPane);

		String[][] test =	new String[0][0];
		table = new JTable(test,nameHead);
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(23);
		table.getColumnModel().getColumn(1).setPreferredWidth(167);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.setEnabled(false);
		scrollPane.setViewportView(table);


		JLabel background = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("shopping/ShoppingBGDrink.jpg")));
		background.setBounds(0, -25, 1280, 800);
		add(background);
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
			Scanner scan = new Scanner(new File("src/textMenu/Drink.txt"));
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
