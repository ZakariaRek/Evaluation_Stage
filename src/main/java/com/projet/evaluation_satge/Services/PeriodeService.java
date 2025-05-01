package com.projet.evaluation_satge.Services;

import com.projet.evaluation_satge.Entities.Periode;
import com.projet.evaluation_satge.Entities.Periode_Id;
import com.projet.evaluation_satge.Entities.Stage;
import com.projet.evaluation_satge.Entities.Stagiaire;
import com.projet.evaluation_satge.Repositories.PeriodeRepository;
import com.projet.evaluation_satge.Repositories.StageRepository;
import com.projet.evaluation_satge.Repositories.StagiaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class PeriodeService {

    @Autowired
    private PeriodeRepository periodeRepository;

    @Autowired
    private StagiaireRepository stagiaireRepository;

    @Autowired
    private StageRepository stageRepository;

    /**
     * Get all periods
     */
    public List<Periode> getAllPeriodes() {
        return periodeRepository.findAll();
    }

    /**
     * Get period by ID
     */
    public Optional<Periode> getPeriodeById(Periode_Id id) {
        return periodeRepository.findById(id);
    }

    /**
     * Get periods by stagiaire ID
     */
    public List<Periode> getPeriodesByStagiaireId(int stagiaireId) {
        // Updated to use the corrected repository method
        return periodeRepository.findById_StagiaireId(stagiaireId);
    }

    /**
     * Get periods by stage ID
     */
    public List<Periode> getPeriodesByStageId(int stageId) {
        // Updated to use the corrected repository method
        return periodeRepository.findById_StageId(stageId);
    }

    /**
     * Get periods by both stagiaire ID and stage ID
     */
    public Optional<Periode> getPeriodeByStagiaireIdAndStageId(int stagiaireId, int stageId) {
        Periode_Id id = new Periode_Id(stagiaireId, stageId);
        return periodeRepository.findById(id);
    }

    /**
     * Get active periods for a stagiaire
     */
    public List<Periode> getActivePeriodesByStagiaireId(int stagiaireId) {
        String currentDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
        return periodeRepository.findActivePeriodsByStagiaireId(stagiaireId, currentDate);
    }


    @Transactional
    public Periode savePeriode(Periode periode) {
        // Verify that the stagiaire exists
        Optional<Stagiaire> stagiaire = stagiaireRepository.findById(periode.getStagiaireId());
        if (stagiaire.isEmpty()) {
            throw new IllegalArgumentException("Stagiaire with ID " + periode.getStagiaireId() + " not found");
        }

        // Verify that the stage exists
        Optional<Stage> stage = stageRepository.findById(periode.getStageId());
        if (stage.isEmpty()) {
            throw new IllegalArgumentException("Stage with ID " + periode.getStageId() + " not found");
        }

        // Validate dates
        if (periode.getDate_debut() == null || periode.getDate_fin() == null) {
            throw new IllegalArgumentException("Start date and end date must be provided");
        }

        try {
            LocalDate startDate = LocalDate.parse(periode.getDate_debut());
            LocalDate endDate = LocalDate.parse(periode.getDate_fin());

            if (endDate.isBefore(startDate)) {
                throw new IllegalArgumentException("End date cannot be before start date");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid date format. Use ISO format (YYYY-MM-DD)");
        }

        return periodeRepository.save(periode);
    }

    /**
     * Delete a period
     */
    public void deletePeriode(Periode_Id id) {
        periodeRepository.deleteById(id);
    }


    public boolean areDatesValid(String newStartDate, String newEndDate) {
        try {
            LocalDate startDate = LocalDate.parse(newStartDate);
            LocalDate endDate = LocalDate.parse(newEndDate);

            return !endDate.isBefore(startDate);
        } catch (Exception e) {
            return false;
        }
    }
}