package br.com.vfmneto.cashbackapi.service;

import br.com.vfmneto.cashbackapi.domain.Disco;

import java.math.BigDecimal;

public interface CalculadorValorCashbackComponent {

    BigDecimal calcular(Disco disco);
}
