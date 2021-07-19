package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.Gson;

import beans.UserBean;
import model.UserDAO;

@WebServlet("/AdminCustomerControl")
public class AdminCustomerControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	UserDAO userModel = new UserDAO(ds);
	Collection<UserBean> users = null;
	try {
	    users = userModel.doRetrieveAll("user_id");
	} catch (SQLException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}

	Map<String, Object> map = new HashMap<String, Object>();
	@SuppressWarnings("unused")
	boolean isValid = true;

	for (UserBean x : users) {
	    String user_id = String.valueOf(x.getId());
	    String email = x.getEmail();
	    String first_name = x.getFirstName();
	    String last_name = x.getLastName();
	    String phone = x.getPhone();

	    map.put("user_id", user_id);
	    map.put("email", email);
	    map.put("first_name", first_name);
	    map.put("last_name", last_name);
	    map.put("phone", phone);
	}

	try {
	    write(response, map);

	} catch (Exception e) {
	    isValid = false;
	    System.out.print(e);
	}

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	doGet(request, response);
    }

    private void write(HttpServletResponse response, Map<String, Object> map) throws IOException {
	response.setContentType("application/json");
	response.setCharacterEncoding("UTF-8");
	response.getWriter().write(new Gson().toJson(map));
    }
}