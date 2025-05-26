package com.example.demo.home;

import com.example.demo.category.CategoriesService;
import com.example.demo.category.CategoryDTO;
import com.example.demo.product.ProductDTO;
import com.example.demo.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final ProductService productService;
    private final CategoriesService categoriesService;

    public HomeController(ProductService productService, CategoriesService categoriesService) {
        this.productService = productService;
        this.categoriesService = categoriesService;
    }

    @GetMapping
    public String home(ModelMap map) {
        SearchForm searchForm = new SearchForm();
        List<CategoryDTO> categories = categoriesService.getCategories();
        map.addAttribute("searchForm", searchForm);
        map.addAttribute("categories", categories);
        return "home";
    }

    @PostMapping("/search")
    public String search(ModelMap map, @ModelAttribute SearchForm searchForm) {
//        /*! wrong list*/List<Product> allProducts = service.findAll();
        List<ProductDTO> searchedProducts = productService.findAllByPhrase(searchForm.getPhrase());
        map.addAttribute("products", searchedProducts);
        return "searchResults";
    }
}
