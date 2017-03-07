package Object;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class RegisterFile {
	private File file;
	private boolean append;
	String username = "", password = "", name = "", lastname = "", personID = "", gender = "", memberClass = "" , totalPrice = "";
	int age = 0;

	public RegisterFile(String username, String password, String name, String lastname, String personID, String gender,
			int age) {

		this.username = username;
		this.password = password;
		this.name = name;
		this.lastname = lastname;
		this.personID = personID;
		this.gender = gender;
		this.age = age;
		this.memberClass = "none";
		this.totalPrice = "0";
	}

	public boolean file() {
		file = new File("./src/user/" + (username) + ".txt");
		PrintStream toFile = null;
		append = true;
		boolean isNotExist = !file.exists();
		if (isNotExist) {
			file.getParentFile().mkdirs();
			try {
				file.createNewFile();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}

			try {
				toFile = new PrintStream(new FileOutputStream(file, append));
			} catch (FileNotFoundException fnfe) {
				fnfe.printStackTrace();
			}
			toFile.print(username + " ");
			toFile.print(password + " ");
			toFile.print(name + " ");
			toFile.print(lastname + " ");
			toFile.print(personID + " ");
			toFile.print(gender + " ");
			toFile.print(age + " ");
			toFile.println(memberClass);
			toFile.println(totalPrice);
		}
		return isNotExist;
	}
}