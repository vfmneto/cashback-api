package br.com.vfmneto.cashbackapi.web.rest;

import br.com.vfmneto.cashbackapi.domain.Disco;
import br.com.vfmneto.cashbackapi.dto.DiscoDTO;
import br.com.vfmneto.cashbackapi.dto.PaginaDTO;
import br.com.vfmneto.cashbackapi.mapper.DiscoMapper;
import br.com.vfmneto.cashbackapi.service.DiscoService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static br.com.vfmneto.cashbackapi.domain.Genero.ROCK;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DiscoResourceTest {

    private DiscoResource discoResource;

    @Mock private DiscoService discoService;
    @Mock private DiscoMapper mapper;

    @Before
    public void inicializar() {
        discoResource = new DiscoResource(discoService, mapper);
    }

    @Test
    public void deveriaConsultarDiscosPorGeneroOrdenadoDeFormaCrescentePeloNome() {

        PaginaDTO pagina = new PaginaDTO(0, 20);

        Disco disco = new Disco();
        Page<Disco> resultado = new PageImpl<>(asList(disco));
        when(discoService.consultarDiscosPorGeneroOrdenadoDeFormaCrescentePeloNome(ROCK, pagina)).thenReturn(resultado);

        DiscoDTO discoDTO = new DiscoDTO();
        when(mapper.toDto(disco)).thenReturn(discoDTO);

        ResponseEntity<Page<DiscoDTO>> responseEntity = discoResource.consultarDiscosPorGeneroOrdenadoDeFormaCrescentePeloNome(ROCK, pagina);

        assertThat(responseEntity.getBody().get().collect(toList()).contains(discoDTO), equalTo(true));
        assertThat(responseEntity.getStatusCode(), equalTo(HttpStatus.OK));
    }

}