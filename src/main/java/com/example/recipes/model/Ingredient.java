package com.example.recipes.model;

import jakarta.persistence.*;

@Embeddable
public class Ingredient {
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    private int quantity;
    @Enumerated(EnumType.STRING)
    private MeasuringUnit unit;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public MeasuringUnit getUnit() {
        return unit;
    }

    public void setUnit(MeasuringUnit unit) {
        this.unit = unit;
    }
}
