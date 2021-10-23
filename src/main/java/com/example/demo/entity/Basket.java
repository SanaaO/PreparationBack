package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "basket")
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long basketid;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "baskets")
    private List<Product> products = new ArrayList<>();

    public Basket() {
    }

    public Basket(List<Product> products) {
        this.products = products;
    }

    public Long getBasketid() {
        return basketid;
    }

    public void setBasketid(Long basketid) {
        this.basketid = basketid;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "basketid=" + basketid +
                ", products=" + products +
                '}';
    }
}
