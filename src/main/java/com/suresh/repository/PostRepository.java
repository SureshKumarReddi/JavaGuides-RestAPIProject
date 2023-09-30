package com.suresh.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suresh.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}