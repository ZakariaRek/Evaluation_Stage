package com.projet.evaluation_satge.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Periode {

    @EmbeddedId
    private Periode_Id Periode_Id;
    private String date_debut;
    private String date_fin;
    @ManyToOne
    @JoinColumn(name = "stagiaire_id", insertable = false, updatable = false)
    private Stagiaire stagiaire;

    @ManyToOne
    @JoinColumn(name = "stage_id", insertable = false, updatable = false)
    private Stage stage;

    @OneToMany(mappedBy = "periode")
    private List<Appreciation> appreciations;

}
