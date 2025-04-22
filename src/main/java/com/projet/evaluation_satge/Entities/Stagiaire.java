package com.projet.evaluation_satge.Entities;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")

public class Stagiaire  extends Persone {

    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String institution;
    private String niveau;
    @OneToMany(mappedBy = "stagiaire")
    @JsonIdentityReference(alwaysAsId = true)
    private List<Periode> periodes;
}
