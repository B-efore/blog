package com.jiwon.blog.controller;

import com.jiwon.blog.dto.MemberJoinRequest;
import com.jiwon.blog.service.JoinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService) {
        this.joinService = joinService;
    }

    @PostMapping("/join")
    public ResponseEntity<?> register(@RequestBody MemberJoinRequest request) {
        joinService.join(request);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);
    }
}