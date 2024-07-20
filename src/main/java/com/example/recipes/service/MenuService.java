package com.example.recipes.service;

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

    public Optional<Menu> getMenuByDate(LocalDate date) {
        return menuRepository.findByDate(date);
    }

    public Menu saveMenu(Menu menu) {
        return menuRepository.save(menu);
    }
}
