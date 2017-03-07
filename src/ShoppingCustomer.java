
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Scanner;

import javax.swing.*;

import Object.ReadFromMenuFile;


public class ShoppingCustomer extends JPanel {
	private Restaurant res;
	private ClassLoader loader = this.getClass().getClassLoader();
	private JTable table;
	private String User = "";
	private String Pass = "";
	private String name = "", last = "", ID = "", gender = "", age = "", memberClass = "";
	private JLabel labelName, labelAgeGender;
	private String[] nameHead = {"No","Menu Item","Qty"};
	private JScrollPane scrollPane ;
	private JLabel lblPrice = new JLabel("0");
	private ReadFromMenuFile order;

	/**
	 * Create the panel.
	 */
	public ShoppingCustomer(Restaurant res) {
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
		btnLogout.setOpaque(false);
		btnLogout.setContentAreaFilled(false);
		btnLogout.setBorderPainted(false);
		btnLogout.setBorder(BorderFactory.createEmptyBorder());
		btnLogout.setIcon(new ImageIcon(Shopping.class.getResource("/shopping/LogoutCustomer.png")));
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

		JButton btnFood = new JButton("1");
		btnFood.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res.setShoppingCustomerFood();
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

		JLabel lblTmbRestaurant = new JLabel("TMB Restaurant");
		lblTmbRestaurant.setFont(new Font("Phosphate", Font.PLAIN, 23));
		lblTmbRestaurant.setBounds(74, 87, 187, 27);
		add(lblTmbRestaurant);

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

		scrollPane = new JScrollPane();
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


		JLabel background = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("shopping/ShoppingBG.jpg")));
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
}
