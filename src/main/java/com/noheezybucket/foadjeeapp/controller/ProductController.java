package com.noheezybucket.foadjeeapp.controller;

import com.noheezybucket.foadjeeapp.entities.Product;
import com.noheezybucket.foadjeeapp.service.CategoryService;
import com.noheezybucket.foadjeeapp.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;
    private CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    // Liste des produits
    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAll());
        return "products/list";
    }

    // Formulaire création
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAll());
        return "products/create";
    }

    // Sauvegarder produit
    @PostMapping
    public String saveProduct(@ModelAttribute("product") Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    // Formulaire édition
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Product product = productService.getById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAll());
        return "products/create";
    }

    // Supprimer produit
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.delete(id);
        return "redirect:/products";
    }
}
