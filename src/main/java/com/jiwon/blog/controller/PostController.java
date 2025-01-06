package com.jiwon.blog.controller;

import com.jiwon.blog.dto.request.PostRequest;
import com.jiwon.blog.dto.response.PostDetailResponse;
import com.jiwon.blog.dto.response.PostSummaryResponse;
import com.jiwon.blog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public ResponseEntity<?> createPost(@RequestBody PostRequest request) {
        try {
            PostDetailResponse response = postService.createPost(request);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostSummaryResponse>> getAllPosts(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size
    ) {
        Page<PostSummaryResponse> postsPaged = postService.findPostsPaged(page, size);
        return new ResponseEntity<>(postsPaged.getContent(), HttpStatus.OK);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<?> getPost(@PathVariable(name = "id") Long id) {
        try {
            PostDetailResponse response = postService.findPost(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}