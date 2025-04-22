package com.projet.evaluation_satge.Repositories;

import com.projet.evaluation_satge.Entities.Periode;
import com.projet.evaluation_satge.Entities.Periode_Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeriodeRepository extends JpaRepository<Periode, Periode_Id> {
}
