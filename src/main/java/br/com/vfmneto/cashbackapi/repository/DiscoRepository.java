package br.com.vfmneto.cashbackapi.repository;

import br.com.vfmneto.cashbackapi.domain.Disco;
import br.com.vfmneto.cashbackapi.domain.Genero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscoRepository extends JpaRepository<Disco, Long> {

    Page<Disco> findByGeneroOrderByNomeAlbumAsc(Genero genero, Pageable pageable);
}
