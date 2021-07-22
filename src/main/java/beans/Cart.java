package beans;

import java.util.ArrayList;

public class Cart
{
	ArrayList<ProductBean> items;

	public Cart()
	{
		items = new ArrayList<ProductBean>();
	}

	public void addItem(ProductBean item)
	{
		if (items.contains(item))
		{
			ProductBean product = (ProductBean) items.get(items.indexOf(item));
			product.setQuantity(product.getQuantity() + 1);
			return;
		}

		((ProductBean) item).setQuantity(1);
		items.add(item);
	}

	public void deleteItem(ProductBean item)
	{
		items.remove(item);
	}

	public void removeOneItem(ProductBean item)
	{
		if (items.contains(item))
		{
			ProductBean product = (ProductBean) items.get(items.indexOf(item));

			if (product.getQuantity() - 1 > 0)
				product.setQuantity(product.getQuantity() - 1);
			else
				deleteItem(item);
		}

		else
			deleteItem(item);

	}

	public ArrayList<ProductBean> getItems()
	{
		return items;
	}

	public int getTotalQuantity()
	{
		int tot = 0;
		for (ProductBean product : items)
		{
			tot += product.getQuantity();
		}
		return tot;
	}

	public int getProductQuantity(int id)
	{
		int qty = 0;
		for (ProductBean product : items)
		{
			if (product.getCode() == id)
				qty = product.getQuantity();
		}

		return qty;
	}

	public void deleteItems()
	{
		items.clear();
	}

	public double getTotalValue()
	{
		double tot = 0;
		for (ProductBean item : items)
			tot += (item.getRealPrice() * item.getQuantity());

		return (Math.floor(tot * 100) / 100);

	}

}