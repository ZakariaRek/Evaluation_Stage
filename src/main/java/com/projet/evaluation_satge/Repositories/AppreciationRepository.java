package com.projet.evaluation_satge.Repositories;

import com.projet.evaluation_satge.Entities.Appreciation;
import com.projet.evaluation_satge.Entities.Appreciation_Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppreciationRepository extends JpaRepository<Appreciation, Appreciation_Id> {
    List<Appreciation> findByIdTuteurId(int tuteurId);
    List<Appreciation> findByIdPeriodeId(int periodeId);
}