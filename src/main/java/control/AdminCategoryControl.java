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

@WebServlet("/AdminCategoryControl")
public class AdminCategoryControl extends HttpServlet {
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
		if (action.equals("delete")) {
		    String id = request.getParameter("id");

		    CategoryBean category = new CategoryBean();
		    category.setId(Integer.parseInt(id));
		    model2.doDelete(category);
		    request.setAttribute("message", "Categoria rimossa con successo.");

		} else if (action.equals("redirect")) {
		    String id = request.getParameter("redirect");
		    request.setAttribute("id", id);
		    RequestDispatcher dispatcher = this.getServletContext()
			    .getRequestDispatcher("/admin_pages/modify_category.jsp");
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
	RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/admin_pages/categories.jsp");
	dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	doGet(request, response);
    }

}