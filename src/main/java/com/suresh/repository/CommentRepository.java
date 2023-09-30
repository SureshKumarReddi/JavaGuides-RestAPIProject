package com.suresh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suresh.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
