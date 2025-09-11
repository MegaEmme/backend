package org.lessons.java.spring_cineteca.controller;

import java.util.List;

import org.lessons.java.spring_cineteca.model.Film;
import org.lessons.java.spring_cineteca.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/films")
public class FilmController {

    @Autowired
    private FilmRepository filmRepository;

    @GetMapping
    public String index(Model model) {

        List<Film> films = filmRepository.findAll();
        model.addAttribute("films", films);
        return "films/index";
    }
}
