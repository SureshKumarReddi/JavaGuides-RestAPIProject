package com.suresh.service;

import java.util.List;

import com.suresh.dto.CommentDTO;

public interface CommentService {

	CommentDTO createComment(long postid, CommentDTO commentdto);

	List<CommentDTO> getCommentsByPostID(long postId);

	CommentDTO getCommentById(long postId, long commentId);

	CommentDTO updateCommentById(long postId, long commentId, CommentDTO commentdto);

	void deleteCommentById(long postId, long commentId);
}