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


//    @Column(name = "tuteur_id")
//    private int tuteur_Id;
//    @Column(name = "periode_id")
//    private int periode_Id;
//

    @ManyToOne
    @JoinColumn(name = "tuteur_id", insertable = false, updatable = false)
    @JsonIdentityReference(alwaysAsId = true)
    private Tuteur tuteur;

    @ManyToOne
    @JoinColumn(name = "periode_id", insertable = false, updatable = false)
    private Periode periode;

    @OneToMany(mappedBy = "appreciation", cascade = CascadeType.ALL)
    private List<Evaluation> evaluations;

    @OneToMany(mappedBy = "appreciation", cascade = CascadeType.ALL)
    private List<Competences> competences;
}