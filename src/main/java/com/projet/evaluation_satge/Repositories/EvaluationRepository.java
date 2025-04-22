package com.projet.evaluation_satge.Repositories;

import com.projet.evaluation_satge.Entities.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationRepository extends JpaRepository<Evaluation,Integer> {
}
