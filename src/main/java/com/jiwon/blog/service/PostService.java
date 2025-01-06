package com.jiwon.blog.service;

import com.jiwon.blog.dto.PostRequest;
import com.jiwon.blog.dto.PostResponse;
import com.jiwon.blog.entity.Category;
import com.jiwon.blog.entity.Member;
import com.jiwon.blog.entity.Post;
import com.jiwon.blog.repository.CategoryRepository;
import com.jiwon.blog.repository.MemberRepository;
import com.jiwon.blog.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final MemberRepository memberRepository;
    private final CategoryRepository categoryRepository;
    private final PostRepository postRepository;

    public PostService(MemberRepository memberRepository, CategoryRepository categoryRepository, PostRepository postRepository) {
        this.memberRepository = memberRepository;
        this.categoryRepository = categoryRepository;
        this.postRepository = postRepository;
    }

    public PostResponse createPost(PostRequest request) {
        Member member = findMember(request.getMemberId());
        Category category = findCategory(request.getCategoryId());
        Post post = request.toEntity(member, category);

        postRepository.save(post);
        return PostResponse.of(post);
    }

    private Member findMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다."));
    }

    private Category findCategory(Long categoryId) {
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리입니다."));
    }
}
