package com.projet.evaluation_satge.Repositories;

import com.projet.evaluation_satge.Entities.Appreciation;
import com.projet.evaluation_satge.Entities.Appreciation_Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppreciationRepository extends JpaRepository<Appreciation, Appreciation_Id> {
    // Find by tuteur ID
    List<Appreciation> findById_TuteurId(int tuteurId);

    // Find by periode's stagiaire ID
    List<Appreciation> findById_PeriodeStagiaireId(int stagiaireId);

    // Find by periode's stage ID
    List<Appreciation> findById_PeriodeStageId(int stageId);

    // Find by both periode's stagiaire ID and stage ID
    List<Appreciation> findById_PeriodeStagiaireIdAndId_PeriodeStageId(int stagiaireId, int stageId);
}