package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import beans.Cart;
import beans.ProductBean;
import model.GenericDAO;
import model.ProductDAO;
import utils.Utility;

@WebServlet("/CartControl")
public class CartControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	GenericDAO<ProductBean> model = new ProductDAO(ds);
	HttpSession session = request.getSession();

	Cart cart = (Cart) session.getAttribute("cart");

	if (cart == null) {
	    cart = new Cart();
	    session.setAttribute("cart", cart);
	}

	String action = request.getParameter("action");

	try {
	    if (action != null) {
		switch (action) {
		case ("clearCart"):

		    cart.deleteItems();
		    request.setAttribute("message", "Carrello svuotato");

		    break;

		case ("deleteCart"):

		    String id = request.getParameter("id");

		    ProductBean product = new ProductBean();
		    product.setCode(Integer.parseInt(id));

		    ProductBean bean = model.doRetrieve(product);
		    if (bean != null && !bean.isEmpty()) {
			cart.deleteItem(bean);
			request.setAttribute("message", "Prodotto " + bean.getName() + " rimosso dal carrello");
		    }

		    break;

		case ("buy"):

		    if (cart == null || cart.getItems().isEmpty()) {
			request.setAttribute("message", "Non hai articoli nel carrello!");
			break;
		    } else {
			RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher("/user_pages/summary_order.jsp");
			dispatcher.forward(request, response);
			return;
		    }

		}
	    }
	}

	catch (SQLException | NumberFormatException e) {
	    Utility.print(e);
	    request.setAttribute("error", e.getMessage());
	}

	session.setAttribute("cart", cart);

	RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/common_pages/cart.jsp");
	dispatcher.forward(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	doGet(request, response);
    }
}
