package com.jiwon.blog.service;

import com.jiwon.blog.dto.request.CategoryRequest;
import com.jiwon.blog.dto.response.CategoryResponse;
import com.jiwon.blog.entity.Category;
import com.jiwon.blog.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public void createCategory(CategoryRequest request) {
        Category category = request.toEntity();
        categoryRepository.save(category);
    }

    @Transactional(readOnly = true)
    public List<CategoryResponse> findCategories() {
        return categoryRepository.findAll().stream()
                .map(CategoryResponse::of)
                .collect(Collectors.toList());
    }
}
