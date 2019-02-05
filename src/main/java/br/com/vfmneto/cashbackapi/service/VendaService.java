package br.com.vfmneto.cashbackapi.service;

import br.com.vfmneto.cashbackapi.domain.Venda;
import br.com.vfmneto.cashbackapi.dto.PaginaDTO;
import org.springframework.data.domain.Page;

import java.util.Date;
import java.util.Optional;

public interface VendaService {

    Optional<Venda> consultarPeloIdentificador(Long id);

    Venda registrarVenda(Venda venda);

    Page<Venda> consultarEntreDuasDatasOrdenandoDeFormaDecrescentePelaDataVenda(Date dataInicial, Date dataFinal, PaginaDTO pagina);
}
