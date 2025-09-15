package org.lessons.java.spring_cineteca.repository;

import java.util.List;

import org.lessons.java.spring_cineteca.model.Category;
import org.lessons.java.spring_cineteca.model.Film;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Integer> {

    public List<Film> findByTitleContaining(String title);

    public List<Film> findByCategoriesOrderByTitle(Category category, Sort sort);
}
