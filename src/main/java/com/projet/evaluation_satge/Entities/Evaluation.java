package com.projet.evaluation_satge.Entities;


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

public class Evaluation
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Enumerated(EnumType.STRING)
    private Evaluation_Category categorie;

    @Enumerated(EnumType.STRING)
    private Evaluation_Value valeur;

    @ManyToOne
    @JoinColumn(name = "appreciation_id", insertable = false, updatable = false)
    private Appreciation appreciation;
}
