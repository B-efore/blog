package com.jiwon.blog.dto;

import com.jiwon.blog.entity.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class PostResponse {
    private final Long postId;
    private final String memberName;
    private final String categoryName;
    private final String title;
    private final String content;
    private final LocalDateTime createDate;
    private final LocalDateTime modifyDate;

    public static PostResponse of(Post post) {
        return PostResponse.builder()
                .postId(post.getPostId())
                .memberName(post.getMember().getName())
                .categoryName(post.getCategory().getName())
                .title(post.getTitle())
                .content(post.getContent())
                .createDate(post.getCreateDate())
                .modifyDate(post.getModifyDate())
                .build();
    }
}