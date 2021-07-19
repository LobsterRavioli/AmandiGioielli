package control;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.google.gson.Gson;

import beans.AddressBean;
import beans.UserBean;
import model.AddressDAO;

/**
 * Servlet implementation class AddressControl
 */
@WebServlet("/update")
public class AddressControl extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddressControl() {
	super();
	// TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {

    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
	AddressDAO addressModel = new AddressDAO(ds);
	HttpSession session = request.getSession();
	UserBean user = (UserBean) session.getAttribute("user");
	Map<String, Object> map = new HashMap<String, Object>();

	AddressBean address = new AddressBean();
	String street = (String) request.getParameter("street");
	String province = (String) request.getParameter("province");
	String zip = (String) request.getParameter("zip");
	String phone = (String) request.getParameter("phone");
	String city = (String) request.getParameter("city");
	String addressNumber = (String) request.getParameter("addressNumber");

	map.put("street", street);
	map.put("province", province);
	map.put("zip", zip);
	map.put("phone", phone);
	map.put("city", city);
	map.put("addressNumber", addressNumber);
	address.setZip(zip);
	address.setCity(city);
	address.setPhone(phone);
	address.setProvince(province);
	address.setPhone(phone);
	address.setUserId(user.getId());
	address.setAddressNumber(Integer.parseInt(addressNumber));

	boolean isValid = true;
	try {
	    addressModel.doSave(address);
	} catch (Exception e) {
	    isValid = false;
	    System.out.print(e);
	}
	map.put("isValid", isValid);
	write(response, map);
    }

    private void write(HttpServletResponse response, Map<String, Object> map) throws IOException {
	response.setContentType("application/json");
	response.setCharacterEncoding("UTF-8");
	response.getWriter().write(new Gson().toJson(map));
    }

}
