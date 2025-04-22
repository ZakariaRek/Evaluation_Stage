package com.projet.evaluation_satge.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Periode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "stagiaire_id")
    private int stagiaireId;

    @Column(name = "stage_id")
    private int stageId;


    private String date_debut;
    private String date_fin;
    @OneToMany(mappedBy = "periode", cascade = CascadeType.ALL)

    private List<Appreciation> appreciations;

    @ManyToOne
    @JoinColumn(name = "stagiaire_id", insertable = false, updatable = false)
    @JsonIdentityReference(alwaysAsId = true)
    private Stagiaire stagiaire;

    @ManyToOne
    @JoinColumn(name = "stage_id", insertable = false, updatable = false)
    @JsonIdentityReference(alwaysAsId = true)
    private Stage stage;
}