package com.projet.evaluation_satge.Controllers;

import com.projet.evaluation_satge.DTO.CreatePeriodeRequest;
import com.projet.evaluation_satge.DTO.UpdatePeriodeRequest;
import com.projet.evaluation_satge.Entities.Periode;
import com.projet.evaluation_satge.Entities.Periode_Id;
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
     * Get period by stagiaire ID and stage ID
     */
    @GetMapping("/{stagiaireId}/{stageId}")
    public ResponseEntity<Periode> getPeriodeById(
            @PathVariable int stagiaireId,
            @PathVariable int stageId) {
        Periode_Id id = new Periode_Id(stagiaireId, stageId);
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
            periode.setId(new Periode_Id(request.getStagiaireId(), request.getStageId()));
            periode.setDate_debut(request.getDate_debut());
            periode.setDate_fin(request.getDate_fin());

            // Check if the period already exists
            Optional<Periode> existingPeriode = periodeService.getPeriodeByStagiaireIdAndStageId(
                    request.getStagiaireId(), request.getStageId());
            if (existingPeriode.isPresent()) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "A period for this stagiaire and stage already exists");
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            }

            // Validate dates
            if (!periodeService.areDatesValid(request.getDate_debut(), request.getDate_fin())) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Invalid dates. End date cannot be before start date or dates are in invalid format");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
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
    @PutMapping("/{stagiaireId}/{stageId}")
    public ResponseEntity<?> updatePeriode(
            @PathVariable int stagiaireId,
            @PathVariable int stageId,
            @RequestBody UpdatePeriodeRequest request) {
        try {
            // Check if the period exists
            Optional<Periode> existingPeriodeOpt = periodeService.getPeriodeByStagiaireIdAndStageId(
                    stagiaireId, stageId);

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

            // Validate dates
            if (!periodeService.areDatesValid(existingPeriode.getDate_debut(), existingPeriode.getDate_fin())) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Invalid dates. End date cannot be before start date or dates are in invalid format");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
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
    @DeleteMapping("/{stagiaireId}/{stageId}")
    public ResponseEntity<Void> deletePeriode(
            @PathVariable int stagiaireId,
            @PathVariable int stageId) {

        Periode_Id id = new Periode_Id(stagiaireId, stageId);
        Optional<Periode> existingPeriode = periodeService.getPeriodeById(id);

        if (existingPeriode.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        periodeService.deletePeriode(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}