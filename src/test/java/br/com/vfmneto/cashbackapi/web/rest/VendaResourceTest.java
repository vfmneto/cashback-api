package br.com.vfmneto.cashbackapi.web.rest;

import br.com.vfmneto.cashbackapi.domain.Venda;
import br.com.vfmneto.cashbackapi.dto.VendaDTO;
import br.com.vfmneto.cashbackapi.mapper.VendaMapper;
import br.com.vfmneto.cashbackapi.service.VendaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@RunWith(MockitoJUnitRunner.class)
public class VendaResourceTest {

    private VendaResource vendaResource;
    
    @Mock private VendaService vendaService;
    @Mock private VendaMapper mapper;

    @Before
    public void inicializar() {
        vendaResource = new VendaResource(vendaService, mapper);
    }

    @Test
    public void deveriaRetornarVendaComIdentificadorExistente() {

        Venda vendaEsperado = new Venda();
        when(vendaService.consultarPeloIdentificador(10l)).thenReturn(Optional.of(vendaEsperado));

        VendaDTO vendaDTOEsperado = new VendaDTO();
        when(mapper.toDto(vendaEsperado)).thenReturn(vendaDTOEsperado);

        ResponseEntity<VendaDTO> responseEntity = vendaResource.consultarPeloIdentificador(10l);

        assertThat(responseEntity.getStatusCode(), equalTo(OK));
        assertThat(responseEntity.getBody(), is(vendaDTOEsperado));
    }

    @Test
    public void deveriaRetornarNotFoundQuandoIdentificadorNaoExistir() {

        when(vendaService.consultarPeloIdentificador(20l)).thenReturn(Optional.empty());

        ResponseEntity<VendaDTO> responseEntity = vendaResource.consultarPeloIdentificador(20l);

        assertThat(responseEntity.getStatusCode(), equalTo(NOT_FOUND));
    }
}