package com.example.demo.repository;



import com.example.demo.entity.Discente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DiscenteRepository extends JpaRepository<Discente, Long> {
    @Query("SELECT d FROM Discente d ORDER BY d.id ")
    List<Discente> findAllByOrderByIdAsc();
    @Query("SELECT d FROM Discente d ORDER BY d.nome ASC")
    List<Discente> findAllOrderByNomeAsc();
    @Query("SELECT d FROM Discente d ORDER BY d.nome  DESC")
    List<Discente> findAllOrderByNomeDesc();
    @Query("SELECT d FROM Discente d WHERE d.cittaResidenza = :cittaResidenza")
    List<Discente> findByCittaResidenza(String cittaResidenza);

    Optional<Discente> findByNomeAndCognome(String s, String s1);
}