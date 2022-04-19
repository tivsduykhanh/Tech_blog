package com.example.demo.Services;

import com.example.demo.models.Post;
import com.example.demo.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired private PostRepository repo;

    public List<Post> listAll() {
        return (List<Post>) repo.findAll();
    }

    public Post save(Post post) {
        repo.save(post);
        return post;
    }

    public Post get(Integer id) throws PostNotFoundException {
        Optional<Post> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new PostNotFoundException("Could not find any posts with ID " + id);
    }

    public void delete(Integer id) throws PostNotFoundException {
        Long count = repo.countById(id);
        if (count == null || count ==0) {
            throw new PostNotFoundException("Could not find any posts with ID " + id);
        }
        repo.deleteById(id);
    }


}
