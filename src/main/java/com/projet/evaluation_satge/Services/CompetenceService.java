package com.projet.evaluation_satge.Services;

import com.projet.evaluation_satge.Entities.Competences;
import com.projet.evaluation_satge.Repositories.CompetenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompetenceService {

    @Autowired
    private CompetenceRepository competenceRepository;

    public List<Competences> getAllCompetences() {
        return competenceRepository.findAll();
    }

    public Optional<Competences> getCompetenceById(int id) {
        return competenceRepository.findById(id);
    }

    public Competences saveCompetence(Competences competence) {
        return competenceRepository.save(competence);
    }

    public void deleteCompetence(int id) {
        competenceRepository.deleteById(id);
    }
}