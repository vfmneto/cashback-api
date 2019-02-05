package br.com.vfmneto.cashbackapi.web.rest;

import br.com.vfmneto.cashbackapi.domain.Venda;
import br.com.vfmneto.cashbackapi.dto.PaginaDTO;
import br.com.vfmneto.cashbackapi.dto.VendaDTO;
import br.com.vfmneto.cashbackapi.mapper.VendaMapper;
import br.com.vfmneto.cashbackapi.service.VendaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.*;

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

    @Test
    public void deveriaRegistrarVenda() {

        Venda vendaInformada = new Venda();
        VendaDTO vendaDTOInformada = new VendaDTO();
        when(mapper.toEntity(vendaDTOInformada)).thenReturn(vendaInformada);

        Venda vendaRegistrada = new Venda();
        when(vendaService.registrarVenda(vendaInformada)).thenReturn(vendaRegistrada);

        VendaDTO vendaDTORegistrada = new VendaDTO();
        when(mapper.toDto(vendaRegistrada)).thenReturn(vendaDTORegistrada);

        ResponseEntity<VendaDTO> vendaDTOResponseEntity = vendaResource.registrarVenda(vendaDTOInformada);

        assertThat(vendaDTOResponseEntity.getStatusCode(), equalTo(CREATED));
        assertThat(vendaDTOResponseEntity.getBody(), is(vendaDTORegistrada));
    }

    @Test
    public void deveriaConsultarEntreDuasDatasOrdenandoDeFormaDecrescentePelaDataVenda() {

        PaginaDTO pagina = new PaginaDTO(0, 20);

        Venda venda = new Venda();
        Page<Venda> resultado = new PageImpl<>(asList(venda));
        Date dataInicial = new Date();
        Date dataFinal = new Date();
        when(vendaService.consultarEntreDuasDatasOrdenandoDeFormaDecrescentePelaDataVenda(dataInicial, dataFinal, pagina)).thenReturn(resultado);

        VendaDTO vendaDTO = new VendaDTO();
        when(mapper.toDto(venda)).thenReturn(vendaDTO);

        ResponseEntity<Page<VendaDTO>> responseEntity = vendaResource.consultarEntreDuasDatasOrdenandoDeFormaDecrescentePelaDataVenda(dataInicial, dataFinal, pagina);

        assertThat(responseEntity.getBody().get().collect(toList()).contains(vendaDTO), equalTo(true));
        assertThat(responseEntity.getStatusCode(), equalTo(OK));
    }
}