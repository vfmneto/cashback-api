package br.com.vfmneto.cashbackapi.repository.impl;

import br.com.vfmneto.cashbackapi.domain.Disco;
import br.com.vfmneto.cashbackapi.domain.Genero;
import br.com.vfmneto.cashbackapi.exception.SpotifyApiException;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.model_objects.specification.Paging;
import com.wrapper.spotify.requests.data.search.simplified.SearchAlbumsRequest;
import org.hamcrest.CoreMatchers;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static br.com.vfmneto.cashbackapi.domain.Genero.CLASSICAL;
import static br.com.vfmneto.cashbackapi.domain.Genero.ROCK;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SpotifyRepositoryImplTest {

    public static final String NOME_ALBUM_1 = "Album 1";
    private SpotifyRepositoryImpl spotifyRepository;

    @Mock private SpotifyApi spotifyApi;
    @Mock private SearchAlbumsRequest.Builder searchAlbumsBuilder;
    @Mock private SearchAlbumsRequest searchAlbumsRequest;
    @Mock private Paging<AlbumSimplified> pagingAlbumSimplified;

    @Before
    public void inicializar() {
        spotifyRepository = new SpotifyRepositoryImpl(spotifyApi);
    }

    @Test
    public void deveriaConsultarDiscosPeloGenero_e_limite() throws IOException, SpotifyWebApiException {

        when(spotifyApi.searchAlbums(CLASSICAL.name())).thenReturn(searchAlbumsBuilder);
        when(searchAlbumsBuilder.limit(2)).thenReturn(searchAlbumsBuilder);
        when(searchAlbumsBuilder.build()).thenReturn(searchAlbumsRequest);
        when(searchAlbumsRequest.execute()).thenReturn(pagingAlbumSimplified);

        AlbumSimplified[] albumSimplifieds = new AlbumSimplified[]{criarAlbumSimplified("Album 1"), criarAlbumSimplified("Album 2")};
        when(pagingAlbumSimplified.getItems()).thenReturn(albumSimplifieds);

        List<Disco> resultado = spotifyRepository.consultar(CLASSICAL, 2);

        assertThat(resultado.size(), IsEqual.equalTo(2));
        verificarDiscoRetornado("Album 1", CLASSICAL, resultado);
        verificarDiscoRetornado("Album 2", CLASSICAL, resultado);
    }

    @Test(expected = SpotifyApiException.class)
    public void deveriaLancarSpotifyApiExceptionQuandoHouverErroDeComunicacaoComApiSpotify() throws IOException, SpotifyWebApiException {

        when(spotifyApi.searchAlbums(ROCK.name())).thenReturn(searchAlbumsBuilder);
        when(searchAlbumsBuilder.limit(4)).thenReturn(searchAlbumsBuilder);
        when(searchAlbumsBuilder.build()).thenReturn(searchAlbumsRequest);
        when(searchAlbumsRequest.execute()).thenThrow(new SpotifyWebApiException());

        spotifyRepository.consultar(ROCK, 4);

    }

    private void verificarDiscoRetornado(String nomeAlbum, Genero genero, List<Disco> resultado) {

        Optional<Disco> discoUmOptional = resultado.stream().filter(disco -> disco.getNomeAlbum().equals(nomeAlbum)).findFirst();
        assertThat(discoUmOptional.isPresent(), Is.is(true));

        Disco discoUm = discoUmOptional.get();
        assertThat(discoUm.getGenero(), IsEqual.equalTo(genero));
        assertThat(discoUm.getNomeAlbum(), IsEqual.equalTo(nomeAlbum));
        assertThat(discoUm.getPreco(), Is.is(notNullValue()));
    }

    private AlbumSimplified criarAlbumSimplified(String nomeAlbum) {
        AlbumSimplified.Builder builder = new AlbumSimplified.Builder();
        builder.setName(nomeAlbum);
        return builder.build();
    }

}