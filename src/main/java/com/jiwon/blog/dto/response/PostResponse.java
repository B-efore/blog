package com.jiwon.blog.dto.response;

import com.jiwon.blog.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
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