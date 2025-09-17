package org.lessons.java.spring_cineteca.controller;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_cineteca.model.Film;
import org.lessons.java.spring_cineteca.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/films")
public class FilmRestController {

    @Autowired
    private FilmService filmService;

    // INDEX
    @GetMapping
    public List<Film> index(@RequestParam(name = "title", required = false) String title) {
        List<Film> films;
        if (title != null) {
            films = filmService.findByTitle(title);
            if (films.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND);
            }
        } else {
            films = filmService.findAllSortedByTitle();
        }
        return films;
    }

    // SHOW
    @GetMapping("/{id}")
    public ResponseEntity<Film> show(@PathVariable("id") Integer id) {
        Optional<Film> filmAttempt = filmService.findById(id);
        if (filmAttempt.isEmpty()) {
            return new ResponseEntity<Film>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Film>(filmAttempt.get(), HttpStatus.OK);
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Film> store(@Valid @RequestBody Film film) {
        return new ResponseEntity<Film>(filmService.create(film), HttpStatus.OK);
    }

    // UPDATE
    @PutMapping("{id}")
    public ResponseEntity<Film> update(@Valid @RequestBody Film film, @PathVariable("id") Integer id) {
        if (filmService.findById(id).isEmpty()) {
            return new ResponseEntity<Film>(HttpStatus.NOT_FOUND);
        }
        film.setId(id);
        return new ResponseEntity<Film>(filmService.update(film), HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("{id}")
    public ResponseEntity<Film> delete(@PathVariable("id") Integer id) {
        if (filmService.findById(id).isEmpty()) {
            return new ResponseEntity<Film>(HttpStatus.NOT_FOUND);
        }
        filmService.deleteById(id);
        return new ResponseEntity<Film>(HttpStatus.OK);
    }

}
