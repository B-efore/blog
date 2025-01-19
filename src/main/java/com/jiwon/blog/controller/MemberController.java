package com.jiwon.blog.controller;

import com.jiwon.blog.dto.request.MemberJoinRequest;
import com.jiwon.blog.dto.response.MemberResponse;
import com.jiwon.blog.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/members")
@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @Operation(summary = "회원 등록")
    @PostMapping("")
    public ResponseEntity<String> register(@RequestBody MemberJoinRequest request) {
        try {
            memberService.join(request);
            return new ResponseEntity<>("회원가입 성공", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "전체 유저 정보 조회")
    @GetMapping("")
    public ResponseEntity<List<MemberResponse>> getAllMembers() {
        List<MemberResponse> members = memberService.findAllMembers();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @Operation(summary = "단일 유저 정보 조회")
    @GetMapping("/{memberId}")
    public ResponseEntity<?> getMember(@PathVariable("memberId") Long memberId) {
        try {
            MemberResponse memberResponse = memberService.findMember(memberId);
            return new ResponseEntity<>(memberResponse, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "유저 정보 삭제")
    @DeleteMapping("/{memberId}")
    public ResponseEntity<?> deleteMember(@PathVariable("memberId") Long memberId) {
        try {
            memberService.deleteMember(memberId);
            return new ResponseEntity<>("회원 삭제 완료", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}