package control;

import java.io.File;
import java.io.IOException;
import java.security.SecureRandom;
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

import beans.ProductBean;
import model.GenericDAO;
import model.ProductDAO;

/**
 * Servlet implementation class RegistrationProductControl
 */
@WebServlet("/RegistrationProductControl")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
	maxFileSize = 1024 * 1024 * 10, // 10MB
	maxRequestSize = 1024 * 1024 * 50) // 50MB

public class RegistrationProductControl extends HttpServlet {
    private static final long serialVersionUID = 1L;
    static String SAVE_DIR = "/products_images";


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
		String savePath = (String) getServletContext().getAttribute("path");
		GenericDAO<ProductBean> productModel = new ProductDAO(ds);
		ProductBean bean = new ProductBean();
		bean.setName(request.getParameter("name"));
		bean.setDescription(request.getParameter("description"));
		bean.setPrice(Double.parseDouble(request.getParameter("price")));
		bean.setShortDescription(request.getParameter("shortDescription"));
		bean.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		bean.setTaxRate(Double.parseDouble(request.getParameter("taxRate")));
		bean.setDiscount(Double.parseDouble(request.getParameter("discount")));
	
		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) {
		    fileSaveDir.mkdir();
		}
		String fileName;
		String code;
		for (Part part : request.getParts()) {
		    fileName = extractFileName(part);
		    if (fileName != null && !fileName.equals("")) {
				while (!isUnique(fileName, savePath)) {
				    code = createRandomCode(8, "ABCDEFGHILMNOPQRZWY");
				    fileName = code + request.getParameter("format");
				}
				part.write(savePath + File.separator + fileName);
				bean.setUrl(request.getContextPath()+"/images/products/"+fileName);
				System.out.println(savePath + File.separator + fileName);
		    }
		}
	
		try {
		    productModel.doSave(bean);
		} catch (Exception e) {
		    // TODO: handle exception
		}
	
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/admin_pages/catalogue.jsp");
		dispatcher.forward(request, response);
	
	    }
	
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
	
	    public String createRandomCode(int codeLength, String id) {
		List<Character> temp = id.chars().mapToObj(i -> (char) i).collect(Collectors.toList());
		Collections.shuffle(temp, new SecureRandom());
		return temp.stream().map(Object::toString).limit(codeLength).collect(Collectors.joining());
	    }
	
	    public boolean isUnique(String fileName, String path) {
	
		File[] files = new File(path).listFiles();
		if (files == null || files.length == 0)
		    return true;
		for (File file : files) {
		    System.out.println(file.getName());
		    if (file.getName().equals(fileName))
			return false;
		}
		return true;
    }

}
