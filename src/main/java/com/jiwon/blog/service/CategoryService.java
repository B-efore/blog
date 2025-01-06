package com.jiwon.blog.service;

import com.jiwon.blog.dto.CategoryRequest;
import com.jiwon.blog.dto.CategoryResponse;
import com.jiwon.blog.entity.Category;
import com.jiwon.blog.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public CategoryResponse createCategory(CategoryRequest request) {
        Category category = request.toEntity();
        categoryRepository.save(category);
        return CategoryResponse.of(category);
    }
}
