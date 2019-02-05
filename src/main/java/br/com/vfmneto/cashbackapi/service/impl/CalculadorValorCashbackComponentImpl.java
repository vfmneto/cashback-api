package br.com.vfmneto.cashbackapi.service.impl;

import br.com.vfmneto.cashbackapi.domain.DiaSemana;
import br.com.vfmneto.cashbackapi.domain.Disco;
import br.com.vfmneto.cashbackapi.domain.PorcetagemCashback;
import br.com.vfmneto.cashbackapi.repository.PorcetagemCashbackRepository;
import br.com.vfmneto.cashbackapi.service.CalculadorValorCashbackComponent;
import br.com.vfmneto.cashbackapi.util.ObtedorDataUtil;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CalculadorValorCashbackComponentImpl implements CalculadorValorCashbackComponent {

    public static final BigDecimal CEM_POR_CENTO = BigDecimal.valueOf(100l);

    private final PorcetagemCashbackRepository porcetagemCashbackRepository;
    private final ObtedorDataUtil obtedorDataUtil;

    public CalculadorValorCashbackComponentImpl(PorcetagemCashbackRepository porcetagemCashbackRepository, ObtedorDataUtil obtedorDataUtil) {
        this.porcetagemCashbackRepository = porcetagemCashbackRepository;
        this.obtedorDataUtil = obtedorDataUtil;
    }

    @Override
    public BigDecimal calcular(Disco disco) {
        PorcetagemCashback porcetagemCashback = porcetagemCashbackRepository.findByGeneroAndDiaSemana(disco.getGenero(), DiaSemana.valueOf(obtedorDataUtil.obterDataAtual()));
        return disco.getPreco().multiply(porcetagemCashback.getPorcetagem().divide(CEM_POR_CENTO));
    }
}
