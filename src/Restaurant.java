
import javax.swing.*;

import Object.LoginFile;
import Object.ReadFromMenuFile;

import java.awt.*;
public class Restaurant extends JFrame {
	private ReadFromMenuFile orderList = new ReadFromMenuFile();
	private Shopping shopping = new Shopping(this);
	private ShoppingCustomer shoppingCustomer = new ShoppingCustomer(this);
	private ShoppingDessert dessert = new ShoppingDessert(this);
	private ShoppingFood food = new ShoppingFood(this);
	private ShoppingDrink drink = new ShoppingDrink(this);
	private ShoppingCustomerDessert cusDessert = new ShoppingCustomerDessert(this);
	private ShoppingCustomerDrink cusDrink = new ShoppingCustomerDrink(this);
	private ShoppingCustomerFood cusFood = new ShoppingCustomerFood(this);
	private RestaurantMenu res = new RestaurantMenu(this);
	private Login login = new Login(this);
	private Register register = new Register(this);
	private CustomerSearch customerSearch = new CustomerSearch(this);
	private CustomerAdd customerAdd = new CustomerAdd(this);
	private StoreGUI store = new StoreGUI(this);
	private Payment payment = new Payment(this);
	private OrderGUI order = new OrderGUI(this);
	private OrderGUICustomer orderCustomer = new OrderGUICustomer(this);
	

	public ReadFromMenuFile getOrderList() {
		return orderList;
	}
	/**
	 * Launch the application.
	 */
	public void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Restaurant frame = new Restaurant();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the frame.
	 */
	public Restaurant() {
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1280, 750);
		setContentPane(login);
	}
	public void setMenu(){
		setContentPane(res);
	}
	public void setShopping(){
		res.setVisible(true);
		shopping.setBasket();
		setContentPane(shopping);
	}
	public void setShoppingCustomer(){
		shoppingCustomer.setBasket();
		setContentPane(shoppingCustomer);
	}
	public void setShoppingDessert(){
		dessert.setNumOrder();
		dessert.setBasket();
		setContentPane(dessert);
	}
	public void setShoppingFood(){
		food.setNumOrder();
		food.setBasket();
		setContentPane(food);
	}
	public void setShoppingDrink(){
		drink.setNumOrder();
		drink.setBasket();
		setContentPane(drink);
	}
	public void setShoppingCustomerFood() {
		cusFood.setNumOrder();
		cusFood.setBasket();
		setContentPane(cusFood);
	}
	public void setShoppingCustomerDrink() {
		cusDrink.setNumOrder();
		cusDrink.setBasket();
		setContentPane(cusDrink);
	}
	public void setShoppingCustomerDessert() {
		cusDessert.setNumOrder();
		cusDessert.setBasket();
		setContentPane(cusDessert);
	}
	public void setLogin(){
		setContentPane(login);
	}
	public void setRegister(){
		setContentPane(register);
	}
	public void setSearch(){
		setContentPane(customerSearch);
	}
	public void setAdd(){
		setContentPane(customerAdd);
	}
	public void setStore(){
		store.setStore();
		store.setMoney();
		setContentPane(store);
	}
	public void setPayment(){
		payment.setBasket();
		setContentPane(payment);
	}
	public void setOrder(){
		order.setBasket();
		setContentPane(order);
	}
	public void setOrderCustomer(){
		orderCustomer.setBasket();
		setContentPane(orderCustomer);
	}
	
	public Shopping getShopping() {
		return shopping;
	}
	public ShoppingDessert getDessert() {
		return dessert;
	}
	public ShoppingFood getFood() {
		return food;
	}
	public ShoppingDrink getDrink() {
		return drink;
	}
	public ShoppingCustomer getShoppingCustomer() {
		return shoppingCustomer;
	}
	public ShoppingCustomerDessert getShoppingCustomerDessert() {
		return cusDessert;
	}
	public ShoppingCustomerFood getShoppingCustomerFood() {
		return cusFood;
	}
	public ShoppingCustomerDrink getShoppingCustomerDrink() {
		return cusDrink;
	}
	public OrderGUICustomer getOrderCustomer() {
		return orderCustomer;
	}
}
