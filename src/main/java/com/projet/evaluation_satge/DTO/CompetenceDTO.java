package com.projet.evaluation_satge.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompetenceDTO {
    private String intitule;
    private double note;
    private List<CategoryDTO> categories;
}