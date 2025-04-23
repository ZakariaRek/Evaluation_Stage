package com.projet.evaluation_satge.Services;

import com.projet.evaluation_satge.Entities.Appreciation;
import com.projet.evaluation_satge.Entities.Appreciation_Id;
import com.projet.evaluation_satge.Repositories.AppreciationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AppreciationService {

    @Autowired
    private  AppreciationRepository appreciationRepository;

    /// /                             gets              ////////////////////////////////////
     public List<Appreciation> getAllAppreciations() {
        return appreciationRepository.findAll();
    }

     public Optional<Appreciation> getAppreciationById(Appreciation_Id id) {
        return appreciationRepository.findById(id);
    }

     public List<Appreciation> getAppreciationsByTuteurId(int tuteurId) {
        return appreciationRepository.findByIdTuteurId(tuteurId);
    }

     public List<Appreciation> getAppreciationsByPeriodeId(int periodeId) {
        return appreciationRepository.findByIdPeriodeId(periodeId);
    }


    /// /                             saves              ////////////////////////////////////

     public Appreciation saveAppreciation(Appreciation appreciation) {
        return appreciationRepository.save(appreciation);
    }

    /// /                             updates              ////////////////////////////////////
     public void deleteAppreciation(Appreciation_Id id) {
        appreciationRepository.deleteById(id);
    }

     public boolean existsAppreciationById(Appreciation_Id id) {
        return appreciationRepository.existsById(id);
    }
}