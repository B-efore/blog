package com.jiwon.blog.dto.response;

import com.jiwon.blog.entity.Category;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryResponse {
    private final String name;
//    private final List<PostSummaryResponse> posts;

    public static CategoryResponse of(Category category) {
        return CategoryResponse.builder()
                .name(category.getName())
//                .posts(category.getPosts().stream()
//                        .map(PostSummaryResponse::of)
//                        .collect(Collectors.toList()))
                .build();
    }
}
