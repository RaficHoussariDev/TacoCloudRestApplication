package com.example.tacocloudrest.services;

import com.example.tacocloudrest.models.Ingredient;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class IngredientRestService {
    private final RestTemplate restTemplate;

    @Autowired
    public IngredientRestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Ingredient getIngredientById(String ingredientId) {
        return restTemplate.getForObject(
                "http://localhost:8080/ingredients/{id}",
                Ingredient.class,
                ingredientId
        );
    }

    public Ingredient getIngredientByIdWithMap(String ingredientId) {
        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("id", ingredientId);

        return restTemplate.getForObject(
                "http://localhost:8080/ingredients/{id}",
                Ingredient.class,
                urlVariables
        );
    }

    public Ingredient getIngredientByIdWithUri(String ingredientId) {
        Map<String, String> urlVariables = new HashMap<>();
        urlVariables.put("id", ingredientId);

        URI url = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8080/ingredients/{id}")
                .build(urlVariables);

        return restTemplate.getForObject(url, Ingredient.class);
    }

    public Ingredient getIngredientByIdWithResponseEntity(String ingredientId) {
        ResponseEntity<Ingredient> responseEntity =
                restTemplate.getForEntity(
                        "http://localhost:8080/ingredients/{id}",
                        Ingredient.class,
                        ingredientId
                );

        log.info("Fetched time: {}", responseEntity.getHeaders().getDate());
        return responseEntity.getBody();
    }

    public void updateIngredient(Ingredient ingredient) {
        restTemplate.put(
                "http://localhost:8080/ingredients/{id}",
                ingredient,
                ingredient.getId()
        );
    }

    public void deleteIngredient(String ingredientId) {
        restTemplate.delete("http://localhost:8080/ingredients/{id}", ingredientId);
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        return restTemplate.postForObject(
                "http://localhosy:8080/ingredients",
                ingredient,
                Ingredient.class
        );
    }

    public URI createIngredientWithUriResponse(Ingredient ingredient) {
        return restTemplate.postForLocation("http://localhosy:8080/ingredients", ingredient);
    }

    public Ingredient createIngredientWithResponseEntity(Ingredient ingredient) {
        ResponseEntity<Ingredient> responseEntity =
                restTemplate.postForEntity(
                        "http://localhosy:8080/ingredients",
                        ingredient,
                        Ingredient.class
                );

        log.info("New resource created at {}", responseEntity.getHeaders().getLocation());
        return responseEntity.getBody();
    }
}
