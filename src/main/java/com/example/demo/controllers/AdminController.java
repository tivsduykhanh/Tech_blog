package com.example.demo.controllers;

import com.example.demo.Services.PostService;
import com.example.demo.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private PostService service;

    @GetMapping("/admin")
    public String showPostList(Model model) {
        List<Post> listPosts = service.listAll();
        model.addAttribute("listPosts", listPosts);

        return "layouts/admin/index";
    }

    @GetMapping("/admin/post/add")
    public String addPost_form(Model model) {
        model.addAttribute("post", new Post());
        return "layouts/admin/addpost";
    }

    @PostMapping("/admin/post/save")
    public String savePost(Post post) {
        service.save(post);

        return "redirect:/admin";
    }


}
