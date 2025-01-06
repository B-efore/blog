package com.jiwon.blog.dto.response;

import com.jiwon.blog.entity.Category;
import com.jiwon.blog.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class CategoryResponse {
    private final String name;
    private final List<Post> posts;

    public static CategoryResponse of(Category category) {
        return CategoryResponse.builder()
                .name(category.getName())
                .posts(category.getPosts())
                .build();
    }
}
