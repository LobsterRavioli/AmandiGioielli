package control;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import beans.OrderBean;
import beans.UserBean;
import model.OrderDAO;
import model.OrderDetailsDAO;

@WebServlet("/OrderControl")
public class OrderControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	HttpSession session = request.getSession();
	UserBean user = (UserBean) session.getAttribute("user");

	if (user == null) {
	    RequestDispatcher dispatcher = this.getServletContext()
		    .getRequestDispatcher("/common_pages/login_user.jsp");
	    dispatcher.forward(request, response);
	}

	String orderId = request.getParameter("order_id");
	if (orderId != null) {
	    try {
		request.removeAttribute("order_id");
		request.removeAttribute("orderDetails");
		request.setAttribute("ordersDetails", new OrderDetailsDAO(ds).doRetrieveAll(orderId));
		RequestDispatcher dispatcher = this.getServletContext()
			.getRequestDispatcher("/user_pages/order_details.jsp");
		dispatcher.forward(request, response);
		return;
	    } catch (Exception e) {
		// TODO: handle exception
	    }

	}

	try {

	    OrderDAO orderModel = new OrderDAO(ds);
	    Collection<OrderBean> orders = orderModel.doRetrieveAllByUserId(String.valueOf(user.getId()));
	    request.setAttribute("orders", orders);

	} catch (Exception e) {
	    // TODO: handle exception
	}

	RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/user_pages/orders.jsp");
	dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	doGet(request, response);
    }

}
