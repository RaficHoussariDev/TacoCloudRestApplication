package com.example.tacocloudrest.controllers.impl;

import com.example.tacocloudrest.controllers.interfaces.IngredientController;
import com.example.tacocloudrest.models.Ingredient;
import com.example.tacocloudrest.repositories.IngredientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IngredientControllerImpl implements IngredientController {
    public final IngredientRepository ingredientRepository;

    @Autowired
    public IngredientControllerImpl(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Iterable<Ingredient> allIngredients() {
        return ingredientRepository.findAll();
    }

    @Override
    public Ingredient saveIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    @Override
    public void deleteIngredient(String ingredientId) {
        ingredientRepository.deleteById(ingredientId);
    }
}
