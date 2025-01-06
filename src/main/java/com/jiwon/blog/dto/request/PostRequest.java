package com.jiwon.blog.dto.request;

import com.jiwon.blog.entity.Category;
import com.jiwon.blog.entity.Member;
import com.jiwon.blog.entity.Post;
import lombok.Getter;

@Getter
public class PostRequest {

    private Long memberId;
    private Long categoryId;
    private String title;
    private String content;

    public Post toEntity(Member member, Category category) {
        return Post.builder()
                .member(member)
                .category(category)
                .title(title)
                .content(content)
                .build();
    }
}