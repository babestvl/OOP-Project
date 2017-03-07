package Object;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadAndWrite {
	private String username = "", checkUser = "", checkPass = "", checkName = "", checkLast = "", checkID = "",
			checkGender = "", checkAge = "", checkClass = "", checkOrderName = "";
	private double checkPrice = 0;
	private int checkQTY = 0;
	private ArrayList<String> listOrder = new ArrayList<String>();

	public ReadAndWrite(String username) {
		this.username = username;
	}
	public ReadAndWrite() {
		
	}

	public ArrayList<String> read() {
		File file = new File("./src/user/" + (username) + ".txt");
		Scanner scan = null;
		if (file.exists()) {

			try {
				scan = new Scanner(new FileInputStream(file));
			} catch (Exception e) {
				e.printStackTrace();
			}
			checkUser = scan.next();
			checkPass = scan.next();
			checkName = scan.next();
			checkLast = scan.next();
			checkID = scan.next();
			checkGender = scan.next();
			checkAge = scan.next();
			checkClass = scan.next();
			while (scan.hasNext()) {
				checkPrice = scan.nextDouble();
				checkQTY = scan.nextInt();
				checkOrderName = scan.nextLine();
				double sumPrice = checkPrice*checkQTY;
				String toString = String.format("%42s %d %.2f %.2f",checkOrderName,checkQTY,checkPrice,sumPrice);
				listOrder.add(toString);
			}
		}
		return listOrder;
	}
	public void write() {
		File file = new File("./src/user/" + (username) + ".txt");
		PrintStream print = null;
		if (file.exists()) {
			try {
				print = new PrintStream(new FileOutputStream(file,true));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}