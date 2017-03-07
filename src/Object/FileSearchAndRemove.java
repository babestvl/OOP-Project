package Object;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class FileSearchAndRemove {
	private String username, name, lastname, personID, gender, memberClass;
	private String checkUser, checkPass, checkName, checkLast, checkID, checkGender,
			checkAge, checkClass;
	private String dir = "./src/user/";
	private ArrayList<String> files = new ArrayList<String>();
	private ArrayList<String> infoList = new ArrayList<String>();
	private File[] listOfFiles;
	private File folder, file;
	private String toString = "";
	private int index; 
	String nameCus;//change to int 
	private int count;
	private String[] checkList;
	private ArrayList<Integer> remainCheckList = new ArrayList<Integer>();

	public FileSearchAndRemove(String username, String name, String lastname, String personID, String gender,
			String memberClass) {
			this.username = username;
			this.name = name;
			this.lastname = lastname;
			this.personID = personID;
			this.gender = gender;
			this.memberClass = memberClass;
		checkList = new String[] { username, name, lastname, personID, gender, memberClass };
		for (int i = 0; i < checkList.length; i++) {
			if (!checkList[i].equals("")&&!checkList[i].equals("-Select-")) {
				remainCheckList.add(i);
			}
		}
	}

	public FileSearchAndRemove(String nameCus) { //change to int
		this.nameCus = nameCus;
	}

	public void allFiles() {

		folder = new File(dir);
		listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {

			if (listOfFiles[i].isFile()) {
				files.add(listOfFiles[i].getName());
			}
		}
	}

	public ArrayList<String> printInfo() {
		String[] checkInFile = null;
		allFiles();
		for (int i = 0; i < files.size(); i++) {
			count = 0;
			file = new File(dir + files.get(i));
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
					toString = String.format("%s %s %s %s %s %s %s %s", checkUser, checkPass, checkName, checkLast,
							checkID, checkGender, checkAge, checkClass);
					checkInFile = new String[] { checkUser, checkName, checkLast, checkID, checkGender, checkClass };
					scan.close();
				} catch (FileNotFoundException fnfe) {
					fnfe.printStackTrace();
				}

				for (int j = 0; j < remainCheckList.size(); j++) {
					if (checkList[remainCheckList.get(j)]
							.equals(checkInFile[remainCheckList.get(j)]))
						count++;
					if (count == remainCheckList.size())
						infoList.add(toString);
				}

			}
		}
		return infoList;
	}

	public void removeUser() {
		allFiles();
		file = new File(dir + nameCus + ".txt");
		file.delete();
	}
}