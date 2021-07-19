package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import beans.CategoryBean;
import beans.ProductBean;
import model.CategoryDAO;
import model.GenericDAO;
import model.ProductDAO;

/**
 * Servlet implementation class ProductDetailsControl
 */
@WebServlet("/ProductModifyControl")

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
	maxFileSize = 1024 * 1024 * 10, // 10MB
	maxRequestSize = 1024 * 1024 * 50) // 50MB

public class ProductModifyControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductModifyControl() {
	super();
	// TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	ProductBean bean = new ProductBean();
	GenericDAO<ProductBean> productModel = new ProductDAO(ds);
	GenericDAO<CategoryBean> categoryModel = new CategoryDAO(ds);
	int id;
	if (request.getParameter("id") != null) {
	    id = Integer.parseInt(request.getParameter("id"));
	    bean.setCode(id);
	}

	try {
	    request.setAttribute("productBean", productModel.doRetrieve(bean));
	    request.setAttribute("categories", categoryModel.doRetrieveAll(""));
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	String action = request.getParameter("action");
	if (action != null) {

	    bean.setDescription(request.getParameter("description"));
	    bean.setShortDescription(request.getParameter("shortDescription"));
	    bean.setName(request.getParameter("name"));
	    bean.setQuantity(Integer.parseInt(request.getParameter("quantity")));
	    bean.setTaxRate(Double.parseDouble(request.getParameter("taxRate")));
	    bean.setDiscount(Double.parseDouble(request.getParameter("discount")));
	    bean.setPrice(Double.parseDouble(request.getParameter("price")));
	    String[] categories = request.getParameterValues("categories");

	    if (categories != null) {
		for (String category : categories) {
		    String[] parts = category.split("\\.");
		    bean.addCategory(new CategoryBean(Integer.parseInt(parts[0]), parts[1]));
		}
	    }

	    switch (action) {
	    case "insert":
		try {
		    productModel.doUpdate(bean);
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		RequestDispatcher dispatcher = this.getServletContext()
			.getRequestDispatcher("/admin_pages/catalogue.jsp");
		dispatcher.forward(request, response);
		return;

	    default:
		break;
	    }
	}

	RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/admin_pages/modify_product.jsp");
	dispatcher.forward(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	doGet(request, response);
    }

    public static String extractFileName(Part part) {
	String contentDisp = part.getHeader("content-disposition");
	String[] items = contentDisp.split(";");
	for (String s : items) {
	    if (s.trim().startsWith("filename")) {
		return s.substring(s.indexOf("=") + 2, s.length() - 1);
	    }
	}
	return "";
    }

}
