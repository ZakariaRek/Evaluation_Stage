package com.projet.evaluation_satge.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.projet.evaluation_satge.Entities.Enum.Competence_Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Competences {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private Competence_Type intitule;

    private double note;

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
    @JsonBackReference("appreciation-competences")
    private Appreciation appreciation;

    @OneToMany(mappedBy = "competences")
    @JsonManagedReference("competences-categories")
    private List<Category> categories;

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