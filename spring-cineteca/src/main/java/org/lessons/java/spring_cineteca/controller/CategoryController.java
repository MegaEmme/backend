package org.lessons.java.spring_cineteca.controller;

import org.lessons.java.spring_cineteca.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
