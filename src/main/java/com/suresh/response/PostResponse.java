package com.suresh.response;

import java.util.List;

import com.suresh.dto.PostDto;

public class PostResponse {

	private List<PostDto> postDto;
	private int totalPages;
	private long totalElements;
	private int pageNo;
	private int pageSize;

	public List<PostDto> getPostDto() {
		return postDto;
	}

	public void setPostDto(List<PostDto> postDto) {
		this.postDto = postDto;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public long getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(long totalElements) {
		this.totalElements = totalElements;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
