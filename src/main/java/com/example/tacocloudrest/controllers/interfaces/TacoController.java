package com.example.tacocloudrest.controllers.interfaces;

import com.example.tacocloudrest.models.Taco;

import com.example.tacocloudrest.models.TacoOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "/api/tacos", produces = "application/json")
@CrossOrigin(origins = "http://tacocloud:8000")
public interface TacoController {
    @GetMapping(params = "recent")
    Iterable<Taco> recentTacos();

    @GetMapping("/{id}")
    ResponseEntity<Taco> tacoById(@PathVariable("id") Long id);

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    Taco postTaco(@RequestBody Taco taco);

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    ResponseEntity<TacoOrder> patchOrder(@PathVariable("orderId") Long orderId, @RequestBody TacoOrder patch);

    @PutMapping(path = "/{orderId}", consumes = "application/json")
    TacoOrder putOrder(@PathVariable("orderId") Long orderId, @RequestBody TacoOrder tacoOrder);

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteOrder(@PathVariable("orderId") Long orderId);
}
