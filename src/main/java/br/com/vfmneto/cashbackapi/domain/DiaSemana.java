package br.com.vfmneto.cashbackapi.domain;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public enum DiaSemana {

    SEGUNDA,
    TERCA,
    QUARTA,
    QUINTA,
    SEXTA,
    SABADO,
    DOMINGO;

    public static DiaSemana valueOf(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return DiaSemana.values()[localDate.getDayOfWeek().getValue()-1];
    }

}
