package br.com.vfmneto.cashbackapi.web.rest;

import br.com.vfmneto.cashbackapi.domain.Venda;
import br.com.vfmneto.cashbackapi.domain.Genero;
import br.com.vfmneto.cashbackapi.dto.VendaDTO;
import br.com.vfmneto.cashbackapi.dto.PaginaDTO;
import br.com.vfmneto.cashbackapi.mapper.VendaMapper;
import br.com.vfmneto.cashbackapi.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class VendaResource {

    private final VendaService vendaService;
    private final VendaMapper mapper;

    @Autowired
    public VendaResource(VendaService vendaService, VendaMapper mapper) {
        this.vendaService = vendaService;
        this.mapper = mapper;
    }

    @GetMapping("/vendas/{id}")
    public ResponseEntity<VendaDTO> consultarPeloIdentificador(@PathVariable Long id) {

        Optional<Venda> vendaOptional = vendaService.consultarPeloIdentificador(id);

        return vendaOptional.map(venda -> new ResponseEntity<>(mapper.toDto(venda), HttpStatus.OK))
                            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
