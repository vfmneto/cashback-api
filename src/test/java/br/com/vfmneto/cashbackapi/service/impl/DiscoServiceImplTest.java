package br.com.vfmneto.cashbackapi.service.impl;

import br.com.vfmneto.cashbackapi.domain.Disco;
import br.com.vfmneto.cashbackapi.dto.PaginaDTO;
import br.com.vfmneto.cashbackapi.repository.DiscoRepository;
import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import static br.com.vfmneto.cashbackapi.domain.Genero.ROCK;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DiscoServiceImplTest {

    private DiscoServiceImpl discoService;
    @Mock private DiscoRepository discoRepository;

    @Before
    public void inicializar() {
        discoService = new DiscoServiceImpl(discoRepository);
    }

    @Test
    public void deveriaConsultarDiscosPorGeneroOrdenadoDeFormaCrescentePeloNome() {

        PaginaDTO pagina = new PaginaDTO(1, 10);

        Page<Disco> resultadoEsperado = new PageImpl<>(asList());
        when(discoRepository.findByGeneroOrderByNomeAlbumAsc(ROCK, pagina.toPageable())).thenReturn(resultadoEsperado);

        Page<Disco> discos = discoService.consultarDiscosPorGeneroOrdenadoDeFormaCrescentePeloNome(ROCK, pagina);
        assertThat(discos, CoreMatchers.sameInstance(resultadoEsperado));
    }

}