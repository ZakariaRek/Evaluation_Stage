package com.projet.evaluation_satge.Controllers;

import com.projet.evaluation_satge.Entities.Tuteur;
import com.projet.evaluation_satge.Services.TuteurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tuteurs")
@CrossOrigin("*")
public class TuteurController {

    @Autowired
    private TuteurService tuteurService;

    @GetMapping
    public ResponseEntity<List<Tuteur>> getAllTuteurs() {
        List<Tuteur> tuteurs = tuteurService.getAllTuteurs();
        return new ResponseEntity<>(tuteurs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tuteur> getTuteurById(@PathVariable int id) {
        Optional<Tuteur> tuteur = tuteurService.getTuteurById(id);
        return tuteur.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Tuteur> createTuteur(@RequestBody Tuteur tuteur) {
        Tuteur savedTuteur = tuteurService.saveTuteur(tuteur);
        return new ResponseEntity<>(savedTuteur, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tuteur> updateTuteur(@PathVariable int id, @RequestBody Tuteur tuteur) {
        Optional<Tuteur> existingTuteur = tuteurService.getTuteurById(id);
        if (existingTuteur.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        tuteur.setId(id);
        Tuteur updatedTuteur = tuteurService.saveTuteur(tuteur);
        return new ResponseEntity<>(updatedTuteur, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTuteur(@PathVariable int id) {
        Optional<Tuteur> existingTuteur = tuteurService.getTuteurById(id);
        if (existingTuteur.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        tuteurService.deleteTuteur(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}