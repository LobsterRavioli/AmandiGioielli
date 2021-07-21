package beans;

public class OrderDetailBean
{

	public OrderDetailBean()
	{
		id = -1;
		orderId = -1;
		productId = -1;
		name = "";
		quantity = 0;
		price = 0.0;
		taxRate = 0.0;
		discount = 0.0;
	}

	public int getId()
	{
		return this.id;
	}

	public void setId(int orderDetailsId)
	{
		this.id = orderDetailsId;
	}

	public int getOrderId()
	{
		return orderId;
	}

	public void setOrderId(int orderId)
	{
		this.orderId = orderId;
	}

	public int getProductId()
	{
		return productId;
	}

	public void setProductId(int productId)
	{
		this.productId = productId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{

		this.name = name;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public double getTaxRate()
	{
		return taxRate;
	}

	public void setTaxRate(double taxRate)
	{
		this.taxRate = taxRate;
	}

	public double getDiscount()
	{
		return discount;
	}

	public void setDiscount(double discount)
	{
		this.discount = discount;
	}

	@Override
	public String toString()
	{
		return "OrderDetailBean [id=" + id + ", orderId=" + orderId + ", productId=" + productId + ", name=" + name
				+ ", quantity=" + quantity + ", price=" + price + ", taxRate=" + taxRate + ", discount=" + discount
				+ "]";
	}

	private int id;
	private int orderId;
	private int productId;
	private String name;
	private int quantity;
	private double price;
	private double taxRate;
	private double discount;

}
