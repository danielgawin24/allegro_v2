package com.example.demo.category;

import com.example.demo.product.ProductDTO;
import com.example.demo.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/category")
public class CategoriesController {
    private final ProductService productService;

    public CategoriesController(ProductService service) {
        this.productService = service;
    }

    @GetMapping("/{categoryName}")
    public String category(ModelMap map, @PathVariable String categoryName) {
        List<ProductDTO> products = productService.findAllByCategoryName(categoryName);
        map.addAttribute("categoryName", categoryName);
        map.addAttribute("products", products);
        return "category";
    }

    /*
    some menu with all categories on main site
    when clicked upon one category, all products with that category are shown.
     */
}
