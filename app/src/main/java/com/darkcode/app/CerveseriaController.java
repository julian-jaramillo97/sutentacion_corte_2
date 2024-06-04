package com.darkcode.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.darkcode.app.domain.Cerveseria;
import com.darkcode.app.repository.CerveseriaRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CerveseriaController {

    @Autowired
    private CerveseriaRepository cerveseriaRepository;

    @GetMapping("/cerveserias")
    public ResponseEntity<List<Cerveseria>> getAllCerveserias(@RequestParam(required = false) String title) {
        try {
            List<Cerveseria> cerveserias = new ArrayList<>();

            if (title == null)
                cerveserias.addAll(cerveseriaRepository.findAll()); // Get all Cerveserias
            else
                cerveserias.addAll(cerveseriaRepository.findByTitleContaining(title)); // Filter by title

            if (cerveserias.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(cerveserias, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cerveserias/{id}")
    public ResponseEntity<Cerveseria> getCerveseriaById(@PathVariable("id") long id) {
        Optional<Cerveseria> cerveseriaData = cerveseriaRepository.findById(id);

        if (cerveseriaData.isPresent()) {
            return new ResponseEntity<>(cerveseriaData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/cerveserias")
    public ResponseEntity<Cerveseria> createCerveseria(@RequestBody Cerveseria cerveseria) {
        try {
            Cerveseria _cerveseria = cerveseriaRepository.save(cerveseria);
            return new ResponseEntity<>(_cerveseria, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cerveserias/{id}")
    public ResponseEntity<Cerveseria> updateCerveseria(@PathVariable("id") long id,
            @RequestBody Cerveseria cerveseria) {
        Optional<Cerveseria> cerveseriaData = cerveseriaRepository.findById(id);

        if (cerveseriaData.isPresent()) {
            Cerveseria _cerveseria = cerveseriaData.get();
            _cerveseria.setTitle(cerveseria.getTitle());
            _cerveseria.setDepartamento(cerveseria.getDepartamento());
            _cerveseria.setMunicipio(cerveseria.getMunicipio());
            _cerveseria.setNomenclatura(cerveseria.getNomenclatura());
            _cerveseria.setMarcaCerveza(cerveseria.getMarcaCerveza());
            _cerveseria.setCantidadEnStock(cerveseria.getCantidadEnStock());

            return new ResponseEntity<>(cerveseriaRepository.save(_cerveseria), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/cerveserias/{id}")
    public ResponseEntity<HttpStatus> deleteCerveseria(@PathVariable("id") long id) {
        try {
            cerveseriaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/cerveserias")
    public ResponseEntity<HttpStatus> deleteAllCerveserias() {
        try {
            cerveseriaRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cerveserias/nomenclatura")
    public ResponseEntity<List<Cerveseria>> findByNomenclaturaContaining(@RequestParam String nomenclatura) {
        try {
            List<Cerveseria> cerveserias = cerveseriaRepository.findByNomenclaturaContaining(nomenclatura);

            if (cerveserias.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(cerveserias, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
