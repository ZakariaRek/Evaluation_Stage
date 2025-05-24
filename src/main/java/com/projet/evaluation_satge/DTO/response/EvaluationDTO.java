package com.projet.evaluation_satge.DTO.response;

import com.projet.evaluation_satge.Entities.Enum.Evaluation_Category;
import com.projet.evaluation_satge.Entities.Enum.Evaluation_Value;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EvaluationDTO {
    private int id;
    private Evaluation_Category categorie;
    private Evaluation_Value valeur;
}
