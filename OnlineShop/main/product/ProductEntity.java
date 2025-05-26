package com.example.demo.product;

import com.example.demo.category.CategoryEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEntity {
    @Id
    private int id;

    private String name;

    private int price;

    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category;
}
