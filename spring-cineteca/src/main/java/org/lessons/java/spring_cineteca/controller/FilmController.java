package org.lessons.java.spring_cineteca.controller;

import java.util.List;

import org.lessons.java.spring_cineteca.model.Film;
import org.lessons.java.spring_cineteca.service.CategoryService;
import org.lessons.java.spring_cineteca.service.FilmService;
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
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @Autowired
    private CategoryService categoryService;

    // INDEX CON FILTRO RICERCA
    // @GetMapping
    // public String index(@RequestParam(name = "searchTerm", required = false)
    // String searchTerm, Model model) {
    // List<Film> films;
    // if (searchTerm != null && !searchTerm.isEmpty()) {
    // films = filmService.findByTitle(searchTerm);
    // if (films.isEmpty()) {
    // films = filmService.findAll();
    // }
    // } else {
    // films = filmService.findAll();
    // }
    // model.addAttribute("films", films);
    // return "films/index";
    // }

    // INDEX
    @GetMapping
    public String index(Model model) {
        List<Film> films = filmService.findAll();
        model.addAttribute("films", films);
        model.addAttribute("categories", categoryService.findAll());
        return "films/index";
    }

    // SHOW
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Film film = filmService.getById(id);
        model.addAttribute("film", film);
        return "films/show";
    }

    // CREATE
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("film", new Film());
        model.addAttribute("categories", categoryService.findAll());
        return "films/create-or-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("film") Film formFilm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "films/create-or-edit";
        }
        filmService.create(formFilm);
        return "redirect:/films";
    }

    // UPDATE
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("film", filmService.getById(id));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("edit", true);
        return "films/create-or-edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@Valid @ModelAttribute("film") Film formFilm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.findAll());
            return "films/create-or-edit";
        }
        filmService.update(formFilm);
        return "redirect:/films";
    }

    // DELETE
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        Film film = filmService.getById(id);
        filmService.delete(film);
        return "redirect:/films";
    }
}
