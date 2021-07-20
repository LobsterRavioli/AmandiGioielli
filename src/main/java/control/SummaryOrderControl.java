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
import beans.OrderDetailBean;
import beans.ProductBean;
import beans.UserBean;
import model.AddressDAO;
import model.OrderDAO;
import model.OrderDetailsDAO;
import utils.Utility;


@WebServlet("/SummaryOrderControl")
public class SummaryOrderControl extends HttpServlet 
{
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {

		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		AddressDAO addressModel = new AddressDAO(ds);
		OrderDAO orderModel = new OrderDAO(ds);
		OrderDetailsDAO orderDetailsModel = new OrderDetailsDAO(ds);
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		UserBean user = (UserBean) session.getAttribute("user");
		LinkedList<AddressBean> addresses;
	
		String action = request.getParameter("action");
		if (action != null) {
		    try 
		    {
				addresses = (LinkedList<AddressBean>) addressModel.doRetrieveAll(Integer.toString(user.getId()));
				request.setAttribute("addresses", addresses);
				switch (action) 
				{
					case "buy":
					    OrderBean order = new OrderBean();
					    order.setUserId(user.getId());
					    String destination = request.getParameter("radios");
					    order.setDestination(destination);
					    order.setNumProducts(cart.getItems().size());
					    order.setTotalPrice(cart.totalValue());
			
					    double total_discount = 0;
			
					    for (ProductBean prd : cart.getItems())
					    {
					    	OrderDetailBean orderDetail = new OrderDetailBean();
					    	orderDetail.setProductId(prd.getCode());
					    	orderDetail.setName(prd.getName());
					    	orderDetail.setQuantity(prd.getQuantity());
					    	orderDetail.setPrice(prd.getPrice());
					    	orderDetail.setTaxRate(prd.getTaxRate());
					    	order.addItem(orderDetail);
							Utility.print(orderDetail.toString());
					    	
					    	total_discount += (prd.getPrice() * prd.getDiscount()) / 100;
					    }
			
					    order.setTotalDiscount(total_discount);
					    orderModel.doSave(order);
					    cart.deleteItems();
					    request.setAttribute("message", "Operazione completata!");
					    response.sendRedirect(request.getContextPath()+"/user_pages/order_success.jsp");
						return;

				}

		    } catch (SQLException e){
	    		e.printStackTrace();
		    }
	}

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/user_pages/summary_order.jsp");
		dispatcher.forward(request, response);

    }

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
		doGet(request, response);
    }
    
    
    

}
