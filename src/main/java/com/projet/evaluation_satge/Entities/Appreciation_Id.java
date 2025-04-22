package com.projet.evaluation_satge.Entities;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Appreciation_Id {

    private int  Periode_Id;
    private int  Tuteur_Id;


}
