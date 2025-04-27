package com.projet.evaluation_satge.Services;

import com.projet.evaluation_satge.Entities.Appreciation;
import com.projet.evaluation_satge.Entities.Appreciation_Id;
import com.projet.evaluation_satge.Entities.Periode_Id;
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
    private AppreciationRepository appreciationRepository;

    /// /                             gets              ////////////////////////////////////
    public List<Appreciation> getAllAppreciations() {
        return appreciationRepository.findAll();
    }

    public Optional<Appreciation> getAppreciationById(Appreciation_Id id) {
        return appreciationRepository.findById(id);
    }

    public List<Appreciation> getAppreciationsByTuteurId(int tuteurId) {
        return appreciationRepository.findById_TuteurId(tuteurId);
    }

    // New method replacing getAppreciationsByPeriodeId
    public List<Appreciation> getAppreciationsByPeriodeStagiaireAndStageId(int stagiaireId, int stageId) {
        return appreciationRepository.findById_PeriodeStagiaireIdAndId_PeriodeStageId(stagiaireId, stageId);
    }

    // Additional helpful methods
    public List<Appreciation> getAppreciationsByStagiaireId(int stagiaireId) {
        return appreciationRepository.findById_PeriodeStagiaireId(stagiaireId);
    }

    public List<Appreciation> getAppreciationsByStageId(int stageId) {
        return appreciationRepository.findById_PeriodeStageId(stageId);
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

    // Helper method to create Appreciation_Id from Periode_Id and tuteurId
    public Appreciation_Id createAppreciationId(Periode_Id periodeId, int tuteurId) {
        return new Appreciation_Id(periodeId, tuteurId);
    }
}