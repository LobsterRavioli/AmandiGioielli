package control;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import beans.CategoryBean;
import model.CategoryDAO;
import model.GenericDAO;

/**
 * Servlet implementation class RegistrayionCategoryControl
 */
@WebServlet("/RegistrationCategoryControl")
public class RegistrationCategoryControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationCategoryControl() {
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
	GenericDAO<CategoryBean> categoryModel = new CategoryDAO(ds);

	CategoryBean bean = new CategoryBean();
	bean.setName(request.getParameter("name"));
	bean.setDescription(request.getParameter("description"));

	try {
	    categoryModel.doSave(bean);
	} catch (Exception e) {
	    // TODO: handle exception
	}

	RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/admin_pages/categories.jsp");
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

    public String createRandomCode(int codeLength, String id) {
	List<Character> temp = id.chars().mapToObj(i -> (char) i).collect(Collectors.toList());
	Collections.shuffle(temp, new SecureRandom());
	return temp.stream().map(Object::toString).limit(codeLength).collect(Collectors.joining());
    }

    public boolean isUnique(String fileName, String path) {
	File[] files = new File(path).listFiles();
	for (File file : files) {
	    if (file.getName().equals(fileName))
		return false;
	}
	return true;
    }

    @SuppressWarnings("unused")
	private String extractFileName(Part part) {
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
