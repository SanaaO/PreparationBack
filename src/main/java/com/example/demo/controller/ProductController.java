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


    //add new product method
    @PostMapping(value = "/addproduct", consumes = {"multipart/form-data"})
    public ResponseEntity addProduct(@RequestPart Product productF, @RequestPart MultipartFile file) {

        if (productF == null || file == null) {
            return ResponseEntity.badRequest().body("cannot create new product with empty fields !!");
        }

        Product productB = new Product(productF.getName(), productF.getDescription(),
                productF.getSize(), productF.getPrice(), productF.getInstock());

        //fetch for category object based on category's name and assign it to the product
        Category category = this.categoryService.findByName(productF.getCategory().getName());
        productB.setCategory(category);


        //save file (product's image) in resources/uploads folder
        // and save the path to access to it database by setting product.picture with path
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


    // get List of all products from database
    @GetMapping(value = "/getProducts")
    public ResponseEntity getProducts() throws IOException {

        //List of products
        List<Product> products = this.productsService.getAllProducts();
        if (products.isEmpty()) {
            return ResponseEntity.badRequest().body("cannot find any product !!");
        }
        //List of files (image) relating to each product
        List<byte[]> files = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            byte[] img = Files.readAllBytes(Paths.get(products.get(i).getPicture()));
            files.add(img);
        }
        //Response will contain List of products and List of files
        //each file is relative to the product in the same index order
        List<Object> response = new ArrayList<>();
        response.add(products);
        response.add(files);


        return ResponseEntity.ok(response);
    }

    //update selected product (id)
    @PutMapping(value = "/update", consumes = {"multipart/form-data"})
    public ResponseEntity updateProduct(@RequestParam long id, @RequestPart Product productF, @RequestPart MultipartFile file) {

        //fetch for product to update with the corresponding id
        Product productB = this.productsService.getProduct(id);

        if (productB == null) {
            return ResponseEntity.badRequest().body("Can't find any product with this id");
        }

        Category category = categoryService.findByName(productF.getCategory().getName());

        productB.setName(productF.getName());
        productB.setDescription(productF.getDescription());
        productB.setSize(productF.getSize());
        productB.setPrice(productF.getPrice());
        productB.setInstock(productF.getInstock());
        productB.setCategory(category);

        if (file != null) {
            try {
                //delete existing file by getting the path from product.picture
                //save new file (product's image) in resources/uploads folder
                //and save the path to access to it database by setting product.picture with path
                Files.delete(Paths.get(productB.getPicture()));
                byte[] bytes = file.getBytes();
                String name = UUID.randomUUID() + ".png";
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(new File("src/main/resources/uploads/" + name)));
                productB.setPicture("src/main/resources/uploads/" + name);
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return ResponseEntity.ok(this.productsService.updateProduct(productB));
    }


    //delete selected product(id)
    @DeleteMapping(value = "/delete")
    public ResponseEntity delete(@RequestParam Long id) throws IOException {

        //fetch for product to delete with the corresponding id
        Product product = productsService.getProduct(id);
        if (product == null) {
            return ResponseEntity.badRequest().body("Can't find any product with this id");
        }
        //delete existing file (product's image) by getting the path from product.picture
        Files.delete(Paths.get(product.getPicture()));
        productsService.delete(id);
        return ResponseEntity.ok("Product successfully deleted !");
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

}
