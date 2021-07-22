package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

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

@WebServlet("/RegistrationControl")
public class RegistrationControl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		UserDAO model = new UserDAO(ds);
		HttpSession session = request.getSession();

		UserBean user = new UserBean();
		Collection<UserBean> users = new ArrayList<UserBean>();

		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String email = request.getParameter("email");
		String pw = request.getParameter("password");
		String newsletter = request.getParameter("newsletter");

		user.setFirstName(name);
		user.setLastName(surname);
		user.setEmail(email);
		user.setPassword(pw);

		if (newsletter == null)
			newsletter = "false";

		user.setNewsletter(Boolean.parseBoolean(newsletter));

		try
		{
			users = model.doRetrieveAll("");

			for (UserBean bean : users)
			{

				if (bean.getEmail().equals(user.getEmail()))
				{
					request.removeAttribute("message");
					request.setAttribute("message", "Sei già registrato");
					RequestDispatcher dispatcher = this.getServletContext()
							.getRequestDispatcher("/common_pages/registration.jsp");
					dispatcher.forward(request, response);
					return;
				}
			}

			model.doSave(user);
			session.setAttribute("user", model.doRetrieve(user));
			session.setAttribute("userRoles", true);

		} catch (Exception e)
		{
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
		}

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/common_pages/home.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
