package com.projet.evaluation_satge.Entities;


import com.projet.evaluation_satge.Entities.Enum.Competence_Category;
import com.projet.evaluation_satge.Entities.Enum.Evaluation_Category;
import com.projet.evaluation_satge.Entities.Enum.Evaluation_Competence;
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
    @Enumerated(EnumType.STRING)
    private Competence_Category intitule;
    @Enumerated(EnumType.STRING)
    private Evaluation_Competence valeur;

    @ManyToOne
    @JoinColumn(name = "competence_id", insertable = false, updatable = false)
    private Competences competences;
}
