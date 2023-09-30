package com.suresh.service;

import com.suresh.dto.PostDto;
import com.suresh.response.PostResponse;

public interface PostService {

	PostDto createPost(PostDto postdto);

	PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

	PostDto getPostById(Long id);

	PostDto updatePost(PostDto postDto, Long id);

	void deletepostById(long id);

}