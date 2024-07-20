package com.example.recipes.controller;

import com.example.recipes.model.Menu;
import com.example.recipes.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/menus")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping
    public ResponseEntity<Menu> getMenuByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        Optional<Menu> menu = menuService.getMenuByDate(date);
        return menu.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Menu> saveMenu(@RequestBody Menu menu) {
        Menu savedMenu = menuService.saveMenu(menu);
        return ResponseEntity.ok(savedMenu);
    }
}
