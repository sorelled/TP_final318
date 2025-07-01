package com.hinkaku.product_manager.controller;

import com.hinkaku.product_manager.dto.CategoryDto;
import com.hinkaku.product_manager.model.Category;
import com.hinkaku.product_manager.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories/list";
    }

    @GetMapping("/add")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("categoryDto", new CategoryDto());
        return "categories/add";
    }

    @GetMapping("/{id}")
    public String getCategoryById(@PathVariable UUID id, Model model) {
        model.addAttribute("category", categoryService.getCategoryById(id));
        return "categories/view";
    }

    @PostMapping("/save")
    public String saveCategory(@ModelAttribute("categoryDto") CategoryDto categoryDto) {
        categoryService.createCategory(categoryDto);
        return "redirect:/categories";
    }

    @GetMapping("/{id}/edit")
    public String showEditCategoryForm(@PathVariable UUID id, Model model) {
        model.addAttribute("categoryDto", categoryService.getCategoryById(id));
        return "categories/edit";
    }

    @PostMapping("/{id}/update")
    public String updateCategory(@PathVariable UUID id, @ModelAttribute("categoryDto") CategoryDto categoryDto) {
        categoryService.updateCategory(id, categoryDto);
        return "redirect:/categories";
    }

    @GetMapping("/{id}/delete")
    public String deleteCategory(@PathVariable UUID id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


}
