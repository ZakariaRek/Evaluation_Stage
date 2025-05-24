package com.projet.evaluation_satge.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PeriodeDTO {
    private int stagiaireId;
    private int stageId;
    private String dateDebut;
    private String dateFin;
    private StagiaireDTO stagiaire;
    private StageDTO stage;
}
