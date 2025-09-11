package org.lessons.java.spring_cineteca.repository;

import org.lessons.java.spring_cineteca.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Integer> {

}
