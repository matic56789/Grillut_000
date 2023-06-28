/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package ph.pup.itech.grillut.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ph.pup.itech.grillut.model.ProductModel;
import ph.pup.itech.grillut.dao.ProductDao;

public class addProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        switch (action) {
            case "/products/add":
                getProduct(request, response);
                break;
            default:
                showForm(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void getProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int productID = Integer.parseInt(request.getParameter("productID"));
        String productName = request.getParameter("productName");
        String productDescription = request.getParameter("productDescription");
        String productSize = request.getParameter("productSize");
        double productPrice = Double.parseDouble(request.getParameter("productPrice"));
        int productQuantity = Integer.parseInt(request.getParameter("productQuantity"));

        ProductModel prd = new ProductModel(
                productID, productName, productDescription, productSize, productPrice, productQuantity);

        ProductDao productDao = new ProductDao();
        ProductModel getProduct = productDao.getProductDetails(prd);

        String message = getProduct.getproductName() + " with " + getProduct.getproductID() + " has been added to inventory.";

        request.setAttribute("product", getProduct);
        request.setAttribute("message", message);
        
        System.out.println(message);
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/products.jsp");
        rd.forward(request, response);
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = getServletContext().getRequestDispatcher(
                "/products.jsp");
        rd.forward(request, response);
    }
}
