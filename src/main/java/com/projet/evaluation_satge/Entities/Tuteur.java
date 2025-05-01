package com.projet.evaluation_satge.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Tuteur extends Persone {

    private String Entreprise;

    private String fonction;
    private String technos;

    @OneToMany(mappedBy = "tuteur")
    @JsonIgnore
    private List<Appreciation> appreciations;




//    @OneToMany(mappedBy = "tuteur")
//    private List<Stagiaire> stagiaires;
}
