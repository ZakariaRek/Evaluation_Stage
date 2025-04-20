package com.projet.evaluation_satge.Entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Stagiaire  extends Persone {

    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String institution;
    private String niveau;

    @OneToMany(mappedBy = "stagiaire")
    private List<Periode> periodes;
}
