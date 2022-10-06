package com.example.tacocloudrest.repositories;

import com.example.tacocloudrest.models.Taco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TacoRepository extends JpaRepository<Taco, Long> {
}
