package com.suresh.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.suresh.dto.PostDto;
import com.suresh.entity.Post;
import com.suresh.exception.ResourceNotFoundException;
import com.suresh.repository.PostRepository;
import com.suresh.response.PostResponse;
import com.suresh.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	private PostRepository repository;
	private ModelMapper modelMapper;

	public PostServiceImpl(PostRepository repository, ModelMapper modelMapper) {
		this.repository = repository;
		this.modelMapper = modelMapper;
	}

	@Override
	public PostDto createPost(PostDto postdto) {
		Post post = convertDtoToEntity(postdto);
		Post save = repository.save(post);
		PostDto dto = convertEntityToDto(save);
		return dto;
	}

	@Override
	public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();

		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		System.out.println("pageNo, pageSize " + pageNo + " , " + pageSize);
		Page<Post> findAll = repository.findAll(pageable);
		List<Post> content = findAll.getContent();
		List<PostDto> dtolist = content.stream().map(post -> convertEntityToDto(post)).collect(Collectors.toList());

		PostResponse postResponse = new PostResponse();
		postResponse.setPostDto(dtolist);
		postResponse.setPageNo(findAll.getNumber());
		postResponse.setPageSize(findAll.getSize());
		postResponse.setTotalElements(findAll.getTotalElements());
		postResponse.setTotalPages(findAll.getTotalPages());
		return postResponse;
	}

	@Override
	public PostDto getPostById(Long id) {
		Post post = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

		return convertEntityToDto(post);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Long id) {
		// first fetch the record or check whether that record exists in DB or not.
		Post post = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setDescription(postDto.getDescription());

		Post updatedPost = repository.save(post);

		PostDto dto = convertEntityToDto(updatedPost);

		return dto;
	}

	@Override
	public void deletepostById(long id) {

		// first fetch the record or check whether that record exists in DB or not

		Post post = repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

		repository.deleteById(post.getId());

	}

	private Post convertDtoToEntity(PostDto postdto) {
		Post post = modelMapper.map(postdto, Post.class);
		return post;

		/*
		 * Post post = new Post(); post.setId(postdto.getId());
		 * post.setTitle(postdto.getTitle());
		 * post.setDescription(postdto.getDescription());
		 * post.setContent(postdto.getContent());
		 * 
		 * return post;
		 */
	}

	private PostDto convertEntityToDto(Post save) {

		PostDto postDto = modelMapper.map(save, PostDto.class);
		return postDto;

		/*
		 * PostDto dto = new PostDto(); dto.setId(save.getId());
		 * dto.setTitle(save.getTitle()); dto.setDescription(save.getDescription());
		 * dto.setContent(save.getContent()); dto.setComment(save.getComment()); return
		 * dto;
		 */

	}

}
