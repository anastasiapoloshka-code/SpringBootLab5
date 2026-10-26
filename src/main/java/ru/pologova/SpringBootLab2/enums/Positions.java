package ru.pologova.SpringBootLab2.enums;

import lombok.Getter;

@Getter
public enum Positions {

    DEV("Developer", 2.2, false),
    HR("HR-specialist", 1.2, false),
    TL("Manager", 2.0, true),

    PO("Product Owner", 1.8, true),
    TPM("Technical Product Manager", 2.1, true),
    CTO("Chief Technology Officer", 2.5, true);

    private final String description;
    private final double positionCoefficient;
    private final boolean isManager;

    Positions(String description,
              double positionCoefficient,
              boolean isManager) {

        this.description = description;
        this.positionCoefficient = positionCoefficient;
        this.isManager = isManager;
    }
}