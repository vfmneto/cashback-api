package br.com.vfmneto.cashbackapi.repository;

import br.com.vfmneto.cashbackapi.domain.DiaSemana;
import br.com.vfmneto.cashbackapi.domain.Genero;
import br.com.vfmneto.cashbackapi.domain.PorcetagemCashback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PorcetagemCashbackRepository extends JpaRepository<PorcetagemCashback, Long> {

    PorcetagemCashback findByGeneroAndDiaSemana(Genero genero, DiaSemana diaSemana);
}
