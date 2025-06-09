package com.example.demo.repository;

import com.example.demo.data.entity.Discente;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface DiscenteRepository extends JpaRepository<Discente, Long> {
    Optional<Discente> findByNomeDiscenteAndCognomeDiscente(String nomeDiscente, String cognomeDiscente);
}