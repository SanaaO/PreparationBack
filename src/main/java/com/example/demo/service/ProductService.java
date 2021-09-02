package com.example.demo.service;


import com.example.demo.entity.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product AddNewProduct(Product product) {
        return this.productRepository.save(product);
    }

    public Product getProduct(Long id) {
        return this.productRepository.getById(id);
    }

    public List<Product> getAllProducts() {
        return this.productRepository.findAll();
    }

    public Product updateProduct(Product product) {
        return this.productRepository.save(product);
    }

    public void delete(Long id) {
        productRepository.deleteById(id);
    }

}
