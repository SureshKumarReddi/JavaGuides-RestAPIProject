package com.suresh.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.suresh.dto.PostDto;
import com.suresh.response.PostResponse;
import com.suresh.service.PostService;
import com.suresh.utils.AppConstants;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/posts")
public class PostController {

	private PostService service;

	public PostController(PostService postService) {
		this.service = postService;

	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto dto) {
		PostDto createPost = service.createPost(dto);
		return new ResponseEntity<>(createPost, HttpStatus.CREATED);
	}

	@GetMapping()
	public PostResponse findAll(
			@RequestParam(name = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(name = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIR, required = false) String sortDir) {
		return service.getAllPosts(pageNo, pageSize, sortBy, sortDir);
	}

	// get post by id
	@GetMapping("/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable("id") Long id) {

		// return ResponseEntity.ok(service.getPostById(id));
		return ResponseEntity.ok().body(service.getPostById(id));

	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto dto, @PathVariable("id") Long id) {
		PostDto updatePost = service.updatePost(dto, id);

		return new ResponseEntity<>(updatePost, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deletePost(@PathVariable long id) {
		service.deletepostById(id);
		return new ResponseEntity<>("post entity deleted successfully..", HttpStatus.OK);
	}
}
