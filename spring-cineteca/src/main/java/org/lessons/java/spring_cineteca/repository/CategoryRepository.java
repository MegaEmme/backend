package org.lessons.java.spring_cineteca.repository;

import org.lessons.java.spring_cineteca.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
