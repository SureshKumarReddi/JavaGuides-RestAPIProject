package com.suresh.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.suresh.dto.CommentDTO;
import com.suresh.entity.Comment;
import com.suresh.entity.Post;
import com.suresh.exception.BlogAPIException;
import com.suresh.exception.ResourceNotFoundException;
import com.suresh.repository.CommentRepository;
import com.suresh.repository.PostRepository;
import com.suresh.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	private PostRepository postRepository;
	private CommentRepository commentRepository;
	private ModelMapper modelMapper;

	CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository, ModelMapper modelMapper) {
		this.postRepository = postRepository;
		this.commentRepository = commentRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public CommentDTO createComment(long postId, CommentDTO commentdto) {

		// fetch the post for the given postid

		Post post = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("POST", "id", postId));

		// build the comment entity data
		Comment comment = mapDTOToEntity(commentdto);
		// set the post foreign key in comments table
		comment.setPost(post);
		// save the comment entity.
		Comment save = commentRepository.save(comment);
		// convert the entity to DTO
		CommentDTO convertedDTO = mapEntityToDTO(save);
		return convertedDTO;
	}

	@Override
	public List<CommentDTO> getCommentsByPostID(long postId) {

		List<Comment> listOfComments = commentRepository.findByPostId(postId);

		return listOfComments.stream().map(c -> mapEntityToDTO(c)).collect(Collectors.toList());
	}

	@Override
	public CommentDTO getCommentById(long postId, long commentId) {

		// fetch the post entity by id
		Post postEntity = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("POST", "postid", postId));

		// fetch the comment entity by id
		Comment commentEntity = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
		// comapre both ids
		if (!postEntity.getId().equals(commentEntity.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");

		}

		return mapEntityToDTO(commentEntity);
	}

	@Override
	public CommentDTO updateCommentById(long postId, long commentId, CommentDTO commentdto) {
		Post postEntity = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("POST", "postid", postId));
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
		if (!postEntity.getId().equals(comment.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");

		}
		comment.setBody(commentdto.getBody());
		comment.setEmail(commentdto.getEmail());
		comment.setName(commentdto.getName());
		Comment save = commentRepository.save(comment);
		return mapEntityToDTO(save);
	}

	@Override
	public void deleteCommentById(long postId, long commentId) {
		Post postEntity = postRepository.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("POST", "postid", postId));
		Comment commentEntity = commentRepository.findById(commentId)
				.orElseThrow(() -> new ResourceNotFoundException("Comment", "commentId", commentId));
		if (!postEntity.getId().equals(commentEntity.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");

		}

		commentRepository.delete(commentEntity);
	}

	private CommentDTO mapEntityToDTO(Comment comment) {
		return modelMapper.map(comment, CommentDTO.class);
		/*
		 * CommentDTO dto = new CommentDTO(); dto.setId(save.getId());
		 * dto.setBody(save.getBody()); dto.setEmail(save.getEmail());
		 * dto.setName(save.getName()); return dto;
		 */
	}

	private Comment mapDTOToEntity(CommentDTO commentdto) {
		return modelMapper.map(commentdto, Comment.class);
		/*
		 * Comment comment = new Comment(); comment.setBody(commentdto.getBody());
		 * comment.setEmail(commentdto.getEmail());
		 * comment.setName(commentdto.getName());
		 * 
		 * return comment;
		 */
	}
}
