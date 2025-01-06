package com.jiwon.blog.dto;

import com.jiwon.blog.entity.Member;
import com.jiwon.blog.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberJoinRequest {

    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    private String passwordConfirm;
    @NotBlank
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