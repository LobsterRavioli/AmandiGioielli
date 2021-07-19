package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;


import javax.sql.DataSource;
import beans.OrderBean;
import beans.OrderDetailBean;
import utils.Utility;

public class OrderDAO implements GenericDAO<OrderBean>
{
	private DataSource ds = null;
	
	public OrderDAO(DataSource ds)
	{
		this.ds = ds;
	}
	
	@Override
	public OrderBean doRetrieve(OrderBean order) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		OrderBean bean = new OrderBean();
		int code = order.getId();
		
		String selectSQL = "SELECT * FROM orders WHERE order_id = ?";

		try 
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			Utility.print("doRetrieve: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) 
			{
				bean.setId(rs.getInt("order_id"));
				bean.setUserId(rs.getInt("user_id"));
				bean.setDestination(rs.getString("destination"));
				bean.setTotalDiscount(rs.getDouble("total_discount"));
				bean.setTotalPrice(rs.getDouble("total_price"));
				bean.setNumProducts(rs.getInt("n_products"));
				bean.setStatus(rs.getString("status"));
			}

			Utility.print(bean.toString());
		} 
		finally 
		{
			try 
			{
				if (preparedStatement != null)
					preparedStatement.close();
			} 
			finally 
			{
				if (connection != null) 
					connection.close();
			}
		}

		return bean;
	
	}
	
	@Override
	public Collection<OrderBean> doRetrieveAll(String order) throws SQLException 
	{
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		
		Collection<OrderBean> orders = new ArrayList<OrderBean>();
		
		String selectSQL = "SELECT * FROM orders";
		
		if (order != null && !order.equals("")) 
			selectSQL += " ORDER BY " + order;
		
		try 
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			Utility.print("doRetrieveByKey: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) 
			{
				OrderBean bean = new OrderBean();
				
				bean.setId(rs.getInt("order_id"));
				bean.setUserId(rs.getInt("user_id"));
				bean.setDestination(rs.getString("destination"));
				bean.setTotalDiscount(rs.getDouble("total_discount"));
				bean.setTotalPrice(rs.getDouble("total_price"));
				bean.setNumProducts(rs.getInt("n_products"));
				bean.setStatus(rs.getString("status"));
				orders.add(bean);
			}
		} 
		finally 
		{
			try 
			{
				if (preparedStatement != null)
					preparedStatement.close();
			} 
			finally 
			{
				if (connection != null) 
					connection.close();
			}
		}

		return orders;
	}
	
	@Override
	public void doSave(OrderBean order) throws SQLException 
	{
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		OrderDetailsDAO model = new OrderDetailsDAO(ds);

		String insertSQL = "INSERT INTO orders (user_id,destination,total_discount,total_price,n_products,status) VALUES (?,?,?,?,?,?)";
		
		try 
		{
			connection = ds.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setInt(1, order.getUserId());
			preparedStatement.setString(2, order.getDestination());
			preparedStatement.setDouble(3, order.getTotalDiscount());
			preparedStatement.setDouble(4, order.getTotalPrice());
			preparedStatement.setInt(5, order.getNumProducts());
			preparedStatement.setString(6, order.getStatus());
			
			Utility.print("doSave: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			
			for(OrderDetailBean product: order.getItems())
			{
				product.setOrderId(order.getId());
				product.toString();
				model.doSave(product);
			}
			
			connection.commit();
		}
		
		finally 
		{
			try 
			{
				if (preparedStatement != null)
					preparedStatement.close();
			} 
			finally 
			{
				if (connection != null) 
					connection.close();
				
			}
		}
	}

	@Override
	public void doUpdate(OrderBean order) throws SQLException
	{
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE orders SET user_id = ?, destination = ?, total_discount = ?, "
				+ "total_price = ?, n_products = ?, status = ? WHERE order_id = ?";
		
		try 
		{
			connection = ds.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setInt(1, order.getUserId());
			preparedStatement.setString(2, order.getDestination());
			preparedStatement.setDouble(3, order.getTotalDiscount());
			preparedStatement.setDouble(4, order.getTotalPrice());
			preparedStatement.setInt(5, order.getNumProducts());
			preparedStatement.setString(6, order.getStatus());
			preparedStatement.setInt(7, order.getId());
			
		
			Utility.print("doUpdate: " + preparedStatement.toString());
			preparedStatement.executeUpdate();

			connection.commit();

		} 
		finally 
		{
			try 
			{
				if (preparedStatement != null)
					preparedStatement.close();
			} 
			finally 
			{
				if (connection != null) 
					connection.close();
			}
		}
	}



	@Override
	public void doDelete(OrderBean bean) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "DELETE FROM orders WHERE order_id = ?";

		try 
		{
			connection = ds.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, bean.getId());

			Utility.print("doDelete: " + preparedStatement.toString());
			preparedStatement.executeUpdate();

			connection.commit();

		}
		finally 
		{
			try 
			{
				if (preparedStatement != null)
					preparedStatement.close();
			} 
			finally 
			{
				if (connection != null) 
					connection.close();
			}
		}
	}

	public Collection<OrderBean> doRetrieveAllByUserId(String userId) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		
		Collection<OrderBean> orders = new ArrayList<OrderBean>();
		
		String selectSQL = "SELECT * FROM orders WHERE user_id = ?";
		
		
		try 
		{
			connection = ds.getConnection();
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, Integer.parseInt(userId));
			
			Utility.print("doRetrieveByKey: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			connection.commit();

			while (rs.next()) 
			{
				OrderBean bean = new OrderBean();
				
				bean.setId(rs.getInt("order_id"));
				bean.setUserId(rs.getInt("user_id"));
				bean.setDestination(rs.getString("destination"));
				bean.setTotalDiscount(rs.getDouble("total_discount"));
				bean.setTotalPrice(rs.getDouble("total_price"));
				bean.setNumProducts(rs.getInt("n_products"));
				bean.setStatus(rs.getString("status"));
				orders.add(bean);
			}
		} 
		finally 
		{
			try 
			{
				if (preparedStatement != null)
					preparedStatement.close();
			} 
			finally 
			{
				if (connection != null) 
					connection.close();
			}
		}

		return orders;
	}




}