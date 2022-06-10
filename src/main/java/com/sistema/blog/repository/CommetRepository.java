package com.sistema.blog.repository;

import com.sistema.blog.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommetRepository extends JpaRepository<Comment, Long> {
    @Query
    public List<Comment> findByPostId(long postId);
}
