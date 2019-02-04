package br.com.vfmneto.cashbackapi.exception;

public class SpotifyApiException extends RuntimeException {

    public SpotifyApiException() {
        super("Erro ao tentar consumir API Spotify.");
    }
}
