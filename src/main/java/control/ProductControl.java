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

import com.google.gson.Gson;

import beans.Cart;
import beans.CategoryBean;
import beans.ProductBean;
import model.CategoryDAO;
import model.ProductDAO;
import utils.Utility;

@WebServlet("/ProductControl")
public class ProductControl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		ProductDAO model = new ProductDAO(ds);
		CategoryDAO modelCat = new CategoryDAO(ds);
		HttpSession session = request.getSession();

		Cart cart = (Cart) session.getAttribute("cart");

		if (cart == null)
		{
			cart = new Cart();
			session.setAttribute("cart", cart);
		}

		String sort = request.getParameter("sort");
		String categoryId = request.getParameter("categoryId");
		String action = request.getParameter("action");
		String page = request.getParameter("page");

		try
		{
			if (categoryId != null)
			{
				CategoryBean catBean = new CategoryBean();
				catBean.setId(Integer.parseInt(categoryId));
				request.removeAttribute("products");
				request.setAttribute("products", model.DoRetrieveByCategory(catBean));

				request.removeAttribute("title");
				request.setAttribute("title", modelCat.doRetrieve(catBean).getName());

				RequestDispatcher dispatcher = this.getServletContext()
						.getRequestDispatcher("/common_pages/catalogue.jsp");
				dispatcher.forward(request, response);
				return;
			}

			if (action != null)
			{
				if (action.equals("details"))
				{
					String id = request.getParameter("id");

					ProductBean product = new ProductBean();
					product.setCode(Integer.parseInt(id));

					request.removeAttribute("product");
					request.setAttribute("product", model.doRetrieve(product));

					RequestDispatcher dispatcher = this.getServletContext()
							.getRequestDispatcher("/common_pages/product_details.jsp");
					dispatcher.forward(request, response);
					return;
				} else if (action.equals("insert"))
				{
					String name = request.getParameter("name");
					String description = request.getParameter("description");
					double price = Double.parseDouble(request.getParameter("price"));
					int quantity = Integer.parseInt(request.getParameter("quantity"));

					ProductBean bean = new ProductBean();
					bean.setName(name);
					bean.setDescription(description);
					bean.setPrice(price);
					bean.setQuantity(quantity);

					model.doSave(bean);
					request.setAttribute("message", "Prodotto " + bean.getName() + " aggiunto con successo.");

				} else if (action.equals("delete"))
				{
					String id = request.getParameter("id");

					ProductBean product = new ProductBean();
					product.setCode(Integer.parseInt(id));

					ProductBean bean = model.doRetrieve(product);
					if (bean != null && !bean.isEmpty())
					{
						model.doDelete(bean);
						request.setAttribute("message", "Prodotto " + bean.getName() + " rimosso con successo.");
					}
				} else if (action.equals("update"))
				{
					String id = request.getParameter("id");
					String name = request.getParameter("name");
					String description = request.getParameter("description");
					double price = Double.parseDouble(request.getParameter("price"));
					int quantity = Integer.parseInt(request.getParameter("quantity"));

					ProductBean bean = new ProductBean();
					bean.setCode(Integer.parseInt(id));
					bean.setName(name);
					bean.setDescription(description);
					bean.setPrice(price);
					bean.setQuantity(quantity);

					model.doUpdate(bean);
					request.setAttribute("message", "Prodotto " + bean.getName() + " aggiornato con successo");
				}
			}
		} catch (SQLException | NumberFormatException e)
		{
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
		}

		session.setAttribute("cart", cart);

		try
		{

			request.removeAttribute("products");
			request.setAttribute("products", model.doRetrieveAll(sort));
		} catch (SQLException e)
		{
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
		}

		if (page != null && page.equals("home"))
		{
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/common_pages/home.jsp");
			dispatcher.forward(request, response);
		} else
		{
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/common_pages/catalogue.jsp");
			dispatcher.forward(request, response);
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		ProductDAO model = new ProductDAO(ds);
		HttpSession session = request.getSession();
		boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
		Cart cart = (Cart) session.getAttribute("cart");
		if (ajax)
		{
			try
			{
				String id = request.getParameter("id");
				int qty = cart.getProductQuantity(Integer.parseInt(id));
				ProductBean product = new ProductBean();
				product.setCode(Integer.parseInt(id));
				ProductBean bean = model.doRetrieve(product);
				String json;
				if (bean != null && !bean.isEmpty() && (bean.getQuantity() - qty) - 1 >= 0)
				{
					cart.addItem(bean);

					request.removeAttribute("cart");
					request.setAttribute("cart", cart);
					json = new Gson().toJson(cart.getTotalQuantity());
					response.getWriter().write(json);

				} else
				{
					json = new Gson().toJson(cart.getTotalQuantity());
					response.getWriter().write(json);
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}

		}
	}

}
