package com.example.recipes.controller.dto;

import java.util.List;

public class GroceryList {
    private List<GroceryItem> items;

    public GroceryList(List<GroceryItem> items) {
        this.items = items;
    }

    public List<GroceryItem> getItems() {
        return items;
    }

    public void setItems(List<GroceryItem> items) {
        this.items = items;
    }
}
