package com.example.demo.controllers;

import com.example.demo.Services.CategoryService;
import com.example.demo.Services.PostNotFoundException;
import com.example.demo.Services.PostService;
import com.example.demo.models.Category;
import com.example.demo.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class PostController {

    @Autowired
    PostService service;

    @Autowired
    CategoryService service_category;
    @GetMapping("/category/{category_id}")
    public String getPostByCategoryId(@PathVariable("category_id") Integer category_id, Model model) {

        List<Post> listPosts = service.getPostByCategoryId(category_id);
        model.addAttribute("listPosts", listPosts);
        List<Category> listCategories = service_category.listAll();
        model.addAttribute("listCategories", listCategories);
        model.addAttribute("pageTitle", "Category");

        return "/layouts/posts";

//        return service.getPostByCategoryId(category_id);
    }

    @GetMapping("/post/{id}")
    public String showDetailPost(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Post post = service.get(id);
            model.addAttribute("post", post);
            model.addAttribute("pageTitle", "Detail Post (ID: " + id + ")");
            List<Category> listCategories = service_category.listAll();
            model.addAttribute("listCategories", listCategories);
            return "/layouts/post_detail";
        } catch (PostNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/";
        }
    }

}
