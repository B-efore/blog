package com.jiwon.blog.controller;

import com.jiwon.blog.dto.request.CategoryRequest;
import com.jiwon.blog.dto.response.CategoryResponse;
import com.jiwon.blog.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/categories")
@RestController
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Operation(summary = "카테고리 생성")
    @PostMapping("")
    public ResponseEntity<String> createCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryService.createCategory(categoryRequest);
        return new ResponseEntity<>("카테고리 등록 완료", HttpStatus.OK);
    }
}
