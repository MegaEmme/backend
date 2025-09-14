package org.lessons.java.spring_cineteca.controller;

import org.lessons.java.spring_cineteca.model.Category;
import org.lessons.java.spring_cineteca.model.Film;
import org.lessons.java.spring_cineteca.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // INDEX
    @GetMapping
    public String index(Model model) {
        model.addAttribute("categories", categoryService.findAll());
        return "categories/index";
    }

    // SHOW
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Category category = categoryService.getById(id);
        model.addAttribute("category", category);
        return "categories/show";
    }

    // CREATE
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("category", new Category());
        return "categories/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("category") Category formCategory, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "categories/create-or-edit";
        }
        categoryService.create(formCategory);
        return "redirect:/categories";
    }

    // UPDATE
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("category", categoryService.getById(id));
        model.addAttribute("edit", true);
        return "categories/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("category") Category formCategory, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "categories/create-or-edit";
        }
        categoryService.update(formCategory);
        return "redirect:/categories";
    }

    // DELETE
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        Category categoryToDelete = categoryService.getById(id);
        for (Film linkedFilm : categoryToDelete.getFilms()) {
            linkedFilm.getCategories().remove(categoryToDelete);
        }
        categoryService.delete(categoryToDelete);
        return "redirect:/ingredients";

    }
}
