package com.projet.evaluation_satge.Services;

import com.projet.evaluation_satge.Entities.Tuteur;
import com.projet.evaluation_satge.Repositories.TuteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TuteurService {

    @Autowired
    private TuteurRepository tuteurRepository;

    public List<Tuteur> getAllTuteurs() {
        return tuteurRepository.findAll();
    }

    public Optional<Tuteur> getTuteurById(int id) {
        return tuteurRepository.findById(id);
    }

    public Tuteur saveTuteur(Tuteur tuteur) {
        return tuteurRepository.save(tuteur);
    }

    public void deleteTuteur(int id) {
        tuteurRepository.deleteById(id);
    }
}