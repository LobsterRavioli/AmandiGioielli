import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import utils.Utility;

@WebListener
public class MainContext implements ServletContextListener {

    public void contextInitialized(ServletContextEvent sce) {
	Utility.print("Startup web application");

	ServletContext context = sce.getServletContext();

	DataSource ds = null;
	try {
	    Context initCtx = new InitialContext();
	    Context envCtx = (Context) initCtx.lookup("java:comp/env");

	    ds = (DataSource) envCtx.lookup("jdbc/amandigioielli");

	    try {
		Connection con = ds.getConnection();

		DatabaseMetaData metaData = con.getMetaData();
		Utility.print("JDBC version: " + metaData.getJDBCMajorVersion() + "." + metaData.getJDBCMinorVersion());
		Utility.print("Product name: " + metaData.getDatabaseProductName());
		Utility.print("Product version: " + metaData.getDatabaseProductVersion());

	    } catch (SQLException e) {
		Utility.print(e);
	    }

	} catch (NamingException e) {
	    Utility.print(e);
	}

	context.setAttribute("DataSource", ds);
	String productsPath = "/Users/tommasosorrentino/Desktop/Progetto/AmandiGioielli/products_images";
	context.setAttribute("path", productsPath);
	Utility.print("DataSource creation: " + ds.toString());

    }

    public void contextDestroyed(ServletContextEvent sce) {
	ServletContext context = sce.getServletContext();

	DataSource ds = (DataSource) context.getAttribute("DataSource");
	context.removeAttribute("DataSource");

	Utility.print("DataSource deletion: " + ds.toString());
    }
}