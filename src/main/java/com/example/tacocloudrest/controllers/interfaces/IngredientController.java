package com.example.tacocloudrest.controllers.interfaces;

import com.example.tacocloudrest.models.Ingredient;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/api/ingredients", produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
public interface IngredientController {
    @GetMapping
    Iterable<Ingredient> allIngredients();

    @PostMapping
//    @PreAuthorize("#{hasRole('ADMIN')}")
    @ResponseStatus(HttpStatus.CREATED)
    Ingredient saveIngredient(@RequestBody Ingredient ingredient);

    @DeleteMapping("/{id}")
//    @PreAuthorize("#{hasRole('ADMIN')}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteIngredient(@PathVariable("id") String ingredientId);
}
