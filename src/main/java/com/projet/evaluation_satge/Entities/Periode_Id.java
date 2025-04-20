package com.projet.evaluation_satge.Entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Periode_Id {

    private int  Stagiare_Id;
    private int  Stage_Id;


}
