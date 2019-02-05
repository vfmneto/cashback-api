package br.com.vfmneto.cashbackapi.service.impl;

import br.com.vfmneto.cashbackapi.domain.DiaSemana;
import br.com.vfmneto.cashbackapi.domain.Disco;
import br.com.vfmneto.cashbackapi.domain.Genero;
import br.com.vfmneto.cashbackapi.domain.PorcetagemCashback;
import br.com.vfmneto.cashbackapi.repository.PorcetagemCashbackRepository;
import br.com.vfmneto.cashbackapi.util.ObtedorDataUtil;
import org.hamcrest.core.IsEqual;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;

import static br.com.vfmneto.cashbackapi.domain.Genero.ROCK;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CalculadorValorCashbackComponentImplTest {

    private CalculadorValorCashbackComponentImpl calculadorValorCashbackComponent;
    @Mock private PorcetagemCashbackRepository porcetagemCashbackRepository;
    @Mock private ObtedorDataUtil obtedorDataUtil;

    @Before
    public void inicializar() {
        calculadorValorCashbackComponent = new CalculadorValorCashbackComponentImpl(porcetagemCashbackRepository, obtedorDataUtil);
    }

    @Test
    public void deveriaCalcularCashbachPorGenero_e_diaDaSemana() throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        when(obtedorDataUtil.obterDataAtual()).thenReturn(simpleDateFormat.parse("10/02/2019"));

        PorcetagemCashback porcetagemCashback = new PorcetagemCashback();
        porcetagemCashback.setPorcetagem(BigDecimal.TEN);
        when(porcetagemCashbackRepository.findByGeneroAndDiaSemana(ROCK, DiaSemana.DOMINGO)).thenReturn(porcetagemCashback);

        Disco disco = new Disco();
        disco.setGenero(ROCK);
        disco.setPreco(BigDecimal.valueOf(200));

        BigDecimal cashbackCalculado = calculadorValorCashbackComponent.calcular(disco);

        assertThat(cashbackCalculado.doubleValue(), IsEqual.equalTo(BigDecimal.valueOf(20).doubleValue()));

    }

}