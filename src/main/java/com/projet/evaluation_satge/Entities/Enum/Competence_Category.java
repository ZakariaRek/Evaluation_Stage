package com.projet.evaluation_satge.Entities.Enum;

public enum Competence_Category {
    ANALYSE_SYNTHESE("Être capable d'analyse et de synthèse"),
    PROPOSER_METHODES("Être capable de proposer des méthodes et des axes de travail"),
    FAIRE_ADHERER("Être capable de faire adhérer les acteurs"),
    CONTEXTE_INTERNATIONAL("Être capable de travailler dans un contexte international et interculturel"),
    AUTOEVALUATION("Être capable de s'autoévaluer"),
    IDENTIFIER_PROBLEMES("Être capable d'identifier des problèmes complexes"),

    ANALYSER_FONCTIONNEMENT("Être capable d'analyser le fonctionnement de l'entreprise d'accueil"),
    IDENTIFIER_REGLEMENTATION("Être capable d'identifier la réglementation, hiérarchie, droit du travail, etc."),
    ANALYSER_DEMARCHE_PROJET("Être capable d'analyser la démarche projet, et d'organiser et de structurer un projet"),
    POLITIQUE_ENVIRONNEMENTALE("Être capable d'apprendre à déceler et à comprendre la politique environnementale de l'entreprise"),
    RECHERCHER_INFORMATION("Être capable de rechercher, de sélectionner l'information nécessaire à ses activités"),

    CONCEPTION_PRELIMINAIRE("Être capable d'assurer la conception préliminaire de produits/services/processus/usages");

    private final String label;
    Competence_Category(String label) {
        this.label = label;
    }
    public String getLabel() {
        return label;
    }
    public static Competence_Category fromLabel(String label) {
        for (Competence_Category category : Competence_Category.values()) {
            if (category.getLabel().equals(label)) {
                return category;
            }
        }
        throw new IllegalArgumentException("No enum constant with label " + label);
    }
}
