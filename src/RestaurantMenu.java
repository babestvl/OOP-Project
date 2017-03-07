import java.awt.Image;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;

public class RestaurantMenu extends JPanel {
	private ClassLoader loader = this.getClass().getClassLoader();
	private Restaurant res;

	/**
	 * Create the panel.
	 */
	public RestaurantMenu(Restaurant res) {

		this.res = res;
		setBounds(0, 0, 1280, 800);
		setLayout(null);
		JButton btnClose = new JButton();
		btnClose.setBounds(1152, 0, 130, 250);
		btnClose.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e)
		    {
		        res.setLogin();
		    }
		});
		btnClose.setOpaque(false);
		btnClose.setContentAreaFilled(false);
		btnClose.setBorderPainted(false);
		btnClose.setBorder(BorderFactory.createEmptyBorder());
		btnClose.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/Logout.png")));
		add(btnClose);
		
		JButton btnShopping = new JButton();
		btnShopping.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				res.setShopping();
			}
		});
		btnShopping.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnShopping.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/ShoppingEnter.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnShopping.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/Shopping.png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnShopping.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/Shopping.png")));
			}
		});
		btnShopping.setBounds(83, 316, 300, 300);
		btnShopping.setOpaque(false);
		btnShopping.setContentAreaFilled(false);
		btnShopping.setBorderPainted(false);
		btnShopping.setBorder(BorderFactory.createEmptyBorder());
		btnShopping.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/Shopping.png")));
		add(btnShopping);
		
		JButton btnCustomer = new JButton();
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res.setSearch();
			}
		});
		btnCustomer.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnCustomer.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/CustomerIconEnter.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnCustomer.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/CustomerIcon.png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnCustomer.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/CustomerIcon.png")));
			}
		});
		btnCustomer.setBounds(481, 316, 300, 300);
		URL urlCustomer = loader.getResource("img/CustomerIcon.png");
		ImageIcon imgCustomer = new ImageIcon(urlCustomer);
		btnCustomer.setOpaque(false);
		btnCustomer.setContentAreaFilled(false);
		btnCustomer.setBorderPainted(false);
		btnCustomer.setBorder(BorderFactory.createEmptyBorder());
		btnCustomer.setIcon(imgCustomer);
		add(btnCustomer);
		
		JButton btnStore = new JButton();
		btnStore.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res.setStore();
			}
		});
		btnStore.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnStore.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/StoreIconEnter.png")));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnStore.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/StoreIcon.png")));
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				btnStore.setIcon(new ImageIcon(this.getClass().getClassLoader().getResource("img/StoreIcon.png")));
			}
		});
		btnStore.setBounds(892, 316, 300, 300);
		URL urlStore= loader.getResource("img/StoreIcon.png");
		ImageIcon imgStore = new ImageIcon(urlStore);
		btnStore.setOpaque(false);
		btnStore.setContentAreaFilled(false);
		btnStore.setBorderPainted(false);
		btnStore.setBorder(BorderFactory.createEmptyBorder());
		btnStore.setIcon(imgStore);
		add(btnStore);
		
		JLabel background = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("img/bgRestaurant2.jpg")));
		background.setBounds(0, -25, 1280, 800);
		add(background);
		
	}

}
