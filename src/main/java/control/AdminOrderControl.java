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

@WebServlet("/AdminOrderControl")
public class AdminOrderControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	OrderDAO orderModel = new OrderDAO(ds);

	String costumerId = request.getParameter("customer-id");
	String startDate = request.getParameter("start-date");
	String endDate = request.getParameter("end-date");
	String submit = request.getParameter("submit");

	try {
	    request.removeAttribute("orders");
	    if (startDate != null && endDate != null) {
		request.setAttribute("orders",
			orderModel.retrieveByData(null, Date.valueOf(startDate), Date.valueOf(endDate)));
	    } else if (costumerId != null) {
		request.setAttribute("orders", orderModel.retrieveByUserId(null, Integer.parseInt(costumerId)));
	    }
	} catch (NumberFormatException e) {
	    System.out.println("Error: " + e.getMessage());
	} catch (Exception e) {
	    System.out.println("Error: " + e.getMessage());
	}

	RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/admin_pages/orders.jsp");
	dispatcher.forward(request, response);
    }

}
