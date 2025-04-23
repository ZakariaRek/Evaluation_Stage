package com.projet.evaluation_satge.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"periode_id"})
})
public class Appreciation_Id {
    @Column(name = "periode_id" )
    private int periodeId;

    @Column(name = "tuteur_id")
    private int tuteurId;

}
