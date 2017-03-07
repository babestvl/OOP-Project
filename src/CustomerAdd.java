import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.URL;
import Object.RegisterFile;
public class CustomerAdd extends JPanel {
	private Restaurant res;
	private JTextField textFirstName;
	private JTextField textLastname;
	private JTextField textPersonID;
	private JTextField textAge;
	private JTextField textUsername;
	private JTextField textPassword;
	private JComboBox ComboGender;
	private String[] gender = {"Male","Female"};
	private RegisterFile regis;
	

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public CustomerAdd(Restaurant res) {
		this.res = res;
		setBounds(0, 0, 1280, 800);
		setLayout(null);
		
		JButton btnAdd = new JButton("New button");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean isExist = true;
				if (!textUsername.getText().equals("") && !textPassword.getText().equals("")
						&& !textFirstName.getText().equals("") && !textLastname.getText().equals("")
						&& !textPersonID.equals("") && !textAge.getText().equals("")) {
					regis = new RegisterFile(textUsername.getText(), textPassword.getText(), textFirstName.getText(),
							textLastname.getText(), textPersonID.getText(), gender[ComboGender.getSelectedIndex()],
							Integer.parseInt(textAge.getText()));
					isExist = regis.file();
					if (!isExist) {
						showDialogExist();
					} else if (isExist) {
						regis.file();
						showDialogComplete();
						res.setSearch();
					} 
				} else {
					showDialog();
				}
				
//				if (!textFirstName.getText().equals("") && !textLastname.getText().equals("") && !textPersonID.getText().equals("")  && !textAge.getText().equals("")) {
//					regis = new RegisterFile(textUsername.getText(),textPassword.getText(),textFirstName.getText(),textLastname.getText(),textPersonID.getText(),gender[ComboGender.getSelectedIndex()], Integer.parseInt(textAge.getText()));
//					showDialog();
					textUsername.setText("");
					textPassword.setText("");
					textFirstName.setText("");
					textLastname.setText("");
					textPersonID.setText("");
					ComboGender.setSelectedIndex(0);
					textAge.setText("");
//				}
//				else {
//					showDialog();
//				}
			}
		});
		ClassLoader loaderA = this.getClass().getClassLoader();
		URL urlA = loaderA.getResource("customerIMG/add.png");
		ImageIcon imgA = new ImageIcon(urlA);
		btnAdd.setOpaque(false);
		btnAdd.setContentAreaFilled(false);
		btnAdd.setBorderPainted(false);
		btnAdd.setBorder(BorderFactory.createEmptyBorder());
		btnAdd.setIcon(imgA);
		btnAdd.setBounds(992, 611, 238, 79);
		add(btnAdd);
		
		JButton btnS = new JButton();
		btnS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res.setSearch();
			}
		});
		ClassLoader loaderS = this.getClass().getClassLoader();
		URL urlS = loaderS.getResource("customerIMG/S.png");
		ImageIcon imgS = new ImageIcon(urlS);
		btnS.setOpaque(false);
		btnS.setContentAreaFilled(false);
		btnS.setBorderPainted(false);
		btnS.setBorder(BorderFactory.createEmptyBorder());
		btnS.setIcon(imgS);
		btnS.setBounds(37, 36, 214, 79);
		add(btnS);
		
		
		textUsername = new JTextField();
		textUsername.setColumns(10);
		textUsername.setBounds(387, 172, 680, 47);
		add(textUsername);
		
		textPassword = new JTextField();
		textPassword.setColumns(10);
		textPassword.setBounds(387, 251, 680, 47);
		add(textPassword);
		
		textFirstName = new JTextField();
		textFirstName.setBounds(387, 328, 680, 47);
		add(textFirstName);
		textFirstName.setColumns(10);
		
		textLastname = new JTextField();
		textLastname.setBounds(387, 407, 680, 47);
		add(textLastname);
		textLastname.setColumns(10);
		
		textPersonID = new JTextField();
		textPersonID.setBounds(387, 483, 680, 47);
		add(textPersonID);
		textPersonID.setColumns(10);
		
		ComboGender = new JComboBox(gender);
		ComboGender.setBounds(387, 553, 96, 50);
		add(ComboGender);
		
		
		textAge = new JTextField();
		textAge.setBounds(387, 630, 96, 42);
		add(textAge);
		textAge.setColumns(10);
		
		JButton btnBack = new JButton();
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res.setMenu();
			}
		});
		btnBack.setIcon(new ImageIcon(CustomerSearch.class.getResource("/customerIMG/btnBack.png")));
		btnBack.setOpaque(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.setBorder(BorderFactory.createEmptyBorder());
		btnBack.setFont(new Font("Phosphate", Font.PLAIN, 16));
		btnBack.setBounds(1150, 47, 94, 85);
		add(btnBack);
		
		JLabel imgBG = new JLabel(new ImageIcon(this.getClass().getClassLoader().getResource("customerIMG/imgAdd.jpg")));
		imgBG.setBounds(0, -25, 1280, 800);
		add(imgBG);
		
		
		
		
	}
	
//	public void showDialog() {
//		String str = "";
//		String head = "";
//		if (!textFirstName.getText().equals("") && !textLastname.getText().equals("") && !textPersonID.getText().equals("") && !textAge.getText().equals("")) {
//			str = "Registed Complete!";
//			head = "Complete";
//		}
//		else {
//			str = "Please fill in all fields !!";
//			head = "ERROR";
//		}
//		JOptionPane.showMessageDialog(this,  str, head, JOptionPane.PLAIN_MESSAGE);
//	}
	
	public void showDialog() {
		JOptionPane.showMessageDialog(this, "Please fill in all fields !!", "ERROR", JOptionPane.PLAIN_MESSAGE);
	}

	public void showDialogComplete() {
		JOptionPane.showMessageDialog(this, "Register Complete !!", "Complete", JOptionPane.PLAIN_MESSAGE);
	}

	public void showDialogExist() {
		JOptionPane.showMessageDialog(this, "This username has been used !!", "ERROR", JOptionPane.PLAIN_MESSAGE);
	}
}
