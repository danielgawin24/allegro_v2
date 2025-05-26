package com.example.demo.product;

import com.example.demo.exceptions.ProductNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductEntityRepository productRepository;
    private final ModelMapper modelMapper;

    public ProductService(ProductEntityRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    public List<ProductDTO> findAllByCategoryName(String category) {
        return productRepository.findByCategoryName(category).stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductDTO.class))
                .toList();
    }

    public List<ProductDTO> findAllByPhrase(String phrase) {
        return findAll().stream()
                .filter(productEntity -> productEntity.getName().toLowerCase().contains(phrase.toLowerCase()) ||
                        productEntity.getDescription().toLowerCase().contains(phrase.toLowerCase()))
                .map(productEntity -> modelMapper.map(productEntity, ProductDTO.class))
                .toList();

//        JdbcClient1 jdbcClient = new JdbcClient1();
//        return jdbcClient.getObjects(
//                "SELECT p.id, p.name, p.price, p.description, p.category_id AS categoryId " +
//                        "FROM product p WHERE p.name LIKE '%" + phrase + "%'" + " OR p.description LIKE '%" + phrase + "%'",
//                Product.class
//        );

//        return repository.findByNameLikeAndDescriptionLike(phrase, phrase).stream()
//                .map(productEntity -> modelMapper.map(productEntity, Product.class))
//                .toList();
    }

    public ProductDTO findById(int id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found: id=" + id));
        return new ProductDTO(productEntity.getId(), productEntity.getName(), productEntity.getPrice(), productEntity.getDescription(), productEntity.getCategory().getId());
//        return productEntity.map(entity -> new Product(
//                productEntity.get().getId(),
//                productEntity.get().getName(),
//                productEntity.get().getPrice(),
//                productEntity.get().getDescription(),
//                productEntity.get().getCategory().getId()
//        )).orElseThrow(() -> new UserNotFoundException("User not found: id=" + id));
    }

    public List<ProductDTO> findAll() {
        return productRepository.findAll().stream()
                .map(productEntity -> modelMapper.map(productEntity, ProductDTO.class))
                .toList();
    }
}
