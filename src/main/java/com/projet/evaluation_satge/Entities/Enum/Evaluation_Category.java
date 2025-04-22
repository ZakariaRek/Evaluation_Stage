package com.projet.evaluation_satge.Entities.Enum;

public enum Evaluation_Category {

    IMPLICATION_ACTIVITE("Implication dans ses activites"),
    OUVERTURE_AUX_AUTRES("Ouverture aux autres"),
    QUALITE_DE_SES_PRODUCTIONS("Qualite de ses productions"),
    OBSERVATION_SUR_ENSEMBLE_DU_TRAVAIL_ACCOMPLI("Observation sur l'ensemble du travail accompli"),
    ;
    private final String label;

    Evaluation_Category(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    public static Evaluation_Category fromLabel(String label) {
        for (Evaluation_Category category : Evaluation_Category.values()) {
            if (category.getLabel().equalsIgnoreCase(label)) {
                return category;
            }
        }
        return null;
    }
}
