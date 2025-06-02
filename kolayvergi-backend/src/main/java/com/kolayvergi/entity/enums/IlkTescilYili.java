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

    private final String value;

    IlkTescilYili(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
