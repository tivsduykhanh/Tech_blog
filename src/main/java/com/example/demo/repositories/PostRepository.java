package com.example.demo.repositories;

import com.example.demo.models.Category;
import com.example.demo.models.Post;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface PostRepository extends CrudRepository<Post, Integer> {
    public Long countById(Integer id);

    public List<Post> findByCategoryId(Integer category_id);

}
