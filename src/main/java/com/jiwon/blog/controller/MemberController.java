package com.jiwon.blog.controller;

import com.jiwon.blog.dto.MemberJoinRequest;
import com.jiwon.blog.dto.MemberResponse;
import com.jiwon.blog.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/members")
    public ResponseEntity<String> register(@RequestBody MemberJoinRequest request) {
        try {
            memberService.join(request);
            return new ResponseEntity<>("회원가입 성공", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/members")
    public ResponseEntity<List<MemberResponse>> getAllMembers() {
        List<MemberResponse> members = memberService.findAllMembers();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

    @GetMapping("/members/{id}")
    public ResponseEntity<MemberResponse> getMember(@PathVariable(name = "id") Long id) {
        try {
            MemberResponse memberResponse = memberService.findMember(id);
            return new ResponseEntity<>(memberResponse, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/members/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable(name = "id") Long id) {
        try {
            memberService.deleteMember(id);
            return new ResponseEntity<>("회원 삭제 완료", HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}