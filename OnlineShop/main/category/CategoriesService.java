package com.example.demo.category;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesService {
    private final CategoryEntityRepository categoryRepository;
    private final ModelMapper modelMapper;

    public CategoriesService(CategoryEntityRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    public List<CategoryDTO> getCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryEntity -> modelMapper.map(categoryEntity, CategoryDTO.class))
                .toList();
    }
}
