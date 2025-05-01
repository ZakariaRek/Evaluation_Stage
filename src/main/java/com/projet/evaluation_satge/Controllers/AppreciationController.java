package com.projet.evaluation_satge.Controllers;

import com.projet.evaluation_satge.DTO.AppreciationDTO;
import com.projet.evaluation_satge.DTO.CategoryDTO;
import com.projet.evaluation_satge.DTO.CompetenceDTO;
import com.projet.evaluation_satge.DTO.EvaluationDTO;
import com.projet.evaluation_satge.Entities.*;
import com.projet.evaluation_satge.Entities.Enum.Competence_Type;
import com.projet.evaluation_satge.Entities.Enum.Evaluation_Category;
import com.projet.evaluation_satge.Entities.Enum.Evaluation_Competence;
import com.projet.evaluation_satge.Entities.Enum.Evaluation_Value;
import com.projet.evaluation_satge.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Map;

@RestController
@RequestMapping("/api/appreciations")
@CrossOrigin("*")
public class AppreciationController {

    @Autowired
    private AppreciationService appreciationService;

    @Autowired
    private PeriodeService periodeService;

    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    private CompetenceService competencesService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Appreciation>> getAllAppreciations() {
        List<Appreciation> appreciations = appreciationService.getAllAppreciations();
        return new ResponseEntity<>(appreciations, HttpStatus.OK);
    }

