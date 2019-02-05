package br.com.vfmneto.cashbackapi.dto;

import br.com.vfmneto.cashbackapi.domain.Disco;
import br.com.vfmneto.cashbackapi.domain.Venda;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class ItemVendaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private BigDecimal cashback;
    private DiscoDTO disco;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCashback() {
        return cashback;
    }

    public void setCashback(BigDecimal cashback) {
        this.cashback = cashback;
    }

    public DiscoDTO getDisco() {
        return disco;
    }

    public void setDisco(DiscoDTO disco) {
        this.disco = disco;
    }
}
