package com.example.demo.controllers.admin;

import com.example.demo.Services.CategoryService;
import com.example.demo.Services.PostNotFoundException;
import com.example.demo.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
public class AdminCategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping("/admin/category")
    public String showCategoryList(Model model) {
        List<Category> listCategories = service.listAll();
        model.addAttribute("listCategories", listCategories);

        return "layouts/admin/category";
    }

    @GetMapping("/admin/category/add")
    public String addPost_form(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("pageTitle", "Add New Category");
        return "layouts/admin/category_form";
    }

    @PostMapping("/admin/category/save")
    public String savePost(@ModelAttribute(name = "category") Category category, RedirectAttributes ra) {

        Category savedCategory = service.save(category);

        ra.addFlashAttribute("message","The user has been saved successfully.");

        return "redirect:/admin/category";
    }

    @GetMapping("/admin/category/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Category category = service.get(id);
            model.addAttribute("category", category);
            model.addAttribute("pageTitle", "Edit category (ID: " + id + ")");
            return "layouts/admin/category_form";
        } catch (PostNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/admin/category";
        }
    }

    @GetMapping("/admin/category/delete/{id}")
    public String deletePost(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The category ID " + id + " has been deleted");
        } catch (PostNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/admin/category";
    }
}
