package org.lessons.java.spring_cineteca.service;

import java.util.List;

import org.lessons.java.spring_cineteca.model.Category;
import org.lessons.java.spring_cineteca.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    // Lista categorie
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    // Recupera per ID
    public Category getById(Integer id) {
        return categoryRepository.findById(id).get();
    }

    // Create
    public Category create(Category category) {
        return categoryRepository.save(category);
    }

    // Update
    public Category update(Category category) {
        return categoryRepository.save(category);
    }

    // Delete pura
    public void delete(Category category) {
        categoryRepository.delete(category);
    }

    // Delete per ID
    public void deleteById(Integer id) {
        Category category = getById(id);
        categoryRepository.delete(category);
    }

}
