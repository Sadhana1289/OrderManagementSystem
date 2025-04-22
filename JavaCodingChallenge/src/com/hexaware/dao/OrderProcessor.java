package com.hexaware.dao;

import com.hexaware.entity.*;
import com.hexaware.exception.*;

import java.util.*;

public class OrderProcessor implements IOrderManagementRepository {
    private ProductDAO productDAO = new ProductDAO();
    private UserDAO userDAO = new UserDAO();

    public void createUser(User user) {
        userDAO.insertUser(user);
    }

    public void createProduct(User user, Product product) {
        if ("Admin".equalsIgnoreCase(user.getRole())) {
            productDAO.insertProduct(product);
        }
    }

    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    public List<Product> getOrderByUser(User user) {
        return getAllProducts(); // Placeholder: You can implement real order logic later
    }

    public void createOrder(User user, List<Product> productList) {
        System.out.println("Order created successfully!"); // Future enhancement: Insert into DB
    }

    public void cancelOrder(int userId, int orderId) throws UserNotFoundException, OrderNotFoundException {
        throw new UnsupportedOperationException("Cancel order logic not implemented yet.");
    }
}
