package com.kolayvergi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum VergiTuru {
    KDV("KDV Vergisi"),
    MTV("MTV Vergisi"),
    OTV("ÖTV Vergisi");

    private final String aciklama;
}