package br.com.vfmneto.cashbackapi.config;

import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.credentials.ClientCredentials;
import com.wrapper.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class SpotifyConfiguration {

    @Value("${application.spotify.clientId}") String clientId;
    @Value("${application.spotify.clientSecret}") String clientSecret;

    @Bean
    public SpotifyApi spotifyApi() throws IOException, SpotifyWebApiException {
        SpotifyApi.Builder builder = new SpotifyApi.Builder();
        SpotifyApi spotifyApi = builder.setClientId(clientId)
                                       .setClientSecret(clientSecret)
                                       .build();
        ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();
        ClientCredentials clientCredentials = clientCredentialsRequest.execute();
        spotifyApi.setAccessToken(clientCredentials.getAccessToken());
        return spotifyApi;
    }
}
