package br.com.vfmneto.cashbackapi.repository;

import br.com.vfmneto.cashbackapi.domain.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

}
