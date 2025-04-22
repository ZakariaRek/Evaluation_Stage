package com.projet.evaluation_satge.Entities.Enum;


public enum Evaluation_Competence {
    NA("Non applicable"),
    DEBUTANT("Débutant"),
    AUTONOME("Autonome"),
    AUTONOME_PLUS("Autonome +");

    private String description;

    Evaluation_Competence(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public static Evaluation_Competence fromDescription(String description) {
        for (Evaluation_Competence competence : Evaluation_Competence.values()) {
            if (competence.getDescription().equalsIgnoreCase(description)) {
                return competence;
            }
        }
        throw new IllegalArgumentException("No constant with description " + description + " found");
    }
}
