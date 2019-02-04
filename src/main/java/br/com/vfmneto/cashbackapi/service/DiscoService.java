package br.com.vfmneto.cashbackapi.service;

import br.com.vfmneto.cashbackapi.domain.Disco;
import br.com.vfmneto.cashbackapi.domain.Genero;
import br.com.vfmneto.cashbackapi.dto.PaginaDTO;
import org.springframework.data.domain.Page;

public interface DiscoService {

    Page<Disco> consultarDiscosPorGeneroOrdenadoDeFormaCrescentePeloNome(Genero genero, PaginaDTO pagina);

}
