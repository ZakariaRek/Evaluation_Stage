package com.projet.evaluation_satge.DTO.response;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TuteurDTO {
    private int id;
    private String cin;
    private String nom;
    private String prenom;
    private String email;
}
