package com.example.tacocloudrest.repositories;

import com.example.tacocloudrest.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, String> { }
