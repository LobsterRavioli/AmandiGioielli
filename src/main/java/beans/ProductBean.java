package beans;

import java.io.Serializable;
import java.util.ArrayList;

public class ProductBean implements Serializable
{

	private static final long serialVersionUID = 1L;

	public ProductBean()
	{
		categories = new ArrayList<CategoryBean>();
		code = -1;
		name = "";
		description = "";
		shortDescription = "";
		price = 0.0;
		quantity = 0;
		taxRate = 0.0;
		discount = 0.0;
		isActive = true;
	}

	public int getCode()
	{
		return code;
	}

	public void setCode(int code)
	{
		this.code = code;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getShortDescription()
	{
		return shortDescription;
	}

	public void setShortDescription(String shortDescription)
	{
		this.shortDescription = shortDescription;
	}

	public double getPrice()
	{
		return price;
	}

	public double getRealPrice()
	{
		double realPrice = (this.price - (this.price * this.discount / 100)) + (this.price * (this.taxRate / 100));

		return (Math.floor(realPrice * 100) / 100);
	}

	public String getRealPriceString()
	{
		return Double.toString(this.getRealPrice()).replace('.', ',');
	}

	public String getRealPriceStringAll()
	{
		return Double.toString(this.getRealPrice() * quantity).replace('.', ',');
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public int getQuantity()
	{
		return quantity;
	}

	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	public boolean isEmpty()
	{

		return quantity == 0;
	}

	@Override
	public boolean equals(Object other)
	{
		return (this.getCode() == ((ProductBean) other).getCode());
	}

	public boolean equalsName(ProductBean bean)
	{
		return (this.getName().equals(bean.getName()));
	}

	@Override
	public String toString()
	{
		return "ProductBean [code=" + code + ", name=" + name + ", description=" + description + ", shortDescription="
				+ shortDescription + ", price=" + price + ", quantity=" + quantity + ", taxRate=" + taxRate
				+ ", discount=" + discount + ", isActive=" + isActive + ", categories=" + categories + ", url=" + url
				+ "]";
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

	public boolean isActive()
	{
		return isActive;
	}

	public void setActive(boolean isActive)
	{
		this.isActive = isActive;
	}

	public void addCategory(CategoryBean category)
	{
		this.categories.add(category);
	}

	public ArrayList<CategoryBean> getCategories()
	{
		return categories;

	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getUrl()
	{
		return url;
	}

	private int code;
	private String name;
	private String description;
	private String shortDescription;
	private double price;
	private int quantity;
	private double taxRate;
	private double discount;
	private boolean isActive;
	private ArrayList<CategoryBean> categories;
	private String url;

}