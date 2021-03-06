package br.com.vfmneto.cashbackapi.web.rest;

import br.com.vfmneto.cashbackapi.domain.Disco;
import br.com.vfmneto.cashbackapi.domain.Genero;
import br.com.vfmneto.cashbackapi.dto.DiscoDTO;
import br.com.vfmneto.cashbackapi.dto.PaginaDTO;
import br.com.vfmneto.cashbackapi.mapper.DiscoMapper;
import br.com.vfmneto.cashbackapi.service.DiscoService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static java.util.Collections.EMPTY_LIST;

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

    @GetMapping("/discos/{id}")
    public ResponseEntity<DiscoDTO> consultarPeloIdentificador(@PathVariable Long id) {

        Optional<Disco> discoOptional = discoService.consultarPeloIdentificador(id);

        return discoOptional.map(disco -> new ResponseEntity<>(mapper.toDto(disco), HttpStatus.OK))
                            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/discos")
    @HystrixCommand(fallbackMethod = "consultarPorGeneroOrdenandoDeFormaCrescentePeloNomeReliable")
    public ResponseEntity<Page<DiscoDTO>> consultarPorGeneroOrdenandoDeFormaCrescentePeloNome(String genero, PaginaDTO pagina) {

        Page<Disco> page = discoService.consultarPorGeneroOrdenandoDeFormaCrescentePeloNome(Genero.valueOf(genero), pagina);

        return new ResponseEntity<>(page.map(mapper::toDto), HttpStatus.OK);
    }

    public ResponseEntity<Page<DiscoDTO>> consultarPorGeneroOrdenandoDeFormaCrescentePeloNomeReliable(String genero, PaginaDTO pagina) {
        return new ResponseEntity<>(new PageImpl<>(EMPTY_LIST), HttpStatus.OK);
    }

}