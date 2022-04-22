package com.example.demo.controllers;

import com.example.demo.Services.CategoryService;
import com.example.demo.Services.ContactService;
import com.example.demo.Services.PostNotFoundException;
import com.example.demo.models.Category;
import com.example.demo.models.Contact;
import com.example.demo.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ContactController {
    @Autowired
    private ContactService service;
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/admin/contact")
    public String showContactList(Model model) {
        List<Contact> listContacts = service.listAll();
        model.addAttribute("listContacts", listContacts);
        model.addAttribute("pageTitle", "Contact manager");

        return "layouts/admin/contact";
    }

    @GetMapping("/contact/add")
    public String addContact_form(Model model) {
        List<Category> listCategories = categoryService.listAll();
        model.addAttribute("listCategories", listCategories);
        model.addAttribute("contact", new Contact());
        model.addAttribute("pageTitle", "Add New Contact");
        return "/layouts/contact_form";
    }

    @PostMapping("/contact/save")
    public String savePost(@ModelAttribute(name = "category") Contact contact, RedirectAttributes ra) {

        Contact savedContact = service.save(contact);

        ra.addFlashAttribute("message","The contact has been saved successfully.");

        return "redirect:/contact/add";
    }

    @GetMapping("/admin/contact/detail/{id}")
    public String showDetailPost(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            Contact contact = service.get(id);
            model.addAttribute("contact", contact);
            model.addAttribute("pageTitle", "Detail contact (ID: " + id + ")");
            return "/layouts/admin/detailContact";
        } catch (PostNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/admin/contact";
        }
    }

//    @GetMapping("/admin/category/edit/{id}")
//    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
//        try {
//            Category category = service.get(id);
//            model.addAttribute("category", category);
//            model.addAttribute("pageTitle", "Edit category (ID: " + id + ")");
//            return "layouts/admin/category_form";
//        } catch (PostNotFoundException e) {
//            ra.addFlashAttribute("message", e.getMessage());
//            return "redirect:/admin/category";
//        }
//    }

    @GetMapping("/admin/contact/delete/{id}")
    public String deletePost(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {
        try {
            service.delete(id);
            ra.addFlashAttribute("message", "The contact ID " + id + " has been deleted");
        } catch (PostNotFoundException e) {
            ra.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/admin/category";
    }
}
