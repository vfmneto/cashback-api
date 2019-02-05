package br.com.vfmneto.cashbackapi.domain;

import java.time.LocalDate;

public enum DiaSemana {

    SEGUNDA,
    TERCA,
    QUARTA,
    QUINTA,
    SEXTA,
    SABADO,
    DOMINGO;

    public static DiaSemana valueOf(LocalDate localDate) {
        return DiaSemana.values()[localDate.getDayOfWeek().getValue()-1];
    }

}
