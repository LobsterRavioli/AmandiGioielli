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

import beans.UserBean;
import model.UserDAO;
import utils.Utility;

@WebServlet("/RegistrationControl")
public class RegistrationControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	UserDAO model = new UserDAO(ds);
	HttpSession session = request.getSession();

	UserBean user = new UserBean();

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

	try {

	    // Se l'utente e' gia' loggato
	    if (session.getAttribute("user") != null) {
		// redirect account page
		request.setAttribute("message", "Sei già loggato");
		response.sendRedirect(request.getContextPath() + "/common_pages/home.jsp");
		return;
	    }

	    // se non trovo l'utente con email e pass nel db
	    else if (model.doRetrieve(user) == null) {
		try {
		    model.doSave(user);
		} catch (SQLException e) {
		    // se l'utente � gi� registrato ma prova a registrarsi nuovamente con la stessa
		    // mail ma password diversa
		    request.setAttribute("message", "Sei gi� un utente registrato");
		    response.sendRedirect(request.getContextPath() + "/common_pages/login.jsp");
		    return;
		}

		session.setAttribute("user", user);
		request.setAttribute("message", "Benvenuto" + user.getFirstName());
	    }

	    else if (model.doRetrieve(user) != null) {
		request.setAttribute("message", "Sei gi� un utente registrato");
		response.sendRedirect(request.getContextPath() + "/common_pages/login.jsp");
		return;
	    }

	} catch (Exception e) {
	    Utility.print(e);
	    request.setAttribute("error", e.getMessage());
	}

	RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/common_pages/home.jsp");
	dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	doGet(request, response);
    }

}