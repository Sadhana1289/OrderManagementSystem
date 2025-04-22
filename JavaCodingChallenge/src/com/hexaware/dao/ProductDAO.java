package com.hexaware.dao;

import com.hexaware.database.DatabaseConnector;
import com.hexaware.entity.*;

import java.sql.*;
import java.util.*;

public class ProductDAO {
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        try (Connection conn = DatabaseConnector.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Product")) {

            while (rs.next()) {
                int id = rs.getInt("productid");
                String name = rs.getString("productname");
                String desc = rs.getString("description");
                double price = rs.getDouble("price");
                int qty = rs.getInt("quantityinstock");
                String type = rs.getString("type");

                if ("electronics".equalsIgnoreCase(type)) {
                    String brand = rs.getString("brand");
                    int warranty = rs.getInt("warrantyPeriod");
                    products.add(new Electronics(id, name, desc, price, qty, brand, warranty));
                } else if ("clothing".equalsIgnoreCase(type)) {
                    String size = rs.getString("size");
                    String color = rs.getString("color");
                    products.add(new Clothing(id, name, desc, price, qty, type, size, color));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    public void insertProduct(Product product) {
        try (Connection conn = DatabaseConnector.getConnection()) {
            String query;
            PreparedStatement ps;

            if (product instanceof Electronics) {
                query = "INSERT INTO Product (productid, productname, description, price, quantityinstock, type, brand, warrantyPeriod) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                ps = conn.prepareStatement(query);
                Electronics e = (Electronics) product;
                ps.setInt(1, e.getProductId());
                ps.setString(2, e.getProductName());
                ps.setString(3, e.getDescription());
                ps.setDouble(4, e.getPrice());
                ps.setInt(5, e.getQuantityInStock());
                ps.setString(6, e.getType());
                ps.setString(7, e.getBrand());
                ps.setInt(8, e.getWarrantyPeriod());
            } else if (product instanceof Clothing) {
                query = "INSERT INTO Product (productid, productname, description, price, quantityinstock, type, size, color) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                ps = conn.prepareStatement(query);
                Clothing c = (Clothing) product;
                ps.setInt(1, c.getProductId());
                ps.setString(2, c.getProductName());
                ps.setString(3, c.getDescription());
                ps.setDouble(4, c.getPrice());
                ps.setInt(5, c.getQuantityInStock());
                ps.setString(6, c.getType());
                ps.setString(7, c.getSize());
                ps.setString(8, c.getColor());
            } else {
                return;
            }

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
