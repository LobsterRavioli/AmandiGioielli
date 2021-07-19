package control;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import beans.OrderBean;
import beans.UserBean;
import model.OrderDAO;
import model.UserDAO;


@WebServlet("/AdminOrderControl")
public class AdminOrderControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");

	try {

	    OrderDAO orderModel = new OrderDAO(ds);
	    Collection<OrderBean> orders = orderModel.doRetrieveAll("user_id");
	    request.setAttribute("orders", orders);
	    
	    UserDAO userModel = new UserDAO(ds);
	    Collection<UserBean> users = userModel.doRetrieveAll("user_id");
	    request.setAttribute("users", users);

	} catch (Exception e) {
	    // TODO: handle exception
	}

	RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/admin_pages/orders.jsp");
	dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	doGet(request, response);
    }

}
