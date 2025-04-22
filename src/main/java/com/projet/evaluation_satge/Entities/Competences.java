package com.projet.evaluation_satge.Entities;

import com.projet.evaluation_satge.Entities.Enum.Competence_Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Competences {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id ;
    @Enumerated(EnumType.STRING)
    private Competence_Type intitule;
    private double note;

    @ManyToOne
    @JoinColumn(name = "appreciation_id", insertable = false, updatable = false)
    private Appreciation appreciation;

    @OneToMany(mappedBy = "competences")
    private List<Category> categories;
}
