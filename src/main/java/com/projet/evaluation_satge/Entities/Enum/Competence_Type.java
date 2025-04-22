package com.projet.evaluation_satge.Entities.Enum;

public enum Competence_Type {
    COMPETENCE_INDIVIDUELLE("Compétences liées à l'individu"),
    COMPETENCE_ENTREPRISE("Compétences liées à l'entreprise"),
    COMPETENCE_TECHNIQUE("Compétences scientifiques et techniques"),;

    private String description;
    Competence_Type(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
    public static Competence_Type fromDescription(String description) {
        for (Competence_Type type : Competence_Type.values()) {
            if (type.getDescription().equalsIgnoreCase(description)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant with description " + description);
    }
}
