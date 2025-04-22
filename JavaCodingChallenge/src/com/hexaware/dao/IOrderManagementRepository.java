package com.hexaware.dao;

import java.util.*;
import com.hexaware.entity.*;
import com.hexaware.exception.*;

public interface IOrderManagementRepository {
    void createUser(User user);
    void createProduct(User user, Product product);
    void createOrder(User user, List<Product> products);
    void cancelOrder(int userId, int orderId) throws UserNotFoundException, OrderNotFoundException;

    List<Product> getAllProducts();
    List<Product> getOrderByUser(User user);
}