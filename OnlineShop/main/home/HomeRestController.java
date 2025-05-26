package com.example.demo.home;

import com.example.demo.category.CategoriesService;
import com.example.demo.category.CategoryDTO;
import com.example.demo.product.ProductDTO;
import com.example.demo.product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeRestController {
    private final CategoriesService categoriesService;
    private final ProductService productService;

    public HomeRestController(CategoriesService categoriesService, ProductService productService) {
        this.categoriesService = categoriesService;
        this.productService = productService;
    }

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getCategories() {
        return new ResponseEntity<>(categoriesService.getCategories(), HttpStatus.OK);
    }

    @GetMapping("/searchForm")
    public ResponseEntity<SearchForm> getSearchForm() {
        return new ResponseEntity<>(new SearchForm(), HttpStatus.OK);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }
}
