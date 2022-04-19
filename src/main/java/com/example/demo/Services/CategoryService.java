package com.example.demo.Services;

import com.example.demo.models.Category;
import com.example.demo.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repo;

    public List<Category> listAll() {
        return (List<Category>) repo.findAll();
    }

    public Category save(Category category) {
        repo.save(category);
        return category;
    }

    public Category get(Integer id) throws PostNotFoundException {
        Optional<Category> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new PostNotFoundException("Could not find any categories with ID " + id);
    }

    public void delete(Integer id) throws PostNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count ==0) {
            throw new PostNotFoundException("Could not find any categories with ID " + id);
        }
        repo.deleteById(id);
    }
}
