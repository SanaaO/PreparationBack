package com.example.demo.controller;

import com.example.demo.entity.Category;
import com.example.demo.entity.Product;
import com.example.demo.service.CategoryService;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/products")
public class ProductController {
    @Autowired
    ProductService productsService;

    @Autowired
    CategoryService categoryService;

    @PostMapping(value = "/addproduct", consumes = {"multipart/form-data"})
    public ResponseEntity addProduct(@RequestPart Product productF, @RequestPart MultipartFile file) {

        System.out.println(productF);
        Product productB = new Product(productF.getName(), productF.getDescription(),
                productF.getSize(), productF.getPrice(), productF.getInstock());

        Category category = this.categoryService.findByName(productF.getCategory().toString());
        productB.setCategory(category);

        try {
            byte[] bytes = file.getBytes();
            String name = UUID.randomUUID() + ".png";
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(
                    new File("src/main/resources/uploads/" + name)));
            productB.setPicture("src/main/resources/uploads/" + name);
            stream.write(bytes);
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(this.productsService.AddNewProduct(productB));
    }


    @GetMapping(value = "/getProducts")
    public ResponseEntity getProducts() throws IOException {

        List<Product> products = this.productsService.getAllProducts();
        List<byte[]> files = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            byte[] img = Files.readAllBytes(Paths.get(products.get(i).getPicture()));
            files.add(img);
        }
        List<Object> response = new ArrayList<>();
        response.add(products);
        response.add(files);


        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/update", consumes = {"multipart/form-data"})
    public ResponseEntity updateProduct(@RequestParam long id, @RequestPart Product product, @RequestPart MultipartFile file) {
        Product prod = this.productsService.getProduct(id);
        Category category = categoryService.findByName(product.getCategory().getName());

        prod.setName(product.getName());
        prod.setDescription(product.getDescription());
        prod.setSize(product.getSize());
        prod.setPrice(product.getPrice());
        prod.setInstock(product.getInstock());
        prod.setCategory(category);

        if (file != null) {
            try {
                Files.delete(Paths.get(prod.getPicture()));
                byte[] bytes = file.getBytes();
                String name = UUID.randomUUID() + ".png";
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File("src/main/resources/uploads/" + name)));
                prod.setPicture("src/main/resources/uploads/" + name);
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return ResponseEntity.ok(this.productsService.updateProduct(prod));
    }


    @PostMapping(value = "/addCategory")
    public ResponseEntity create(@RequestBody Category category) {

        return ResponseEntity.ok(categoryService.create(category));
    }

    @GetMapping(value = "/getCategories")
    public ResponseEntity getallCategories() {

        return ResponseEntity.ok(categoryService.getAll());
    }

    @GetMapping(value = "/getCategory")
    public ResponseEntity getCategory(@RequestParam String name) {

        return ResponseEntity.ok(categoryService.findByName(name));
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity delete(@RequestParam Long id) throws IOException {

        Product product = productsService.getProduct(id);
        Files.delete(Paths.get(product.getPicture()));
        productsService.delete(id);
        return ResponseEntity.ok("Product successfully deleted !");
    }
}
