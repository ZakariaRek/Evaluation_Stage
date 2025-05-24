package com.projet.evaluation_satge.Mappers;

import com.projet.evaluation_satge.DTO.response.*;
import com.projet.evaluation_satge.Entities.*;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DTOMapper {

    // Tuteur mapper
    public TuteurDTO toTuteurDTO(Tuteur tuteur) {
        if (tuteur == null) return null;

        return new TuteurDTO(
                tuteur.getId(),
                tuteur.getCin(),
                tuteur.getNom(),
                tuteur.getPrenom(),
                tuteur.getEmail()
        );
    }

    // Stagiaire mapper
    public StagiaireDTO toStagiaireDTO(Stagiaire stagiaire) {
        if (stagiaire == null) return null;

        return new StagiaireDTO(
                stagiaire.getId(),
                stagiaire.getCin(),
                stagiaire.getNom(),
                stagiaire.getPrenom(),
                stagiaire.getEmail()
        );
    }

    // Stage mapper
    public StageDTO toStageDTO(Stage stage) {
        if (stage == null) return null;

        return new StageDTO(
                stage.getId(),
                stage.getDescription(),
                stage.getObjectif(),
                stage.getEntreprise()
        );
    }

    // Periode mapper
    public PeriodeDTO toPeriodeDTO(Periode periode) {
        if (periode == null) return null;

        return new PeriodeDTO(
                periode.getStagiaireId(),
                periode.getStageId(),
                periode.getDate_debut(),
                periode.getDate_fin(),
                toStagiaireDTO(periode.getStagiaire()),
                toStageDTO(periode.getStage())
        );
    }

    // Category mapper
    public AppreciationDetailDTO.CategoryDTO toCategoryDTO(Category category) {
        if (category == null) return null;

        return new AppreciationDetailDTO.CategoryDTO(
                category.getId(),
                category.getIntitule(),
                category.getValeur()
        );
    }

    // Competences mapper
    public AppreciationDetailDTO.CompetencesDTO toCompetencesDTO(Competences competences) {
        if (competences == null) return null;

        List<AppreciationDetailDTO.CategoryDTO> categoryDTOs = competences.getCategories() != null ?
                competences.getCategories().stream()
                        .map(this::toCategoryDTO)
                        .collect(Collectors.toList()):
                Collections.emptyList();

        return new AppreciationDetailDTO.CompetencesDTO(
                competences.getId(),
                competences.getIntitule(),
                competences.getNote(),
                categoryDTOs
        );
    }

    // Evaluation mapper
    public EvaluationDTO toEvaluationDTO(Evaluation evaluation) {
        if (evaluation == null) return null;

        return new EvaluationDTO(
                evaluation.getId(),
                evaluation.getCategorie(),
                evaluation.getValeur()
        );
    }

    // Appreciation mapper
    public AppreciationDTO toAppreciationDTO(Appreciation appreciation) {
        if (appreciation == null) return null;

        List<EvaluationDTO> evaluationDTOs = appreciation.getEvaluations() != null ?
                appreciation.getEvaluations().stream()
                        .map(this::toEvaluationDTO)
                        .collect(Collectors.toList()) :
                Collections.emptyList();

        List<AppreciationDetailDTO.CompetencesDTO> competencesDTOs = appreciation.getCompetences() != null ?
                appreciation.getCompetences().stream()
                        .map(this::toCompetencesDTO)
                        .collect(Collectors.toList()) :
                Collections.emptyList();

        return new AppreciationDTO(
                appreciation.getId().getPeriodeStagiaireId(),
                appreciation.getId().getPeriodeStageId(),
                appreciation.getId().getTuteurId(),
                toTuteurDTO(appreciation.getTuteur()),
                toPeriodeDTO(appreciation.getPeriode()),
                evaluationDTOs,
                competencesDTOs
        );
    }

    // Comprehensive AppreciationDetail mapper that includes all related information
    public AppreciationDetailDTO toAppreciationDetailDTO(Appreciation appreciation) {
        if (appreciation == null) return null;

        List<EvaluationDTO> evaluationDTOs = appreciation.getEvaluations() != null ?
                appreciation.getEvaluations().stream()
                        .map(this::toEvaluationDTO)
                        .collect(Collectors.toList()) :
                Collections.emptyList();

        List<AppreciationDetailDTO.CompetencesDTO> competencesDTOs = appreciation.getCompetences() != null ?
                appreciation.getCompetences().stream()
                        .map(this::toCompetencesDTO)
                        .collect(Collectors.toList()) :
                Collections.emptyList();

        Periode periode = appreciation.getPeriode();

        return new AppreciationDetailDTO(
                appreciation.getId().getPeriodeStagiaireId(),
                appreciation.getId().getPeriodeStageId(),
                appreciation.getId().getTuteurId(),
                toTuteurDTO(appreciation.getTuteur()),
                periode != null ? toStagiaireDTO(periode.getStagiaire()) : null,
                periode != null ? toStageDTO(periode.getStage()) : null,
                toPeriodeDTO(periode),
                evaluationDTOs,
                competencesDTOs
        );
    }

    // Convert list of entities to list of DTOs
    public List<AppreciationDTO> toAppreciationDTOList(List<Appreciation> appreciations) {
        if (appreciations == null) return Collections.emptyList();

        return appreciations.stream()
                .map(this::toAppreciationDTO)
                .collect(Collectors.toList());
    }

    public List<AppreciationDetailDTO> toAppreciationDetailDTOList(List<Appreciation> appreciations) {
        if (appreciations == null) return Collections.emptyList();

        return appreciations.stream()
                .map(this::toAppreciationDetailDTO)
                .collect(Collectors.toList());
    }
}