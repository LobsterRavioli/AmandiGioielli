package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import beans.AdminBean;
import model.AdminDAO;

@WebServlet("/LoginAdmin")
public class LoginAdminControl extends HttpServlet
{

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		String email = request.getParameter("username");
		String password = request.getParameter("password");
		String redirectedPage;
		try
		{
			checkLogin(email, password);
			request.getSession().setAttribute("adminRoles", true);
			request.getSession().setAttribute("adminName", email);
			redirectedPage = "/admin_pages/controlPanel.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(redirectedPage);
			dispatcher.forward(request, response);
		} catch (Exception e)
		{
			request.getSession().removeAttribute("adminRoles");
			redirectedPage = "/common_pages/login_admin.jsp";
			utils.Utility.print(e);
			response.sendRedirect(request.getContextPath() + redirectedPage);
		}

	}

	private void checkLogin(String email, String password) throws Exception
	{
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		AdminBean bean = new AdminBean();
		bean.setPassword(password);
		bean.setEmail(email);
		AdminDAO adminDao = new AdminDAO(ds);
		if (adminDao.doRetrieve(bean) != null)
		{
			return;
		} else
			throw new Exception("Invalid login and password");
	}

	private static final long serialVersionUID = 1L;

	public LoginAdminControl()
	{
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}

}
