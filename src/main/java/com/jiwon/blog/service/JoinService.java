package com.jiwon.blog.service;

import com.jiwon.blog.dto.MemberJoinRequest;
import com.jiwon.blog.entity.Member;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    private final MemberService memberService;

    public JoinService(MemberService memberService) {
        this.memberService = memberService;
    }

    public Member join(MemberJoinRequest request) {
        return memberService.join(request);
    }
}