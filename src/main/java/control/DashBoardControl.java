package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
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
import beans.OrderBean;
import beans.OrderDetailBean;
import beans.UserBean;
import model.AddressDAO;
import model.OrderDAO;
import model.OrderDetailsDAO;
import model.UserDAO;
import utils.Utility;

@WebServlet("/DashBoard")
public class DashBoardControl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		AddressDAO addressModel = new AddressDAO(ds);
		OrderDAO orderModel = new OrderDAO(ds);
		UserDAO userModel = new UserDAO(ds);
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		LinkedList<AddressBean> addresses;

		String scope = request.getParameter("scope");

		try
		{
			if (scope != null)
			{
				switch (scope)
				{
				case "addresses":

					addresses = (LinkedList<AddressBean>) addressModel.doRetrieveAll(Integer.toString(user.getId()));
					request.setAttribute("addresses", addresses);

					RequestDispatcher dispatcher = this.getServletContext()
							.getRequestDispatcher("/user_pages/addresses.jsp");
					dispatcher.forward(request, response);
					return;

				case "orders":
					String orderId = request.getParameter("order_id");
					if (orderId != null)
					{

						OrderDetailsDAO odModel = new OrderDetailsDAO(ds);
						Collection<OrderDetailBean> orderDetails = odModel.doRetrieveAll(orderId);

						request.removeAttribute("order_id");
						request.removeAttribute("orderDetails");
						request.setAttribute("ordersDetails", orderDetails);
						Utility.print(orderDetails.toString());
						dispatcher = this.getServletContext().getRequestDispatcher("/user_pages/order_details.jsp");
						dispatcher.forward(request, response);
						return;
					}

					Collection<OrderBean> orders = orderModel.doRetrieveAllByUserId(String.valueOf(user.getId()));
					request.setAttribute("orders", orders);
					dispatcher = this.getServletContext().getRequestDispatcher("/user_pages/orders.jsp");
					dispatcher.forward(request, response);
					return;
				}
			}

		} catch (SQLException e)
		{
			request.setAttribute("error", e);
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/user_pages/dashboard.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		UserDAO userModel = new UserDAO(ds);
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");

		String scope = request.getParameter("scope");

		if (scope != null)
		{
			if (scope.equals("account"))
			{
				String firstName = request.getParameter("firstName");
				String lastName = request.getParameter("lastName");
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				String phone = request.getParameter("phone");

				UserBean newUser = new UserBean();
				newUser.setId(user.getId());
				newUser.setFirstName(firstName);
				newUser.setLastName(lastName);
				newUser.setEmail(email);
				newUser.setPassword(password);
				newUser.setPhone(phone);

				session.removeAttribute("user");
				session.setAttribute("user", newUser);
				try
				{
					userModel.doUpdate(newUser);
				} catch (SQLException e)
				{
					e.printStackTrace();
				}

				RequestDispatcher dispatcher = this.getServletContext()
						.getRequestDispatcher("/user_pages/account_details.jsp");
				dispatcher.forward(request, response);

			}
		}

	}

}
