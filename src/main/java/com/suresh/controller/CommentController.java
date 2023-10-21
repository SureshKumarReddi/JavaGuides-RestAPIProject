package com.suresh.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.suresh.dto.CommentDTO;
import com.suresh.service.CommentService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CommentController {

	private CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping("posts/{postid}/comments")
	public ResponseEntity<CommentDTO> createPost(@PathVariable("postid") long postId,
			@Valid @RequestBody CommentDTO commentdto) {

		CommentDTO createComment = commentService.createComment(postId, commentdto);
		return new ResponseEntity<>(createComment, HttpStatus.CREATED);
	}

	@GetMapping("posts/{postid}/comments")
	public List<CommentDTO> getCommentsByPostId(@PathVariable("postid") long postId) {

		return commentService.getCommentsByPostID(postId);
	}

	@GetMapping("posts/{postid}/comments/{commentId}")
	public ResponseEntity<CommentDTO> getCommentById(@PathVariable("postid") long postId,
			@PathVariable("commentId") long commentId) {
		return new ResponseEntity<>(commentService.getCommentById(postId, commentId), HttpStatus.OK);
	}

	@PutMapping("posts/{postid}/comments/{commentId}")
	public ResponseEntity<CommentDTO> updateCommentById(@PathVariable("postid") long postId,
			@PathVariable("commentId") long commentId, @RequestBody CommentDTO commentdto) {
		return new ResponseEntity<>(commentService.updateCommentById(postId, commentId, commentdto), HttpStatus.OK);
	}

	@DeleteMapping("posts/{postid}/comments/{commentId}")
	public ResponseEntity<String> deleteCommentById(@PathVariable("postid") long postId,
			@PathVariable("commentId") long commentId) {
		commentService.deleteCommentById(postId, commentId);
		return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
	}
}
