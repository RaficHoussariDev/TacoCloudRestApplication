package com.example.tacocloudrest.utils;

import com.example.tacocloudrest.models.Ingredient;
import com.example.tacocloudrest.models.Ingredient.Type;
import com.example.tacocloudrest.models.Taco;
import com.example.tacocloudrest.models.TacoOrder;
import com.example.tacocloudrest.repositories.IngredientRepository;
import com.example.tacocloudrest.repositories.OrderRepository;
import com.example.tacocloudrest.repositories.TacoRepository;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Component
@Transactional
public class MockDataRunner implements CommandLineRunner {
    private final IngredientRepository ingredientRepository;
    private final TacoRepository tacoRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public MockDataRunner(
            IngredientRepository ingredientRepository,
            TacoRepository tacoRepository,
            OrderRepository orderRepository) {
        this.ingredientRepository = ingredientRepository;
        this.tacoRepository = tacoRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Ingredient flourTortilla = new Ingredient("FLTO", "Flour Tortilla", Type.WRAP);
        Ingredient cornTortilla = new Ingredient("COTO", "Corn Tortilla", Type.WRAP);
        Ingredient groundBeef = new Ingredient("GRBF", "Ground Beef", Type.PROTEIN);
        Ingredient carnitas = new Ingredient("CARN", "Carnitas", Type.PROTEIN);
        Ingredient dicedTomatoes = new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES);
        Ingredient lettuce = new Ingredient("LETC", "Lettuce", Type.VEGGIES);
        Ingredient cheddar = new Ingredient("CHED", "Cheddar", Type.CHEESE);
        Ingredient monterreyJack = new Ingredient("JACK", "Monterrey Jack", Type.CHEESE);
        Ingredient salsa = new Ingredient("SLSA", "Salsa", Type.SAUCE);
        Ingredient sourCream = new Ingredient("SRCR", "Sour Cream", Type.SAUCE);

        this.ingredientRepository.save(flourTortilla);
        this.ingredientRepository.save(cornTortilla);
        this.ingredientRepository.save(groundBeef);
        this.ingredientRepository.save(carnitas);
        this.ingredientRepository.save(dicedTomatoes);
        this.ingredientRepository.save(lettuce);
        this.ingredientRepository.save(cheddar);
        this.ingredientRepository.save(monterreyJack);
        this.ingredientRepository.save(salsa);
        this.ingredientRepository.save(sourCream);

        Taco taco = new Taco();
        taco.setName("Carnivore");
        taco.setIngredients(List.of(flourTortilla, groundBeef, carnitas, sourCream, salsa, cheddar));
        this.tacoRepository.save(taco);

        Taco anotherTaco = new Taco();
        anotherTaco.setName("Bovine Bounty");
        anotherTaco.setIngredients(List.of(cornTortilla, groundBeef, cheddar, monterreyJack, sourCream));
        this.tacoRepository.save(anotherTaco);

        Taco yetAnotherTaco = new Taco();
        yetAnotherTaco.setName("Veg-Out");
        yetAnotherTaco.setIngredients(List.of(flourTortilla, cornTortilla, dicedTomatoes, lettuce, salsa));
        this.tacoRepository.save(yetAnotherTaco);

        TacoOrder tacoOrder = new TacoOrder();
        tacoOrder.setDeliveryName("Rafic Address");
        tacoOrder.setDeliveryCity("City");
        tacoOrder.setDeliveryStreet("Street");
        tacoOrder.setDeliveryState("US");
        tacoOrder.setDeliveryZip("000000");
        tacoOrder.addTaco(taco);
        tacoOrder.addTaco(anotherTaco);
        tacoOrder.addTaco(yetAnotherTaco);
        this.orderRepository.save(tacoOrder);
        log.info("tacoOrderId: {}", tacoOrder.getId());
    }
}
