package org.lessons.java.spring_cineteca.controller;

import java.util.List;

import org.lessons.java.spring_cineteca.model.Film;
import org.lessons.java.spring_cineteca.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    // INDEX
    @GetMapping
    public String index(@RequestParam(name = "searchTerm", required = false) String searchTerm, Model model) {
        List<Film> films;
        if (searchTerm != null && !searchTerm.isBlank()) {
            films = filmRepository.findByTitleContaining(searchTerm);
        } else {
            films = filmRepository.findAll();
        }
        model.addAttribute("films", films);
        return "films/index";
    }

    // SHOW
    @GetMapping("/{id}")
    public String show(@PathVariable("id") Integer id, Model model) {
        Film film = filmRepository.findById(id).get();
        model.addAttribute("film", film);
        return "films/show";
    }
}
