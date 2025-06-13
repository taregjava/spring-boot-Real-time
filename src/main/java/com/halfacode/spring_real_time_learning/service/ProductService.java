package com.halfacode.spring_real_time_learning.service;


import com.halfacode.spring_real_time_learning.model.Product;
import com.halfacode.spring_real_time_learning.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<Product> getProducts() {
        return productRepository.getAllProducts();
    }

    public Product getProductById(int id) {
        return productRepository.findById(id);
    }

    public String deleteProduct(int id) {
        productRepository.delete(id);
        return "Product deleted! ID: " + id;
    }

    public Product updateProduct(Product product) {
        return productRepository.update(product);
    }
}