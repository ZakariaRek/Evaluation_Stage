package com.projet.evaluation_satge.Services;

import com.projet.evaluation_satge.Entities.Periode;
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
    public Optional<Periode> getPeriodeById(int id) {
        return periodeRepository.findById(id);
    }

    /**
     * Get periods by stagiaire ID
     */
    public List<Periode> getPeriodesByStagiaireId(int stagiaireId) {
        return periodeRepository.findByStagiaireId(stagiaireId);
    }

    /**
     * Get periods by stage ID
     */
    public List<Periode> getPeriodesByStageId(int stageId) {
        return periodeRepository.findByStageId(stageId);
    }

    /**
     * Get periods by both stagiaire ID and stage ID
     */
    public List<Periode> getPeriodesByStagiaireIdAndStageId(int stagiaireId, int stageId) {
        return periodeRepository.findByStagiaireIdAndStageId(stagiaireId, stageId);
    }

    /**
     * Get active periods for a stagiaire
     */
    public List<Periode> getActivePeriodesByStagiaireId(int stagiaireId) {
        String currentDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
        return periodeRepository.findActivePeriodsByStagiaireId(stagiaireId, currentDate);
    }

    /**
     * Save a period
     */
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
    public void deletePeriode(int id) {
        periodeRepository.deleteById(id);
    }

    /**
     * Check if periods overlap
     */
    public boolean periodsOverlap(Periode newPeriode, List<Periode> existingPeriodes) {
        LocalDate newStart = LocalDate.parse(newPeriode.getDate_debut());
        LocalDate newEnd = LocalDate.parse(newPeriode.getDate_fin());

        for (Periode existing : existingPeriodes) {
            LocalDate existingStart = LocalDate.parse(existing.getDate_debut());
            LocalDate existingEnd = LocalDate.parse(existing.getDate_fin());

            // Check if periods overlap
            if (!(newEnd.isBefore(existingStart) || newStart.isAfter(existingEnd))) {
                return true;
            }
        }

        return false;
    }
}