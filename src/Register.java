import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import Object.RegisterFile;

public class Register extends JPanel {
	private Restaurant res;
	private JTextField textUsername;
	private JTextField textPassword;
	private JTextField textFirstName;
	private JTextField textLastname;
	private JTextField textPersonID;
	private JTextField textAge;
	private String[] gender = { "Male", "Female" };
	private JComboBox ComboGender;
	private RegisterFile regis;

	/**
	 * Create the panel.
	 */
	public Register(Restaurant res) {
		this.res = res;
		setBounds(0, 0, 1280, 800);
		setLayout(null);

		JButton btnClose = new JButton();
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				res.setLogin();
				textUsername.setText("");
				textPassword.setText("");
				textFirstName.setText("");
				textLastname.setText("");
				textPersonID.setText("");
				ComboGender.setSelectedIndex(0);
				textAge.setText("");
			}
		});
		btnClose.setOpaque(false);
		btnClose.setContentAreaFilled(false);
		btnClose.setBorderPainted(false);
		btnClose.setBorder(BorderFactory.createEmptyBorder());
		btnClose.setIcon(new ImageIcon(Login.class.getResource("/img/LoginClose.png")));
		btnClose.setBounds(1158, 293, 50, 50);
		add(btnClose);

		textUsername = new JTextField();
		textUsername.setBounds(389, 343, 256, 50);
		add(textUsername);
		textUsername.setColumns(10);

		textPassword = new JTextField();
		textPassword.setColumns(10);
		textPassword.setBounds(389, 418, 256, 50);
		add(textPassword);

		textFirstName = new JTextField();
		textFirstName.setColumns(10);
		textFirstName.setBounds(389, 494, 256, 50);
		add(textFirstName);

		textLastname = new JTextField();
		textLastname.setColumns(10);
		textLastname.setBounds(389, 569, 256, 50);
		add(textLastname);

		textPersonID = new JTextField();
		textPersonID.setColumns(10);
		textPersonID.setBounds(925, 343, 256, 50);
		add(textPersonID);

		textAge = new JTextField();
		textAge.setColumns(10);
		textAge.setBounds(925, 494, 96, 50);
		add(textAge);

		JButton btnRegister = new JButton();
		btnRegister.addActionListener(new ActionListener() {
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
						res.setLogin();
					}
				} else {
					showDialog();
				}
				textUsername.setText("");
				textPassword.setText("");
				textFirstName.setText("");
				textLastname.setText("");
				textPersonID.setText("");
				ComboGender.setSelectedIndex(0);
				textAge.setText("");
			}
		});
		btnRegister.setOpaque(false);
		btnRegister.setContentAreaFilled(false);
		btnRegister.setBorderPainted(false);
		btnRegister.setBorder(BorderFactory.createEmptyBorder());
		btnRegister.setIcon(new ImageIcon(Register.class.getResource("/img/RegisterButton.png")));
		btnRegister.setBounds(704, 570, 524, 123);
		add(btnRegister);

		ComboGender = new JComboBox(gender);
		ComboGender.setBounds(925, 425, 96, 50);
		add(ComboGender);

		JLabel background = new JLabel(
				new ImageIcon(this.getClass().getClassLoader().getResource("img/RegisterBG.jpg")));
		background.setBounds(0, -25, 1280, 800);
		add(background);

	}

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
