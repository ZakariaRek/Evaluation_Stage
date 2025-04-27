package com.projet.evaluation_satge.Repositories;

import com.projet.evaluation_satge.Entities.Stagiaire;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StagiaireRepository extends JpaRepository<Stagiaire, Integer> {

    Boolean existsByCin(String cin);

    Stagiaire findByCin(String cin);
}
