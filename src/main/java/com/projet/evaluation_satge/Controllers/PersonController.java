package com.projet.evaluation_satge.Controllers;

import com.projet.evaluation_satge.Entities.Persone;
import com.projet.evaluation_satge.Services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/persons")
@CrossOrigin("*")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping
    public ResponseEntity<List<Persone>> getAllPersons() {
        List<Persone> persons = personService.getAllPersons();
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persone> getPersonById(@PathVariable int id) {
        Optional<Persone> person = personService.getPersonById(id);
        return person.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Persone> createPerson(@RequestBody Persone person) {
        Persone savedPerson = personService.savePerson(person);
        return new ResponseEntity<>(savedPerson, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persone> updatePerson(@PathVariable int id, @RequestBody Persone person) {
        Optional<Persone> existingPerson = personService.getPersonById(id);
        if (existingPerson.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        person.setId(id);
        Persone updatedPerson = personService.savePerson(person);
        return new ResponseEntity<>(updatedPerson, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable int id) {
        Optional<Persone> existingPerson = personService.getPersonById(id);
        if (existingPerson.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}