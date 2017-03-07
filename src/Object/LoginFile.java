package Object;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class LoginFile {
	String username = "", password = "";
	String checkUser = "";
	String checkPass = "";
	String checkName = "", checkLast = "", checkID = "", checkGender = "", checkAge = "", checkClass = "";

	public LoginFile(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public int isLogin() {
		File file = new File("./src/user/" + (username) + ".txt");
		if (file.exists()) {
			try {
				Scanner scan = new Scanner(new FileInputStream(file));
				checkUser = scan.next();
				checkPass = scan.next();
				checkName = scan.next();
				checkLast = scan.next();
				checkID = scan.next();
				checkGender = scan.next();
				checkAge = scan.next();
				checkClass = scan.next();
				scan.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

			
			if (username.equals(checkUser) && password.equals(checkPass)) {
				File fileTemp = new File("./src/temp/temp.txt");
				if (!fileTemp.exists()) {
					fileTemp.getParentFile().mkdir();
					try {
						fileTemp.createNewFile();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				else {
					fileTemp.delete();
					fileTemp = new File("./src/temp/temp.txt");
					fileTemp.getParentFile().mkdir();
					try {
						fileTemp.createNewFile();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				try {
					PrintStream print = new PrintStream(new FileOutputStream(fileTemp, true));
					print.print(checkUser + " ");
					print.print(checkPass + " ");
					print.print(checkName + " ");
					print.print(checkLast + " ");
					print.print(checkID + " ");
					print.print(checkGender + " ");
					print.print(checkAge + " ");
					print.println(checkClass + " ");
					print.close();
				} catch (FileNotFoundException fnfe) {
					fnfe.printStackTrace();
				}

				if (checkClass.equals("admin"))
					return 0;
				else
					return 1;
			}
		}
		return -99;
	}

	public void close() {
		username = "";
		password = "";
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getCheckUser() {
		return checkUser;
	}

	public String getCheckPass() {
		return checkPass;
	}

	public String getCheckName() {
		return checkName;
	}

	public String getCheckLast() {
		return checkLast;
	}

	public String getCheckID() {
		return checkID;
	}

	public String getCheckGender() {
		return checkGender;
	}

	public String getCheckAge() {
		return checkAge;
	}

	public String getCheckClass() {
		return checkClass;
	}

}