    @GetMapping("/{periodeStagiaireId}/{periodeStageId}/{tuteurId}")
    public ResponseEntity<Appreciation> getAppreciationById(
            @PathVariable int periodeStagiaireId,
            @PathVariable int periodeStageId,
            @PathVariable int tuteurId) {

        Appreciation_Id appreciationId = new Appreciation_Id(periodeStagiaireId, periodeStageId, tuteurId);
        Optional<Appreciation> appreciation = appreciationService.getAppreciationById(appreciationId);

        return appreciation
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping
    public ResponseEntity<?> createAppreciation(@RequestBody AppreciationDTO appreciationDTO) {
        try {
            System.out.println("Starting appreciation creation process...");

            // Create Periode_Id first
            Periode_Id periodeId = new Periode_Id(
                    appreciationDTO.getStagiaireId(),
                    appreciationDTO.getStageId()
            );

            // Check if periode exists
            Optional<Periode> periodeOpt = periodeService.getPeriodeById(periodeId);
            if (periodeOpt.isEmpty()) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Periode not found with stagiaireId: " +
                        appreciationDTO.getStagiaireId() + " and stageId: " +
                        appreciationDTO.getStageId());
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }

            // Create appreciation ID using helper method
            Appreciation_Id appreciationId = appreciationService.createAppreciationId(
                    periodeId,
                    appreciationDTO.getTuteurId()
            );

            // Check if appreciation already exists
            if (appreciationService.existsAppreciationById(appreciationId)) {
                Map<String, String> response = new HashMap<>();
                response.put("error", "Appreciation already exists");
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            }

            // Create new appreciation
            Appreciation appreciation = new Appreciation();
            appreciation.setId(appreciationId);

            // Save the appreciation first to get its ID
            appreciation = appreciationService.saveAppreciation(appreciation);
            System.out.println("Appreciation created with ID: " + appreciationId);

            if (appreciationDTO.getEvaluations() != null && !appreciationDTO.getEvaluations().isEmpty()) {
                System.out.println("Processing " + appreciationDTO.getEvaluations().size() + " evaluations");
                List<Evaluation> evaluations = new ArrayList<>();

                for (var evalDTO : appreciationDTO.getEvaluations()) {
                    Evaluation evaluation = new Evaluation();
                    try {
                        evaluation.setCategorie(Evaluation_Category.valueOf(evalDTO.getCategorie()));
                        evaluation.setValeur(Evaluation_Value.valueOf(evalDTO.getValeur()));
                    } catch (IllegalArgumentException e) {
                        System.err.println("Invalid enum value: " + e.getMessage());
                        continue; // Skip this evaluation
                    }

                    // Set the foreign key columns explicitly
                    evaluation.setAppreciationPeriodeStagiaireId(appreciationId.getPeriodeStagiaireId());
                    evaluation.setAppreciationPeriodeStageId(appreciationId.getPeriodeStageId());
                    evaluation.setAppreciationTuteurId(appreciationId.getTuteurId());

                    evaluations.add(evaluation);
                }

                // Save evaluations
                evaluations = evaluationService.saveAllEvaluations(evaluations);
                appreciation.setEvaluations(evaluations);
                System.out.println("Saved " + evaluations.size() + " evaluations");
            }

            // Create and save competences if they exist in the DTO
            if (appreciationDTO.getCompetences() != null && !appreciationDTO.getCompetences().isEmpty()) {
                System.out.println("Processing " + appreciationDTO.getCompetences().size() + " competences");
                List<Competences> competencesList = new ArrayList<>();

                for (var compDTO : appreciationDTO.getCompetences()) {
                    System.out.println("Processing competence: " + compDTO.getIntitule() +
                            " with " + (compDTO.getCategories() != null ?
                            compDTO.getCategories().size() : 0) + " categories");

                    Competences competence = new Competences();
                    try {
                        competence.setIntitule(Competence_Type.valueOf(compDTO.getIntitule()));
                    } catch (IllegalArgumentException e) {
                        System.err.println("Invalid competence type: " + compDTO.getIntitule());
                        continue; // Skip this competence
                    }

                    competence.setNote(compDTO.getNote());

                    // Set the foreign key columns explicitly
                    competence.setAppreciationPeriodeStagiaireId(appreciationId.getPeriodeStagiaireId());
                    competence.setAppreciationPeriodeStageId(appreciationId.getPeriodeStageId());
                    competence.setAppreciationTuteurId(appreciationId.getTuteurId());

                    // Save the competence first to get its ID
                    try {
                        competence = competencesService.saveCompetence(competence);
                        System.out.println("Saved competence with ID: " + competence.getId());
                    } catch (Exception e) {
                        System.err.println("Error saving competence: " + e.getMessage());
                        e.printStackTrace();
                        continue; // Skip categories for this competence
                    }

                    // Handle categories if they exist
                    if (compDTO.getCategories() != null && !compDTO.getCategories().isEmpty()) {
                        processCategoriesForCompetence(competence, compDTO.getCategories());
                    }

                    competencesList.add(competence);
                }

                // Set competences on appreciation
                appreciation.setCompetences(competencesList);
                System.out.println("Saved " + competencesList.size() + " competences");
            }

            // Update appreciation with collections
            appreciation = appreciationService.saveAppreciation(appreciation);
            System.out.println("Appreciation updated successfully");

            return new ResponseEntity<>(appreciation, HttpStatus.CREATED);
        } catch (Exception e) {
            System.err.println("Error in createAppreciation: " + e.getMessage());
            e.printStackTrace();

            Map<String, String> response = new HashMap<>();
            response.put("error", "An error occurred: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    protected void processCategoriesForCompetence(Competences competence, List<CategoryDTO> categoryDTOs) {
        System.out.println("Processing " + categoryDTOs.size() + " categories for competence ID: " + competence.getId());
        List<Category> categories = new ArrayList<>();

        for (var catDTO : categoryDTOs) {
            try {
                Category category = new Category();

                // Set the intitule directly as a string (limit to 100 chars to avoid truncation)
                String intitule = catDTO.getIntitule();
                if (intitule != null && intitule.length() > 100) {
                    intitule = intitule.substring(0, 100);
                }
                category.setIntitule(intitule);

                // Set the valeur enum
                category.setValeur(Evaluation_Competence.valueOf(catDTO.getValeur()));
                category.setCompetenceId(competence.getId());

                // Save category individually to catch any specific errors
                try {
                    Category savedCategory = categoryService.saveCategory(category);
                    categories.add(savedCategory);
                    System.out.println("Category saved: " + savedCategory.getIntitule());
                } catch (Exception e) {
                    System.err.println("Error saving category '" + category.getIntitule() + "': " + e.getMessage());
                    e.printStackTrace();
                }
            } catch (Exception e) {
                System.err.println("Error processing category: " + e.getMessage());
                e.printStackTrace();
            }
        }

        System.out.println("Successfully saved " + categories.size() + " out of " + categoryDTOs.size() + " categories");
    }

    // Add a method in CategoryService to save a single category
    // This assumes you have a method in CategoryService to save a single category
    // If not, you should add it:
    /*
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
    */
}