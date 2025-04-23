package com.projet.evaluation_satge.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeriodeDTO {

    private  int stagiaireId;
    private int  stageId;
    private String   date_debut;
    private String   date_fin;


}
