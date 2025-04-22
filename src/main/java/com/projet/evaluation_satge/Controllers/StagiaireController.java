package com.projet.evaluation_satge.Controllers;

import com.projet.evaluation_satge.Entities.Stagiaire;
import com.projet.evaluation_satge.Services.StagiaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stagiaires")
@CrossOrigin("*")
public class StagiaireController {

    @Autowired
    private StagiaireService stagiaireService;

    @GetMapping
    public ResponseEntity<List<Stagiaire>> getAllStagiaires() {
        List<Stagiaire> stagiaires = stagiaireService.getAllStagiaires();
        return new ResponseEntity<>(stagiaires, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stagiaire> getStagiaireById(@PathVariable int id) {
        Optional<Stagiaire> stagiaire = stagiaireService.getStagiaireById(id);
        return stagiaire.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Stagiaire> createStagiaire(@RequestBody Stagiaire stagiaire) {
        Stagiaire savedStagiaire = stagiaireService.saveStagiaire(stagiaire);
        return new ResponseEntity<>(savedStagiaire, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stagiaire> updateStagiaire(@PathVariable int id, @RequestBody Stagiaire stagiaire) {
        Optional<Stagiaire> existingStagiaire = stagiaireService.getStagiaireById(id);
        if (existingStagiaire.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        stagiaire.setId(id);
        Stagiaire updatedStagiaire = stagiaireService.saveStagiaire(stagiaire);
        return new ResponseEntity<>(updatedStagiaire, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStagiaire(@PathVariable int id) {
        Optional<Stagiaire> existingStagiaire = stagiaireService.getStagiaireById(id);
        if (existingStagiaire.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        stagiaireService.deleteStagiaire(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}