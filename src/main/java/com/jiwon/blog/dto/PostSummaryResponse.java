package com.jiwon.blog.dto;

import com.jiwon.blog.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostSummaryResponse {
    private final Long postId;
    private final String categoryName;
    private final String title;
    private final LocalDateTime createDate;
    private final LocalDateTime modifyDate;

    public static PostSummaryResponse of(Post post) {
        return PostSummaryResponse.builder()
                .postId(post.getPostId())
                .categoryName(post.getCategory().getName())
                .title(post.getTitle())
                .createDate(post.getCreateDate())
                .modifyDate(post.getModifyDate())
                .build();
    }
}
