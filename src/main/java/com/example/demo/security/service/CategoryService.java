package com.example.demo.service;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRespository categoryRepository;

    public Category create (Category category)
    {return categoryRepository.save(category);}


    public  Category findByName (String name)
    {return  categoryRepository.findByName(name);}

    public List<Category> getAll ()
    {return  categoryRepository.findAll();}
}
