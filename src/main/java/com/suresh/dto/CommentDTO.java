package com.suresh.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CommentDTO {
	private Long id;
	@NotEmpty(message = "Name should not be null or empty")
	private String name;
	@NotEmpty
	@Email(message = "Email should not be null or empty")
	// email field validation.
	private String email;
	@NotEmpty
	@Size(message = "Comment body must be minimum 10 characters")
	private String body;

	public CommentDTO() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

}
