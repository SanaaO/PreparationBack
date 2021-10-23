package com.example.demo.controller;


import com.example.demo.entity.Favorites;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import com.example.demo.service.ProductService;
import com.example.demo.service.UserService;
import com.example.demo.service.wishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api/wishList")
public class wishListController {

    @Autowired
    wishListService wishListService;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;


    @PostMapping(value = "/addWishList")
    public ResponseEntity <?> addProductToWishList(@RequestBody Product product, @RequestParam Long id) throws Exception {

        Favorites wishList;

        List<Product> products = new ArrayList<>();
        List<Favorites> favorites = new ArrayList<>();

        User user = this.userService.findById(id);

        //if user has no wishList we have to create a new one
        if (user.getWishList() == null) {

            products.add(product);
            Favorites favorite = new Favorites(products);

            //create new wish List with List of product
            wishList = this.wishListService.newWishList(favorite);

            //associate product to wishList
            favorites.add(wishList);
            product.setWishLists(favorites);
            this.productService.updateProduct(product);

            //associate wishList to user
            user.setWishList(wishList);
            this.userService.updateUser(user);

        } else {
            //if user has a wishList add the new selected product to it
            wishList = user.getWishList();

            products = wishList.getProducts();
            products.add(product);
            wishList.setProducts(products);
            this.wishListService.updateWishList(wishList);
            System.out.println(wishList);

            favorites = product.getWishLists();
            favorites.add(wishList);
            product.setWishLists(favorites);
            this.productService.updateProduct(product);
        }
        return ResponseEntity.ok("product successfully added to wishlist !");
    }

    @DeleteMapping(value = "/remove")
    public ResponseEntity <?> deleteProductFromWishList(@RequestParam Long productID, @RequestParam Long userID) throws Exception {

        List<Product> products ;
        List<Favorites> favorites ;

        User user = this.userService.findById(userID);
        Product product = this.productService.getProduct(productID);

        Favorites wishList = user.getWishList();
        products = wishList.getProducts();
        products.remove(product);
        wishList.setProducts(products);
        this.wishListService.updateWishList(wishList) ;

        favorites = product.getWishLists();
        favorites.remove(wishList);
        product.setWishLists(favorites);
        this.productService.updateProduct(product);

        return ResponseEntity.ok(user.getWishList().getProducts());
    }


}
