package com.example.BookStore.controller;

import com.example.BookStore.entity.Category;
import com.example.BookStore.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String showAllCategories(Model model){
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories",categories);
        return "category/list";
    }
    @GetMapping("/add")
    public String addCategoryform(Model model){
        model.addAttribute("category", new Category());
        return "category/add";
    }
    @PostMapping("/add")
    public String addCategory (@ModelAttribute("categories")Category category){
        categoryService.addCategory(category);
        return "redirect:/categories";
    }
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id){
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable("id") Long id, Model model){
        model.addAttribute("category", categoryService.getCategoryById(id));
        return "category/edit";
    }
    @PostMapping("edit/{id}")
    public String editCategories(@PathVariable("id") Long id, @ModelAttribute("category") Category editCategory){
        Category category = categoryService.getCategoryById(id);
        category.setId(editCategory.getId());
        category.setName(editCategory.getName());
        categoryService.updateCategory(category);
        return "redirect:/categories";
    }
}