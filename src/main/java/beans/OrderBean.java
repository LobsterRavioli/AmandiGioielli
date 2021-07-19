package beans;

import java.util.ArrayList;

public class OrderBean
{
	public OrderBean(){
		id = -1;
		userId = -1;
		destination = null;
		totalDiscount = 0;
		totalPrice = 0;
		numProducts = 0;
		items = new ArrayList<OrderDetailBean>();
	}

	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) 
	{
		this.destination = destination;
	}

	public String getStatus() 
	{
		return status;
	}

	public void setStatus(String status) 
	{
		this.status = status;
	}

	public double getTotalDiscount() {
		return totalDiscount;
	}

	public void setTotalDiscount(double totalDiscount) {
		this.totalDiscount = totalDiscount;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getNumProducts() 
	{
		return numProducts;
	}

	public void setNumProducts(int numProducts) 
	{
		this.numProducts = numProducts;
	}
	
	public ArrayList<OrderDetailBean> getItems()
	{
		return this.items;
	}
	public void addItem(OrderDetailBean bean)
	{
		this.items.add(bean);
	}

	private int id;
	private int userId;
	private String destination;
	private String status;
	private double totalDiscount;
	private double totalPrice;
	private int numProducts;
	
	private ArrayList<OrderDetailBean> items;
	
}
