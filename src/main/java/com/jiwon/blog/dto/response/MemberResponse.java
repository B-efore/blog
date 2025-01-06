package com.jiwon.blog.dto.response;

import com.jiwon.blog.entity.Member;
import com.jiwon.blog.entity.Role;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class MemberResponse {
    private final Long memberId;
    private final String email;
    private final String name;
    private final Role role;
    private final List<PostSummaryResponse> posts;

    public static MemberResponse of(Member member) {
        return MemberResponse.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .name(member.getName())
                .role(member.getRole())
                .posts(member.getPosts().stream()
                        .map(PostSummaryResponse::of)
                        .collect(Collectors.toList()))
                .build();
    }
}
