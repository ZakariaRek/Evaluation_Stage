package com.projet.evaluation_satge.Services;

import com.projet.evaluation_satge.Entities.Persone;
import com.projet.evaluation_satge.Repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Persone> getAllPersons() {
        return personRepository.findAll();
    }

    public Optional<Persone> getPersonById(int id) {
        return personRepository.findById(id);
    }

    public Persone savePerson(Persone person) {
        return personRepository.save(person);
    }

    public void deletePerson(int id) {
        personRepository.deleteById(id);
    }
}