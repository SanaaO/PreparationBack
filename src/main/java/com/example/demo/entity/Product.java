package com.example.demo.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long prodid;
    private String name;
    private String description;
    private String size;
    private double price;
    private int instock ;
    //@Column( length = 1000)
    private String picture;
    //many products could belong to one category and one category have many products
    @ManyToOne
    private Category category;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "WishList_Product",
            joinColumns = { @JoinColumn(name = "prodid") },
            inverseJoinColumns = { @JoinColumn(name = "idFav") })
    private List<Favorites> wishLists = new ArrayList<>();

    public Product() {
    }

    public Product(String name, String description, String size, double price, int instock) {
        this.name = name;
        this.description = description;
        this.size = size;
        this.price = price;
        this.instock = instock;
    }

    public Long getProdid() {
        return prodid;
    }

    public void setProdid(Long prodid) {
        this.prodid = prodid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInstock() {
        return instock;
    }

    public void setInstock(int instock) {
        this.instock = instock;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Favorites> getWishLists() {
        return wishLists;
    }

    public void setWishLists(List<Favorites> wishLists) {
        this.wishLists = wishLists;
    }

    @Override
    public String toString() {
        return "Product{" +
                "prodid=" + prodid +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", size='" + size + '\'' +
                ", price=" + price +
                ", instock=" + instock +
                ", picture='" + picture + '\'' +
                ", category=" + category +
                '}';
    }
}
