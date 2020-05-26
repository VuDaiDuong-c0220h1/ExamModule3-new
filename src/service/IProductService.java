package service;

import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface IProductService {
    List<Product> getProductList() throws SQLException;
    Product getProduct(int id) throws SQLException;
    boolean updateProduct(Product product, int id) throws SQLException;
    boolean deleteProduct(int id) throws SQLException;
    void createProduct(Product product) throws SQLException;
}
