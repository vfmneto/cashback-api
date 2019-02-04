package br.com.vfmneto.cashbackapi.repository;

import br.com.vfmneto.cashbackapi.domain.Disco;
import br.com.vfmneto.cashbackapi.domain.Genero;

import java.util.List;

public interface SpotifyRepository {

    List<Disco> consultar(Genero genero, Integer limite);
}
