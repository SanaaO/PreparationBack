package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Categid;
    private String name;
    @JsonIgnore
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @OneToMany( mappedBy = "category")

    private List<Product> products  = new ArrayList<>();

    public Category() {
    }

    public Category(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }

    public Long getCategid() {
        return Categid;
    }

    public void setCategid(Long categid) {
        Categid = categid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Category{" +
                "Categid=" + Categid +
                ", name='" + name + '\'' +
                '}';
    }
}
