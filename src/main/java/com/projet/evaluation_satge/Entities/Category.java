package com.projet.evaluation_satge.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String intitule;
    private String valeur;

    @ManyToOne
    @JoinColumn(name = "competence_id", insertable = false, updatable = false)
    private Competences competences;
}
