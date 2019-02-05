package br.com.vfmneto.cashbackapi.service.impl;

import br.com.vfmneto.cashbackapi.domain.Disco;
import br.com.vfmneto.cashbackapi.domain.ItemVenda;
import br.com.vfmneto.cashbackapi.domain.Venda;
import br.com.vfmneto.cashbackapi.repository.DiscoRepository;
import br.com.vfmneto.cashbackapi.repository.PorcetagemCashbackRepository;
import br.com.vfmneto.cashbackapi.repository.VendaRepository;
import br.com.vfmneto.cashbackapi.service.CalculadorValorCashbackComponent;
import br.com.vfmneto.cashbackapi.util.ObtedorDataUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VendaServiceImplTest {

    public static final long ID_DISCO_UM = 1l;
    public static final long ID_DISCO_DOIS = 2l;
    public static final BigDecimal CASHBACK_DISCO_UM = BigDecimal.TEN;
    public static final BigDecimal CASHBACK_DISCO_DOIS = BigDecimal.ONE;

    private VendaServiceImpl vendaService;

    @Mock private VendaRepository vendaRepository;
    @Mock private ObtedorDataUtil obtedorDataUtil;
    @Mock private PorcetagemCashbackRepository porcetagemCashbackRepository;
    @Mock private DiscoRepository discoRepository;
    @Mock private CalculadorValorCashbackComponent calculadorValorCashbackComponent;

    @Captor private ArgumentCaptor<Venda> vendaArgumentCaptor;

    @Before
    public void inicializar() {
        vendaService = new VendaServiceImpl(vendaRepository, obtedorDataUtil, discoRepository, calculadorValorCashbackComponent);
    }

    @Test
    public void deveriaConsultarVendaPeloIdentificador() {

        Venda vendaEsperado = new Venda();

        when(vendaRepository.findById(5l)).thenReturn(Optional.of(vendaEsperado));

        Optional<Venda> vendaOptional = vendaService.consultarPeloIdentificador(5l);

        assertThat(vendaOptional.get(), is(vendaEsperado));

    }

    @Test
    public void deveriaRegistrarVendaCalculandoValorCashback() {

        Venda vendaInformada = new Venda();
        List<ItemVenda> itens = new ArrayList<>();
        itens.add(criarItemVenda(ID_DISCO_UM));
        itens.add(criarItemVenda(ID_DISCO_DOIS));
        vendaInformada.setItensVenda(itens);

        LocalDate dataAtualEsperada = LocalDate.now();
        when(obtedorDataUtil.obterDataAtual()).thenReturn(dataAtualEsperada);

        Optional<Disco> discoUm = criarExpectativaPorDisco(ID_DISCO_UM, CASHBACK_DISCO_UM);
        Optional<Disco> discoDois = criarExpectativaPorDisco(ID_DISCO_DOIS, CASHBACK_DISCO_DOIS);

        Venda vendaRegistrada = new Venda();
        when(vendaRepository.save(vendaInformada)).thenReturn(vendaRegistrada);

        Venda resultado = vendaService.registrarVenda(vendaInformada);

        verify(vendaRepository).save(vendaArgumentCaptor.capture());
        Venda vendaCapturada = vendaArgumentCaptor.getValue();
        assertThat(vendaCapturada.getCashbackTotal(), equalTo(BigDecimal.valueOf(11l)));
        assertThat(vendaCapturada.getData(), equalTo(dataAtualEsperada));

        verificarItemVenda(vendaInformada, discoUm.get(), CASHBACK_DISCO_UM, vendaCapturada);
        verificarItemVenda(vendaInformada, discoDois.get(), CASHBACK_DISCO_DOIS, vendaCapturada);

        assertThat(resultado, is(vendaRegistrada));
    }

    private void verificarItemVenda(Venda vendaEsperada, Disco discoEsperado, BigDecimal cashbackDiscoEsperado, Venda vendaCapturada) {
        ItemVenda itemVenda = vendaCapturada.getItensVenda()
                                                .stream()
                                                .filter(itemVenda1 -> itemVenda1.getDisco().getId().equals(discoEsperado.getId()))
                                                .findFirst().get();
        assertThat(itemVenda.getCashback(), equalTo(cashbackDiscoEsperado));
        assertThat(itemVenda.getVenda(), is(vendaEsperada));
        assertThat(itemVenda.getDisco(), is(discoEsperado));
    }

    private Optional<Disco> criarExpectativaPorDisco(long idDiscoUm, BigDecimal cashbackDiscoUm) {
        Optional<Disco> discoUm = criarDisco(idDiscoUm);
        when(discoRepository.findById(idDiscoUm)).thenReturn(discoUm);
        when(calculadorValorCashbackComponent.calcular(discoUm.get())).thenReturn(cashbackDiscoUm);
        return discoUm;
    }

    private Optional<Disco> criarDisco(long id) {
        Disco disco = new Disco();
        disco.setId(id);
        return Optional.of(disco);
    }

    private ItemVenda criarItemVenda(long idDisco) {
        ItemVenda itemVenda = new ItemVenda();
        itemVenda.setDisco(criarDisco(idDisco).get());
        return itemVenda;
    }

}