package com.example.demo.repositories;

import com.example.demo.models.Category;
import com.example.demo.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository <Category, Integer> {
    public Long countById(Integer id);
}
