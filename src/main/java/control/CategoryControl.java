package control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.Gson;

import beans.CategoryBean;
import model.CategoryDAO;

/**
 * Servlet implementation class CategoryControl
 */
@WebServlet("/CategoryControl")
public class CategoryControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoryControl() {
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
	CategoryDAO categoryDAO = new CategoryDAO(ds);
	CategoryBean category = new CategoryBean();
	Map<String, Object> map = new HashMap<String, Object>();

	String name = (String) request.getParameter("name");
	String description = (String) request.getParameter("description");
	category.setName(name);
	category.setDescription(name);
	map.put("name", name);
	map.put("description", description);

	boolean isValid = true;
	try {
	    categoryDAO.doSave(category);
	} catch (Exception e) {
	    isValid = false;
	    System.out.print(e);
	}
	map.put("isValid", isValid);
	write(response, map);
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

    private void write(HttpServletResponse response, Map<String, Object> map) throws IOException {
	response.setContentType("application/json");
	response.setCharacterEncoding("UTF-8");
	response.getWriter().write(new Gson().toJson(map));
    }

}
