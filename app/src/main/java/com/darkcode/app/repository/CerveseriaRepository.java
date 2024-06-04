package com.darkcode.app.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.darkcode.app.domain.Cerveseria;

@Repository
public interface CerveseriaRepository extends JpaRepository<Cerveseria, Long> {
    List<Cerveseria> findByTitleContaining(String title); // Para el filtro por título

    List<Cerveseria> findByNomenclaturaContaining(String nomenclatura); // Para el filtro por nomenclatura
}