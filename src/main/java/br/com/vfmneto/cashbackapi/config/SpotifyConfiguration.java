package br.com.vfmneto.cashbackapi.config;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class SpotifyConfiguration {

    @Bean
    public SpotifyApi spotifyApi() throws IOException, SpotifyWebApiException {
        SpotifyApi.Builder builder = new SpotifyApi.Builder();
        SpotifyApi spotifyApi = builder.setClientId("90ff7c96027c4aa4953ac55d46517f4f")
                                       .setClientSecret("664cbb31787449ca960af5d528de552a")
                                       .build();
        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
        ClientCredentials clientCredentials = clientCredentialsRequest.execute();
        spotifyApi.setAccessToken(clientCredentials.getAccessToken());
        return spotifyApi;
    }
}
