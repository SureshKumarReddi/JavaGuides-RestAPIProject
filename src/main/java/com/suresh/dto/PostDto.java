package com.suresh.dto;

import java.util.Set;

import com.suresh.entity.Comment;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PostDto {
	private long id;
	// post title should not be null or empty
	// post title should have at least 2 characters
	@NotEmpty
	@Size(min = 2, message = "Post title should have atleast 2 characters")
	private String title;
	// post description should not be null or empty
	// post description should have at least 10 characters
	private String description;
	@NotEmpty
	private String content;
	private Set<Comment> comment;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Set<Comment> getComment() {
		return comment;
	}

	public void setComment(Set<Comment> comment) {
		this.comment = comment;
	}

}
