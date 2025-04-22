package com.projet.evaluation_satge.Repositories;

import com.projet.evaluation_satge.Entities.Stage;
import com.projet.evaluation_satge.Entities.Stagiaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StageRepository extends JpaRepository<Stage, Integer> {
}
