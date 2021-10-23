package com.example.demo.repository;

import com.example.demo.entity.Favorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface wishListRepository extends JpaRepository<Favorites, Long> {


}
