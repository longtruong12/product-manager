package com.coeding.dao;

import com.coeding.model.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ProductDao implements IProductDao {

	private String jdbcURL = "jdbc:mysql://localhost:3306/demo?useUnicode=yes&characterEncoding=UTF-8";
    private String jdbcUsername = "root";
    private String jdbcPassword = null;

    private static final String INSERT_PRODUCTS_SQL = "INSERT INTO products" + "  (name, description, price, category) VALUES " +
            " (?, ?, ?, ?);";

    private static final String SELECT_PRODUCT_BY_ID = "select * from products where id = ?";
    private static final String SELECT_ALL_PRODUCTS = "select * from products";
    private static final String DELETE_PRODUCTS_SQL = "delete from products where id = ?;";
    private static final String UPDATE_PRODUCTS_SQL = "update products set name = ?,description= ?, price= ?, category= ? where id = ?;";

    public ProductDao() {}

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    
	@Override
	public void insertProduct(Product product) throws SQLException {
		System.out.println(INSERT_PRODUCTS_SQL);
        // try-with-resource statement will auto close the connection.
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PRODUCTS_SQL)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getDescription());
            preparedStatement.setFloat(3, product.getPrice());
            preparedStatement.setString(4, product.getCategory());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
	}

	@Override
	public Product selectProduct(int id) {
		Product product = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PRODUCT_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            if (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                Float price = rs.getFloat("price");
                String category = rs.getString("category");
                product = new Product(id, name, description, price, category);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return product;
	}

	@Override
	public List<Product> selectAllProducts() {
        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Product> products = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) { 
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                Float price = rs.getFloat("price");
                String category = rs.getString("category");
                products.add(new Product(id, description, name, price, category));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
	}
	
	@Override
	public List<Product> selectAllProducts(int page, int limit) {
        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Product> products = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_PRODUCTS+" ORDER BY id DESC LIMIT "+page+","+limit);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) { 
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                Float price = rs.getFloat("price");
                String category = rs.getString("category");
                products.add(new Product(id, description, name, price, category));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return products;
	}

	@Override
	public boolean deleteProduct(int id) throws SQLException {
		boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCTS_SQL);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
	}

	@Override
	public boolean updateProduct(Product product) throws SQLException {
		boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCTS_SQL);) {
            statement.setString(1, product.getName());
            statement.setString(2, product.getDescription());
            statement.setFloat(3, product.getPrice());
            statement.setString(4, product.getCategory());
            statement.setInt(5, product.getId());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
	}
	
    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
