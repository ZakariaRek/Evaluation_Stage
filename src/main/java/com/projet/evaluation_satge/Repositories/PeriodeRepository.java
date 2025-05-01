package com.projet.evaluation_satge.Repositories;

import com.projet.evaluation_satge.Entities.Periode;
import com.projet.evaluation_satge.Entities.Periode_Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PeriodeRepository extends JpaRepository<Periode, Periode_Id> {

    // Find periods by stagiaire ID
    List<Periode> findByIdStagiaireId(int stagiaireId);

    // Find periods by stage ID
    List<Periode> findByIdStageId(int stageId);

    // Find active periods (where current date is between date_debut and date_fin)
    @Query("SELECT p FROM Periode p WHERE p.id.stagiaireId = :stagiaireId AND :currentDate BETWEEN p.date_debut AND p.date_fin")
    List<Periode> findActivePeriodsByStagiaireId(@Param("stagiaireId") int stagiaireId, @Param("currentDate") String currentDate);

    List<Periode> findById_StageId(int idStageId);

    List<Periode> findById_StagiaireId(int idStagiaireId);
}