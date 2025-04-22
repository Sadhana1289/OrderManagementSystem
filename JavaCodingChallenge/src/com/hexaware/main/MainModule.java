package com.hexaware.main;

import java.util.*;
import com.hexaware.dao.*;
import com.hexaware.entity.*;
import com.hexaware.exception.*;

public class MainModule {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        OrderProcessor processor = new OrderProcessor();

        while (true) {
            System.out.println("\n===== Order Management System =====");
            System.out.println("1. Create User");
            System.out.println("2. Create Product");
            System.out.println("3. Create Order");
            System.out.println("4. Cancel Order");
            System.out.println("5. Get All Products");
            System.out.println("6. Get Orders by User");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter User ID: ");
                    int userId = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Username: ");
                    String username = sc.nextLine();
                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();
                    System.out.print("Enter Role (Admin/User): ");
                    String role = sc.nextLine();
                    processor.createUser(new User(userId, username, password, role));
                    System.out.println("User created successfully!\n");
                    break;

                case 2:
                    System.out.print("Enter Admin User ID: ");
                    int adminId = sc.nextInt(); sc.nextLine();
                    User admin = new User(adminId, "admin", "pass", "Admin");
                    System.out.print("Enter Product Type (Electronics/Clothing): ");
                    String type = sc.nextLine();

                    System.out.print("Enter Product ID: ");
                    int productId = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Product Name: ");
                    String productName = sc.nextLine();
                    System.out.print("Enter Description: ");
                    String description = sc.nextLine();
                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();
                    System.out.print("Enter Quantity in Stock: ");
                    int qty = sc.nextInt(); sc.nextLine();

                    if (type.equalsIgnoreCase("Electronics")) {
                        System.out.print("Enter Brand: ");
                        String brand = sc.nextLine();
                        System.out.print("Enter Warranty Period (months): ");
                        int warranty = sc.nextInt();
                        Product electronics = new Electronics(productId, productName, description, price, qty, brand, warranty);
                        processor.createProduct(admin, electronics);
                    } else if (type.equalsIgnoreCase("Clothing")) {
                        System.out.print("Enter Size: ");
                        String size = sc.nextLine();
                        System.out.print("Enter Color: ");
                        String color = sc.nextLine();
                        Product clothing = new Clothing(productId, productName, description, price, qty,type, size, color);
                        processor.createProduct(admin, clothing);
                    } else {
                        System.out.println("Invalid product type!");
                    }
                    System.out.println("Product created successfully!\n");
                    break;

                case 3:
                    System.out.print("Enter User ID for Order: ");
                    int orderUserId = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Product ID to Order: ");
                    int orderProdId = sc.nextInt();
                    Product orderedProduct = processor.getAllProducts().stream()
                        .filter(p -> p.getProductId() == orderProdId)
                        .findFirst().orElse(null);

                    if (orderedProduct != null) {
                        User orderUser = new User(orderUserId, "user", "pass", "User");
                        processor.createOrder(orderUser, List.of(orderedProduct));
                        System.out.println("Order created successfully!\n");
                    } else {
                        System.out.println("Product not found!\n");
                    }
                    break;

                case 4:
                    try {
                        System.out.print("Enter User ID: ");
                        int cancelUserId = sc.nextInt();
                        System.out.print("Enter Order ID: ");
                        int cancelOrderId = sc.nextInt();
                        processor.cancelOrder(cancelUserId, cancelOrderId);
                        System.out.println("Order cancelled successfully!\n");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case 5:
                    List<Product> allProducts = processor.getAllProducts();
                    System.out.println("\n--- Product List ---");
                    for (Product p : allProducts) {
                        System.out.println("ID: " + p.getProductId() + ", Name: " + p.getProductName() + ", Price: " + p.getPrice());
                    }
                    break;

                case 6:
                    System.out.print("Enter User ID to view orders: ");
                    int viewUserId = sc.nextInt();
                    User viewUser = new User(viewUserId, "user", "pass", "User");
                    List<Product> userOrders = processor.getOrderByUser(viewUser);
                    System.out.println("\n--- Orders by User ---");
                    for (Product p : userOrders) {
                        System.out.println("ID: " + p.getProductId() + ", Name: " + p.getProductName());
                    }
                    break;

                case 7:
                    System.out.println("Exiting Order Management System. Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.\n");
            }
        }
    }
}
