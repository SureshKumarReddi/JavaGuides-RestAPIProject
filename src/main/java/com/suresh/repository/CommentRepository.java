package com.suresh.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suresh.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByPostId(long psotId);
}
