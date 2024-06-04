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
    CerveseriaRepository CerveseriaRepository;

    @GetMapping("/Cerveserias")
    public ResponseEntity<List<Cerveseria>> getAllCerveserias(@RequestParam(required = false) String title) {
        try {
            List<Cerveseria> Cerveserias = new ArrayList<Cerveseria>();

            if (title == null)
                Cerveserias.addAll(CerveseriaRepository.findAll()); // Get all Cerveserias
            else
                Cerveserias.addAll(CerveseriaRepository.findByTitleContaining(title)); // Filter by title

            return new ResponseEntity<>(Cerveserias, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/Cerveserias/{id}")
    public ResponseEntity<Cerveseria> getCerveseriaById(@PathVariable("id") long id) {
        Optional<Cerveseria> CerveseriaData = CerveseriaRepository.findById(id);

        if (CerveseriaData.isPresent()) {
            return new ResponseEntity<>(CerveseriaData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/Cerveserias")
    public ResponseEntity<Cerveseria> createCerveseria(@RequestBody Cerveseria cerveseria) {
        try {
            Cerveseria _cerveseria = CerveseriaRepository.save(cerveseria);
            return new ResponseEntity<>(_cerveseria, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/Cerveserias/{id}")
    public ResponseEntity<Cerveseria> updateCerveseria(@PathVariable("id") long id,
            @RequestBody Cerveseria Cerveseria) {
        Optional<Cerveseria> CerveseriaData = CerveseriaRepository.findById(id);

        if (CerveseriaData.isPresent()) {
            Cerveseria _Cerveseria = CerveseriaData.get();
            _Cerveseria.setTitle(Cerveseria.getTitle());
            _Cerveseria.setDepartamento(Cerveseria.getDepartamento());
            _Cerveseria.setMunicipio(Cerveseria.getMunicipio());
            _Cerveseria.setNomencaltura(Cerveseria.getNomencaltura());
            _Cerveseria.setMarca_cerveza(Cerveseria.getMarca_cerveza());
            _Cerveseria.setCantidad_en_stock(Cerveseria.getCantidad_en_stock());

            return new ResponseEntity<>(CerveseriaRepository.save(_Cerveseria), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/Cerveserias/{id}")
    public ResponseEntity<HttpStatus> deleteCerveseria(@PathVariable("id") long id) {
        try {
            CerveseriaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/Cerveserias")
    public ResponseEntity<HttpStatus> deleteAllCerveserias() {
        try {
            CerveseriaRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/Cerveserias/Nomenclautra")
    public ResponseEntity<List<Cerveseria>> FindByNomenclatureContaining() {
        try {
            List<Cerveseria> Cerveserias = CerveseriaRepository.findByNomenclatureContaining("");

            if (Cerveserias.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(Cerveserias, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}