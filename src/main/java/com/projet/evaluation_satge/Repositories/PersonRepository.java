package com.projet.evaluation_satge.Repositories;

import com.projet.evaluation_satge.Entities.Persone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Persone, Integer> {
}
