import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import Object.LoginFile;
public class Login extends JPanel {
	private Restaurant res;
	private JTextField textUser;
	private JPasswordField textPass;
	private LoginFile login;
	private String username , password ;
	/**
	 * Create the panel.
	 */
	public Login(Restaurant res) {
		this.res = res;
		setBounds(0, 0, 1280, 800);
		setLayout(null);
		
		textUser = new JTextField();
		textUser.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					
					login = new LoginFile(textUser.getText(),textPass.getText());
					if(login.isLogin()==0){
						showDialogLoginComplete(0);
						res.setMenu();
					} else if (login.isLogin()==1){
						showDialogLoginComplete(1);
						res.getShoppingCustomer().printInfo();
						res.getShoppingCustomerDessert().printInfo();
						res.getShoppingCustomerDrink().printInfo();
						res.getShoppingCustomerFood().printInfo();
						res.setShoppingCustomer();
					} else 
						showDialogLoginFailed();
					username = textUser.getText();
					password = textPass.getText();
					textUser.setText("");
					textPass.setText("");
					
				}
			}
		});
		textUser.setBounds(526, 349, 636, 60);
		textUser.setFont(new Font("Sukhumvit Set", Font.PLAIN, 40));
		add(textUser);
		textUser.setColumns(10);
		
		textPass = new JPasswordField();
		textPass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER){
					login = new LoginFile(textUser.getText(),textPass.getText());
					if(login.isLogin()==0){
						showDialogLoginComplete(0);
						res.setMenu();
					} else if (login.isLogin()==1){
						showDialogLoginComplete(1);
						res.getShoppingCustomer().printInfo();
						res.getShoppingCustomerDessert().printInfo();
						res.getShoppingCustomerDrink().printInfo();
						res.getShoppingCustomerFood().printInfo();
						res.setShoppingCustomer();
					} else 
						showDialogLoginFailed();
					username = textUser.getText();
					password = textPass.getText();
					textUser.setText("");
					textPass.setText("");
					
					
				}
			}
		});
		textPass.setColumns(10);
		textPass.setBounds(526, 440, 636, 60);
		textPass.setFont(new Font("Sukhumvit Set", Font.PLAIN, 40));
		add(textPass);
		
		
		
		JButton btnLogin = new JButton();
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				login = new LoginFile(textUser.getText(),textPass.getText());
				login = new LoginFile(textUser.getText(),textPass.getText());
				if(login.isLogin()==0){
					showDialogLoginComplete(0);
					res.setMenu();
				} else if (login.isLogin()==1){
					showDialogLoginComplete(1);
					res.getShoppingCustomer().printInfo();
					res.getShoppingCustomerDessert().printInfo();
					res.getShoppingCustomerDrink().printInfo();
					res.getShoppingCustomerFood().printInfo();
					res.setShoppingCustomer();
				} else 
					showDialogLoginFailed();
				username = textUser.getText();
				password = textPass.getText();
				textUser.setText("");
				textPass.setText("");
				
			}
		});
		btnLogin.setOpaque(false);
		btnLogin.setContentAreaFilled(false);
		btnLogin.setBorderPainted(false);
		btnLogin.setBorder(BorderFactory.createEmptyBorder());
		btnLogin.setIcon(new ImageIcon(Login.class.getResource("/img/LoginButton.png")));
		btnLogin.setBounds(56, 538, 583, 153);
		
		add(btnLogin);
		
		JButton btnRegister = new JButton();
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res.setRegister();
				textUser.setText("");
				textPass.setText("");
			}
		});
		btnRegister.setOpaque(false);
		btnRegister.setContentAreaFilled(false);
		btnRegister.setBorderPainted(false);
		btnRegister.setBorder(BorderFactory.createEmptyBorder());
		btnRegister.setIcon(new ImageIcon(Login.class.getResource("/img/btnResgister.png")));
		btnRegister.setBounds(638, 538, 590, 153);
		add(btnRegister);
		
		JButton btnClose = new JButton();
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnClose.setOpaque(false);
		btnClose.setContentAreaFilled(false);
		btnClose.setBorderPainted(false);
		btnClose.setBorder(BorderFactory.createEmptyBorder());
		btnClose.setIcon(new ImageIcon(Login.class.getResource("/img/LoginClose.png")));
		btnClose.setBounds(1158, 293, 50, 50);
		add(btnClose);
		
		JLabel background = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("img/Login.jpg")));
		background.setBounds(0, -25, 1280, 800);
		add(background);
		
	}
	
	public void showDialogLoginFailed() {
		JOptionPane.showMessageDialog(this,  "Username or Password Incorrect !!! ", "ERROR", JOptionPane.PLAIN_MESSAGE);
	}
	public void showDialogLoginComplete(int check){
		String member = "";
		if (check == 0)
			member = "Admin";
		else {
			if (login.getCheckGender().equals("Male"))
				member += "Mr. ";
			else 
				member += "Mrs. ";
			member += login.getCheckName();
		}
		
		JOptionPane.showMessageDialog(this,  "Welcome "+ member, "Welcome to TMB Restaurant", JOptionPane.PLAIN_MESSAGE);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
