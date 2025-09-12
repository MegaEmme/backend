package org.lessons.java.spring_cineteca.controller;

import java.util.List;

import org.lessons.java.spring_cineteca.model.Film;
import org.lessons.java.spring_cineteca.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private FilmService filmService;

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
        return "films/index";
    }

    // SHOW
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Film film = filmService.getById(id);
        model.addAttribute("film", film);
        return "films/show";
    }
}
