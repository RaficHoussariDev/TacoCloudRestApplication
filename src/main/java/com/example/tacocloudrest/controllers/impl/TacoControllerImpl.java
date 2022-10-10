package com.example.tacocloudrest.controllers.impl;

import com.example.tacocloudrest.controllers.interfaces.TacoController;
import com.example.tacocloudrest.models.Taco;
import com.example.tacocloudrest.models.TacoOrder;
import com.example.tacocloudrest.repositories.OrderRepository;
import com.example.tacocloudrest.repositories.TacoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class TacoControllerImpl implements TacoController {
    private final TacoRepository tacoRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public TacoControllerImpl(TacoRepository tacoRepository, OrderRepository orderRepository) {
        this.tacoRepository = tacoRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public Iterable<Taco> recentTacos() {
        PageRequest page = PageRequest.of(
                0, 12, Sort.by("createdAt").descending()
        );

        return tacoRepository.findAll(page).getContent();
    }

    @Override
    public ResponseEntity<Taco> tacoById(Long id) {
        Optional<Taco> optionalTaco = this.tacoRepository.findById(id);

        return optionalTaco
                .map(taco -> new ResponseEntity<>(taco, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @Override
    public Taco postTaco(Taco taco) {
        return this.tacoRepository.save(taco);
    }

    @Override
    public ResponseEntity<TacoOrder> patchOrder(Long orderId, TacoOrder patch) {
        TacoOrder tacoOrder;
        Optional<TacoOrder> optionalTacoOrder = this.orderRepository.findById(orderId);

        if(optionalTacoOrder.isPresent()) {
            tacoOrder = optionalTacoOrder.get();
        } else {
            return new ResponseEntity("tacoOrder not found", HttpStatus.NOT_FOUND);
        }

        if(patch.getDeliveryName() != null) {
            tacoOrder.setDeliveryName(patch.getDeliveryName());
        }
        if(patch.getDeliveryStreet() != null) {
            tacoOrder.setDeliveryStreet(patch.getDeliveryStreet());
        }
        if(tacoOrder.getDeliveryState() != null) {
            tacoOrder.setDeliveryState(patch.getDeliveryState());
        }
        if(patch.getDeliveryZip() != null) {
            tacoOrder.setDeliveryZip(patch.getDeliveryZip());
        }
        if(patch.getCcNumber() != null) {
            tacoOrder.setCcNumber(patch.getCcNumber());
        }
        if(patch.getCcExpiration() != null) {
            tacoOrder.setCcExpiration(patch.getCcExpiration());
        }
        if(patch.getCcCVV() != null) {
            tacoOrder.setCcCVV(patch.getCcCVV());
        }

        orderRepository.save(tacoOrder);
        return new ResponseEntity<>(tacoOrder, HttpStatus.OK);
    }

    @Override
    public TacoOrder putOrder(Long orderId, TacoOrder tacoOrder) {
        tacoOrder.setId(orderId);
        return orderRepository.save(tacoOrder);
    }

        @Override
    public void deleteOrder(Long orderId) {
        try {
            orderRepository.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {}
    }
}
