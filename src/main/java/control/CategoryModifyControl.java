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
import model.CategoryDAO;
import model.GenericDAO;

/**
 * Servlet implementation class CategoryModifyControl
 */
@WebServlet("/CategoryModifyControl")
public class CategoryModifyControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryModifyControl() {
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
	CategoryBean categoryBean = new CategoryBean();
	GenericDAO<CategoryBean> categoryModel = new CategoryDAO(ds);
	int id;

	if (request.getParameter("id") != null) {
	    id = Integer.parseInt(request.getParameter("id"));
	    categoryBean.setId(id);
	}

	try {
	    request.setAttribute("category", categoryModel.doRetrieve(categoryBean));
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	String action = request.getParameter("action");
	if (action != null) {
	    CategoryBean bean = new CategoryBean();
	    bean.setId(Integer.parseInt(request.getParameter("id")));
	    bean.setDescription(request.getParameter("description"));

	    switch (action) {
	    case "insert":
		try {
		    categoryModel.doUpdate(bean);
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		RequestDispatcher dispatcher = this.getServletContext()
			.getRequestDispatcher("/admin_pages/categories.jsp");
		dispatcher.forward(request, response);
		return;

	    default:
		break;
	    }
	}
	RequestDispatcher dispatcher = this.getServletContext()
		.getRequestDispatcher("/admin_pages/modify_category.jsp");
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
