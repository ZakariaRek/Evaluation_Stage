package com.projet.evaluation_satge.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.projet.evaluation_satge.Entities.Enum.Evaluation_Category;
import com.projet.evaluation_satge.Entities.Enum.Evaluation_Value;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Evaluation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Enumerated(EnumType.STRING)
    private Evaluation_Category categorie;

    @Enumerated(EnumType.STRING)
    private Evaluation_Value valeur;

    @Column(name = "appreciation_periode_stagiaire_id")
    private int appreciationPeriodeStagiaireId;

    @Column(name = "appreciation_periode_stage_id")
    private int appreciationPeriodeStageId;

    @Column(name = "appreciation_tuteur_id")
    private int appreciationTuteurId;
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "appreciation_periode_stagiaire_id", referencedColumnName = "periode_stagiaire_id", insertable = false, updatable = false),
            @JoinColumn(name = "appreciation_periode_stage_id", referencedColumnName = "periode_stage_id", insertable = false, updatable = false),
            @JoinColumn(name = "appreciation_tuteur_id", referencedColumnName = "tuteur_id", insertable = false, updatable = false)
    })
    @JsonIdentityReference(alwaysAsId = true)

    private Appreciation appreciation;


    @Transient
    private Appreciation_Id appreciationId;

    // Convenience method to set the appreciation ID explicitly
    public void setAppreciationId(Appreciation_Id appreciationId) {
        if (appreciationId != null) {
            this.appreciationPeriodeStagiaireId = appreciationId.getPeriodeStagiaireId();
            this.appreciationPeriodeStageId = appreciationId.getPeriodeStageId();
            this.appreciationTuteurId = appreciationId.getTuteurId();
        }
}
}