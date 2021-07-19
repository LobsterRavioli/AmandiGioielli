package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import beans.AddressBean;
import beans.Cart;
import beans.OrderBean;
import beans.ProductBean;
import beans.UserBean;
import model.AddressDAO;
import model.OrderDAO;

/**
 * Servlet implementation class SummuryOrderControl
 */
@WebServlet("/SummaryOrderControl")
public class SummaryOrderControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SummaryOrderControl() {
	super();
	// TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	AddressDAO addressModel = new AddressDAO(ds);
	OrderDAO orderModel = new OrderDAO(ds);
	HttpSession session = request.getSession();
	Cart cart = (Cart) session.getAttribute("cart");
	UserBean user = (UserBean) session.getAttribute("user");
	LinkedList<AddressBean> addresses;

	String action = request.getParameter("action");
	if (action != null) {
	    try {
		addresses = (LinkedList<AddressBean>) addressModel.doRetrieveAll(Integer.toString(user.getId()));
		request.setAttribute("addresses", addresses);
		switch (action) {
		case "buy":
		    OrderBean order = new OrderBean();
		    order.setUserId(user.getId());
		    String destination = request.getParameter("radios");
		    order.setDestination(destination);
		    order.setNumProducts(cart.getItems().size());
		    order.setTotalPrice(cart.totalValue());

		    double total_discount = 0;
		    for (ProductBean prd : cart.getItems())
			total_discount += (prd.getPrice() * prd.getDiscount()) / 100;

		    order.setTotalDiscount(total_discount);
		    orderModel.doSave(order);
		    cart.deleteItems();
		    request.setAttribute("message", "Operazione completata!");

		    break;

		}

	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

	RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/user_pages/summary_order.jsp");
	dispatcher.forward(request, response);

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	// TODO Auto-generated method stub
	doGet(request, response);
    }

}
