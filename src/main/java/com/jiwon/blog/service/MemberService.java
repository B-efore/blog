package com.jiwon.blog.service;

import com.jiwon.blog.dto.MemberJoinRequest;
import com.jiwon.blog.dto.MemberResponse;
import com.jiwon.blog.entity.Member;
import com.jiwon.blog.repository.MemberRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    public Long join(MemberJoinRequest request) {

        validateDuplicateMember(request);
        validatePasswordConfirm(request);

        String encodedPassword = bCryptPasswordEncoder.encode(request.getPassword());
        Member member = request.toEntity(encodedPassword);
        memberRepository.save(member);
        return member.getMemberId();
    }

    private void validatePasswordConfirm(MemberJoinRequest request) {
        if(!request.getPassword().equals(request.getPasswordConfirm())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

    private void validateDuplicateMember(MemberJoinRequest request) {
        if (memberRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }
    }

    @Transactional(readOnly = true)
    public List<MemberResponse> findAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members.stream()
                .map(MemberResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MemberResponse findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .map(MemberResponse::of)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다"));
    }

    @Transactional
    public void deleteMember(Long memberId) {
        memberRepository.delete(memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다.")));
    }
}