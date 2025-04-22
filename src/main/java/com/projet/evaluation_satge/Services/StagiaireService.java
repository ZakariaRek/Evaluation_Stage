package com.projet.evaluation_satge.Services;

import com.projet.evaluation_satge.Entities.Stagiaire;
import com.projet.evaluation_satge.Repositories.StagiaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StagiaireService {

    @Autowired
    private StagiaireRepository stagiaireRepository;

    public List<Stagiaire> getAllStagiaires() {
        return stagiaireRepository.findAll();
    }

    public Optional<Stagiaire> getStagiaireById(int id) {
        return stagiaireRepository.findById(id);
    }

    public Stagiaire saveStagiaire(Stagiaire stagiaire) {
        return stagiaireRepository.save(stagiaire);
    }

    public void deleteStagiaire(int id) {
        stagiaireRepository.deleteById(id);
    }
}