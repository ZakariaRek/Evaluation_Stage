package com.projet.evaluation_satge.Entities;

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
public class Tuteur extends Persone {

    private String Entreprise;

    private String fonction;
    private String technos;

    @OneToMany(mappedBy = "tuteur")
    private List<Appreciation> appreciations;




//    @OneToMany(mappedBy = "tuteur")
//    private List<Stagiaire> stagiaires;
}
