package com.projet.evaluation_satge.DTO.response;

import com.projet.evaluation_satge.Entities.Enum.Competence_Type;
import com.projet.evaluation_satge.Entities.Enum.Evaluation_Competence;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppreciationDetailDTO {
    private int periodeStagiaireId;
    private int periodeStageId;
    private int tuteurId;

    // Detailed information
    private TuteurDTO tuteur;
    private StagiaireDTO stagiaire;
    private StageDTO stage;
    private PeriodeDTO periode;
    private List<EvaluationDTO> evaluations;
    private List<CompetencesDTO> competences;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CategoryDTO {
        private int id;
        private String intitule;
        private Evaluation_Competence valeur;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CompetencesDTO {
        private int id;
        private Competence_Type intitule;
        private double note;
        private List<CategoryDTO> categories;
    }
}
