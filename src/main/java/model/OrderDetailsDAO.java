package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;


import javax.sql.DataSource;

import beans.OrderDetailBean;

import utils.Utility;

public class OrderDetailsDAO implements GenericDAO<OrderDetailBean>
{
	
	
	private DataSource ds;
	
	public OrderDetailsDAO(DataSource ds) 
	{
		this.ds = ds;
	}
	
	@Override
	public OrderDetailBean doRetrieve(OrderDetailBean orderDetails) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;


		int id = orderDetails.getId();
		
		OrderDetailBean bean = new OrderDetailBean();
		
		String selectSQL = "SELECT * FROM product WHERE order_details_id = ?";

		try 
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			Utility.print("doRetrieve: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			
			while (rs.next()) 
			{

				bean.setId(rs.getInt("product_id"));
				bean.setOrderId(rs.getInt("order_id"));
				bean.setProductId(rs.getInt("product_id"));
				bean.setName(rs.getString("p_name"));
				bean.setQuantity(rs.getInt("p_quantity"));
				bean.setPrice(rs.getDouble("p_price"));
				bean.setTaxRate(rs.getDouble("p_tax_rate"));
				bean.setDiscount(rs.getDouble("p_discount"));
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
	public Collection<OrderDetailBean> doRetrieveAll(String orderId) throws SQLException 
	{
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ArrayList<OrderDetailBean> beans = new ArrayList<OrderDetailBean>();
		

		String selectSQL = "SELECT * FROM order_details WHERE order_id = ?";
		
		try 
		{
			connection = ds.getConnection();
			
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setInt(1, Integer.parseInt(orderId));

			Utility.print("doRetrieveByKey: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) 
			{
				OrderDetailBean bean = new OrderDetailBean();
				
				bean.setId(rs.getInt("order_details_id"));
				bean.setProductId(rs.getInt("product_id"));
				bean.setOrderId(rs.getInt("orderId"));
				bean.setDiscount(rs.getDouble("p_discount"));
				bean.setName(rs.getString("p_name"));
				bean.setPrice(rs.getDouble("p_price"));
				bean.setTaxRate(rs.getDouble("p_tax_rate"));
				bean.setQuantity(rs.getInt("p_quantity"));
				
				beans.add(bean);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
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
		
		return beans;
	}




	@Override
	public void doSave(OrderDetailBean orderDetails) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO order_details (order_id,product_id,p_name,p_quantity,p_price,p_tax_rate,p_discount) VALUES (?,?,?,?,?,?,?)";
		
		try 
		{
			connection = ds.getConnection();

			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setInt(1, orderDetails.getOrderId());
			preparedStatement.setInt(2, orderDetails.getProductId());
			preparedStatement.setString(3, orderDetails.getName());
			preparedStatement.setInt(4, orderDetails.getQuantity());
			preparedStatement.setDouble(5, orderDetails.getPrice());
			preparedStatement.setDouble(6, orderDetails.getTaxRate());
			preparedStatement.setDouble(7, orderDetails.getDiscount());
			
			Utility.print("doSave: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			
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
	public void doUpdate(OrderDetailBean orderDetails) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE order_details SET order_id=?,product_id=?,p_name=?"
				+ ",p_quantity=?,p_price=?,p_tax_rate=?,p_discount=? WHERE order_details_id = ?";
		
		try 
		{
			connection = ds.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setInt(1, orderDetails.getOrderId());
			preparedStatement.setInt(2, orderDetails.getProductId());
			preparedStatement.setString(3, orderDetails.getName());
			preparedStatement.setInt(4, orderDetails.getQuantity());
			preparedStatement.setDouble(5, orderDetails.getPrice());
			preparedStatement.setDouble(6, orderDetails.getTaxRate());
			preparedStatement.setDouble(7, orderDetails.getDiscount());
			preparedStatement.setInt(8, orderDetails.getId());
			
		
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
	public void doDelete(OrderDetailBean orderDetails) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String deleteSQL = "DELETE FROM order_details WHERE order_details_id = ?";

		try 
		{
			connection = ds.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, orderDetails.getId());

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
	
	

}
