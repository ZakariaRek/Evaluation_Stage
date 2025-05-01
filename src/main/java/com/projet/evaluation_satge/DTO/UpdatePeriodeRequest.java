package com.projet.evaluation_satge.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public  class UpdatePeriodeRequest {
    private String date_debut;
    private String date_fin;
}