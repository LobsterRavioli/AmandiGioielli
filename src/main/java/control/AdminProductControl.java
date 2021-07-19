package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import beans.CategoryBean;
import beans.ProductBean;
import model.CategoryDAO;
import model.GenericDAO;
import model.ProductDAO;
import utils.Utility;

@WebServlet("/AdminProductControl")
public class AdminProductControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	GenericDAO<ProductBean> model = new ProductDAO(ds);
	GenericDAO<CategoryBean> model2 = new CategoryDAO(ds);

	String sort = request.getParameter("sort");
	String action = request.getParameter("action");

	try {
	    if (action != null) {
		if (action.equals("details")) {
		    String id = request.getParameter("id");

		    ProductBean product = new ProductBean();
		    product.setCode(Integer.parseInt(id));

		    request.removeAttribute("product");
		    request.setAttribute("product", model.doRetrieve(product));

		} else if (action.equals("insert")) {
		    String name = request.getParameter("name");
		    String description = request.getParameter("description");
		    double price = Double.parseDouble(request.getParameter("price"));
		    int quantity = Integer.parseInt(request.getParameter("quantity"));

		    ProductBean bean = new ProductBean();
		    bean.setName(name);
		    bean.setDescription(description);
		    bean.setPrice(price);
		    bean.setQuantity(quantity);

		    model.doSave(bean);
		    request.setAttribute("message", "Prodotto " + bean.getName() + " aggiunto con successo.");

		} else if (action.equals("delete")) {
		    String id = request.getParameter("id");

		    ProductBean product = new ProductBean();
		    product.setCode(Integer.parseInt(id));

		    ProductBean bean = model.doRetrieve(product);
		    if (bean != null) {
			model.doDelete(bean);
			request.setAttribute("message", "Prodotto " + bean.getName() + " rimosso con successo.");
		    }
		} else if (action.equals("update")) {
		    String id = request.getParameter("id");
		    String name = request.getParameter("name");
		    String description = request.getParameter("description");
		    double price = Double.parseDouble(request.getParameter("price"));
		    int quantity = Integer.parseInt(request.getParameter("quantity"));

		    ProductBean bean = new ProductBean();
		    bean.setCode(Integer.parseInt(id));
		    bean.setName(name);
		    bean.setDescription(description);
		    bean.setPrice(price);
		    bean.setQuantity(quantity);

		    model.doUpdate(bean);
		    request.setAttribute("message", "Prodotto " + bean.getName() + " aggiornato con successo");
		} else if (action.equals("redirect")) {
		    String id = request.getParameter("redirect");
		    request.setAttribute("id", id);
		    RequestDispatcher dispatcher = this.getServletContext()
			    .getRequestDispatcher("/admin_pages/modify_product.jsp");
		    dispatcher.forward(request, response);
		    return;
		}
	    }
	} catch (SQLException | NumberFormatException e) {
	    Utility.print(e);
	    request.setAttribute("error", e.getMessage());
	}

	try {
	    request.removeAttribute("products");
	    request.setAttribute("products", model.doRetrieveAll(sort));
	    request.setAttribute("categories", model2.doRetrieveAll(sort));
	} catch (SQLException e) {
	    Utility.print(e);
	    request.setAttribute("error", e.getMessage());
	}

	RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/admin_pages/catalogue.jsp");
	dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	doGet(request, response);
    }

}