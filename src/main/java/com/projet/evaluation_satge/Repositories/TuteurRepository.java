package com.projet.evaluation_satge.Repositories;

import com.projet.evaluation_satge.Entities.Tuteur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TuteurRepository extends JpaRepository<Tuteur, Integer> {
    Boolean existsByCin(String cin);

    Tuteur findByCin(String cin);
}
