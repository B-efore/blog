package com.jiwon.blog.repository;

import com.jiwon.blog.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByOrderByCreateDateDesc(Pageable pageable);
    Page<Post> findByCategory_CategoryIdOrderByCreateDateDesc(Long categoryId, Pageable pageable);
}
