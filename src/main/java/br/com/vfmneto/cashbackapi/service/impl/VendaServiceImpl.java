package br.com.vfmneto.cashbackapi.service.impl;

import br.com.vfmneto.cashbackapi.domain.Venda;
import br.com.vfmneto.cashbackapi.repository.VendaRepository;
import br.com.vfmneto.cashbackapi.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VendaServiceImpl implements VendaService {

    private final VendaRepository vendaRepository;

    @Autowired
    public VendaServiceImpl(VendaRepository vendaRepository) {
        this.vendaRepository = vendaRepository;
    }

    @Override
    public Optional<Venda> consultarPeloIdentificador(Long id) {
        return vendaRepository.findById(id);
    }
}
