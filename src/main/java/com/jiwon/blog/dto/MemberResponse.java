package com.jiwon.blog.dto;

import com.jiwon.blog.entity.Member;
import com.jiwon.blog.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class MemberResponse {
    private final Long memberId;
    private final String email;
    private final String name;
    @Enumerated(value = EnumType.STRING)
    private final Role role;

    public static MemberResponse of(Member member) {
        return MemberResponse.builder()
                .memberId(member.getMemberId())
                .email(member.getEmail())
                .name(member.getName())
                .role(member.getRole())
                .build();
    }
}
