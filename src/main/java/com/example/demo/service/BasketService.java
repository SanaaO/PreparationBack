package com.example.demo.service;

import com.example.demo.entity.Basket;
import com.example.demo.entity.Favorites;
import com.example.demo.repository.BasketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BasketService {

    @Autowired
    BasketRepository basketRepository;

    public Basket saveBasket (Basket basket) {
        return this.basketRepository.save(basket);
    }

    public Basket getWishListById(Long id) throws Exception {
        return this.basketRepository.findById(id).orElseThrow(() -> new Exception("not found "));
    }

    public void deleteBasket (Long id) {
        basketRepository.deleteById(id);
    }
}
