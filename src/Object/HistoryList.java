package Object;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class HistoryList {
	private ArrayList<String> history =  new ArrayList<String>();
	private String[][] historyList ;
	private String totalPrice = "";
	
	public HistoryList(String username){
		File file = new File ("./src/user/" + (username) + ".txt");
		if (file.exists()) {
			try {
				Scanner scan = new Scanner(new FileInputStream(file));
				scan.nextLine();
				totalPrice = scan.nextLine();
				while(scan.hasNext()){
					String qty = scan.next();
					String name = scan.nextLine();
					String toString = String.format("%s@%s", qty,name);
					history.add(toString);
				}
				historyList = new String[history.size()][2];
				for (int i = 0 ; i < historyList.length ; i++){
					historyList[i] = history.get(i).split("@");
				}
				
				scan.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	public ArrayList<String> getHistory() {
		return history;
	}

	public void setHistory(ArrayList<String> history) {
		this.history = history;
	}

	public String[][] getHistoryList() {
		return historyList;
	}

	public void setHistoryList(String[][] historyList) {
		this.historyList = historyList;
	}

	public String getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
}
