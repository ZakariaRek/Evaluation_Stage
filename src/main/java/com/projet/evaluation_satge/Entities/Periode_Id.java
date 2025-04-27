package com.projet.evaluation_satge.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable

public class Periode_Id {
    @Column(name = "stagiaire_id")
    private int stagiaireId;

    @Column(name = "stage_id")
    private int stageId;

}
