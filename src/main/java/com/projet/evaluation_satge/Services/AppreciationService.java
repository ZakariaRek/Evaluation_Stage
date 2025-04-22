package com.projet.evaluation_satge.Services;

import com.projet.evaluation_satge.Entities.Appreciation;
import com.projet.evaluation_satge.Repositories.AppreciationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppreciationService {

    @Autowired
    private AppreciationRepository appreciationRepository;

    public List<Appreciation> getAllAppreciations() {
        return appreciationRepository.findAll();
    }

    public Optional<Appreciation> getAppreciationById(int id) {
        return appreciationRepository.findById(id);
    }

    public Appreciation saveAppreciation(Appreciation appreciation) {
        return appreciationRepository.save(appreciation);
    }

    public void deleteAppreciation(int id) {
        appreciationRepository.deleteById(id);
    }
}