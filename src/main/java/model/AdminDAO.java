package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import javax.sql.DataSource;

import beans.AdminBean;
import utils.Utility;

public class AdminDAO implements GenericDAO<AdminBean> {
    private DataSource ds = null;

    public AdminDAO(DataSource ds) {
	this.ds = ds;
    }

    @Override
    public AdminBean doRetrieve(AdminBean admin) throws SQLException {
	// preparing some objects for connection
	Connection currentCon = null;
	PreparedStatement preparedStatement = null;

	String beanEmail = admin.getEmail();
	String beanPassword = admin.getPassword();
	String searchQuery = "SELECT * FROM admin WHERE email = ? AND password = ?";

	// "System.out.println" prints in the console;
	Utility.print("Your email is " + beanEmail);
	Utility.print("Your password is " + beanPassword);
	Utility.print("Query: " + searchQuery);

	try {
	    // connect to DB
	    currentCon = ds.getConnection();
	    preparedStatement = currentCon.prepareStatement(searchQuery);
	    preparedStatement.setString(1, beanEmail);
	    preparedStatement.setString(2, beanPassword);
	    Utility.print("doRetrieve: " + preparedStatement.toString());
	    ResultSet rs = preparedStatement.executeQuery();
	    if (!rs.next()) {
		utils.Utility.print("Nessun Account trovato");
		return null;
	    } else {

		String email = rs.getString("email");
		String password = rs.getString("password");
		admin.setId(rs.getInt("admin_id"));
		admin.setEmail(email);
		admin.setPassword(password);
		Utility.print("Account trovato");

	    }

	} finally

	{
	    try {
		if (preparedStatement != null)
		    preparedStatement.close();
	    }

	    finally {
		if (currentCon != null)
		    currentCon.close();
	    }
	}

	return admin;
    }

    public void doSave(AdminBean user) throws SQLException {

    }

    @Override
    public Collection<AdminBean> doRetrieveAll(String order) throws SQLException {
	return null;

    }

    @Override
    public void doUpdate(AdminBean user) throws SQLException {

    }

    @Override
    public void doDelete(AdminBean item) throws SQLException {

    }

}
