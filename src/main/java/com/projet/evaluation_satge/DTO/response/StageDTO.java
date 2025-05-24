package com.projet.evaluation_satge.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StageDTO {
    private int id;
    private String description;
    private String objectif;
    private String entreprise;
}
