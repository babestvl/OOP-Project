import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Scanner;

import javax.swing.*;

import Object.ReadFromMenuFile;

public class ShoppingDessert extends JPanel {
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
	public ShoppingDessert(Restaurant res) {
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

		JButton btnDrink = new JButton("3");
		btnDrink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res.setShoppingDrink();
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
		

		JButton btnOrder = new JButton();
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!comboBox.getSelectedItem().toString().equals("0")){
					order.order("Dessert",numberOfPage,comboBox.getSelectedItem().toString());
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

		JPanel panelDessert1 = new JPanel();
		panelDessert1.setBackground(Color.WHITE);
		panelDessert1.setBounds(387, 184, 789, 497);
		add(panelDessert1);
		panelDessert1.setLayout(null);
		panelDessert1.setOpaque(false);

		JLabel picDessert1 = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("Dessert/GreenTeaRoll.jpg")));
		picDessert1.setBounds(49, 0, 347, 497);
		panelDessert1.add(picDessert1);

		JLabel lblRoll = new JLabel("Green Tea Roll");
		lblRoll.setFont(new Font("Phosphate", Font.PLAIN, 42));
		lblRoll.setBounds(443, 6, 386, 98);
		panelDessert1.add(lblRoll);

		JLabel lblRollSuggest = new JLabel("<html>Green Tea Roll with Strawberry Cream</html>");
		lblRollSuggest.setFont(new Font("Sukhumvit Set", Font.PLAIN, 18));
		lblRollSuggest.setBounds(444, 79, 306, 187);
		panelDessert1.add(lblRollSuggest);

		JLabel lblPrice1 = new JLabel("- 180 Baht -");
		lblPrice1.setFont(new Font("Phosphate", Font.PLAIN, 35));
		lblPrice1.setBounds(498, 267, 186, 64);
		panelDessert1.add(lblPrice1);

		JPanel panelDessert2 = new JPanel();
		panelDessert2.setBackground(Color.WHITE);
		panelDessert2.setBounds(387, 184, 789, 497);
		add(panelDessert2);
		panelDessert2.setVisible(false);
		panelDessert2.setOpaque(false);
		panelDessert2.setLayout(null);

		JLabel picDessert2 = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("Dessert/BerryCheese.jpg")));
		picDessert2.setBounds(49, 0, 347, 497);
		panelDessert2.add(picDessert2);

		JLabel lblCheeseCake = new JLabel("Lemon Berry");
		lblCheeseCake.setFont(new Font("Phosphate", Font.PLAIN, 40));
		lblCheeseCake.setBounds(459, 6, 407, 98);
		panelDessert2.add(lblCheeseCake);

		JLabel lblCheesecakes = new JLabel("Cheesecakes");
		lblCheesecakes.setFont(new Font("Phosphate", Font.PLAIN, 36));
		lblCheesecakes.setBounds(469, 50, 407, 98);
		panelDessert2.add(lblCheesecakes);

		JLabel lblCheesecakesSuggest = new JLabel("<html>Chocolate chip Biscuit with Mixed Berries Cheesecakes topping with lemon Cream</html>");
		lblCheesecakesSuggest.setFont(new Font("Sukhumvit Set", Font.PLAIN, 18));
		lblCheesecakesSuggest.setBounds(444, 79, 306, 187);
		panelDessert2.add(lblCheesecakesSuggest);

		JLabel lblPrice2 = new JLabel("- 169 Baht -");
		lblPrice2.setFont(new Font("Phosphate", Font.PLAIN, 35));
		lblPrice2.setBounds(498, 267, 186, 64);
		panelDessert2.add(lblPrice2);

		JPanel panelDessert3 = new JPanel();
		panelDessert3.setBackground(Color.WHITE);
		panelDessert3.setBounds(387, 184, 789, 497);
		add(panelDessert3);
		panelDessert3.setVisible(false);
		panelDessert3.setOpaque(false);
		panelDessert3.setLayout(null);

		JLabel picDessert3 = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("Dessert/ChocolateBerryCake.jpg")));
		picDessert3.setBounds(49, 0, 347, 497);
		panelDessert3.add(picDessert3);

		JLabel lblChoco = new JLabel("Chocolate and Berries");
		lblChoco.setFont(new Font("Phosphate", Font.PLAIN, 35));
		lblChoco.setBounds(397, 6, 386, 98);
		panelDessert3.add(lblChoco);

		JLabel lblYo = new JLabel("Yogurt Cake");
		lblYo.setFont(new Font("Phosphate", Font.PLAIN, 35));
		lblYo.setBounds(473, 67, 243, 62);
		panelDessert3.add(lblYo);

		JLabel lblChocoSuggest = new JLabel("<html>Chocolate cake with Mixed Berries  Yogurt and Topping with Hokkaido Yogurt</html>");
		lblChocoSuggest.setFont(new Font("Sukhumvit Set", Font.PLAIN, 18));
		lblChocoSuggest.setBounds(444, 79, 306, 187);
		panelDessert3.add(lblChocoSuggest);

		JLabel lblPrice3 = new JLabel("- 265 Baht -");
		lblPrice3.setFont(new Font("Phosphate", Font.PLAIN, 35));
		lblPrice3.setBounds(498, 267, 186, 64);
		panelDessert3.add(lblPrice3);

		JPanel panelDessert4 = new JPanel();
		panelDessert4.setBackground(Color.WHITE);
		panelDessert4.setBounds(387, 184, 789, 497);
		add(panelDessert4);
		panelDessert4.setVisible(false);
		panelDessert4.setOpaque(false);
		panelDessert4.setLayout(null);

		JLabel picDessert4 = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("Dessert/PannaCotta.jpg")));
		picDessert4.setBounds(49, 0, 347, 497);
		panelDessert4.add(picDessert4);

		JLabel lblWhite = new JLabel("White Chocolate");
		lblWhite.setFont(new Font("Phosphate", Font.PLAIN, 42));
		lblWhite.setBounds(417, 6, 386, 98);
		panelDessert4.add(lblWhite);

		JLabel lblPanna = new JLabel("& Berry Panna Cotta");
		lblPanna.setFont(new Font("Phosphate", Font.PLAIN, 36));
		lblPanna.setBounds(408, 52, 375, 98);
		panelDessert4.add(lblPanna);

		JLabel lblPannaCottaSuggest = new JLabel("<html>White Chocolate and Berries Panna Cotta Topping with Berries</html>");
		lblPannaCottaSuggest.setFont(new Font("Sukhumvit Set", Font.PLAIN, 18));
		lblPannaCottaSuggest.setBounds(444, 79, 306, 187);
		panelDessert4.add(lblPannaCottaSuggest);

		JLabel lblPrice4 = new JLabel("- 150 Baht -");
		lblPrice4.setFont(new Font("Phosphate", Font.PLAIN, 35));
		lblPrice4.setBounds(498, 267, 186, 64);
		panelDessert4.add(lblPrice4);

		JPanel panelDessert5 = new JPanel();
		panelDessert5.setBackground(Color.WHITE);
		panelDessert5.setBounds(387, 184, 789, 497);
		add(panelDessert5);
		panelDessert5.setVisible(false);
		panelDessert5.setOpaque(false);
		panelDessert5.setLayout(null);

		JLabel picDrink5 = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("Dessert/MatchaIcecream.jpg")));
		picDrink5.setBounds(49, 0, 347, 497);
		panelDessert5.add(picDrink5);

		JLabel lblMatcha = new JLabel("Matcha Cherimoya");
		lblMatcha.setFont(new Font("Phosphate", Font.PLAIN, 40));
		lblMatcha.setBounds(405, 6, 384, 98);
		panelDessert5.add(lblMatcha);

		JLabel lblIce = new JLabel("Strawerry");
		lblIce.setFont(new Font("Phosphate", Font.PLAIN, 31));
		lblIce.setBounds(525, 83, 375, 98);
		panelDessert5.add(lblIce);

		JLabel lblIceCream = new JLabel("Ice Cream with");
		lblIceCream.setFont(new Font("Phosphate", Font.PLAIN, 33));
		lblIceCream.setBounds(489, 48, 384, 98);
		panelDessert5.add(lblIceCream);

		JLabel lblMatchaSuggest = new JLabel("<html>Matcha mixed with fresh Cherimoya Ice Cream served with fresh strawberries</html>");
		lblMatchaSuggest.setFont(new Font("Sukhumvit Set", Font.PLAIN, 18));
		lblMatchaSuggest.setBounds(444, 100, 306, 187);
		panelDessert5.add(lblMatchaSuggest);

		JLabel lblPrice5 = new JLabel("- 220 Baht -");
		lblPrice5.setFont(new Font("Phosphate", Font.PLAIN, 35));
		lblPrice5.setBounds(498, 267, 186, 64);
		panelDessert5.add(lblPrice5);

		JPanel[] allPageFood = {panelDessert1,panelDessert2,panelDessert3,panelDessert4,panelDessert5};

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

		String[][] test = new String[0][0];
		table = new JTable(test,nameHead);
		table.getTableHeader().setReorderingAllowed(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(23);
		table.getColumnModel().getColumn(1).setPreferredWidth(167);
		table.getColumnModel().getColumn(2).setPreferredWidth(30);
		table.setEnabled(false);
		scrollPane.setViewportView(table);


		JLabel background = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("shopping/ShoppingBGDessert.jpg")));
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
			Scanner scan = new Scanner(new File("src/textMenu/Dessert.txt"));
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
