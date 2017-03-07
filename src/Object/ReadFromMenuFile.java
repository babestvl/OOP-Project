package Object;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class ReadFromMenuFile {
	private String orderName, textSearch, atb1, atb2, atb3, menuName, numStock, numRestock, price, buyingPrice, numIndex,
	fileName, checkFileName = "" , revenue , expense;
	private String dir = "./src/textMenu/";
	private ArrayList<String> files = new ArrayList<String>();
	private ArrayList<String> menuList = new ArrayList<String>();
	private File[] listOfFiles;
	private File folder, file;
	private ArrayList<String> keep = new ArrayList<String>();
	private String[][] sent;
	private int size = 0, index , countAmountSearch = 0 , qty = 0;
	private String[][] keepAllList = new String[5][10];
	private String[] keepFile = new String[10];
	private ArrayList<String> searchList = new ArrayList<String>();
	private ArrayList<String> orderList = new ArrayList<String>();

	public ReadFromMenuFile(String atb) {
		this.textSearch = atb;
	}

	public ReadFromMenuFile(String fileName, int index) {
		this.checkFileName = fileName;
		this.index = index;
	}
	public ReadFromMenuFile(){
		allFiles();
	}

	public void allFiles() {

		folder = new File(dir);
		listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {

			if (listOfFiles[i].isFile() && listOfFiles[i].getName().endsWith(".txt") ) {
				files.add(listOfFiles[i].getName());
			}
		}
	}

	public String[][] allMenu(){
//		allFiles();
		Scanner scan  = null;
		sent = new String[15][6];
		size = 0;
		for (int i = 0; i < files.size(); i++) {
			file = new File(dir + files.get(i));
			if (file.exists()) {
				try {
					scan = new Scanner(new FileInputStream(file));
					while (scan.hasNext()) {
						fileName = scan.next();
						numIndex = scan.next();
						numStock = scan.next();
						numRestock = scan.next();
						price = scan.next();
						buyingPrice = scan.next();
						atb1 = scan.next();
						atb2 = scan.next();
						atb3 = scan.next();
						menuName = scan.nextLine();
						String toString = String.format("%s@%s@%s@%s@%s@%s", numStock, price, buyingPrice, menuName,
								numIndex, fileName);
						sent[size] = toString.split("@");
						size++;
					}
					scan.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		return sent ;
	}

	public String[][] fromFile() {
		allFiles();
		Scanner scan = null;
		for (int i = 0; i < files.size(); i++) {
			file = new File(dir + files.get(i));
			if (file.exists()) {
				try {
					scan = new Scanner(new FileInputStream(file));
					while (scan.hasNext()) {
						fileName = scan.next();
						numIndex = scan.next();
						numStock = scan.next();
						numRestock = scan.next();
						price = scan.next();
						buyingPrice = scan.next();
						atb1 = scan.next();
						atb2 = scan.next();
						atb3 = scan.next();
						menuName = scan.nextLine();
						String toString = String.format("%s@%s@%s@%s@%s@%s", numStock, price, buyingPrice, menuName,
								numIndex, fileName);
						if (menuName.toLowerCase().contains(textSearch.toLowerCase()) || atb1.equalsIgnoreCase(textSearch)
								|| atb2.equalsIgnoreCase(textSearch) || atb3.equalsIgnoreCase(textSearch)) {
							countAmountSearch++;
							searchList.add(toString);
						}
					}
					scan.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		sent = new String[countAmountSearch][6];
		for (int i = 0 ; i < searchList.size() ; i++){
			sent[i] = searchList.get(i).split("@");
		}
		return sent;
	}
	public void order(String fileName , int index , String qty){
		this.checkFileName = fileName;
		this.index = index;
		this.qty = Integer.parseInt(qty);
		keep = new ArrayList<String>();
		File file = new File("./src/textMenu/" + (checkFileName) + ".txt");
		keepFile = new String[5];
		if (file.exists()){
			try{
				Scanner scan = new Scanner(new FileInputStream(file));
				while (scan.hasNext()) {
					fileName = scan.next();
					numIndex = scan.next();
					numStock = scan.next();
					numRestock = scan.next();
					price = scan.next();
					buyingPrice = scan.next();
					atb1 = scan.next();
					atb2 = scan.next();
					atb3 = scan.next();
					menuName = scan.nextLine();
					if (Integer.parseInt(numIndex) == index){
						numStock = Integer.toString(Integer.parseInt(numStock) - this.qty);
						String toString = String.format("%s@%s@%d", menuName,price,this.qty);
						orderList.add(toString);
					} 
					String toString2 = String.format("%s %s %s %s %s %s %s %s %s%s", fileName , numIndex , numStock , numRestock , price , buyingPrice , atb1 ,atb2 ,atb3 ,menuName );
					keep.add(toString2);
				}
				scan.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			file.delete();
			File fileAfterOrder = new File("./src/textMenu/" + (checkFileName) + ".txt");
			PrintStream toFile = null;
			if (!fileAfterOrder.exists()){
				fileAfterOrder.getParentFile().mkdirs();
				try {
					fileAfterOrder.createNewFile();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}

				try {
					toFile = new PrintStream(new FileOutputStream(fileAfterOrder, true));
				} catch (FileNotFoundException fnfe) {
					fnfe.printStackTrace();
				}
				for (int k = 0 ; k < keepFile.length ; k++){
					toFile.println(keep.get(k));
				}
			}
		}
	}

	public String[][] getOrderListForShopping(){
		String[][] orderListForShopping = new String[orderList.size()][3];
		for (int i = 0 ; i < orderList.size() ; i++){
			orderListForShopping[i] = orderList.get(i).split("@");
		}
		String[][] newOrderListForShopping = new String[orderList.size()][3];
		for (int j = 0 ; j < orderList.size() ; j++){
			newOrderListForShopping[j][0] = (j+1)+"";
			newOrderListForShopping[j][1] = orderListForShopping[j][0];
			newOrderListForShopping[j][2] = orderListForShopping[j][2];
		}
		return newOrderListForShopping;
	}
	public String[][] getOrderListForBasketAndPayment(){
		String[][] orderListForBasketAndPayment = new String[orderList.size()][3];
		for (int i = 0 ; i < orderList.size() ; i++){
			orderListForBasketAndPayment[i] = orderList.get(i).split("@");
		}
		String[][] neworderListForBasketAndPayment = new String[orderList.size()][5];
		for (int k = 0 ; k < orderList.size() ; k++){
			neworderListForBasketAndPayment[k][0] = (k+1)+"";
			neworderListForBasketAndPayment[k][1] = orderListForBasketAndPayment[k][0];
			neworderListForBasketAndPayment[k][2] = orderListForBasketAndPayment[k][2];
			neworderListForBasketAndPayment[k][3] = orderListForBasketAndPayment[k][1];
			neworderListForBasketAndPayment[k][4] = Integer.toString(Integer.parseInt(orderListForBasketAndPayment[k][1])*Integer.parseInt(orderListForBasketAndPayment[k][2]));
		}
		return neworderListForBasketAndPayment;
	}
	public String getTotalPrice(){
		int totalPrice = 0;
		String[][] orderListForShopping = new String[orderList.size()][3];
		for (int i = 0 ; i < orderList.size() ; i++){
			orderListForShopping[i] = orderList.get(i).split("@");
		}
		for (int j = 0 ; j < orderList.size() ; j++){
			totalPrice += Integer.parseInt(orderListForShopping[j][1]) * Integer.parseInt(orderListForShopping[j][2]) ;
		}
		return Integer.toString(totalPrice);
	}
	public String getShippingFee(){
		int totalOrder = 0;
		String[][] orderListForShopping = new String[orderList.size()][3];
		for (int i = 0 ; i < orderList.size() ; i++){
			orderListForShopping[i] = orderList.get(i).split("@");
		}
		for (int j = 0 ; j < orderList.size() ; j++){
			totalOrder += Integer.parseInt(orderListForShopping[j][2]) ;
		}
		return Integer.toString(((totalOrder/5)+1)*50);
	}

	public void restock(String restockSelect){
		Scanner scan = null;
		boolean check = false;
		PrintStream print = null;
		for (int i = 0; i < files.size(); i++) {
			file = new File(dir + files.get(i));

			keep = new ArrayList<String>();
			if (file.exists()) {
				try {
					scan = new Scanner(new FileInputStream(file));
					while (scan.hasNext()){
						fileName = scan.next();
						numIndex = scan.next();
						numStock = scan.next();
						numRestock = scan.next();
						price = scan.next();
						buyingPrice = scan.next();
						atb1 = scan.next();
						atb2 = scan.next();
						atb3 = scan.next();
						menuName = scan.nextLine();
						
						if (menuName.equals(restockSelect)){
							numStock = Integer.toString(Integer.parseInt(numStock) + Integer.parseInt(numRestock));
							check = true;
							
							File money = new File("./src/money/money.txt");
							try {
								if (money.exists()) {
									Scanner scanMoney = new Scanner(money);
									revenue = scanMoney.next();
									expense = scanMoney.next();
									scanMoney.close();
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
							expense = Integer.toString(Integer.parseInt(expense)+ (Integer.parseInt(numRestock)*Integer.parseInt(buyingPrice)));
							money.delete();
							PrintStream toFile = null;
							if (!money.exists()){
								money.getParentFile().mkdirs();
								try {
									money.createNewFile();
								} catch (IOException ioe) {
									ioe.printStackTrace();
								}

								try {
									toFile = new PrintStream(new FileOutputStream(money, true));
								} catch (FileNotFoundException fnfe) {
									fnfe.printStackTrace();
								}
									toFile.print(revenue+" ");
									toFile.print(expense);
							}
						}
						String toString = String.format("%s %s %s %s %s %s %s %s %s%s", fileName , numIndex , numStock , numRestock , price , buyingPrice , atb1 ,atb2 ,atb3 ,menuName );
						keep.add(toString);
						
					}
					if (check){
						File fileDelete = new File("./src/textMenu/" + (fileName) + ".txt");
						fileDelete.delete();
						File fileAfterRestock = new File("./src/textMenu/" + (fileName) + ".txt");
						PrintStream toFile = null;
						if (!fileAfterRestock.exists()){
							fileAfterRestock.getParentFile().mkdirs();
							try {
								fileAfterRestock.createNewFile();
							} catch (IOException ioe) {
								ioe.printStackTrace();
							}

							try {
								toFile = new PrintStream(new FileOutputStream(fileAfterRestock, true));
							} catch (FileNotFoundException fnfe) {
								fnfe.printStackTrace();
							}
							for (int k = 0 ; k < keep.size() ; k++){
								toFile.println(keep.get(k));
							}
						}
						check = false;
					}

					scan.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void removeOrderList(String menuSelect , String numQtyOrder){
//		allFiles();
		Scanner scan = null;
		boolean check = false;
		PrintStream print = null;
		for (int i = 0; i < files.size(); i++) {
			file = new File(dir + files.get(i));
			keep = new ArrayList<String>();
			if (file.exists()) {
				try {
					scan = new Scanner(new FileInputStream(file));
					while (scan.hasNext()){
						fileName = scan.next();
						numIndex = scan.next();
						numStock = scan.next();
						numRestock = scan.next();
						price = scan.next();
						buyingPrice = scan.next();
						atb1 = scan.next();
						atb2 = scan.next();
						atb3 = scan.next();
						menuName = scan.nextLine();
						if (menuName.equals(menuSelect)){
							numStock = Integer.toString(Integer.parseInt(numStock) + Integer.parseInt(numQtyOrder));
							check = true;	
						}
						String toString = String.format("%s %s %s %s %s %s %s %s %s%s", fileName , numIndex , numStock , numRestock , price , buyingPrice , atb1 ,atb2 ,atb3 ,menuName );
						keep.add(toString);
						
					}
					if (check){
						File fileDelete = new File("./src/textMenu/" + (fileName) + ".txt");
						fileDelete.delete();
						File fileAfterRemove = new File("./src/textMenu/" + (fileName) + ".txt");
						PrintStream toFile = null;
						if (!fileAfterRemove.exists()){
							fileAfterRemove.getParentFile().mkdirs();
							try {
								fileAfterRemove.createNewFile();
							} catch (IOException ioe) {
								ioe.printStackTrace();
							}

							try {
								toFile = new PrintStream(new FileOutputStream(fileAfterRemove, true));
							} catch (FileNotFoundException fnfe) {
								fnfe.printStackTrace();
							}
							for (int k = 0 ; k < keep.size() ; k++){
								toFile.println(keep.get(k));
							}
						}
						check = false;
					}

					scan.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		String[][] orderListForRemove = new String[orderList.size()][3];
		for (int i = 0 ; i < orderList.size() ; i++){
			orderListForRemove[i] = orderList.get(i).split("@");
			if (orderListForRemove[i][0].equals(menuSelect)){
				orderList.remove(i);
			}
		}	
	}
	public void change(String menuSelect , String numChange){
//		allFiles();
		Scanner scan = null;
		boolean check = false;
		PrintStream print = null;
		for (int i = 0; i < files.size(); i++) {
			file = new File(dir + files.get(i));

			keep = new ArrayList<String>();
			if (file.exists()) {
				try {
					scan = new Scanner(new FileInputStream(file));
					while (scan.hasNext()){
						fileName = scan.next();
						numIndex = scan.next();
						numStock = scan.next();
						numRestock = scan.next();
						price = scan.next();
						buyingPrice = scan.next();
						atb1 = scan.next();
						atb2 = scan.next();
						atb3 = scan.next();
						menuName = scan.nextLine();
						
						if (menuName.equals(menuSelect)){
							numStock = Integer.toString(Integer.parseInt(numStock) - Integer.parseInt(numChange));
							check = true;
						}
						String toString = String.format("%s %s %s %s %s %s %s %s %s%s", fileName , numIndex , numStock , numRestock , price , buyingPrice , atb1 ,atb2 ,atb3 ,menuName );
						keep.add(toString);
						
					}
					if (check){
						File fileDelete = new File("./src/textMenu/" + (fileName) + ".txt");
						fileDelete.delete();
						File fileAfterRestock = new File("./src/textMenu/" + (fileName) + ".txt");
						PrintStream toFile = null;
						if (!fileAfterRestock.exists()){
							fileAfterRestock.getParentFile().mkdirs();
							try {
								fileAfterRestock.createNewFile();
							} catch (IOException ioe) {
								ioe.printStackTrace();
							}

							try {
								toFile = new PrintStream(new FileOutputStream(fileAfterRestock, true));
							} catch (FileNotFoundException fnfe) {
								fnfe.printStackTrace();
							}
							for (int k = 0 ; k < keep.size() ; k++){
								toFile.println(keep.get(k));
							}
						}
						check = false;
					}

					scan.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		boolean checkSame = false;
		String[][] orderListForChange = new String[1][3];
		for (int i = 0 ; i < orderList.size() ; i++){
			if (orderList.get(i).contains(menuSelect) && !checkSame){
				orderListForChange[0] = orderList.get(i).split("@");
				orderList.remove(i);
				checkSame = true;
				orderList.add(i, String.format("%s@%s@%d", orderListForChange[0][0] , orderListForChange[0][1] ,Integer.parseInt(orderListForChange[0][2])+Integer.parseInt(numChange)));
			}
		}	
		checkSame = false;
	}

	public ArrayList<String> getOrderList() {
		return orderList;
	}

}
