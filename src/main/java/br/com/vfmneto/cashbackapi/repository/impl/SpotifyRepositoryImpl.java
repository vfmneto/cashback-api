package br.com.vfmneto.cashbackapi.repository.impl;

import br.com.vfmneto.cashbackapi.domain.Disco;
import br.com.vfmneto.cashbackapi.domain.Genero;
import br.com.vfmneto.cashbackapi.exception.SpotifyApiException;
import br.com.vfmneto.cashbackapi.repository.SpotifyRepository;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.model_objects.specification.AlbumSimplified;
import com.wrapper.spotify.requests.data.search.simplified.SearchAlbumsRequest;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static java.math.BigDecimal.valueOf;

@Repository
public class SpotifyRepositoryImpl implements SpotifyRepository {

    private final SpotifyApi spotifyApi;

    public SpotifyRepositoryImpl(SpotifyApi spotifyApi) {
        this.spotifyApi = spotifyApi;
    }

    @Override
    public List<Disco> consultar(Genero genero, Integer limite) {

        SearchAlbumsRequest searchAlbumsRequest = spotifyApi.searchAlbums(genero.name()).limit(limite).build();

        try {
            AlbumSimplified[] albumSimplifieds = searchAlbumsRequest.execute().getItems();
            return Arrays.asList(albumSimplifieds).stream().map(albumSimplified -> {
                Disco disco = new Disco();
                disco.setGenero(genero);
                disco.setNomeAlbum(albumSimplified.getName());
                disco.setPreco(valueOf(new RandomDataGenerator().nextUniform(10D, 30D)));
                return disco;
            }).collect(Collectors.toList());

        } catch (Exception e) {
            throw new SpotifyApiException();
        }
    }
}
