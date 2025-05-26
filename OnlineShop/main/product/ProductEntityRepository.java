package com.example.demo.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductEntityRepository extends JpaRepository<ProductEntity, Integer> {
    List<ProductEntity> findByCategoryName(String categoryName);
}
