package control;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.sql.DataSource;

import beans.CategoryBean;
import beans.ProductBean;
import model.CategoryDAO;
import model.GenericDAO;
import model.ProductDAO;
import utils.Utility;

/**
 * Servlet implementation class ProductDetailsControl
 */
@WebServlet("/ProductModifyControl")

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB

public class ProductModifyControl extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{

		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		GenericDAO<ProductBean> productModel = new ProductDAO(ds);
		GenericDAO<CategoryBean> categoryModel = new CategoryDAO(ds);
		String id = request.getParameter("id");
		ProductBean bean = new ProductBean();

		try
		{
			if (id != null)
			{
				bean.setCode(Integer.parseInt(id));
				request.setAttribute("productBean", productModel.doRetrieve(bean));

				request.setAttribute("categories", categoryModel.doRetrieveAll(""));
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/admin_pages/modify_product.jsp");
		dispatcher.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		String savePath = (String) getServletContext().getAttribute("path");
		GenericDAO<ProductBean> productModel = new ProductDAO(ds);
		GenericDAO<CategoryBean> categoryModel = new CategoryDAO(ds);

		String id = request.getParameter("id");
		ProductBean bean = new ProductBean();

		try
		{
			if (id != null)
			{
				bean.setCode(Integer.parseInt(id));
				bean.setDescription(request.getParameter("description"));
				bean.setShortDescription(request.getParameter("shortDescription"));
				bean.setName(request.getParameter("name"));
				bean.setQuantity(Integer.parseInt(request.getParameter("quantity")));
				bean.setTaxRate(Double.parseDouble(request.getParameter("taxRate")));
				bean.setDiscount(Double.parseDouble(request.getParameter("discount")));
				bean.setPrice(Double.parseDouble(request.getParameter("price")));

				String[] categories = request.getParameterValues("categories");

				if (categories != null)
				{
					for (String category : categories)
					{
						String[] parts = category.split("\\.");
						bean.addCategory(new CategoryBean(Integer.parseInt(parts[0]), parts[1]));
					}
				}

				File fileSaveDir = new File(savePath);
				if (!fileSaveDir.exists())
				{
					fileSaveDir.mkdir();
				}

				String fileName;
				String code;
				for (Part part : request.getParts())
				{
					fileName = extractFileName(part);
					if (fileName != null && !fileName.equals(""))
					{
						while (!isUnique(fileName, savePath))
						{
							code = createRandomCode(8, "ABCDEFGHILMNOPQRZWY");
							fileName = code + request.getParameter("format");
						}

						if (fileName.contains(" "))
						{
							fileName = fileName.replace(' ', '_');
						}

						String oldPath = productModel.doRetrieve(bean).getUrl();

						String[] split = oldPath.split("/");
						oldPath = split[split.length - 1];
						System.out.println(oldPath);

						if (oldPath != null)
						{
							oldPath = savePath + File.separator + oldPath;
							new File(oldPath).delete();
						}

						part.write(savePath + File.separator + fileName);
						bean.setUrl(request.getContextPath() + "/images/products/" + fileName);
						System.out.println(savePath + File.separator + fileName);
					}
				}

				productModel.doUpdate(bean);

			}
		} catch (Exception e)
		{
			Utility.print(e);
			request.setAttribute("error", e.getMessage());
		}

		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/admin_pages/catalogue.jsp");
		dispatcher.forward(request, response);
	}

	private String extractFileName(Part part)
	{
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items)
		{
			if (s.trim().startsWith("filename"))
			{
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}

	public String createRandomCode(int codeLength, String id)
	{
		List<Character> temp = id.chars().mapToObj(i -> (char) i).collect(Collectors.toList());
		Collections.shuffle(temp, new SecureRandom());
		return temp.stream().map(Object::toString).limit(codeLength).collect(Collectors.joining());
	}

	public boolean isUnique(String fileName, String path)
	{

		File[] files = new File(path).listFiles();
		if (files == null || files.length == 0)
			return true;
		for (File file : files)
		{
			System.out.println(file.getName());
			if (file.getName().equals(fileName))
				return false;
		}
		return true;
	}

}
