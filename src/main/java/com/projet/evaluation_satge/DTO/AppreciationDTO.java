package com.projet.evaluation_satge.DTO;

import com.projet.evaluation_satge.Entities.Stage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppreciationDTO {
    private int periodeId;
    private int tuteurId;
    private List<EvaluationDTO> evaluations;
    private List<CompetenceDTO> competences;
}
