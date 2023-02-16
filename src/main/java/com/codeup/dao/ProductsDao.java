package com.codeup.dao;

import com.codeup.models.Product;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductsDao {
    private Connection connection = null;

    public ProductsDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                    config.getUrl(),
                    config.getUser(),
                    config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    private Product extract(ResultSet rs) throws SQLException {
        return new Product(
                rs.getLong("id"),
                rs.getString("name"),
                rs.getDouble("price"),
                rs.getInt("quantity")
        );
    }

    private List<Product> createFromResultSet(ResultSet rs) throws SQLException {
        List<Product> products = new ArrayList<>();
        while (rs.next()) {
            products.add(extract(rs));
        }
        return products;
    }

    public List<Product> all() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM products");
            ResultSet rs = stmt.executeQuery();
            return createFromResultSet(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving the products.", e);
        }
    }

    public Product findById(Long id) {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM products WHERE id = ?");
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extract(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding product #" + id, e);
        }
        return null;
    }

    public Long insert(Product product) {
        try {
            String insertQuery = "INSERT INTO products(name, price, quantity) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getQuantity());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting a product", e);
        }
    }

    public void update(Product product) {
        try {
            String updateQuery = "UPDATE products SET name = ?, price = ?, quantity = ? WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(updateQuery);
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getQuantity());
            stmt.setLong(4, product.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating product #" + product.getId(), e);
        }
    }

    public void delete(Long id) {
        try {
            String deleteQuery = "DELETE FROM products WHERE id = ?";
            PreparedStatement stmt = connection.prepareStatement(deleteQuery);
            stmt.setLong(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting product #" + id, e);
        }
    }
    public Long create(Product product) {
        try {
            String insertQuery = "INSERT INTO products(name, price, quantity) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, product.getName());
            stmt.setDouble(2, product.getPrice());
            stmt.setInt(3, product.getQuantity());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting a product", e);
        }
    }

}
