package com.projet.evaluation_satge.Controllers;

import com.projet.evaluation_satge.DTO.AppreciationDTO;
import com.projet.evaluation_satge.DTO.EvaluationDTO;
import com.projet.evaluation_satge.DTO.CompetenceDTO;

import com.projet.evaluation_satge.DTO.CategoryDTO;


import com.projet.evaluation_satge.Entities.*;
import com.projet.evaluation_satge.Entities.Enum.*;
import com.projet.evaluation_satge.Services.AppreciationService;
import com.projet.evaluation_satge.Services.CategoryService;
import com.projet.evaluation_satge.Services.CompetenceService;
import com.projet.evaluation_satge.Services.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/appreciations")
@CrossOrigin("*")
public class AppreciationController {
    @Autowired
    private AppreciationService appreciationService;

    @Autowired
    private EvaluationService evaluationService;
    @Autowired
    private CompetenceService competencesService;
    @Autowired
    private CategoryService categoryService;

    // Updated createAppreciation method for AppreciationController
    @PostMapping
    public ResponseEntity<Appreciation> createAppreciation(@RequestBody AppreciationDTO appreciationDTO) {
        try {
            // Create appreciation ID
            Appreciation_Id appreciationId = new Appreciation_Id(
                    appreciationDTO.getPeriodeId(),
                    appreciationDTO.getTuteurId()
            );

            // Check if appreciation already exists
            if (appreciationService.existsAppreciationById(appreciationId)) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

            // Create new appreciation
            Appreciation appreciation = new Appreciation();
            appreciation.setId(appreciationId);

            // Save the appreciation first to get its ID
            appreciation = appreciationService.saveAppreciation(appreciation);

            // Create and save evaluations if they exist in the DTO
            if (appreciationDTO.getEvaluations() != null && !appreciationDTO.getEvaluations().isEmpty()) {
                List<Evaluation> evaluations = new ArrayList<>();

                for (var evalDTO : appreciationDTO.getEvaluations()) {
                    Evaluation evaluation = new Evaluation();
                    evaluation.setCategorie(Evaluation_Category.valueOf(evalDTO.getCategorie()));
                    evaluation.setValeur(Evaluation_Value.valueOf(evalDTO.getValeur()));

                    // Set the embedded ID
                    evaluation.setAppreciationId(appreciationId);

                    evaluations.add(evaluation);
                }

                // Save evaluations
                evaluations = evaluationService.saveAllEvaluations(evaluations);
                appreciation.setEvaluations(evaluations);
            }

            // Create and save competences if they exist in the DTO
            if (appreciationDTO.getCompetences() != null && !appreciationDTO.getCompetences().isEmpty()) {
                List<Competences> competencesList = new ArrayList<>();

                for (var compDTO : appreciationDTO.getCompetences()) {
                    Competences competence = new Competences();
                    competence.setIntitule(Competence_Type.valueOf(compDTO.getIntitule()));
                    competence.setNote(compDTO.getNote());

                    // Set the embedded ID
                    competence.setAppreciationId(appreciationId);

                    // Handle categories if they exist
                    if (compDTO.getCategories() != null && !compDTO.getCategories().isEmpty()) {
                        // Save the competence first to get its ID
                        competence = competencesService.saveCompetence(competence);

                        List<Category> categories = new ArrayList<>();
                        for (var catDTO : compDTO.getCategories()) {
                            Category category = new Category();
                            category.setIntitule(Competence_Category.valueOf(catDTO.getIntitule()));
                            category.setValeur(Evaluation_Competence.valueOf(catDTO.getValeur()));
                            category.setCompetenceId(competence.getId());
                            categories.add(category);
                        }

                        // Save the categories
                        categories = categoryService.saveAllCategories(categories);
                        competence.setCategories(categories);
                    }

                    competencesList.add(competence);
                }

                // Save all competences
                competencesList = competencesService.saveAllCompetences(competencesList);
                appreciation.setCompetences(competencesList);
            }

            // Update appreciation with collections
            appreciation = appreciationService.saveAppreciation(appreciation);

            return new ResponseEntity<>(appreciation, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace(); // Log the error for debugging
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}