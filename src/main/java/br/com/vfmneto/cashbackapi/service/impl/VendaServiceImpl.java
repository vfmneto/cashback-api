package br.com.vfmneto.cashbackapi.service.impl;

import br.com.vfmneto.cashbackapi.domain.DiaSemana;
import br.com.vfmneto.cashbackapi.domain.Disco;
import br.com.vfmneto.cashbackapi.domain.PorcetagemCashback;
import br.com.vfmneto.cashbackapi.domain.Venda;
import br.com.vfmneto.cashbackapi.repository.DiscoRepository;
import br.com.vfmneto.cashbackapi.repository.PorcetagemCashbackRepository;
import br.com.vfmneto.cashbackapi.repository.VendaRepository;
import br.com.vfmneto.cashbackapi.service.CalculadorValorCashbackComponent;
import br.com.vfmneto.cashbackapi.service.VendaService;
import br.com.vfmneto.cashbackapi.util.ObtedorDataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class VendaServiceImpl implements VendaService {

    private final VendaRepository vendaRepository;
    private final ObtedorDataUtil obtedorDataUtil;
    private final DiscoRepository discoRepository;
    private final CalculadorValorCashbackComponent calculadorValorCashbackComponent;

    @Autowired
    public VendaServiceImpl(VendaRepository vendaRepository, ObtedorDataUtil obtedorDataUtil, DiscoRepository discoRepository, CalculadorValorCashbackComponent calculadorValorCashbackComponent) {
        this.vendaRepository = vendaRepository;
        this.obtedorDataUtil = obtedorDataUtil;
        this.discoRepository = discoRepository;
        this.calculadorValorCashbackComponent = calculadorValorCashbackComponent;
    }

    @Override
    public Optional<Venda> consultarPeloIdentificador(Long id) {
        return vendaRepository.findById(id);
    }

    @Override
    public Venda registrarVenda(Venda venda) {

        venda.setData(obtedorDataUtil.obterDataAtual());
        venda.setCashbackTotal(BigDecimal.ZERO);

        venda.getItensVenda().forEach(itemVenda -> {
            Disco disco = discoRepository.findById(itemVenda.getDisco().getId()).get();
            BigDecimal cashbackDisco = calculadorValorCashbackComponent.calcular(disco);
            itemVenda.setVenda(venda);
            itemVenda.setDisco(disco);
            itemVenda.setCashback(cashbackDisco);
            venda.setCashbackTotal(venda.getCashbackTotal().add(cashbackDisco));
        });

        return vendaRepository.save(venda);
    }

}
