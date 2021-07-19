package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;

import javax.sql.DataSource;

import beans.ProductCategoriesBean;
import utils.Utility;

public class ProductCategoriesDAO implements GenericDAO<ProductCategoriesBean> {

    private DataSource ds;

    public ProductCategoriesDAO(DataSource ds) {
	this.ds = ds;

    }

    public void doDelete(ProductCategoriesBean item) throws java.sql.SQLException {

	Connection connection = null;
	PreparedStatement preparedStatement = null;

	String deleteSQL = "DELETE FROM product_categories WHERE product_id = ? ";

	try {
	    connection = ds.getConnection();
	    connection.setAutoCommit(false);
	    preparedStatement = connection.prepareStatement(deleteSQL);
	    preparedStatement.setInt(1, Integer.parseInt(item.getProductId()));

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

    @Override
    public ProductCategoriesBean doRetrieve(ProductCategoriesBean item) throws SQLException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public Collection<ProductCategoriesBean> doRetrieveAll(String filter) throws SQLException {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void doSave(ProductCategoriesBean item) throws SQLException {
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	String insertSQL = "INSERT INTO product_categories (category_id,product_id) VALUES (?,?)";

	try {
	    connection = ds.getConnection();
	    connection.setAutoCommit(false);
	    preparedStatement = connection.prepareStatement(insertSQL);
	    preparedStatement.setInt(1, Integer.parseInt(item.getCategoryId()));
	    preparedStatement.setInt(2, Integer.parseInt(item.getProductId()));

	    Utility.print("doSave: " + preparedStatement.toString());
	    preparedStatement.executeUpdate();
	    connection.commit();

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
    public void doUpdate(ProductCategoriesBean item) throws SQLException {
	// TODO Auto-generated method stub

    }

}
