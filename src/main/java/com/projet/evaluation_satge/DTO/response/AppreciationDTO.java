package com.projet.evaluation_satge.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppreciationDTO {
    private int periodeStagiaireId;
    private int periodeStageId;
    private int tuteurId;
    private TuteurDTO tuteur;
    private PeriodeDTO periode;
    private List<EvaluationDTO> evaluations;
    private List<AppreciationDetailDTO.CompetencesDTO> competences;
}
