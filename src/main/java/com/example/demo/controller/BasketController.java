package com.example.demo.controller;

import com.example.demo.entity.Basket;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.service.BasketService;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/basket")
public class BasketController {

    @Autowired
    BasketService basketService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;


    @PostMapping(value = "/addBasket")
    public ResponseEntity<?> addProductToWishList(@RequestBody Product product, @RequestParam Long id) throws Exception {

        Basket basket;

        List<Product> products = new ArrayList<>();
        List<Basket> baskets = new ArrayList<>();

        User user = this.userService.findById(id);

        //if user has no basket we have to create a new one
        if (user.getBasket() == null) {

            products.add(product);
            Basket newBasket = new Basket(products);

            //create new basket with List of product
            basket = this.basketService.saveBasket(newBasket);

            //associate product to basket
            baskets.add(basket);
            product.setBaskets(baskets);
            this.productService.updateProduct(product);

            //associate basket to user
            user.setBasket(basket);
            this.userService.updateUser(user);

        } else {
            //if user has a basket add the new selected product to it
            basket = user.getBasket();

            products = basket.getProducts();
            products.add(product);
            basket.setProducts(products);
            this.basketService.saveBasket(basket);

            baskets = product.getBaskets();
            baskets.add(basket);
            product.setBaskets(baskets);
            this.productService.updateProduct(product);
        }
        return ResponseEntity.ok("product successfully added to basket !");
    }

    @DeleteMapping(value = "/remove")
    public ResponseEntity<?> deleteProductFromWishList(@RequestParam Long productID, @RequestParam Long userID) throws Exception {

        List<Product> products;
        List<Basket> baskets;

        User user = this.userService.findById(userID);
        Product product = this.productService.getProduct(productID);

        Basket basket = user.getBasket();
        products = basket.getProducts();
        products.remove(product);
        basket.setProducts(products);
        this.basketService.saveBasket(basket);

        baskets = product.getBaskets();
        baskets.remove(basket);
        product.setBaskets(baskets);
        this.productService.updateProduct(product);

        return ResponseEntity.ok("product successfully removed from user's basket !");
    }


}
