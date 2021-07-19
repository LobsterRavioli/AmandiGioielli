package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

import javax.sql.DataSource;

import beans.CategoryBean;
import utils.Utility;

public class CategoryDAO implements GenericDAO<CategoryBean> {

    @Override
    public void doDelete(CategoryBean item) throws SQLException {
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	PreparedStatement preparedStatement2 = null;

	String deleteSQL = "DELETE FROM category WHERE category_id = ?";
	String deleteSQL2 = "DELETE FROM product_categories WHERE category_id = ? ";

	try {
	    connection = ds.getConnection();
	    connection.setAutoCommit(false);
	    preparedStatement = connection.prepareStatement(deleteSQL);
	    preparedStatement2 = connection.prepareStatement(deleteSQL2);
	    preparedStatement.setInt(1, item.getId());
	    preparedStatement2.setInt(1, item.getId());

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

    @Override
    public CategoryBean doRetrieve(CategoryBean item) throws SQLException {

	Connection connection = null;
	PreparedStatement preparedStatement = null;

	CategoryBean bean = new CategoryBean();
	int code = item.getId();

	String selectSQL = "SELECT * FROM category WHERE category_id = ?";

	try {
	    connection = ds.getConnection();
	    preparedStatement = connection.prepareStatement(selectSQL);
	    preparedStatement.setInt(1, code);

	    Utility.print("doRetrieve: " + preparedStatement.toString());
	    ResultSet rs = preparedStatement.executeQuery();

	    while (rs.next()) {
		bean.setId(rs.getInt("category_id"));
		bean.setName(rs.getString("name"));
		bean.setDescription(rs.getString("description"));

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
    public Collection<CategoryBean> doRetrieveAll(String filter) throws SQLException {

	Connection connection = null;
	PreparedStatement preparedStatement = null;

	Collection<CategoryBean> categories = new LinkedList<CategoryBean>();

	String selectSQL = "SELECT * FROM category";

	try {
	    connection = ds.getConnection();
	    preparedStatement = connection.prepareStatement(selectSQL);

	    Utility.print("doRetrieveAll:" + preparedStatement.toString());
	    ResultSet rs = preparedStatement.executeQuery();

	    while (rs.next()) {
		CategoryBean bean = new CategoryBean();

		bean.setName(rs.getString("name"));
		bean.setDescription(rs.getString("description"));
		bean.setId(rs.getInt("category_id"));

		categories.add(bean);
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

	return categories;
    }

    @Override
    public void doSave(CategoryBean item) throws SQLException {
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	String insertSQL = "INSERT INTO category" + " (name,description) " + "VALUES (?,?)";
	try {
	    connection = ds.getConnection();
	    connection.setAutoCommit(false);
	    preparedStatement = connection.prepareStatement(insertSQL);

	    preparedStatement.setString(1, item.getName());
	    preparedStatement.setString(2, item.getDescription());

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
    public void doUpdate(CategoryBean item) throws SQLException {
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	String updateSQL = "UPDATE category SET " + "description=? WHERE category_id = ?";

	try {
	    connection = ds.getConnection();
	    connection.setAutoCommit(false);
	    preparedStatement = connection.prepareStatement(updateSQL);

	    preparedStatement.setString(1, item.getDescription());
	    preparedStatement.setInt(2, item.getId());
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

    public CategoryDAO() {
	// TODO Auto-generated constructor stub
    }

    private DataSource ds = null;

    public CategoryDAO(DataSource ds) {
	this.ds = ds;
    }

}
