package com.example.demo;

import com.example.demo.models.Post;
import com.example.demo.repositories.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class PostRepositoryTest {
    @Autowired private PostRepository repo;

    @Test
    public void testAddNew() {
        Post post = new Post();
        post.setTitle("Ghê vậy sao");
        post.setContent("alo alo");
        post.setImage("shiba shiba");
        post.setUser_id(1);

        Post savePost = repo.save(post);
        Assertions.assertThat(savePost).isNotNull();
        Assertions.assertThat(savePost.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll() {
        Iterable<Post> posts = repo.findAll();
        Assertions.assertThat(posts).hasSizeGreaterThan(0);

        for (Post post : posts) {
            System.out.println(post);
        }
    }

    @Test
    public void testUpdate() {
        Integer postId = 1;
        Optional<Post> optionalPost = repo.findById(postId);
        Post post = optionalPost.get();
        post.setTitle("abcdf");
        repo.save(post);

        Post updatePost = repo.findById(postId).get();
        Assertions.assertThat(updatePost.getTitle()).isEqualTo("abcdf");
    }

    @Test
    public void testGet() {
        Integer postId = 2;
        Optional<Post> optionalPost = repo.findById(postId);
        Assertions.assertThat(optionalPost).isPresent();
        System.out.println(optionalPost.get());
    }

    @Test
    public void tesDelete() {
        Integer postId = 2;
        repo.deleteById(postId);

        Optional<Post> optionalPost = repo.findById(postId);
        Assertions.assertThat(optionalPost).isNotPresent();
    }
}
