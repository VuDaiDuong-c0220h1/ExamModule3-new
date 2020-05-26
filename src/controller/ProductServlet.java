package controller;

import model.Product;
import service.IProductService;
import service.ProductServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/home")
public class ProductServlet extends HttpServlet {
    private IProductService productService = new ProductServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action ==  null){
            action = "";
        }
        try {
            switch (action) {
                case "edit":
                    editProduct(request, response);
                    break;
                case "delete":
                    deleteProduct(request,response);
                    break;
                case "create":
                    createProduct(request,response);
                    break;
                default:
                    listProduct(request,response);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String productName = request.getParameter("name");
        float price = Float.parseFloat(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        int categoryID = Integer.parseInt(request.getParameter("categoryID"));
        Product product = new Product();
        product.setProductID(id);
        product.setProductName(productName);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setColor(color);
        product.setCategoryID(categoryID);
        productService.createProduct(product);
        request.setAttribute("message", "Đã thêm sản phẩm");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/home?action=");
        dispatcher.forward(request, response);
    }
    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productService.deleteProduct(id);
        request.setAttribute("message", "Đã xóa sản phẩm");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/home?action=");
        dispatcher.forward(request, response);
    }

    private void editProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String productName = request.getParameter("name");
        float price = Float.parseFloat(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        int categoryID = Integer.parseInt(request.getParameter("categoryID"));
        Product product = new Product();
        product.setProductID(id);
        product.setProductName(productName);
        product.setPrice(price);
        product.setQuantity(quantity);
        product.setColor(color);
        product.setCategoryID(categoryID);
        productService.updateProduct(product, id);
        request.setAttribute("message", "Đã sửa sản phẩm");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/user/edit.jsp");
        dispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action ==  null){
            action = "";
        }
        try {
            switch (action) {
                case "edit":
                    showEditProduct(request, response);
                    break;
                case "delete":
                    showDeleteProduct(request,response);
                    break;
                case "create":
                    showCreateProduct(request,response);
                    break;
                default:
                    listProduct(request,response);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showCreateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/create.jsp");
        dispatcher.forward(request, response);
    }

    private void showDeleteProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.getProduct(id);
        request.setAttribute("product", product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/delete.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productService.getProduct(id);
        request.setAttribute("product", product);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/edit.jsp");
        dispatcher.forward(request, response);
    }

    private void listProduct(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Product> productList = productService.getProductList();
        request.setAttribute("productList", productList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }
}
