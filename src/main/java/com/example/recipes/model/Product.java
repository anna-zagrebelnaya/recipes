package com.example.recipes.model;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private MeasuringUnit unit;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Product.Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MeasuringUnit getUnit() {
        return unit;
    }

    public void setUnit(MeasuringUnit unit) {
        this.unit = unit;
    }

    public Product.Category getCategory() {
        return category;
    }

    public void setCategory(Product.Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", unit=" + unit +
                '}';
    }

    public enum Category {
        VEGETABLE, FRUIT, MEAT, DAIRY, FISH, OTHER;
    }
}
