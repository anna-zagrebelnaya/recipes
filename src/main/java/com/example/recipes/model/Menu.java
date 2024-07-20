package com.example.recipes.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "breakfast_id")
    private Recipe breakfast;

    @ManyToOne
    @JoinColumn(name = "snack_id")
    private Recipe snack;

    @ManyToOne
    @JoinColumn(name = "lunch_id")
    private Recipe lunch;

    @ManyToOne
    @JoinColumn(name = "dinner_id")
    private Recipe dinner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Recipe getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(Recipe breakfast) {
        this.breakfast = breakfast;
    }

    public Recipe getSnack() {
        return snack;
    }

    public void setSnack(Recipe snack) {
        this.snack = snack;
    }

    public Recipe getLunch() {
        return lunch;
    }

    public void setLunch(Recipe lunch) {
        this.lunch = lunch;
    }

    public Recipe getDinner() {
        return dinner;
    }

    public void setDinner(Recipe dinner) {
        this.dinner = dinner;
    }
}
