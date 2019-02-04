package br.com.vfmneto.cashbackapi.service.impl;

import br.com.vfmneto.cashbackapi.domain.Disco;
import br.com.vfmneto.cashbackapi.domain.Genero;
import br.com.vfmneto.cashbackapi.repository.DiscoRepository;
import br.com.vfmneto.cashbackapi.repository.SpotifyRepository;
import br.com.vfmneto.cashbackapi.service.InicializadorCatalogoDiscoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class InicializadorCatalogoDiscoServiceSpotify implements InicializadorCatalogoDiscoService {

    private static final Integer LIMIT = 50;

    private final SpotifyRepository spotifyRepository;
    private final DiscoRepository discoRepository;

    @Autowired
    public InicializadorCatalogoDiscoServiceSpotify(SpotifyRepository spotifyRepository, DiscoRepository discoRepository) {
        this.spotifyRepository = spotifyRepository;
        this.discoRepository = discoRepository;
    }

    @Override
    public void inicializar() {
        Arrays.asList(Genero.values()).forEach(genero -> {
            List<Disco> discos = spotifyRepository.consultar(genero, LIMIT);
            discoRepository.saveAll(discos);
        });

    }
}
