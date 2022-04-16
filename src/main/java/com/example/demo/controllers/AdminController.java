package com.example.demo.controllers;

import com.example.demo.Services.PostNotFoundException;
import com.example.demo.Services.PostService;
import com.example.demo.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        model.addAttribute("pageTitle", "Add New Post");
        return "layouts/admin/post_form";
    }

    @PostMapping("/admin/post/save")
    public String savePost(Post post, RedirectAttributes ra) {
        service.save(post);
        ra.addFlashAttribute("message","The user has been saved successfully.");
        return "redirect:/admin";
    }

    @GetMapping("/admin/post/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Post post = service.get(id);
            model.addAttribute("post", post);
            model.addAttribute("pageTitle", "Edit User (ID: " + id + ")");
            return "layouts/admin/post_form";
        } catch (PostNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/admin";
        }
    }

    @GetMapping("/admin/post/delete/{id}")
    public String deletePost(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The post ID " + id + " has been deleted");
        } catch (PostNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/admin";
    }

}
