package com.projet.evaluation_satge.Controllers;

import com.projet.evaluation_satge.Entities.Periode;
import com.projet.evaluation_satge.Services.PeriodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/periodes")
@CrossOrigin("*")
public class PeriodeController {

    @Autowired
    private PeriodeService periodeService;

    /**
     * Get all periods
     */
    @GetMapping
    public ResponseEntity<List<Periode>> getAllPeriodes() {
        List<Periode> periodes = periodeService.getAllPeriodes();
        return new ResponseEntity<>(periodes, HttpStatus.OK);
    }

    /**
     * Get period by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<Periode> getPeriodeById(@PathVariable int id) {
        Optional<Periode> periode = periodeService.getPeriodeById(id);
        return periode.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Get periods by stagiaire ID
     */
    @GetMapping("/stagiaire/{stagiaireId}")
    public ResponseEntity<List<Periode>> getPeriodesByStagiaireId(@PathVariable int stagiaireId) {
        List<Periode> periodes = periodeService.getPeriodesByStagiaireId(stagiaireId);
        return new ResponseEntity<>(periodes, HttpStatus.OK);
    }

    /**
     * Get periods by stage ID
     */
    @GetMapping("/stage/{stageId}")
    public ResponseEntity<List<Periode>> getPeriodesByStageId(@PathVariable int stageId) {
        List<Periode> periodes = periodeService.getPeriodesByStageId(stageId);
        return new ResponseEntity<>(periodes, HttpStatus.OK);
    }

    /**
     * Get active periods for a stagiaire
     */
    @GetMapping("/stagiaire/{stagiaireId}/active")
    public ResponseEntity<List<Periode>> getActivePeriodesByStagiaireId(@PathVariable int stagiaireId) {
        List<Periode> periodes = periodeService.getActivePeriodesByStagiaireId(stagiaireId);
        return new ResponseEntity<>(periodes, HttpStatus.OK);
    }

    /**
     * Create a new period
     */
    @PostMapping
    public ResponseEntity<?> createPeriode(@RequestBody CreatePeriodeRequest request) {
        try {
            // Create a new Periode object
            Periode periode = new Periode();
            periode.setStagiaireId(request.getStagiaireId());
            periode.setStageId(request.getStageId());
            periode.setDate_debut(request.getDate_debut());
            periode.setDate_fin(request.getDate_fin());

            // Check for overlapping periods
            List<Periode> existingPeriodes = periodeService.getPeriodesByStagiaireIdAndStageId(
                    request.getStagiaireId(), request.getStageId());

            if (periodeService.periodsOverlap(periode, existingPeriodes)) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "The new period overlaps with an existing period");
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            }

            // Save the periode
            Periode savedPeriode = periodeService.savePeriode(periode);
            return new ResponseEntity<>(savedPeriode, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "An unexpected error occurred: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Update an existing period
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePeriode(@PathVariable int id, @RequestBody UpdatePeriodeRequest request) {
        try {
            Optional<Periode> existingPeriodeOpt = periodeService.getPeriodeById(id);
            if (existingPeriodeOpt.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            Periode existingPeriode = existingPeriodeOpt.get();

            // Update fields if provided
            if (request.getDate_debut() != null) {
                existingPeriode.setDate_debut(request.getDate_debut());
            }

            if (request.getDate_fin() != null) {
                existingPeriode.setDate_fin(request.getDate_fin());
            }

            // Check for overlapping periods (excluding the current period)
            List<Periode> otherPeriodes = periodeService.getPeriodesByStagiaireIdAndStageId(
                    existingPeriode.getStagiaireId(), existingPeriode.getStageId());

            otherPeriodes.removeIf(p -> p.getId() == id);

            if (periodeService.periodsOverlap(existingPeriode, otherPeriodes)) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "The updated period would overlap with an existing period");
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            }

            // Save the updated periode
            Periode updatedPeriode = periodeService.savePeriode(existingPeriode);
            return new ResponseEntity<>(updatedPeriode, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "An unexpected error occurred: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Delete a period
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePeriode(@PathVariable int id) {
        Optional<Periode> existingPeriode = periodeService.getPeriodeById(id);
        if (existingPeriode.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        periodeService.deletePeriode(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Request class for periode creation
     */
    public static class CreatePeriodeRequest {
        private int stagiaireId;
        private int stageId;
        private String date_debut;
        private String date_fin;

        public int getStagiaireId() {
            return stagiaireId;
        }

        public void setStagiaireId(int stagiaireId) {
            this.stagiaireId = stagiaireId;
        }

        public int getStageId() {
            return stageId;
        }

        public void setStageId(int stageId) {
            this.stageId = stageId;
        }

        public String getDate_debut() {
            return date_debut;
        }

        public void setDate_debut(String date_debut) {
            this.date_debut = date_debut;
        }

        public String getDate_fin() {
            return date_fin;
        }

        public void setDate_fin(String date_fin) {
            this.date_fin = date_fin;
        }
    }

    /**
     * Request class for periode update
     */
    public static class UpdatePeriodeRequest {
        private String date_debut;
        private String date_fin;

        public String getDate_debut() {
            return date_debut;
        }

        public void setDate_debut(String date_debut) {
            this.date_debut = date_debut;
        }

        public String getDate_fin() {
            return date_fin;
        }

        public void setDate_fin(String date_fin) {
            this.date_fin = date_fin;
        }
    }
}