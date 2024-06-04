package com.darkcode.app.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.darkcode.app.domain.Cerveseria;

public interface CerveseriaRepository extends JpaRepository<Cerveseria, Long>{
    List<Cerveseria> findByTitleContaining(String title);
    List<Cerveseria> findByNomenclatureContaining(String nomenclature);
    
}
