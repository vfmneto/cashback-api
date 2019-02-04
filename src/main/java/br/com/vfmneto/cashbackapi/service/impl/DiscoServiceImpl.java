package br.com.vfmneto.cashbackapi.service.impl;

import br.com.vfmneto.cashbackapi.domain.Disco;
import br.com.vfmneto.cashbackapi.domain.Genero;
import br.com.vfmneto.cashbackapi.dto.PaginaDTO;
import br.com.vfmneto.cashbackapi.repository.DiscoRepository;
import br.com.vfmneto.cashbackapi.service.DiscoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class DiscoServiceImpl implements DiscoService {

    private final DiscoRepository discoRepository;

    @Autowired
    public DiscoServiceImpl(DiscoRepository discoRepository) {
        this.discoRepository = discoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Disco> consultarPorGeneroOrdenandoDeFormaCrescentePeloNome(Genero genero, PaginaDTO pagina) {
        return discoRepository.findByGeneroOrderByNomeAlbumAsc(genero, pagina.toPageable());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Disco> consultarPeloIdentificador(Long id) {
        return discoRepository.findById(id);
    }

}
