package com.projet.evaluation_satge.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appreciation  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;

    @ManyToOne
    @JoinColumn(name = "tuteur_id", insertable = false, updatable = false)
    private Tuteur tuteur;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "stagiaire_id", insertable = false, updatable = false),
            @JoinColumn(name = "stage_id", insertable = false, updatable = false)
    })
    private Periode periode;

    @OneToMany(mappedBy = "appreciation")
    private List<Evaluation> evaluation;

    @OneToMany(mappedBy = "appreciation")
    private List<Competences>  competences;
}
