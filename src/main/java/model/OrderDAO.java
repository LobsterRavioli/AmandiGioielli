package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

import beans.OrderBean;
import beans.OrderDetailBean;
import beans.ProductBean;
import utils.Utility;

public class OrderDAO implements GenericDAO<OrderBean> {
    private DataSource ds = null;

    public OrderDAO(DataSource ds) {
	this.ds = ds;
    }

    @Override
    public OrderBean doRetrieve(OrderBean order) throws SQLException {
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	OrderBean bean = new OrderBean();
	int code = order.getId();

	String selectSQL = "SELECT * FROM orders WHERE order_id = ?";

	try {
	    connection = ds.getConnection();
	    preparedStatement = connection.prepareStatement(selectSQL);
	    preparedStatement.setInt(1, code);

	    Utility.print("doRetrieve: " + preparedStatement.toString());
	    ResultSet rs = preparedStatement.executeQuery();

	    while (rs.next()) {
		bean.setId(rs.getInt("order_id"));
		bean.setUserId(rs.getInt("user_id"));
		bean.setDestination(rs.getString("destination"));
		bean.setTotalDiscount(rs.getDouble("total_discount"));
		bean.setTotalPrice(rs.getDouble("total_price"));
		bean.setNumProducts(rs.getInt("n_products"));
		bean.setStatus(rs.getString("status"));
		bean.setData(rs.getDate("data"));
	    }

	    Utility.print(bean.toString());
	} finally {
	    try {
		if (preparedStatement != null)
		    preparedStatement.close();
	    } finally {
		if (connection != null)
		    connection.close();
	    }
	}

	return bean;

    }

    public Collection<OrderBean> retrieveByData(String order, Date startDate, Date endDate) throws SQLException {
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	Collection<OrderBean> orders = new ArrayList<OrderBean>();

	String selectSQL = "SELECT * from orders WHERE data >= ? AND data <= ?";

	if (order != null && !order.equals(""))
	    selectSQL += " ORDER BY " + order;

	try {
	    connection = ds.getConnection();
	    preparedStatement = connection.prepareStatement(selectSQL);
	    preparedStatement.setDate(1, startDate);
	    preparedStatement.setDate(2, endDate);
	    Utility.print("retrieveByDate: " + preparedStatement.toString());
	    ResultSet rs = preparedStatement.executeQuery();

	    while (rs.next()) {
		OrderBean bean = new OrderBean();

		bean.setId(rs.getInt("order_id"));
		bean.setUserId(rs.getInt("user_id"));
		bean.setDestination(rs.getString("destination"));
		bean.setTotalDiscount(rs.getDouble("total_discount"));
		bean.setTotalPrice(rs.getDouble("total_price"));
		bean.setNumProducts(rs.getInt("n_products"));
		bean.setStatus(rs.getString("status"));
		bean.setData(rs.getDate("data"));
		orders.add(bean);
	    }
	} finally {
	    try {
		if (preparedStatement != null)
		    preparedStatement.close();
	    } finally {
		if (connection != null)
		    connection.close();
	    }
	}

	return orders;

    }

    public Collection<OrderBean> retrieveByUserId(String order, int id) throws SQLException {
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	Collection<OrderBean> orders = new ArrayList<OrderBean>();

	String selectSQL = "SELECT * FROM orders where user_id = ?";

	if (order != null && !order.equals(""))
	    selectSQL += " ORDER BY " + order;

	try {
	    connection = ds.getConnection();
	    preparedStatement = connection.prepareStatement(selectSQL);
	    preparedStatement.setInt(1, id);
	    Utility.print("retrieveByUserId: " + preparedStatement.toString());
	    ResultSet rs = preparedStatement.executeQuery();

	    while (rs.next()) {
		OrderBean bean = new OrderBean();

		bean.setId(rs.getInt("order_id"));
		bean.setUserId(rs.getInt("user_id"));
		bean.setDestination(rs.getString("destination"));
		bean.setTotalDiscount(rs.getDouble("total_discount"));
		bean.setTotalPrice(rs.getDouble("total_price"));
		bean.setNumProducts(rs.getInt("n_products"));
		bean.setStatus(rs.getString("status"));
		bean.setData(rs.getDate("data"));
		orders.add(bean);
	    }
	} finally {
	    try {
		if (preparedStatement != null)
		    preparedStatement.close();
	    } finally {
		if (connection != null)
		    connection.close();
	    }
	}

	return orders;

    }

    @Override
    public Collection<OrderBean> doRetrieveAll(String order) throws SQLException {

	Connection connection = null;
	PreparedStatement preparedStatement = null;

	Collection<OrderBean> orders = new ArrayList<OrderBean>();

	String selectSQL = "SELECT * FROM orders";

	if (order != null && !order.equals(""))
	    selectSQL += " ORDER BY " + order;

	try {
	    connection = ds.getConnection();
	    preparedStatement = connection.prepareStatement(selectSQL);
	    Utility.print("doRetrieveByKey: " + preparedStatement.toString());
	    ResultSet rs = preparedStatement.executeQuery();

	    while (rs.next()) {
		OrderBean bean = new OrderBean();

		bean.setId(rs.getInt("order_id"));
		bean.setUserId(rs.getInt("user_id"));
		bean.setDestination(rs.getString("destination"));
		bean.setTotalDiscount(rs.getDouble("total_discount"));
		bean.setTotalPrice(rs.getDouble("total_price"));
		bean.setNumProducts(rs.getInt("n_products"));
		bean.setStatus(rs.getString("status"));
		bean.setData(rs.getDate("data"));
		orders.add(bean);
	    }
	} finally {
	    try {
		if (preparedStatement != null)
		    preparedStatement.close();
	    } finally {
		if (connection != null)
		    connection.close();
	    }
	}

	return orders;
    }

