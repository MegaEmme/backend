package org.lessons.java.spring_cineteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;

@Entity
@Table(name = "films")
public class Film {

    // ATTRIBUTI
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Title must be not null, empty, or blank")
    private String title;

    @Lob
    private String plot;

    @NotBlank(message = "Year must be not null, empty, or blank")
    @Min(value = 1895, message = "There are no movies ever made before the year 1895")
    @PastOrPresent(message = "Year cannot be set in the future")
    private Integer year;

    @NotBlank(message = "Duration must be not null, empty, or blank")
    @Min(value = 0, message = "Duration cannot be negative")
    private Integer duration;

    // GETTERS E SETTERS
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlot() {
        return this.plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    // METODI AGGIUNTIVI
    @Override
    public String toString() {
        return String.format("Film: %s, Anno: %d, Durata: %d minuti, Trama: %s", this.title, this.year, this.duration,
                this.plot);
    }

}
