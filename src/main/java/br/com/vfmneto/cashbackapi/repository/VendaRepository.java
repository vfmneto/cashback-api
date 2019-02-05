package br.com.vfmneto.cashbackapi.repository;

import br.com.vfmneto.cashbackapi.domain.Venda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Long> {

    Page<Venda> findByDataGreaterThanEqualAndDataLessThanEqualOrderByDataDesc(Date dataInicial, Date dataFinal, Pageable pageable);

}
