package com.jiwon.blog.dto;

import com.jiwon.blog.entity.Category;
import lombok.Getter;

@Getter
public class CategoryRequest {

    private String name;

    public Category toEntity() {
        return Category.builder()
                .name(name)
                .build();
    }
}
