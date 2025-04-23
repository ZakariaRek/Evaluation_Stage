package com.projet.evaluation_satge.Services;

import com.projet.evaluation_satge.Entities.Evaluation;
import com.projet.evaluation_satge.Repositories.EvaluationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluationService {

    @Autowired
    private EvaluationRepository evaluationRepository;

    public List<Evaluation> getAllEvaluations() {
        return evaluationRepository.findAll();
    }

    public Optional<Evaluation> getEvaluationById(int id) {
        return evaluationRepository.findById(id);
    }

    public Evaluation saveEvaluation(Evaluation evaluation) {
        return evaluationRepository.save(evaluation);
    }
    public List<Evaluation>  saveAllEvaluations(List<Evaluation> evaluation) {
        return  evaluationRepository.saveAll(evaluation);
    }

    public void deleteEvaluation(int id) {
        evaluationRepository.deleteById(id);
    }
}