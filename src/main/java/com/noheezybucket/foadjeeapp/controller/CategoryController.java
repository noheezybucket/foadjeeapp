package com.noheezybucket.foadjeeapp.controller;

import com.noheezybucket.foadjeeapp.entities.Category;
import com.noheezybucket.foadjeeapp.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // Afficher toutes les categories
    @GetMapping
    public String listCategoies(Model model) {
        model.addAttribute("categories", categoryService.getAll());
        return "categories/list";
    }

    // formulaire pour creer une nouvelle categorie
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("category", new Category());
        return "categories/create";
    }

    // enregistrer la nouvelle categorie
    @PostMapping
    public String saveCategory(@ModelAttribute("category") Category category){
        categoryService.save(category);
        return "redirect:/categories";
    }

    // Formulaire pour éditer une catégorie existante
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Category category = categoryService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + id));
        model.addAttribute("category", category);
        return "categories/create";
    }

    // Supprimer une catégorie
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return "redirect:/categories";
    }
}
