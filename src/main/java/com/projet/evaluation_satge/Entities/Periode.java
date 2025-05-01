package com.projet.evaluation_satge.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Periode {


@EmbeddedId
private Periode_Id id;


    private String date_debut;
    private String date_fin;
    @OneToMany(mappedBy = "periode", cascade = CascadeType.ALL)
@JsonIgnore
    private List<Appreciation> appreciations;

    @ManyToOne
    @JoinColumn(name = "stagiaire_id", insertable = false, updatable = false)
//    @JsonIdentityReference(alwaysAsId = false)
    private Stagiaire stagiaire;

    @ManyToOne
    @JoinColumn(name = "stage_id", insertable = false, updatable = false)
//    @JsonIdentityReference(alwaysAsId = true)
    private Stage stage;

    public int getStagiaireId() {
        return id != null ? id.getStagiaireId() : 0;
    }

    // Convenience method to get stageId
    public int getStageId() {
        return id != null ? id.getStageId() : 0;
    }

    // Convenience methods for setting id components
    public void setStagiaireId(int stagiaireId) {
        if (id == null) {
            id = new Periode_Id();
        }
        id.setStagiaireId(stagiaireId);
    }

    public void setStageId(int stageId) {
        if (id == null) {
            id = new Periode_Id();
        }
        id.setStageId(stageId);
    }
}