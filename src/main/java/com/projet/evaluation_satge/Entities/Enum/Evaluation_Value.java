package com.projet.evaluation_satge.Entities.Enum;

public enum Evaluation_Value {

    PARESSEUX("Paresseux"),
    JUSTE_NECESSAIRE("Le juste nécessaire"),
    BONNE("Bonne"),
    TRES_FORTE("Très forte"),
    DEPASSE_OBJECTIFS("Dépasse ses objectifs"),

    ISOLE("Isolé(e) ou en opposition"),
    RENFERME("Renfermé(e) ou obtus"),
    TRES_BONNE("Très bonne"),
    EXCELLENTE("Excellente"),

    MEDIOCRE("Médiocre"),
    ACCEPTABLE("Acceptable"),
    TRES_PROFESSIONNELLE("Très professionnelle"),;

    private final String label;

    Evaluation_Value(String label) {
        this.label = label;
    }

    public String getLabel() {
        return this.label;
    }

    /**
     * Find value by label
     *
     * @param label The label to search for
     * @return The matching value or null if not found
     */
    public static Evaluation_Value fromLabel(String label) {
        for (Evaluation_Value value : Evaluation_Value.values()) {
            if (value.getLabel().equalsIgnoreCase(label)) {
                return value;
            }
        }
        return null;
    }

}
