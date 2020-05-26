package service;

import model.Category;
import model.Product;
import util.DBHandle;
import util.Query;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ProductServiceImpl implements IProductService {
    private Connection connection;
    private PreparedStatement statement;
    public ProductServiceImpl() {
        connection = DBHandle.getConnection();
    }

    public Product getProduct(int id) throws SQLException{
        statement = connection.prepareStatement(Query.SELECT_PRODUCT_BY_ID);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        resultSet.first();
        return parseResultSet(resultSet);
    }
    public List<Product> getProductList() throws SQLException{
        List<Product> products = new LinkedList<>();
        statement = connection.prepareStatement(Query.SELECT_ALL_PRODUCT);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            products.add(parseResultSet(resultSet));
        }
        return products;
    }

    public boolean updateProduct(Product product, int id) throws SQLException{
        statement = connection.prepareStatement(Query.UPDATE_PRODUCT);
        statement.setInt(1, product.getProductID());
        statement.setString(2, product.getProductName());
        statement.setFloat(3, product.getPrice());
        statement.setString(4, product.getColor());
        statement.setInt(5, product.getQuantity());
        statement.setInt(6, product.getCategoryID());
        statement.setInt(7, id);
        return statement.executeUpdate() > 0;
    }

    @Override
    public boolean deleteProduct(int id) throws SQLException {
        boolean rowDeleted;
        statement = connection.prepareStatement(Query.DELETE_PRODUCT_BY_ID);
        statement.setInt(1, id);
        rowDeleted = statement.executeUpdate() >0;
        return rowDeleted;
    }

    public void createProduct(Product product) throws SQLException{
        statement = connection.prepareStatement(Query.CREATE_PRODUCT);
        statement.setInt(1, product.getProductID());
        statement.setString(2, product.getProductName());
        statement.setFloat(3, product.getPrice());
        statement.setString(4, product.getColor());
        statement.setInt(6, product.getQuantity());
        statement.setInt(5, product.getCategoryID());
        int resultSet = statement.executeUpdate();
    }

    private Product parseResultSet(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        Category category = new Category();
        product.setProductID(resultSet.getInt("product.id"));
        product.setProductName(resultSet.getString("product_name"));
        product.setPrice(resultSet.getFloat("price"));
        product.setColor(resultSet.getString("color"));
        product.setQuantity(resultSet.getInt("quantity"));
        product.setCategoryID(resultSet.getInt("category_id"));
        category.setCategoryID(resultSet.getInt("category_id"));
        category.setCategoryName(resultSet.getString("category_name"));
        product.setCategory(category);
        return product;
    }
}
