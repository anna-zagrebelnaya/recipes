package com.example.recipes.service;

import com.example.recipes.controller.dto.MenuUpdate;
import com.example.recipes.model.Menu;
import com.example.recipes.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private RecipeService recipeService;

    public Optional<Menu> getMenuByDate(LocalDate date) {
        return menuRepository.findByDate(date);
    }

    public Menu saveMenu(MenuUpdate menuUpdate) {
        Menu menu = new Menu();
        populateMenu(menu, menuUpdate);
        return menuRepository.save(menu);
    }

    public Menu update(Long id, MenuUpdate menuUpdate) {
        Menu menu = menuRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu not found"));
        populateMenu(menu, menuUpdate);
        return menuRepository.save(menu);
    }

    private void populateMenu(Menu menu, MenuUpdate menuUpdate) {
        menu.setDate(menuUpdate.getDate());
        menu.setBreakfast(menuUpdate.getBreakfastId()!= null
                ? recipeService.getRecipeById(menuUpdate.getBreakfastId())
                : null);
        menu.setSnack(menuUpdate.getSnackId()!= null
                ? recipeService.getRecipeById(menuUpdate.getSnackId())
                : null);
        menu.setLunch(menuUpdate.getLunchId()!= null
                ? recipeService.getRecipeById(menuUpdate.getLunchId())
                : null);
        menu.setDinner(menuUpdate.getDinnerId()!= null
                ? recipeService.getRecipeById(menuUpdate.getDinnerId())
                : null);
    }
}
