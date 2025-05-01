package com.projet.evaluation_satge.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Appreciation {
    @EmbeddedId
    private Appreciation_Id id;

    @ManyToOne
    @JoinColumn(name = "tuteur_id", insertable = false, updatable = false)
//    @JsonIdentityReference(alwaysAsId = true)
    private Tuteur tuteur;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "periode_stagiaire_id", referencedColumnName = "stagiaire_id", insertable = false, updatable = false),
            @JoinColumn(name = "periode_stage_id", referencedColumnName = "stage_id", insertable = false, updatable = false)
    })
    @JsonIdentityReference(alwaysAsId = true)
    private Periode periode;

    @OneToMany(mappedBy = "appreciation", cascade = CascadeType.ALL)
    private List<Evaluation> evaluations;

    @OneToMany(mappedBy = "appreciation", cascade = CascadeType.ALL)
    private List<Competences> competences;
}