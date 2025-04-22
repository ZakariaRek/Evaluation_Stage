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
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EmbeddedId
    private Appreciation_Id id;

    @ManyToOne
    @JoinColumn(name = "tuteur_id", insertable = false, updatable = false)
    @JsonIdentityReference(alwaysAsId = true)
    private Tuteur tuteur;


    @ManyToOne
    @JoinColumn(name = "periode_id", insertable = false, updatable = false)
//    @JsonIdentityReference(alwaysAsId = true)
    private Periode periode;

    @OneToMany(mappedBy = "appreciation")
    private List<Evaluation> evaluation;

    @OneToMany(mappedBy = "appreciation")
    private List<Competences> competences;
}