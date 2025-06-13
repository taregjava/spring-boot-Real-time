package com.halfacode.spring_real_time_learning.repository;

import com.halfacode.spring_real_time_learning.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepository {

    private List<Product> products = new ArrayList<>();

    // List all products
    public List<Product> getAllProducts() {
        return products;
    }

    // Find a product by ID
    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    // Search for products by name
    public List<Product> search(String name) {
        return products.stream()
                .filter(p -> p.getName().startsWith(name))
                .collect(Collectors.toList());
    }

    // Save a new product
    public Product save(Product p) {
        Product product = new Product();
        product.setId(p.getId());
        product.setName(p.getName());
        product.setQuantity(p.getQuantity());
        product.setPrice(p.getPrice());

        products.add(product);
        return product;
    }

    // Delete a product by ID
    public String delete(Integer id) {
        products.removeIf(p -> p.getId() == id);
        return null;
    }

    // Update an existing product
    public Product update(Product product) {
        int idPos = 0;
        int id = 0;

        // Check if product exists and get its position and ID
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == product.getId()) {
                id = product.getId(); // Product ID
                idPos = i;            // Position
                break;
            }
        }

        Product updatedProduct = new Product();
        updatedProduct.setId(id);
        updatedProduct.setName(product.getName());
        updatedProduct.setQuantity(product.getQuantity());
        updatedProduct.setPrice(product.getPrice());
        products.set(idPos, product);

        return updatedProduct;
    }
}