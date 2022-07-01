package com.coeding.controller;

import com.coeding.dao.ProductDao;
import com.coeding.model.Product;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ProductDao productDAO;
    private List<String> listCategory;
    
    public void initCategory() {
    	listCategory = new ArrayList<String>();
    	listCategory.add("Danh mục 1");
    	listCategory.add("Danh mục 2");
    	listCategory.add("Danh mục 3");
    	listCategory.add("Danh mục 4");
    }
    
    public void init() {
    	productDAO = new ProductDao();
    	initCategory();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    insertProduct(request, response);
                    break;
                case "edit":
                    updateProduct(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "create":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteProduct(request, response);
                    break;
                default:
                    listProduct(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Product> listProduct = productDAO.selectAllProducts();
        int page = 1;
        if(request.getParameter("page") != null)
            page = Integer.parseInt(request.getParameter("page"));//1: 0,1,2 - 2:3,4,5 - 3:6,7,8 - 4:9
        int limitPage = 3;
        float pagingCount = (float) listProduct.size() / limitPage;
        int paging = (int) ((pagingCount<1) ? 1 : ((pagingCount > Math.round(pagingCount)) ? (Math.round(pagingCount)+1) : Math.round(pagingCount)));
//        if(page > limitPage) {
//        	limitPage = page - limitPage;
//        	page = 0;
//        }
        List<Product> listProductByPaging = productDAO.selectAllProducts(page, limitPage);
        request.setAttribute("listProduct", listProductByPaging);
        request.setAttribute("paging", paging);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/create.jsp");
        request.setAttribute("listCategory", listCategory);
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product existingProduct = productDAO.selectProduct(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/edit.jsp");
        request.setAttribute("product", existingProduct);
        request.setAttribute("listCategory", listCategory);
        dispatcher.forward(request, response);
    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Float price = Float.parseFloat(request.getParameter("price"));
        String category = request.getParameter("category");
        Product newProduct = new Product(name, description, price, category);
        productDAO.insertProduct(newProduct);
        
        request.setAttribute("listCategory", listCategory);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/create.jsp");
        dispatcher.forward(request, response);
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Float price = Float.parseFloat(request.getParameter("price"));
        String category = request.getParameter("category");
        Product book = new Product(id, name, description, price, category);
        productDAO.updateProduct(book);
        
        List<Product> listProduct = productDAO.selectAllProducts();
        request.setAttribute("listProduct", listProduct);
        request.setAttribute("listCategory", listCategory);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/list.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        productDAO.deleteProduct(id);
        List<Product> listProduct = productDAO.selectAllProducts();
        request.setAttribute("listProduct", listProduct);
        RequestDispatcher dispatcher = request.getRequestDispatcher("product/list.jsp");
        dispatcher.forward(request, response);
    }
    
    public static <T extends Object> List<T[]> splitArray(T[] array, int max){

    	  int x = array.length / max;
    	  int r = (array.length % max); // remainder

    	  int lower = 0;
    	  int upper = 0;

    	  List<T[]> list = new ArrayList<T[]>();

    	  int i=0;

    	  for(i=0; i<x; i++){

    	    upper += max;

    	    list.add(Arrays.copyOfRange(array, lower, upper));

    	    lower = upper;
    	  }

    	  if(r > 0){

    	    list.add(Arrays.copyOfRange(array, lower, (lower + r)));

    	  }

    	  return list;
    	}
}
