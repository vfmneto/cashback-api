package br.com.vfmneto.cashbackapi.service.impl;

import br.com.vfmneto.cashbackapi.domain.Disco;
import br.com.vfmneto.cashbackapi.domain.Genero;
import br.com.vfmneto.cashbackapi.repository.DiscoRepository;
import br.com.vfmneto.cashbackapi.repository.SpotifyRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InicializadorCatalogoDiscoServiceSpotifyTest {

    private InicializadorCatalogoDiscoServiceSpotify inicializadorCatalogoDiscoServiceSpotify;

    @Mock private SpotifyRepository spotifyRepository;
    @Mock private DiscoRepository discoRepository;

    @Before
    public void inicializar() {
        inicializadorCatalogoDiscoServiceSpotify = new InicializadorCatalogoDiscoServiceSpotify(spotifyRepository, discoRepository);
    }

    @Test
    public void deveriaConsumirApiSpotify_e_salvarOs50PrimeirosAlbunsDeCadaGenero() {

        when(spotifyRepository.consultar(Genero.CLASSICAL, 50)).thenReturn(criarDiscos(2));
        when(spotifyRepository.consultar(Genero.MPB, 50)).thenReturn(criarDiscos(3));
        when(spotifyRepository.consultar(Genero.POP, 50)).thenReturn(criarDiscos(4));
        when(spotifyRepository.consultar(Genero.ROCK, 50)).thenReturn(criarDiscos(5));

        inicializadorCatalogoDiscoServiceSpotify.inicializar();

        verify(discoRepository).saveAll(criarDiscos(2));
        verify(discoRepository).saveAll(criarDiscos(3));
        verify(discoRepository).saveAll(criarDiscos(4));
        verify(discoRepository).saveAll(criarDiscos(5));

    }

    private List<Disco> criarDiscos(int quantidades) {

        List<Disco> discos = new ArrayList<>();
        for (int i = 1; i <= quantidades; i++) {
            discos.add(new Disco());
        }

        return discos;
    }

}