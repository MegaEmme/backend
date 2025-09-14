package org.lessons.java.spring_cineteca.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "categories")
public class Category {
    // ATTRIBUTI
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "A category cannot exist without a proper name")
    @Size(min = 3, message = "A name should be at least 3 chars long")
    private String name;

    @Lob
    private String description;

    @ManyToMany(mappedBy = "categories")
    @JsonIgnore
    private List<Film> films;

    // GETTERS E SETTERS
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // MANY TO MANY
    public List<Film> getFilms() {
        return this.films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

}
