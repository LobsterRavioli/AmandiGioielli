package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import beans.CategoryBean;
import beans.ProductBean;
import utils.Utility;

public class ProductDAO implements GenericDAO<ProductBean> {

    private DataSource ds = null;

    public ProductDAO(DataSource ds) {
	this.ds = ds;
    }

    @Override
    public ProductBean doRetrieve(ProductBean product) throws SQLException {

	Connection connection = null;
	PreparedStatement preparedStatement = null;
	PreparedStatement preparedStatement2 = null;

	ProductBean bean = new ProductBean();
	int code = product.getCode();

	String selectSQL = "SELECT * FROM product WHERE product_id = ?";
	String selectSQL2 = "SELECT * FROM category JOIN product_categories ON category.category_id = product_categories.category_id JOIN product ON product_categories.product_id=product.product_id where product.product_id = ?";

	try {
	    connection = ds.getConnection();
	    preparedStatement = connection.prepareStatement(selectSQL);
	    preparedStatement.setInt(1, code);

	    Utility.print("doRetrieve: " + preparedStatement.toString());
	    ResultSet rs = preparedStatement.executeQuery();
	    ResultSet rs2;

	    while (rs.next()) {
		bean.setCode(rs.getInt("product_id"));
		bean.setName(rs.getString("product_name"));
		bean.setDescription(rs.getString("description"));
		bean.setShortDescription(rs.getString("short_description"));
		bean.setPrice(rs.getDouble("price"));
		bean.setQuantity(rs.getInt("quantity"));
		bean.setTaxRate(rs.getDouble("tax_rate"));
		bean.setDiscount(rs.getDouble("discount"));
		bean.setActive(rs.getBoolean("is_active"));
		preparedStatement2 = connection.prepareStatement(selectSQL2);
		preparedStatement2.setInt(1, bean.getCode());
		rs2 = preparedStatement2.executeQuery();
		while (rs2.next()) {
		    bean.addCategory(new CategoryBean(rs2.getInt("category_id"), rs2.getString("name"),
			    rs2.getString("description")));
		}
		preparedStatement2.close();

	    }

	    Utility.print("doRetrieve: " + preparedStatement.toString());
	    rs = preparedStatement.executeQuery();

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

    @Override
    public Collection<ProductBean> doRetrieveAll(String order) throws SQLException {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	PreparedStatement preparedStatement2 = null;
	Collection<ProductBean> products = new LinkedList<ProductBean>();

	String selectSQL = "SELECT * FROM product";
	String selectSQL2 = "SELECT * FROM category JOIN product_categories ON category.category_id = product_categories.category_id JOIN product ON product_categories.product_id=product.product_id where product.product_id = ?";

	if (order != null && !order.equals(""))
	    selectSQL += " ORDER BY " + order;

	try {
	    connection = ds.getConnection();
	    preparedStatement = connection.prepareStatement(selectSQL);
	    preparedStatement2 = connection.prepareStatement(selectSQL2);

	    Utility.print("doRetrieveAll:" + preparedStatement.toString());
	    ResultSet rs = preparedStatement.executeQuery();
	    ResultSet rs2;

	    ProductBean bean;
	    while (rs.next()) {
		bean = new ProductBean();

		bean.setCode(rs.getInt("product_id"));
		bean.setName(rs.getString("product_name"));
		bean.setDescription(rs.getString("description"));
		bean.setShortDescription(rs.getString("short_description"));
		bean.setPrice(rs.getDouble("price"));
		bean.setQuantity(rs.getInt("quantity"));
		bean.setTaxRate(rs.getDouble("tax_rate"));
		bean.setDiscount(rs.getDouble("discount"));
		bean.setActive(rs.getBoolean("is_active"));
		bean.setUrl(rs.getString("url"));

		preparedStatement2 = connection.prepareStatement(selectSQL2);
		preparedStatement2.setInt(1, bean.getCode());
		rs2 = preparedStatement2.executeQuery();

		while (rs2.next()) {
		    bean.addCategory(new CategoryBean(rs2.getInt("category_id"), rs2.getString("name"),
			    rs2.getString("description")));
		}
		preparedStatement2.close();
		products.add(bean);
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

	return products;
    }

    @Override
    public void doSave(ProductBean product) throws SQLException {
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	String insertSQL = "INSERT INTO product"
		+ " (product_name, description, short_description, quantity, price, tax_rate, discount, is_active,url) "
		+ "VALUES (?, ?, ?, ?, ?, ?, ?, ? ,?)";

	try {
	    connection = ds.getConnection();
	    connection.setAutoCommit(false);
	    preparedStatement = connection.prepareStatement(insertSQL);

	    preparedStatement.setString(1, product.getName());
	    preparedStatement.setString(2, product.getDescription());
	    preparedStatement.setString(3, product.getShortDescription());
	    preparedStatement.setInt(4, product.getQuantity());
	    preparedStatement.setDouble(5, product.getPrice());
	    preparedStatement.setDouble(6, product.getTaxRate());
	    preparedStatement.setDouble(7, product.getDiscount());
	    preparedStatement.setBoolean(8, product.isActive());
	    preparedStatement.setString(9, product.getUrl());
	    Utility.print("doSave: " + preparedStatement.toString());
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
    public void doUpdate(ProductBean product) throws SQLException {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	PreparedStatement preparedStatement2 = null;
	PreparedStatement preparedStatement3 = null;

	String updateSQL = "UPDATE product SET "
		+ "product_name=?, description=?,short_description=?,quantity=?,price=?,"
		+ " tax_rate =?, discount=?, is_active=?, url =? WHERE product_id=?";

	String deleteSQL = "DELETE FROM product_categories WHERE product_id=? ";
	String insertSQL = "INSERT INTO product_categories (category_id,product_id) VALUES(?,?) ";

	try {
	    connection = ds.getConnection();
	    connection.setAutoCommit(false);
	    preparedStatement = connection.prepareStatement(updateSQL);
	    preparedStatement2 = connection.prepareStatement(deleteSQL);
	    preparedStatement3 = connection.prepareStatement(insertSQL);

	    preparedStatement.setString(1, product.getName());
	    preparedStatement.setString(2, product.getDescription());
	    preparedStatement.setString(3, product.getShortDescription());
	    preparedStatement.setInt(4, product.getQuantity());
	    preparedStatement.setDouble(5, product.getPrice());
	    preparedStatement.setDouble(6, product.getTaxRate());
	    preparedStatement.setDouble(7, product.getDiscount());
	    preparedStatement.setBoolean(8, product.isActive());
	    preparedStatement.setString(9, product.getUrl());
	    preparedStatement.setInt(10, product.getCode());

	    preparedStatement2.setInt(1, product.getCode());
	    Utility.print("doUpdate: " + preparedStatement.toString());

	    preparedStatement.executeUpdate();
	    preparedStatement2.executeUpdate();
	    if (product.getCategories().size() != 0) {

		for (CategoryBean category : product.getCategories()) {
		    preparedStatement3.setInt(1, category.getId());
		    preparedStatement3.setInt(2, product.getCode());
		    preparedStatement3.executeUpdate();
		}
	    }

	    connection.commit();

	} finally {
	    try {
		if (preparedStatement != null)
		    preparedStatement.close();
		if (preparedStatement2 != null)
		    preparedStatement2.close();
		if (preparedStatement3 != null)
		    preparedStatement3.close();

	    } finally {
		if (connection != null)
		    connection.close();

	    }
	}
    }

    @Override
    public void doDelete(ProductBean product) throws SQLException {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	PreparedStatement preparedStatement2 = null;

	String deleteSQL = "DELETE FROM product WHERE product_id = ?";
	String deleteSQL2 = "DELETE FROM product_categories WHERE product_id = ? ";

	try {
	    connection = ds.getConnection();
	    connection.setAutoCommit(false);
	    preparedStatement = connection.prepareStatement(deleteSQL);
	    preparedStatement2 = connection.prepareStatement(deleteSQL2);
	    preparedStatement.setInt(1, product.getCode());
	    preparedStatement2.setInt(1, product.getCode());

	    Utility.print("doDelete: " + preparedStatement.toString());
	    Utility.print("doDelete: " + preparedStatement2.toString());

	    preparedStatement.executeUpdate();
	    preparedStatement2.executeUpdate();

	    connection.commit();

	} finally {
	    try {
		if (preparedStatement != null)
		    preparedStatement.close();
		else if (preparedStatement2 != null)
		    preparedStatement2.close();

	    } finally {
		if (connection != null)
		    connection.close();
	    }
	}
    }
}
