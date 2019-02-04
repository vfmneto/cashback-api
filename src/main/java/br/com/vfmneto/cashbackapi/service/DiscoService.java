package br.com.vfmneto.cashbackapi.service;

import br.com.vfmneto.cashbackapi.domain.Disco;
import br.com.vfmneto.cashbackapi.domain.Genero;
import br.com.vfmneto.cashbackapi.dto.PaginaDTO;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface DiscoService {

    Page<Disco> consultarPorGeneroOrdenandoDeFormaCrescentePeloNome(Genero genero, PaginaDTO pagina);

    Optional<Disco> consultarPeloIdentificador(Long id);
}
