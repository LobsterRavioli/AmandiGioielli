package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.Gson;

import beans.ProductBean;
import model.ProductDAO;
import utils.Utility;
@WebServlet("/Search")
public class SearchControl extends HttpServlet {
    
    private static final long serialVersionUID = 1L;
     
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
    {
             
        //JsonArray productsJson = new JsonArray();
        String productsJson = "";
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        ProductDAO model = new ProductDAO(ds);
        String query = request.getParameter("partialName");
        Collection<ProductBean> products = null;
        
        if(query != null) {
            
            Utility.print("SEARCH QUERY: " + query);
            
            try {
                products = model.retrieveProductsByPartialName(query);
            } catch(SQLException e) {
                e.printStackTrace();
            }
               
            productsJson = new Gson().toJson(products);
        }
        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(productsJson); 
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
    {
    	doPost(request, response);
    }
}