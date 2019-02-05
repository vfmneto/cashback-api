package br.com.vfmneto.cashbackapi.service.impl;

import br.com.vfmneto.cashbackapi.domain.Venda;
import br.com.vfmneto.cashbackapi.repository.VendaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VendaServiceImplTest {

    private VendaServiceImpl vendaService;

    @Mock private VendaRepository vendaRepository;
    
    @Before
    public void inicializar() {
        vendaService = new VendaServiceImpl(vendaRepository);
    }

    @Test
    public void deveriaConsultarVendaPeloIdentificador() {

        Venda vendaEsperado = new Venda();

        when(vendaRepository.findById(5l)).thenReturn(Optional.of(vendaEsperado));

        Optional<Venda> vendaOptional = vendaService.consultarPeloIdentificador(5l);

        assertThat(vendaOptional.get(), is(vendaEsperado));

    }

}