package com.example.demo.repositories;

import com.example.demo.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Integer> {
    public Long countById(Integer id);
}
