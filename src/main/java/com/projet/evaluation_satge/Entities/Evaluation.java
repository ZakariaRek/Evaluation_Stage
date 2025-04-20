package com.projet.evaluation_satge.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Evaluation
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String categorie;

    private String valeur;

    @ManyToOne
    @JoinColumn(name = "appreciation_id", insertable = false, updatable = false)
    private Appreciation appreciation;
}
