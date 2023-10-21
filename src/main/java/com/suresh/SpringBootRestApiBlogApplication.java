package com.suresh;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.suresh.entity.Post;
import com.suresh.repository.PostRepository;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class SpringBootRestApiBlogApplication {

	@Autowired
	private PostRepository repository;

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestApiBlogApplication.class, args);
	}

	@PostConstruct
	public void init() {
		Post p1 = new Post();
		p1.setId(1L);
		p1.setTitle("SBMS");
		p1.setDescription("SpringBoot Microservices");
		p1.setContent("SpringBooot");

		Post p2 = new Post();
		p2.setId(2L);
		p2.setTitle("Hibernate");
		p2.setDescription("SSpringBoot Hibernate");
		p2.setContent("SpringBooot");

		Post p3 = new Post();
		p3.setId(3L);
		p3.setTitle("SpringDataJPA");
		p3.setDescription("SpringBoot Hibernate");
		p3.setContent("SpringBooot");

		Post p4 = new Post();
		p4.setId(4L);
		p4.setTitle("SpringCore");
		p4.setDescription("SpringBoot ");
		p4.setContent("SpringCore");

		Post p5 = new Post();
		p5.setId(5L);
		p5.setTitle("Core Java");
		p5.setDescription("Java ");
		p5.setContent("Core Java");

		Post p6 = new Post();
		p6.setId(6L);
		p6.setTitle("Advanced Java");
		p6.setDescription("Java ");
		p6.setContent("Advanced Java");

		Post p7 = new Post();
		p7.setId(7L);
		p7.setTitle("JDBC");
		p7.setDescription("Core Java ");
		p7.setContent("Advanced Java");

		Post p8 = new Post();
		p8.setId(8L);
		p8.setTitle("Git");
		p8.setDescription("Git for Beginners ");
		p8.setContent("Mastering Git");

		Post p9 = new Post();
		p9.setId(9L);
		p9.setTitle("Eclipse");
		p9.setDescription("Eclipse for Beginners ");
		p9.setContent("Mastering Eclipse");

		Post p10 = new Post();
		p10.setId(10L);
		p10.setTitle("BitBucket");
		p10.setDescription("BitBucket for Beginners ");
		p10.setContent("Mastering BitBucket");

		List<Post> posts = List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10);

		repository.saveAll(posts);
	}
}
