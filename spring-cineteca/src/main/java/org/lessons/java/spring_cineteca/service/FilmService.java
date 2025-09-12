package org.lessons.java.spring_cineteca.service;

import java.util.List;

import org.lessons.java.spring_cineteca.model.Film;
import org.lessons.java.spring_cineteca.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    // Lista Pizze
    public List<Film> findAll() {
        return filmRepository.findAll();
    }

    // Filtro ricerca per nome
    public List<Film> findByTitle(String title) {
        return filmRepository.findByTitleContaining(title);
    }

    public Film getById(Integer id) {
        return filmRepository.findById(id).get();
    }

    // Create
    public Film create(Film film) {
        return filmRepository.save(film);
    }

    // Update
    public Film update(Film film) {
        return filmRepository.save(film);
    }

    // Delete pura
    public void delete(Film film) {
        filmRepository.delete(film);
    }

    // Delete per Id
    public void deleteById(Integer id) {
        Film film = getById(id);
        filmRepository.delete(film);
    }

}
