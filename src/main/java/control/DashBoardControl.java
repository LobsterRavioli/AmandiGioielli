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
import beans.UserBean;
import model.AddressDAO;
import model.OrderDAO;

/**
 * Servlet implementation class SummuryOrderControl
 */
@WebServlet("/DashBoard")
public class DashBoardControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	AddressDAO addressModel = new AddressDAO(ds);
	OrderDAO orderModel = new OrderDAO(ds);
	HttpSession session = request.getSession();
	UserBean user = (UserBean) session.getAttribute("user");
	LinkedList<AddressBean> addresses;

	String action = request.getParameter("action");
	try {
	    addresses = (LinkedList<AddressBean>) addressModel.doRetrieveAll(Integer.toString(user.getId()));
	    request.setAttribute("addresses", addresses);
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/user_pages/dashboard.jsp");
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
