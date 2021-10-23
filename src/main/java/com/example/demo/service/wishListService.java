package com.example.demo.service;

import com.example.demo.entity.Favorites;
import com.example.demo.entity.Product;
import com.example.demo.repository.wishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class wishListService {
    @Autowired
    wishListRepository wishListRepository;

    public Favorites newWishList(Favorites wishList) {
        return this.wishListRepository.save(wishList);
    }

    public Favorites updateWishList(Favorites wishList) {
        return this.wishListRepository.save(wishList);
    }

    public Favorites getWishListById(Long id) throws Exception {
        return this.wishListRepository.findById(id).orElseThrow(() -> new Exception("not found "));
    }

    public void deleteWishList (Long id) {
        wishListRepository.deleteById(id);
    }





}