    @Override
    public void doSave(OrderBean order) throws SQLException {

	Connection connection = null;
	PreparedStatement preparedStatement = null;
	OrderDetailsDAO model = new OrderDetailsDAO(ds);
	ProductDAO productModel = new ProductDAO(ds);

	String insertSQL = "INSERT INTO orders (order_id, user_id,destination,total_discount,total_price,n_products,status, data) VALUES (?,?,?,?,?,?,?,?)";
	String getMaxKey = "SELECT MAX(order_id) FROM orders";
	int orderId = 0;
	try {
	    connection = ds.getConnection();

	    Statement smt = connection.createStatement();

	    ResultSet rs = smt.executeQuery(getMaxKey);

	    while (rs.next()) {
	    	orderId = rs.getInt(1) + 1;
	    	order.setId(orderId);
	    }

	    preparedStatement = connection.prepareStatement(insertSQL);
	    preparedStatement.setInt(1, order.getId());
	    preparedStatement.setInt(2, order.getUserId());
	    preparedStatement.setString(3, order.getDestination());
	    preparedStatement.setDouble(4, order.getTotalDiscount());
	    preparedStatement.setDouble(5, order.getTotalPrice());
	    preparedStatement.setInt(6, order.getNumProducts());
	    preparedStatement.setString(7, order.getStatus());
	    // data odierna
	    long millis = System.currentTimeMillis();
	    preparedStatement.setDate(8, new java.sql.Date(millis));
	    

	    Utility.print("doSave: " + preparedStatement.toString());
	    preparedStatement.executeUpdate();

	    for (OrderDetailBean product : order.getItems()) {
			product.setOrderId(order.getId());
			Utility.print(product.toString());
			model.doSave(product);
			ProductBean temp = new ProductBean();
			temp.setCode(product.getProductId());
			ProductBean productDB = productModel.doRetrieve(temp);
			productDB.setQuantity(productDB.getQuantity() - product.getQuantity());
			productModel.doUpdate(productDB);
			System.out.println("Fine ciclo");
	    }

	}

	finally {
	    try {
		if (preparedStatement != null)
		    preparedStatement.close();
	    } finally {
		if (connection != null)
		    connection.close();

	    }
	}
    }

    @Override
    public void doUpdate(OrderBean order) throws SQLException {

	Connection connection = null;
	PreparedStatement preparedStatement = null;

	String updateSQL = "UPDATE orders SET user_id = ?, destination = ?, total_discount = ?, "
		+ "total_price = ?, n_products = ?, status = ? WHERE order_id = ?, data = ?";

	try {
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
	    preparedStatement.setDate(8, order.getData());

	    Utility.print("doUpdate: " + preparedStatement.toString());
	    preparedStatement.executeUpdate();

	    connection.commit();

	} finally {
	    try {
		if (preparedStatement != null)
		    preparedStatement.close();
	    } finally {
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

	try {
	    connection = ds.getConnection();
	    connection.setAutoCommit(false);
	    preparedStatement = connection.prepareStatement(deleteSQL);
	    preparedStatement.setInt(1, bean.getId());

	    Utility.print("doDelete: " + preparedStatement.toString());
	    preparedStatement.executeUpdate();

	    connection.commit();

	} finally {
	    try {
		if (preparedStatement != null)
		    preparedStatement.close();
	    } finally {
		if (connection != null)
		    connection.close();
	    }
	}
    }

    public Collection<OrderBean> doRetrieveAllByUserId(String userId) throws SQLException {
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	Collection<OrderBean> orders = new ArrayList<OrderBean>();

	String selectSQL = "SELECT * FROM orders WHERE user_id = ?";

	try {
	    connection = ds.getConnection();
	    connection.setAutoCommit(false);

	    preparedStatement = connection.prepareStatement(selectSQL);
	    preparedStatement.setInt(1, Integer.parseInt(userId));

	    Utility.print("doRetrieveAllByUserId: " + preparedStatement.toString());
	    ResultSet rs = preparedStatement.executeQuery();

	    connection.commit();

	    while (rs.next()) {
		OrderBean bean = new OrderBean();

		bean.setId(rs.getInt("order_id"));
		bean.setUserId(rs.getInt("user_id"));
		bean.setDestination(rs.getString("destination"));
		bean.setTotalDiscount(rs.getDouble("total_discount"));
		bean.setTotalPrice(rs.getDouble("total_price"));
		bean.setNumProducts(rs.getInt("n_products"));
		bean.setStatus(rs.getString("status"));
		bean.setData(rs.getDate("data"));
		orders.add(bean);
		System.out.println(bean.getId());
	    }
	} finally {
	    try {
		if (preparedStatement != null)
		    preparedStatement.close();
	    } finally {
		if (connection != null)
		    connection.close();
	    }
	}

	return orders;
    }

}
