package br.com.vfmneto.cashbackapi.service;

import br.com.vfmneto.cashbackapi.domain.Venda;

import java.util.Optional;

public interface VendaService {

    Optional<Venda> consultarPeloIdentificador(Long id);

    Venda registrarVenda(Venda venda);
}
