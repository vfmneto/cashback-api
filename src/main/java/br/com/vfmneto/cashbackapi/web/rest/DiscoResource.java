package br.com.vfmneto.cashbackapi.web.rest;

import br.com.vfmneto.cashbackapi.domain.Disco;
import br.com.vfmneto.cashbackapi.domain.Genero;
import br.com.vfmneto.cashbackapi.dto.DiscoDTO;
import br.com.vfmneto.cashbackapi.dto.PaginaDTO;
import br.com.vfmneto.cashbackapi.mapper.DiscoMapper;
import br.com.vfmneto.cashbackapi.service.DiscoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DiscoResource {

    private final DiscoService discoService;
    private final DiscoMapper mapper;

    @Autowired
    public DiscoResource(DiscoService discoService, DiscoMapper mapper) {
        this.discoService = discoService;
        this.mapper = mapper;
    }

    @GetMapping("/discos")
    public ResponseEntity<Page<DiscoDTO>> consultarDiscosPorGeneroOrdenadoDeFormaCrescentePeloNome(Genero genero, PaginaDTO pagina) {

        Page<Disco> page = discoService.consultarDiscosPorGeneroOrdenadoDeFormaCrescentePeloNome(genero, pagina);

        return new ResponseEntity<>(page.map(mapper::toDto), HttpStatus.OK);
    }

}
