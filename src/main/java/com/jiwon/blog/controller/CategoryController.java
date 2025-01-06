package com.jiwon.blog.controller;

import com.jiwon.blog.dto.CategoryRequest;
import com.jiwon.blog.dto.CategoryResponse;
import com.jiwon.blog.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest) {
        CategoryResponse response = categoryService.createCategory(categoryRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
