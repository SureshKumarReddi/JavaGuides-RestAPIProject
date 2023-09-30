package com.suresh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.suresh.entity.Comment;
import com.suresh.entity.Post;
import com.suresh.repository.CommentRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class SpringBootRestApiBlogApplication {
	@Autowired
	CommentRepository repository;
	// @Autowired
	// PostRepository prepositPostRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApiBlogApplication.class, args);
	}

	@PostConstruct
	public void init() {
		Post p1 = new Post();
		p1.setTitle("SBMS");
		p1.setDescription("SpringBoot Microservices");
		p1.setContent("SpringBooot");

		Post p2 = new Post();
		p2.setTitle("Hibernate");
		p2.setDescription("SSpringBoot Hibernate");
		p2.setContent("SpringBooot");

		Comment c1 = new Comment();

		c1.setName("suresh");
		c1.setEmail("suresh.y@hcl.com");
		c1.setBody("This course is awesome...");
		c1.setPost(p1);

		Comment c2 = new Comment();

		c2.setName("naresh");
		c2.setEmail("naresh.y@hcl.com");
		c2.setBody("This course is Great...");
		c2.setPost(p1);

		Comment c3 = new Comment();

		c3.setName("suresh");
		c3.setEmail("h.y@hcl.com");
		c3.setBody("This course is awesome...");
		c3.setPost(p2);

		Comment c4 = new Comment();

		c4.setName("naresh");
		c4.setEmail("d.y@hcl.com");
		c4.setBody("This course is Great...");
		c4.setPost(p2);

		// prepositPostRepository.save(p1); without this it is giving errors
		// prepositPostRepository.save(p2);

		repository.save(c1);
		repository.save(c2);
		repository.save(c3);
		repository.save(c4);
	}

}
