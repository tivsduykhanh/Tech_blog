package com.example.demo.Services;

import com.example.demo.models.Post;
import com.example.demo.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired private PostRepository repo;

    public List<Post> listAll() {
        return (List<Post>) repo.findAll();
    }

    public void save(Post post) {
        repo.save(post);
    }

//    public Post get(Integer id){
//        repo.findById(id);
//    }
}
