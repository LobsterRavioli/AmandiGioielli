package control;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import model.OrderDAO;
import model.OrderDetailsDAO;

@WebServlet("/AdminOrderControl")
public class AdminOrderControl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		OrderDAO orderModel = new OrderDAO(ds);
		OrderDetailsDAO odModel = new OrderDetailsDAO(ds);

		String costumerId = request.getParameter("customer-id");
		String startDate = request.getParameter("start-date");
		String endDate = request.getParameter("end-date");
		String submit = request.getParameter("submit");
		String orderId = request.getParameter("order_id");

		try
		{
			request.removeAttribute("orders");
			if (startDate != null && endDate != null)
			{
				request.setAttribute("orders",
						orderModel.retrieveByData(null, Date.valueOf(startDate), Date.valueOf(endDate)));
			} else if (costumerId != null)
			{
				request.setAttribute("orders", orderModel.retrieveByUserId(null, Integer.parseInt(costumerId)));
			} else if (orderId != null)
			{
				request.setAttribute("ordersDetails", odModel.doRetrieveAll(orderId));
				RequestDispatcher dispatcher = this.getServletContext()
						.getRequestDispatcher("/admin_pages/order_details.jsp");
				dispatcher.forward(request, response);
				return;

			}

		} catch (Exception e)
		{
			System.out.println("Error: " + e.getMessage());
		}

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/admin_pages/orders.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		doGet(request, response);

	}

}
