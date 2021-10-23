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

    public Product getProduct(Long id) throws Exception {
        return this.productRepository.findById(id).orElseThrow(() -> new Exception("user not found "));
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
