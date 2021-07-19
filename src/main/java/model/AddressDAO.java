package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import beans.AddressBean;
import utils.Utility;

public class AddressDAO implements GenericDAO<AddressBean> {

	
	private DataSource ds;
	
	public AddressDAO(DataSource ds) {
		this.ds = ds;
	}
	
	@Override
	public Collection<AddressBean> doRetrieveAll(String userId) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<AddressBean> addresses = new LinkedList<AddressBean>();

		String selectSQL = "SELECT * FROM address WHERE user_id = ?";
		

		try 
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, Integer.parseInt(userId));

			Utility.print("doRetrieveAll:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) 
			{
				AddressBean bean = new AddressBean();

				bean.setAddressNumber(rs.getInt("address_number"));
				bean.setUserId(rs.getInt("user_id"));
				bean.setStreetAddress(rs.getString("street_address"));
				bean.setCity(rs.getString("city"));
				bean.setZip(rs.getString("zip"));
				bean.setProvince(rs.getString("province"));
				bean.setPhone(rs.getString("phone"));
				bean.setInfo(rs.getString("info"));

				addresses.add(bean);
				Utility.print(bean.toString());
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

		return addresses;
	}
	
	@Override
	public void doSave(AddressBean bean) throws SQLException {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int userId = bean.getUserId();
		String streetAddress = bean.getStreetAddress();
		String city = bean.getCity();
		String zip = bean.getZip();
		String province = bean.getProvince();
		String phone = bean.getPhone();
		String info = bean.getInfo();
		
		String getAddressNumSQL = "SELECT MAX(address_number) FROM address WHERE user_id = ?";
		String insertSQL = "INSERT INTO address" + " (user_id, street_address, city, zip, province, phone,info) " + "VALUES (?, ?, ?, ?, ?, ?,?)";
		try 
		{
			connection = ds.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(getAddressNumSQL);
			
			preparedStatement.setInt(1, userId);
			preparedStatement.executeQuery();
			
			
			if(preparedStatement != null)
				preparedStatement.close();
				
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, streetAddress);
			preparedStatement.setString(3, city);
			preparedStatement.setString(4, zip);
			preparedStatement.setString(5, province);
			preparedStatement.setString(6, phone);
			preparedStatement.setString(7, info);
			
			Utility.print("doSave: " + preparedStatement.toString());
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
	public AddressBean doRetrieve(AddressBean address) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int addressNumber = address.getAddressNumber();
		int userId = address.getUserId();
		AddressBean bean = new AddressBean();
		
		String selectSQL = "SELECT * FROM user WHERE address_id = ? AND user_id = ?";
		
		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, addressNumber);
			preparedStatement.setInt(2, userId);

			Utility.print("doRetrieveAll:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) 
			{
				bean.setAddressNumber(rs.getInt("address_number"));
				bean.setUserId(rs.getInt("user_id"));
				bean.setStreetAddress(rs.getString("street_address"));
				bean.setCity(rs.getString("city"));
				bean.setZip(rs.getString("zip"));
				bean.setProvince(rs.getString("province"));
				bean.setPhone(rs.getString("phone"));
				bean.setInfo(rs.getString("info"));
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
	public void doUpdate(AddressBean address) throws SQLException 
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String updateSQL = "UPDATE address SET street_address = ?, city = ?, zip = ?, province = ?, phone = ?, info = ? WHERE user_id = ?";
		try 
		{
			connection = ds.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(updateSQL);

			preparedStatement.setString(1, address.getStreetAddress());
			preparedStatement.setString(2, address.getCity());
			preparedStatement.setString(3, address.getZip());
			preparedStatement.setString(4, address.getProvince());
			preparedStatement.setString(5, address.getPhone());
			preparedStatement.setString(6, address.getInfo());



			preparedStatement.setInt(7, address.getUserId ());

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
	public void doDelete(AddressBean address) throws SQLException 
	{
		/*
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		
		String deleteSQL = "DELETE FROM address WHERE address_number = ?";

		try 
		{
			Collection<AddressBean> collection = doRetrieveAll(Integer.toString(address.getUserId()));
			
			connection = ds.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, address.getAddressNumber());

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
		*/
	}
	
}
