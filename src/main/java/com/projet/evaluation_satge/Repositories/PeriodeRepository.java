package com.projet.evaluation_satge.Repositories;

import com.projet.evaluation_satge.Entities.Periode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PeriodeRepository extends JpaRepository<Periode, Integer> {

    // Find periods by stagiaire ID
    List<Periode> findByStagiaireId(int stagiaireId);

    // Find periods by stage ID
    List<Periode> findByStageId(int stageId);

    // Find periods by both stagiaire ID and stage ID
    List<Periode> findByStagiaireIdAndStageId(int stagiaireId, int stageId);

    // Find active periods (where current date is between date_debut and date_fin)
    @Query("SELECT p FROM Periode p WHERE p.stagiaireId = :stagiaireId AND :currentDate BETWEEN p.date_debut AND p.date_fin")
    List<Periode> findActivePeriodsByStagiaireId(@Param("stagiaireId") int stagiaireId, @Param("currentDate") String currentDate);
}