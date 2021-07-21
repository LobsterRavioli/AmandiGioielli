package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import beans.UserBean;
import model.UserDAO;
import utils.Utility;

@WebServlet("/LoginControl")
public class LoginControl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		UserDAO model = new UserDAO(ds);
		HttpSession session = request.getSession();

		UserBean user = new UserBean();

		user.setEmail((String) request.getParameter("email"));
		user.setPassword((String) request.getParameter("password"));

		try
		{

			user = model.doRetrieve(user);

			if (user != null)
			{
				session.setAttribute("user", user);
				request.setAttribute("message", "Benvenuto" + user.getFirstName());
				request.getSession().setAttribute("userRoles", true);
			} else
			{
				request.setAttribute("message", "Attenzione! Non è stato trovato nessun utente con questi dati");
			}

		} catch (Exception e)
		{
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
		}

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/common_pages/login_user.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
