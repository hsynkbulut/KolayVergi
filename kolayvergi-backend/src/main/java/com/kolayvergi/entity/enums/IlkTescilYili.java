package com.kolayvergi.entity.enums;


import com.fasterxml.jackson.annotation.JsonValue;

public enum IlkTescilYili {
    Y2017_ONCESI("2017 ve öncesi"),
    Y2018("2018"),
    Y2019("2019"),
    Y2020("2020"),
    Y2021("2021"),
    Y2022("2022"),
    Y2023("2023"),
    Y2024("2024"),
    Y2025("2025");

    private final String label;

    IlkTescilYili(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }
}
