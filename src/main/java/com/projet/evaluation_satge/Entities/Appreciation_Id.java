package com.projet.evaluation_satge.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"periode_stagiaire_id", "periode_stage_id"})
})
public class Appreciation_Id implements Serializable {
    @Column(name = "periode_stagiaire_id")
    private int periodeStagiaireId;  // Matches actual field purpose for stagiaire ID

    @Column(name = "periode_stage_id")
    private int periodeStageId;      // Matches actual field purpose for stage ID

    @Column(name = "tuteur_id")
    private int tuteurId;

    // Constructor for creating from Periode_Id and tuteurId
    public Appreciation_Id(Periode_Id periodeId, int tuteurId) {
        this.periodeStagiaireId = periodeId.getStagiaireId();
        this.periodeStageId = periodeId.getStageId();
        this.tuteurId = tuteurId;
    }
}