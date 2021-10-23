package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "wishlist")
public class Favorites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFav;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "wishLists")
    private List<Product> products = new ArrayList<>();

    public Favorites() {
    }

    public Favorites(List<Product> products) {
        this.products = products;
    }

    public Long getIdFav() {
        return idFav;
    }

    public void setIdFav(Long idFav) {
        this.idFav = idFav;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Favorites{" +
                "idFav=" + idFav +
                ", products=" + products +
                '}';
    }
}
