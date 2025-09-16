package org.lessons.java.spring_cineteca.service;

import java.util.List;
import java.util.Optional;

import org.lessons.java.spring_cineteca.model.Category;
import org.lessons.java.spring_cineteca.model.Film;
import org.lessons.java.spring_cineteca.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    // lista film ordinati per nome
    public List<Film> findAllSortedByTitle() {
        return filmRepository.findAll(Sort.by("title"));
    }

    // Lista film ordinati per anno
    public List<Film> findAllSortedByYear() {
        return filmRepository.findAll(Sort.by("year"));
    }

    // Lista film legati a categorie per nome
    public List<Film> findByCategoriesOrderByTitle(Category category) {
        return filmRepository.findByCategoriesOrderByTitle(category, Sort.by("title"));
    }

    // Recupera per ID
    public Optional<Film> findById(Integer id) {
        return filmRepository.findById(id);
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
