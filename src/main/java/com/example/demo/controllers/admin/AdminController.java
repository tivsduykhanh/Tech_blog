package com.example.demo.controllers.admin;

import com.example.demo.Services.CategoryService;
import com.example.demo.Services.PostNotFoundException;
import com.example.demo.Services.PostService;
import com.example.demo.models.Category;
import com.example.demo.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private PostService service;
    @Autowired
    private CategoryService service_category;

    @GetMapping("/admin")
    public String showPostList(Model model) {
        List<Post> listPosts = service.listAll();
        model.addAttribute("listPosts", listPosts);
        model.addAttribute("pageTitle", "Post manage");
        return "layouts/admin/index";
    }

    @GetMapping("/admin/post/add")
    public String addPost_form(Model model) {
        List<Category> listCategories = service_category.listAll();
        model.addAttribute("listCategories", listCategories);
        model.addAttribute("post", new Post());
        model.addAttribute("pageTitle", "Add New Post");
        return "layouts/admin/post_form";
    }

    @PostMapping("/admin/post/save")
    public String savePost(@ModelAttribute(name = "post") Post post, RedirectAttributes ra,
                           @RequestParam("fileImage") MultipartFile multipartFile) throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        post.setImage(fileName);
        Post savedPost = service.save(post);

        String uploadDir = "./image-posts/" + savedPost.getId();


        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            System.out.println(filePath.toFile().getAbsolutePath());
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IOException("Could not save uploaded file " + fileName);
        }

        ra.addFlashAttribute("message","The user has been saved successfully.");

        return "redirect:/admin";
    }

    @GetMapping("/admin/post/detail/{id}")
    public String showDetailPost(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Post post = service.get(id);
            model.addAttribute("post", post);
            model.addAttribute("pageTitle", "Detail Post (ID: " + id + ")");
            return "/layouts/admin/detailPost_form";
        } catch (PostNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/admin";
        }
    }

    @GetMapping("/admin/post/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Post post = service.get(id);
            List<Category> listCategories = service_category.listAll();
            model.addAttribute("listCategories", listCategories);
            model.addAttribute("post", new Post());
            model.addAttribute("pageTitle", "Edit Post (ID: " + id + ")");
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


    @GetMapping("/admin/login")
    public String login() {
        return "layouts/admin/login";
    }
}
