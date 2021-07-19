package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import beans.UserBean;
import model.UserDAO;

@WebServlet("/AdminCustomerControl")
public class AdminCustomerControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {

		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		UserDAO userModel = new UserDAO(ds);
		Collection<UserBean> users = null;
		try {
			users = userModel.doRetrieveAll("");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("customers", users);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/admin_pages/customers.jsp");
		dispatcher.forward(request, response);

    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
    	doGet(request, response);
    }
}