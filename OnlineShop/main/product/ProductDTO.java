package com.example.demo.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private int id;
    private String name;
    private int price;
    private String description;
    private int categoryId;
}
