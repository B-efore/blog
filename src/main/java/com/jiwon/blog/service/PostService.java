package com.jiwon.blog.service;

import com.jiwon.blog.dto.request.PostRequest;
import com.jiwon.blog.dto.response.PostDetailResponse;
import com.jiwon.blog.dto.response.PostSummaryResponse;
import com.jiwon.blog.entity.Category;
import com.jiwon.blog.entity.Member;
import com.jiwon.blog.entity.Post;
import com.jiwon.blog.repository.CategoryRepository;
import com.jiwon.blog.repository.MemberRepository;
import com.jiwon.blog.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
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

    @Transactional
    public PostDetailResponse createPost(PostRequest request) {
        Member member = findMember(request.getMemberId());
        Category category = findCategory(request.getCategoryId());
        Post post = request.toEntity(member, category);

        postRepository.save(post);
        return PostDetailResponse.of(post);
    }

    @Transactional(readOnly = true)
    public PostDetailResponse findPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));
        return PostDetailResponse.of(post);
    }

    @Transactional(readOnly = true)
    public Page<PostSummaryResponse> findPostsPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> postPage = postRepository.findAllByOrderByCreateDateDesc(pageable);
        return postPage.map(PostSummaryResponse::of);
    }

    @Transactional
    public Page<PostSummaryResponse> findPostsPagedByCategory(Long categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> postPage = postRepository.findByCategory_CategoryIdOrderByCreateDateDesc(categoryId, pageable);
        return postPage.map(PostSummaryResponse::of);
    }

    @Transactional
    public void deletePost(Long postId) {
        postRepository.delete(postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회원입니다.")));
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
