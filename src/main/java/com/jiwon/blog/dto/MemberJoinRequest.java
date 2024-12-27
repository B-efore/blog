package com.jiwon.blog.dto;

import com.jiwon.blog.entity.Member;
import com.jiwon.blog.entity.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberJoinRequest {
    private String email;
    private String password;
    private String name;

    public Member toEntity(String encodedPassword) {
        return Member.builder()
                .email(email)
                .password(encodedPassword)
                .name(name)
                .role(Role.USER)
                .build();
    }
}