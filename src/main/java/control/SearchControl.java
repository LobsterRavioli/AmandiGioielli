package control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
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
       
        DataSource ds = (DataSource) getServletContext().getAttribute("DataSource");
        ProductDAO model = new ProductDAO(ds);
        String query = request.getParameter("search");
        String action = request.getParameter("action");
        Collection<ProductBean> products = null;
        
        if(query != null) {
            
        	if(action.equals("admin"))
        	{
        		Utility.print("SEARCH QUERY: " + query);
            
        		try 
        		{
        			products = model.retrieveProductsByPartialName(query);
        			request.setAttribute("products", products);
        		} 
        		catch(SQLException e) 
        		{
        			e.printStackTrace();
        		}
        	}
        	
        	else if(action.equals("common"))
        	{
        		try 
        		{
        			products = model.retrieveProductsByPartialName(query);
        			request.setAttribute("products", products);
        		} 
        		catch(SQLException e) 
        		{
        			e.printStackTrace();
        		}
        	}

        }
        
    	RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/"+action+"_pages/catalogue.jsp");
    	dispatcher.forward(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException 
    {
    	doPost(request, response);
    }
}