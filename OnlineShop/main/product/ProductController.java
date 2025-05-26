package com.example.demo.product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {
    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping("/{productId}")
    public String getProduct(ModelMap map, @PathVariable int productId) {
        ProductDTO product = service.findById(productId);
        map.addAttribute("product", product);
        return "product";
    }
}
