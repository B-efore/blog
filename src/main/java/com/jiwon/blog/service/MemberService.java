package com.jiwon.blog.service;

import com.jiwon.blog.dto.MemberJoinRequest;
import com.jiwon.blog.entity.Member;
import com.jiwon.blog.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Member join(MemberJoinRequest request) {
        // TODO: 중복 검사 처리
        if (memberRepository.existsByEmail(request.getEmail())) {

        };

        String encodedPassword = bCryptPasswordEncoder.encode(request.getPassword());
        Member member = request.toEntity(encodedPassword);
        return memberRepository.save(member);
    }
}
