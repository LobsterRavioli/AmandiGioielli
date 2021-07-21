package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import beans.UserBean;
import utils.Utility;

public class UserDAO implements GenericDAO<UserBean>
{
	private DataSource ds = null;

	public UserDAO(DataSource ds)
	{
		this.ds = ds;
	}

	@Override
	public UserBean doRetrieve(UserBean user) throws SQLException
	{
		// preparing some objects for connection
		Connection currentCon = null;
		PreparedStatement preparedStatement = null;

		String beanEmail = user.getEmail();
		String beanPassword = user.getPassword();
		String searchQuery = "SELECT * FROM user WHERE email = ? AND password = ?";

		// "System.out.println" prints in the console;
		Utility.print("Your email is " + beanEmail);
		Utility.print("Your password is " + beanPassword);
		Utility.print("Query: " + searchQuery);

		try
		{
			// connect to DB
			currentCon = ds.getConnection();
			preparedStatement = currentCon.prepareStatement(searchQuery);
			preparedStatement.setString(1, beanEmail);
			preparedStatement.setString(2, beanPassword);
			Utility.print("doRetrieve: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			boolean more = rs.next();

			// if user exists
			if (more)
			{
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				Boolean newsletter = rs.getBoolean("newsletter");
				String phone = rs.getString("phone");
				Boolean isActive = rs.getBoolean("is_active");
				int id = rs.getInt("user_id");

				Utility.print("Benvenuto " + firstName);
				user.setId(rs.getInt("user_id"));
				user.setFirstName(firstName);
				user.setLastName(lastName);
				user.setEmail(email);
				user.setLastName(lastName);
				user.setPassword(password);
				user.setNewsletter(newsletter);
				user.setPhone(phone);
				user.setActive(isActive);
				user.setId(id);
			}

			// if user does not exist return null
			if (!more)
			{
				Utility.print("Account non trovato!");
				return null;
			}

			Utility.print(user.toString());
		} finally
		{
			try
			{
				if (preparedStatement != null)
					preparedStatement.close();
			}

			finally
			{
				if (currentCon != null)
					currentCon.close();
			}
		}

		return user;
	}

	public void doSave(UserBean user) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO user (first_name, last_name, email, password, phone, newsletter) "
				+ "VALUES (?, ?, ?, ?, ?)";
		try
		{
			connection = ds.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setString(5, user.getPhone());
			preparedStatement.setBoolean(6, user.getNewsletter());
			Utility.print("doSave: " + preparedStatement.toString());
			preparedStatement.executeUpdate();

			connection.commit();

		} finally
		{
			try
			{
				if (preparedStatement != null)
					preparedStatement.close();
			} finally
			{
				if (connection != null)
					connection.close();

			}
		}
	}

	@Override
	public Collection<UserBean> doRetrieveAll(String order) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<UserBean> users = new LinkedList<UserBean>();

		String selectSQL = "SELECT * FROM user";

		if (order != null && !order.equals(""))
			selectSQL += " ORDER BY " + order;

		try
		{
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			Utility.print("doRetrieveAll:" + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next())
			{
				UserBean bean = new UserBean();

				bean.setId(rs.getInt("user_id"));
				bean.setFirstName(rs.getString("first_name"));
				bean.setLastName(rs.getString("last_name"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
				bean.setPhone(rs.getString("phone"));
				bean.setNewsletter(rs.getBoolean("newsletter"));
				bean.setActive(rs.getBoolean("is_active"));

				users.add(bean);
			}
		} finally
		{
			try
			{
				if (preparedStatement != null)
					preparedStatement.close();
			} finally
			{
				if (connection != null)
					connection.close();
			}
		}

		return users;
	}

	@Override
	public void doUpdate(UserBean user) throws SQLException
	{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "UPDATE user SET first_name = ?, last_name = ?, email = ?, "
				+ "password = ?, phone = ?, newsletter = ?, is_active = ? WHERE user_id = ?";
		try
		{
			connection = ds.getConnection();

			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setString(3, user.getEmail());
			preparedStatement.setString(4, user.getPassword());
			preparedStatement.setString(5, user.getPhone());
			preparedStatement.setBoolean(6, user.getNewsletter());
			preparedStatement.setBoolean(7, user.isActive());
			preparedStatement.setInt(8, user.getId());

			Utility.print("doSave: " + preparedStatement.toString());
			preparedStatement.executeUpdate();

		} finally
		{
			try
			{
				if (preparedStatement != null)
					preparedStatement.close();
			} finally
			{
				if (connection != null)
					connection.close();

			}
		}

	}

	@Override
	public void doDelete(UserBean item) throws SQLException
	{

	}

}
