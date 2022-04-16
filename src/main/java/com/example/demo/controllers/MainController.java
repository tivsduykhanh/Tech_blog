package com.example.demo.controllers;

import com.example.demo.Services.PostService;
import com.example.demo.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    private PostService service;

    @GetMapping("")
    public String showPostList(Model model) {
        List<Post> listPosts = service.listAll();
        model.addAttribute("listPosts", listPosts);

        return "layouts/index";
    }
}
